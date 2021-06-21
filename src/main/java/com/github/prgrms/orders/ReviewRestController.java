package com.github.prgrms.orders;

import com.github.prgrms.orders.review.Review;
import com.github.prgrms.orders.review.ReviewDto;
import com.github.prgrms.orders.review.ReviewService;
import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
  // TODO review 메소드 구현이 필요합니다.

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/{id}/review")
    public ApiResult<Review> review(Authentication authentication
            , @PathVariable("id") Long orderId
            , @RequestBody @Valid ReviewDto request) {
        JwtAuthentication principal = (JwtAuthentication) authentication.getPrincipal();
        Review review = reviewService.review(principal.id, orderId, request);
        return success(review);
    }

}