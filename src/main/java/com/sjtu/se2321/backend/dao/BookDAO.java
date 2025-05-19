package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public List<Book> findAllByKeywordAndTag(int limit, int offset, Long tagId, String keyword);

    public Long countByKeywordAndTag(Long tagId, String keyword);

    public Book findById(Long id);

    public void updateBookSales(Long id, int sales);

}
