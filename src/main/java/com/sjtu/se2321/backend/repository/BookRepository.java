package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sjtu.se2321.backend.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Modifying
    @Query("UPDATE Book SET sales = sales + :number WHERE id = :id")
    void updateBookSale(@Param("id") Long id, @Param("number") int number);

}
