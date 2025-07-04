package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.BookStatistic;
import com.sjtu.se2321.backend.dto.DateReqParam;
import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.dto.OrderReqParam;
import com.sjtu.se2321.backend.dto.PageResult;

public interface OrderService {
    public PageResult<OrderDTO> findAllByUserIdWithFilter(Long userId, OrderReqParam param);

    public PageResult<OrderDTO> findAllWithFilter(OrderReqParam param);

    public void placeOrder(Long userId, String address, String tel, String receiver, List<Long> itemIds);

    public List<BookStatistic> gerOrderStatistic(Long userId, DateReqParam param);
}
