package com.sjtu.se2321.backend.repository.specification;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.sjtu.se2321.backend.dto.OrderReqParam;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.entity.OrderItem;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class OrderSpecifications {
    public static Specification<Order> withFilters(Long userId, OrderReqParam param) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("user").get("id"), userId));

            if (param != null) {
                if (param.getKeyword() != null) {
                    String keyword = "%" + param.getKeyword().toLowerCase() + "%";

                    Join<Order, OrderItem> orderitemsJoin = root.join("items", JoinType.INNER);

                    Join<OrderItem, Book> bookJoin = orderitemsJoin.join("book", JoinType.INNER);

                    predicates.add(cb.like(cb.lower(bookJoin.get("title")), keyword));
                }

                if (param.getStart() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"),
                            param.getStart().atStartOfDay(ZoneOffset.UTC).toOffsetDateTime()));
                }
                if (param.getEnd() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), param.getEnd().plusDays(1)
                            .atStartOfDay(ZoneOffset.UTC).minusMinutes(1).toOffsetDateTime()));
                }
            }

            if (query != null) {
                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }

}
