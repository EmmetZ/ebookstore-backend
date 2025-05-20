package com.sjtu.se2321.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "OrderItem")
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "book_id")
    private Long bookId;

    private Integer number;

    public OrderItem(Long orderId, Long bookId, Integer number) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.number = number;
    }
}
