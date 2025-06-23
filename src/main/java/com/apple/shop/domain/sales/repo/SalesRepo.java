package com.apple.shop.domain.sales.repo;


import com.apple.shop.domain.sales.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository< Sales, Long>{

}

