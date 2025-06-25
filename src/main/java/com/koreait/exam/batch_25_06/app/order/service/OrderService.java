package com.koreait.exam.batch_25_06.app.order.service;

import com.koreait.exam.batch_25_06.app.cart.entity.CartItem;
import com.koreait.exam.batch_25_06.app.cart.service.CartService;
import com.koreait.exam.batch_25_06.app.member.entity.Member;
import com.koreait.exam.batch_25_06.app.order.entity.Order;
import com.koreait.exam.batch_25_06.app.order.entity.OrderItem;
import com.koreait.exam.batch_25_06.app.order.repository.OrderRepository;
import com.koreait.exam.batch_25_06.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createFromCart(Member member) {
        // 전달받은 회원의 장바구니에 있는 아이템들을 전부 가져와

        // 만약에 장바구니의 특정 상품이 판매 불가 상태라면? => 삭제
        // 만약에 장바구니의 특정 상품이 판매 가능 상태라면? => 주문 품목으로 옮긴 후 삭제
        List<CartItem> cartItems = cartService.getItemsByMember(member);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            ProductOption productOption = cartItem.getProductOption();

            if(productOption.isOrderable(cartItem.getQuantity())) {
                orderItems.add(new OrderItem(productOption, cartItem.getQuantity()));
            }

            cartService.deleteItem(cartItem);
        }

        return create(member,orderItems);
    }

    @Transactional
    public Order create(Member member, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .build();

        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);

        return order;
    }
}