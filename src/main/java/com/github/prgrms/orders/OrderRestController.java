package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.configures.web.SimplePageRequestHandlerMethodArgumentResolver;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
  // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    private final OrderService orderService;

    OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ApiResult<List<Order>> findAll(Pageable pageable){
        List<Order> all = orderService.findAll(pageable);
        return success(all);
    }

}