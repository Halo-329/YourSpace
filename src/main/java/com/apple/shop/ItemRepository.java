package com.apple.shop;

import org.springframework.data.jpa.repository.JpaRepository;

//테이블 인터페이스
public interface ItemRepository extends JpaRepository<Item,Long> {

}
