package com.github.prgrms.orders;

import com.github.prgrms.configures.customEnum.OrderState;
import com.github.prgrms.orders.review.Review;

import java.time.LocalDateTime;

public class Order {

    private Long seq;
    private Long userSeq;
    private Long productSeq;
    private Long reviewSeq;
    private Review review;

    private String state;

    private String requestMsg;
    private String rejectMsg;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getState() {
        return state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public boolean isCompleted() {
        return this.state.equals(OrderState.COMPLETED.name());
    }

    public boolean hasNoReview() {
        return this.reviewSeq == 0;
    }

    public static final class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private Long reviewSeq;
        private String state;
        private String requestMsg;
        private String rejectMsg;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        Builder() {
        }

        public static Builder anOrder() {
            return new Builder();
        }

        public Builder withSeq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder withUserSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder withProductSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder withReviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Builder withRequestMsg(String requestMsg) {
            this.requestMsg = requestMsg;
            return this;
        }

        public Builder withRejectMsg(String rejectMsg) {
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Builder withCompletedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder withRejectedAt(LocalDateTime rejectedAt) {
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder withCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.productSeq = this.productSeq;
            order.completedAt = this.completedAt;
            order.rejectMsg = this.rejectMsg;
            order.rejectedAt = this.rejectedAt;
            order.userSeq = this.userSeq;
            order.createAt = this.createAt;
            order.requestMsg = this.requestMsg;
            order.state = this.state;
            order.reviewSeq = this.reviewSeq;
            order.seq = this.seq;
            return order;
        }
    }
}
