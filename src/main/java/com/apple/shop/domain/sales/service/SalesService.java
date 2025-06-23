package com.apple.shop.domain.sales.service;

import com.apple.shop.domain.item.entity.Item;
import com.apple.shop.domain.item.service.ItemService;
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
    public void saveItemPayRecode(Long id, int count, Authentication auth){
        Item item;
        String usrId;
        Long memberId;

        Optional<Item> opt = itemService.FindItem(id);

        if(opt.isPresent()){
            item = opt.get();
            Sales sales =new Sales();

            usrId=auth.getName();
            Optional<Member> opt_mamber = memberService.findFirstByLoginId(usrId);
            if(opt_mamber.isPresent()){
                sales.setMemberId(opt_mamber.get().getId());
            }

            sales.setItemName(item.getTitle());
            sales.setPrice(item.getPrice());
            sales.setCount(count);

            salesRepo.save(sales);
        }


    }


    // 2. DB 행 불러오기
    public List<OrderDetail> getAllOrderDetailList(){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<Sales> salesList =  salesRepo.findAll();

        for (Sales sales : salesList){
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setItemName(sales.getItemName());
            orderDetail.setPrice(sales.getPrice());
            orderDetail.setCnt(sales.getCount());

            Optional<Member> member = memberService.findById(sales.getMemberId());
            if(member.isPresent()){
                orderDetail.setOrderId(member.get().getLoginId());
            }
            orderDetailList.add(orderDetail);
        }

        return orderDetailList;
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
