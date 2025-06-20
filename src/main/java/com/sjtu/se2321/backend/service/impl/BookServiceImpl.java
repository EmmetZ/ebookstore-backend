package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.OrderDAO;
import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.dto.AdminBookDTO;
import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookEditBody;
import com.sjtu.se2321.backend.dto.DateReqParam;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.entity.OrderItem;
import com.sjtu.se2321.backend.entity.Tag;
import com.sjtu.se2321.backend.repository.specification.BookSpecifications;
import com.sjtu.se2321.backend.repository.specification.OrderSpecifications;
import com.sjtu.se2321.backend.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public PageResult<BookDTO> findBookByKeywordAndTag(Integer pageIndex, Integer pageSize, String tagName,
            String keyword) {
        // tag:
        // tagId = -1 : tag is null, search all books
        // tagId = 0 : tag is not found in db
        // tagId > 0 : tag is found in db
        Long tagId = Long.valueOf(-1);
        if (!tagName.isEmpty()) {
            Tag tag = tagDAO.findByName(tagName);
            if (tag != null) {
                tagId = tag.getId();
            } else {
                tagId = Long.valueOf(0);
            }
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("id").ascending());
        Specification<Book> spec = BookSpecifications.withFilters(tagId, keyword);
        Page<Book> books = bookDAO.findAllByKeywordAndTag(spec, pageable);
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books.getContent()) {
            dtos.add(new BookDTO(book));
        }
        return new PageResult<BookDTO>(books.getTotalPages(), dtos);
    }

    @Override
    public PageResult<AdminBookDTO> findBookByKeywordAndTagAdmin(Integer pageIndex, Integer pageSize, String tagName,
            String keyword) {
        // tag:
        // tagId = -1 : tag is null, search all books
        // tagId = 0 : tag is not found in db
        // tagId > 0 : tag is found in db
        Long tagId = Long.valueOf(-1);
        if (!tagName.isEmpty()) {
            Tag tag = tagDAO.findByName(tagName);
            if (tag != null) {
                tagId = tag.getId();
            } else {
                tagId = Long.valueOf(0);
            }
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("id").ascending());
        Specification<Book> spec = BookSpecifications.withFilters(tagId, keyword, true);
        Page<Book> books = bookDAO.findAllByKeywordAndTag(spec, pageable);
        List<AdminBookDTO> dtos = new ArrayList<>();
        for (Book book : books.getContent()) {
            dtos.add(new AdminBookDTO(book));
        }
        return new PageResult<AdminBookDTO>(books.getTotalPages(), dtos);
    }

    @Override
    public Book findBookById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    @Transactional
    public void editBookInfo(Long id, BookEditBody body) {
        Book book = bookDAO.findById(id);
        BeanUtils.copyProperties(body, book, "tags", "isActive");
        save(book, body.getTags());
    }

    @Override
    @Transactional
    public void save(Book book, List<String> tagNames) {
        Set<Tag> tags = new HashSet<>();
        if (tagNames != null && !tagNames.isEmpty()) {
            for (String tagName : tagNames) {
                Tag tag = tagDAO.findByName(tagName);
                tags.add(tag);
            }
        }
        book.setTags(tags);
        bookDAO.save(book);
    }

    @Override
    @Transactional
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public List<String> findAllTags() {
        return tagDAO.findAll().stream()
                .map(tag -> tag.getName())
                .toList();
    }

    @Override
    public List<AdminBookDTO> getSalesRank(DateReqParam param) {
        Specification<Order> spec = OrderSpecifications.withFilters(param.getStart(), param.getEnd());
        List<Order> orders = orderDAO.findAll(spec);

        Map<Long, Integer> bookSalesMap = new HashMap<>();
        Map<Long, Book> bookMap = new HashMap<>();

        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                Book book = item.getBook();
                Long bookId = book.getId();

                bookSalesMap.put(bookId, bookSalesMap.getOrDefault(bookId, 0) + item.getNumber());

                // 保存书籍信息以避免重复查询
                if (!bookMap.containsKey(bookId)) {
                    bookMap.put(bookId, book);
                }
            }
        }

        // 排序，保存前10
        List<AdminBookDTO> result = bookSalesMap.entrySet().stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> {
                    AdminBookDTO dto = new AdminBookDTO(bookMap.get(entry.getKey()));
                    dto.setSales(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());

        return result;
    }
}