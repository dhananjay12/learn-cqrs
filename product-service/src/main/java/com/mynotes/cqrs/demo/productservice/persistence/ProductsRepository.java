package com.mynotes.cqrs.demo.productservice.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);

}