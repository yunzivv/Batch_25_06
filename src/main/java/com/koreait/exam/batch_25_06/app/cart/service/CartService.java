package com.koreait.exam.batch_25_06.app.cart.service;

import com.koreait.exam.batch_25_06.app.cart.entity.CartItem;
import com.koreait.exam.batch_25_06.app.cart.repository.CartItemRepository;
import com.koreait.exam.batch_25_06.app.member.entity.Member;
import com.koreait.exam.batch_25_06.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartItem addItem(Member member, ProductOption productOption, int quantity) {

        CartItem oldCartItem = cartItemRepository.findByMemberIdAndProductOptionId(member.getId(), productOption.getId()).orElse(null);

        if(oldCartItem != null) {
            oldCartItem.setQuantity(oldCartItem.getQuantity() + quantity);
            cartItemRepository.save(oldCartItem);

            return oldCartItem;
        }

        CartItem cartItem = CartItem.builder()
                .member(member)
                .productOption(productOption)
                .quantity(quantity).build();

        cartItemRepository.save(cartItem);

        return oldCartItem;
    }

    public List<CartItem> getItemsByMember(Member member) {
        return cartItemRepository.findAllByMemberId(member.getId());
    }

    public void deleteItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}