package com.koreait.exam.batch_25_06.app.product.repository;

import com.koreait.exam.batch_25_06.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}