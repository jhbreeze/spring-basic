package hello.core.discount;

import hello.core.Memeber.Grade;
import hello.core.Memeber.Member;

public class FixDiscountPolicy implements DiscountPolicy{

	private int discountFixAmount = 1000;

	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		} else {
			return 0;
		}
	}
}
