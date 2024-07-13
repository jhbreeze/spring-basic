package hello.core.discount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.Memeber.Grade;
import hello.core.Memeber.Member;

class RateDiscountPolicyTest {

	RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP는 10% 할인 적용")
	void vip_o() {
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);

		// when
		int discount = rateDiscountPolicy.discount(member, 10000);

		// then
		assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP가 아니면 할인 적용 불가")
	void vip_x() {
		// given
		Member member = new Member(2L, "memberA", Grade.BASIC);

		// when
		int discount = rateDiscountPolicy.discount(member, 10000);

		// then
		assertThat(discount).isEqualTo(0);

	}


}