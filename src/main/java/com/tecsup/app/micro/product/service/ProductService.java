package com.tecsup.app.micro.product.service;

import com.tecsup.app.micro.product.client.User;
import com.tecsup.app.micro.product.client.UserClient;
import com.tecsup.app.micro.product.dto.Product;
import com.tecsup.app.micro.product.entity.ProductEntity;
import com.tecsup.app.micro.product.mapper.ProductMapper;
import com.tecsup.app.micro.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final UserClient userClient;


    public Product getUserById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        
        if (productEntity == null) {
            log.info("Product with id {} not found", id);
            return null;
        }

        User user = userClient.getUserById(productEntity.getCreatedBy());
        log.info("User found: {}", user);

        return mapper.toDomainWithUser(productEntity, user);
    }
    
    public List<Product> getAllProducts() {
        log.info("Obteniendo todos los productos");
        List<ProductEntity> productEntities = productRepository.findAll();
        
        return productEntities.stream()
            .map(entity -> {
                try {
                    User user = userClient.getUserById(entity.getCreatedBy());
                    return mapper.toDomainWithUser(entity, user);
                } catch (Exception e) {
                    log.error("Error al obtener usuario para el producto {}: {}", entity.getId(), e.getMessage());
                    return mapper.toDomain(entity);
                }
            })
            .collect(Collectors.toList());
    }

    public Product updateProduct(Long id, Product productDTO) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setStock(productDTO.getStock());
        entity.setImageUrl(productDTO.getImageUrl());

        ProductEntity updatedEntity = productRepository.save(entity);

        return mapper.toDomain(updatedEntity);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("No se pudo eliminar. Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }

}
