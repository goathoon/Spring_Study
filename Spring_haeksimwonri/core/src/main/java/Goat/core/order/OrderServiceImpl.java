package Goat.core.order;

import Goat.core.discount.DiscountPolicy;
import Goat.core.member.Member;
import Goat.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member resultMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(resultMember,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}