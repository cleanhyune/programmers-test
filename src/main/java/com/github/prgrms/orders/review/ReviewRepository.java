package com.github.prgrms.orders.review;

public interface ReviewRepository {

    long insert(Review review, Long orderSeq);

}
