package com.github.prgrms.orders.review;

import com.github.prgrms.configures.customEnum.OrderState;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.orders.Order;
import com.github.prgrms.orders.OrderService;
import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static com.google.common.base.Preconditions.checkArgument;

@Service
public class ReviewService {

    private final OrderService orderService;
    private final ReviewRepository reviewRepository;

    ReviewService(OrderService orderService, ReviewRepository reviewRepository) {
        this.orderService = orderService;
        this.reviewRepository = reviewRepository;
    }

    public Review review(Long userSeq, Long orderSeq, ReviewDto request) {
        Order order = orderService.findById(orderSeq).orElseThrow(() -> {
            throw new NotFoundException("could not found order for " + orderSeq);
        });
        checkArgument(
                order.isCompleted(),
                "Could not write review for order 1 because state(REQUESTED) is not allowed");
        checkArgument(
                order.hasNoReview(),
                "Could not write review for order 4 because have already written"
        );

        Review review = new Review.Builder()
                .user_seq(userSeq)
                .product_seq(order.getProductSeq())
                .content(request.getContent())
                .createAt(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()))
                .build();
        long reviewSeq = reviewRepository.insert(review, order.getSeq());
        review.setSeq(reviewSeq);
        return review;
    }

}
