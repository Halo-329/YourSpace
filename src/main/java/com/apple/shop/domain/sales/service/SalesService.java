package com.apple.shop.domain.sales.service;

import com.apple.shop.domain.item.entity.Item;
import com.apple.shop.domain.item.service.ItemService;
import com.apple.shop.domain.member.service.MyUserDetailsService;
import com.apple.shop.domain.sales.entity.Sales;
import com.apple.shop.domain.sales.repo.SalesRepo;
import com.apple.shop.domain.member.entity.Member;
import com.apple.shop.domain.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepo salesRepo;
    private final ItemService itemService;
    private final MemberService memberService;


    // 1. 저장
    public boolean saveItemPayRecode(Long itemId, int count, Authentication auth){
        Item item;
        String usrId;
        Long memberId;

        Optional<Item> opt = itemService.FindItem(itemId);

        if(!isStockAvailable(itemId, count)){
            return false;
        }


        if(opt.isPresent()){
            item = opt.get();
            Sales sales =new Sales();
            sales.setItemName(item.getTitle());
            sales.setPrice(item.getPrice());
            sales.setCount(count);
            itemService.reduceStock(item.getId(),count);    // 아이템 Stock Discount

            MyUserDetailsService.CustomUser usr = (MyUserDetailsService.CustomUser) auth.getPrincipal();
            Member member = new Member();
            member.setId(memberService.findFirstByLoginId(usr.getUsername()).get().getId());
            sales.setMember(member);

            salesRepo.save(sales);

        }
            return true;

    }


    // 2. DB 행 불러오기
    public List<Sales> getAllOrderDetailList(){

        return salesRepo.findAll();
    }

    // 3. 재고 체크
    public boolean isStockAvailable(Long itemId, int count){
        Item item=null;


        Optional<Item> opt = itemService.FindItem(itemId);

        if(opt.isPresent()){
            item=opt.get();
        }

        if(item.getStock()-count<0){
            return false;
        }


        return true;
    }


}

@RequiredArgsConstructor
@Getter @Setter
class OrderDetail{
     String itemName;
     int cnt;
     int price;
     String orderId ;
}
