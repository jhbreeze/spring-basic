package hello.core.beanFind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류 발생")
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class, () -> context.getBean(DiscountPolicy.class));
	}

	@Test
	@DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
	void findBeanByParentTypeBeanName() {
		RateDiscountPolicy rateDiscountPolicy = context.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("특정 하위 타입으로 조회")
	void findBeanBySubType() {
		RateDiscountPolicy rateDiscountPolicy = context.getBean(RateDiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회")
	void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = context.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		for (String key : beansOfType.keySet()) {
			System.out.println("key : " + key + ", bean : " + beansOfType.get(key));
		}
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기 - Object")
	void findAllByObjectType() {
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key : " + key + ", bean : " + beansOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig {

		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();

		}

	}

}
