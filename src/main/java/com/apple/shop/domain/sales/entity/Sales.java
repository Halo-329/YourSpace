package com.apple.shop.domain.sales.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Sales {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String itemName;
    Integer price;
    Integer count;
    Long memberId;

    @CreationTimestamp
    LocalDateTime created;

}
