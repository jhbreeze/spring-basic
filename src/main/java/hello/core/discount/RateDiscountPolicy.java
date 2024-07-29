package hello.core.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;


@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercentage = 10;

	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP) {
			return price * discountPercentage / 100;
		} else {
			return 0;
		}

	}
}
