package com.koreait.exam.batch_25_06.app.base;

import com.koreait.exam.batch_25_06.app.cart.service.CartService;
import com.koreait.exam.batch_25_06.app.member.entity.Member;
import com.koreait.exam.batch_25_06.app.member.service.MemberService;
import com.koreait.exam.batch_25_06.app.order.service.OrderService;
import com.koreait.exam.batch_25_06.app.product.entity.Product;
import com.koreait.exam.batch_25_06.app.product.entity.ProductOption;
import com.koreait.exam.batch_25_06.app.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevInitData {

    @Bean
    public CommandLineRunner initData(MemberService memberService, ProductService productService, CartService cartService, OrderService orderService) {
        return args -> {
            String password = "{noop}1234";
            Member member1 = memberService.join("user1",password,"user1@test.com");
            Member member2 = memberService.join("user2",password,"user2@test.com");
            Member member3 = memberService.join("user3",password,"user3@test.com");
            Member member4 = memberService.join("user4",password,"user4@test.com");

            Product product1 = productService.create("t-shirt", 50000, 45000, "nike",
                    Arrays.asList(
                            new ProductOption("red", "95"),
                            new ProductOption("red", "100"),
                            new ProductOption("red", "105"),
                            new ProductOption("red", "110")
                    ));
            Product product2 = productService.create("pants", 30000, 25000, "nike",
                    Arrays.asList(
                            new ProductOption("blue", "95"),
                            new ProductOption("blue", "100"),
                            new ProductOption("blue", "105"),
                            new ProductOption("blue", "110")
                    ));

            ProductOption productOption__RED_95 = product1.getProductOptions().get(0);
            ProductOption productOption__RED_100 = product1.getProductOptions().get(2);

            cartService.addItem(member1, productOption__RED_95, 1); // 회원1이 productOption__RED_95 1개 추가 / 총 수량 : 1
            cartService.addItem(member1, productOption__RED_95, 2); // 회원1이 productOption__RED_95 2개 추가 / 총 수량 : 3
            cartService.addItem(member1, productOption__RED_100, 1); // 회원1이 productOption__BLUE_95 1개 추가 / 총 수량 : 1

            orderService.createFromCart(member1);
        };
    }

}