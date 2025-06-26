package com.koreait.exam.batch_25_06.app.product.entity;

import com.koreait.exam.batch_25_06.app.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ProductOption extends BaseEntity {
    private String color;
    private String size;

    private int price; // 권장 판매가 30000
    private int salePrice; // 실제 판매가 40000
    private int wholeSalePrice; // 도매가 < 30000
    private int payPrice; // 결제 금액
    private int refundPrice; // 환불 금액
    private int pgFee; // 결제대행사 수수료
    private int refundQuantity; // 환불 갯수
    private boolean idPaid; // 결제 여부


    private String displayColor;
    private String displaySize;

    private boolean isSoldOut; // 관련 옵션의 판매가능 여부
    private int stockQuantity; // 보유 물건 수량


    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private Product product;

    public ProductOption(String color, String size) {
        this.color = color;
        this.displayColor = color;
        this.size = size;
        this.displaySize = size;
    }

    public boolean isOrderable(int quantity) {
        if(isSoldOut() == false) return true;

        return getStockQuantity() >= quantity;
    }

}