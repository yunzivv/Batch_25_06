package com.koreait.exam.batch_25_06.app.product.entity;

import com.koreait.exam.batch_25_06.app.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Product extends BaseEntity {

    private String name;
    private int price;
    private String makerShopName;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = All, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    public void addProductOption(ProductOption option) {
    }
}
