package hello.core;

import hello.core.Memeber.MemberService;
import hello.core.Memeber.MemberServiceImpl;
import hello.core.Memeber.MemoryMemberRepository;
import hello.core.discount.FixDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

	// 생성자 주입

	public MemberService memberService() {
		return new MemberServiceImpl(new MemoryMemberRepository());
	}

	public OrderService orderService() {
		return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
	}


}
