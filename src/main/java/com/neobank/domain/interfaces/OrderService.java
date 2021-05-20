package com.neobank.domain.interfaces;

import com.neobank.dto.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Price checkOut(List<String> productIds);
}
