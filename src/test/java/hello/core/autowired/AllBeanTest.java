package hello.core.autowired;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

public class AllBeanTest {

	@Test
	void findAllBean() {
		// new AnnotationConfigApplicationContext() 를 통해 스프링 컨테이너 생성
		// 넘겨받은 파라미터 클래스를 자동으로 스프링 빈으로 등록
		ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class); // 스프링 컨테이너 생성하면서 스프링 빈 등록하기

		DiscountService discountService = context.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(1000);
	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> poclicyMap;
		private final List<DiscountPolicy> policies;

		public DiscountService(Map<String, DiscountPolicy> poclicyMap, List<DiscountPolicy> policies) {
			this.poclicyMap = poclicyMap;
			this.policies = policies;
		}

		public int discount(Member member, int price, String discountCode) {
			// 할인코드와 빈 이름을 매칭시킨다.
			DiscountPolicy discountPolicy = poclicyMap.get(discountCode);

			return discountPolicy.discount(member, price);
		}
	}
}
