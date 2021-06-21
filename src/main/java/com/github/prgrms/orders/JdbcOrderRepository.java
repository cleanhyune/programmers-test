package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.orders.review.Review;
import com.github.prgrms.products.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> findById(Long id) {
        List<Order> results = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq=?",
                mapper,
                id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<Order> findAll(Pageable pageable) {
        List<Order> results = jdbcTemplate.query(
                "SELECT * FROM orders",
                mapper
        );
        for (Order result : results) {
            if(!result.hasNoReview()) {
                List<Review> query = jdbcTemplate.query(
                        "SELECT * FROM REVIEWS WHERE SEQ = ?",
                        reviewMapper,
                        result.getReviewSeq()
                );
                result.setReview(query.get(0));
            }
        }
        return results;
    }

    static RowMapper<Order> mapper = (rs, rowNum) ->
            new Order.Builder()
            .withSeq(rs.getLong("seq"))
            .withUserSeq(rs.getLong("user_seq"))
            .withProductSeq(rs.getLong("product_seq"))
            .withReviewSeq(rs.getLong("review_seq"))
            .withState(rs.getString("state"))
            .withRequestMsg(rs.getString("request_msg"))
            .withRejectMsg(rs.getString("reject_msg"))
            .withCompletedAt(dateTimeOf(rs.getTimestamp("completed_at")))
            .withRejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
            .withCreateAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();

    static RowMapper<Review> reviewMapper = (rs, rowNum) ->
            new Review.Builder()
            .seq(rs.getLong("seq"))
            .user_seq(rs.getLong("user_seq"))
            .product_seq(rs.getLong("product_seq"))
            .content(rs.getString("content"))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();

}
