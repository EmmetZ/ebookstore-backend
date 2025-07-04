package com.sjtu.se2321.backend.entity;

import java.util.Set;

import com.sjtu.se2321.backend.dto.BookAddBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Book")
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer price;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cover_id", referencedColumnName = "id")
    private Image cover;

    private Integer sales;
    private Integer stock;

    // always fetch tags eagerly
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BookTag", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Column(name = "is_active")
    private Boolean isActive;

    public Book(BookAddBody body) {
        this.author = body.getAuthor();
        this.description = body.getDescription();
        this.price = body.getPrice();
        this.stock = body.getStock();
        this.title = body.getTitle();
        this.sales = 0;
        this.isActive = true;
    }
}
