package com.github.prgrms.orders.review;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcReviewRepository implements ReviewRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long insert(Review review, Long orderSeq) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO REVIEWS(USER_SEQ, PRODUCT_SEQ, CONTENT, CREATE_AT) VALUES(?,?,?,?)",new String[] {"seq"});
            ps.setLong(1, review.getUserSeq());
            ps.setLong(2, review.getProductSeq());
            ps.setString(3, review.getContent());
            ps.setDate(4, java.sql.Date.valueOf(review.getCreateAt().toLocalDate()));
            return ps;
        }, keyHolder);

        long review_seq = keyHolder.getKey().longValue();

        jdbcTemplate.update(
                "UPDATE ORDERS SET REVIEW_SEQ = ? WHERE SEQ = ?",
                review_seq,
                orderSeq
        );

        jdbcTemplate.update(
                "UPDATE PRODUCTS SET REVIEW_COUNT = REVIEW_COUNT + 1 WHERE SEQ = ?",
                review.getProductSeq()
        );

        return review_seq;
    }

}