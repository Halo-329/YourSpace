package com.apple.shop.domain.item.entiity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Item {     //테이블 속성 메타 데이터
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column
    private String title;
    private Integer price;

    @Override
    public String toString() {
        return "Item{id=" + id + ", title='" + title + "', price=" + price + "}";
    }


}
