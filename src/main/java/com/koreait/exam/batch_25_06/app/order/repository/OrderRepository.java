package com.koreait.exam.batch_25_06.app.order.repository;

import com.koreait.exam.batch_25_06.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
