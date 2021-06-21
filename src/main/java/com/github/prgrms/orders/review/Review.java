package com.github.prgrms.orders.review;

import java.time.LocalDateTime;

public class Review {

    private Long seq;
    private Long userSeq;
    private Long productSeq;
    private String content;
    private LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.content = content;
        this.createAt = createAt;
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private String content;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Review review) {
            this.seq = review.seq;
            this.userSeq = review.userSeq;
            this.productSeq = review.productSeq;
            this.content = review.content;
            this.createAt = review.createAt;
        }

        public Review.Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Review.Builder user_seq(Long seq) {
            this.userSeq = seq;
            return this;
        }

        public Review.Builder product_seq(Long seq) {
            this.productSeq = seq;
            return this;
        }

        public Review.Builder content(String content) {
            this.content = content;
            return this;
        }

        public Review.Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Review build() {
            return new Review(
                    seq,
                    userSeq,
                    productSeq,
                    content,
                    createAt
            );
        }
    }

}
