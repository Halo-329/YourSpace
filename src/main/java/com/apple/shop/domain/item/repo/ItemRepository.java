package com.apple.shop.domain.item.repo;

import com.apple.shop.domain.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//테이블 인터페이스
public interface ItemRepository extends JpaRepository<Item,Long> {
    Page<Item> findPageBy(Pageable page);
}
