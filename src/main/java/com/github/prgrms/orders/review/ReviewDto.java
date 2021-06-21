package com.github.prgrms.orders.review;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ReviewDto {

    private Long seq;
    private Long user_seq;
    private Long product_seq;

    @NotBlank(message = "")
    private String content;

    private Date create_at;

    public Long getSeq() {
        return seq;
    }

    public Long getUser_seq() {
        return user_seq;
    }

    public Long getProduct_seq() {
        return product_seq;
    }

    public String getContent() {
        return content;
    }

    public Date getCreate_at() {
        return create_at;
    }
}
