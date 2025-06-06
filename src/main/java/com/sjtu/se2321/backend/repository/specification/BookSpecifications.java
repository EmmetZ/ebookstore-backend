package com.sjtu.se2321.backend.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class BookSpecifications {
    public static Specification<Book> withFilters(Long tagId, String keyword, Boolean showInactive) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tagId != null && tagId != -1) {
                Join<Book, Tag> tags = root.join("tags", JoinType.INNER);
                predicates.add(cb.equal(tags.get("id"), tagId));
                if (query != null) {
                    query.distinct(true);
                }
            }

            if (keyword != null && !keyword.isEmpty()) {
                Predicate titleLike = cb.like(root.get("title"), "%" + keyword + "%");
                Predicate authorLike = cb.like(root.get("author"), "%" + keyword + "%");
                predicates.add(cb.or(titleLike, authorLike));
            }
            
            if (showInactive == null || !showInactive) {
                predicates.add(cb.equal(root.get("isActive"), true));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    public static Specification<Book> withFilters(Long tagId, String keyword) {
        return withFilters(tagId, keyword, false);
    }
}
