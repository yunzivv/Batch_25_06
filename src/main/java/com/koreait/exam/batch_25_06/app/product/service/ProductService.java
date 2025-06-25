package com.koreait.exam.batch_25_06.app.product.service;

import com.koreait.exam.batch_25_06.app.product.entity.Product;
import com.koreait.exam.batch_25_06.app.product.entity.ProductOption;
import com.koreait.exam.batch_25_06.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(String name, int salePrice, int wholeSalePrice, String makerShopName, List<ProductOption> options) {

        int price = (int) Math.ceil(wholeSalePrice * 1.6) / 100 * 100;

        Product product = Product.builder()
                .name(name)
                .price(price)
                .wholeSalePrice(wholeSalePrice)
                .makerShopName(makerShopName).build();

        productRepository.save(product);

        for (ProductOption option : options) {
            product.addProductOption(option);
        }
        return product;
    }
}