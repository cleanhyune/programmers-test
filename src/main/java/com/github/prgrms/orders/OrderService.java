package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

}
