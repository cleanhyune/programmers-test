package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(Long id);

    List<Order> findAll(Pageable pageable);

}
