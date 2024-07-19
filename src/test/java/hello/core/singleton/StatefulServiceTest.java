package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

		StatefulService statefulService1 = context.getBean(StatefulService.class);
		StatefulService statefulService2 = context.getBean(StatefulService.class);

		// Thread A
		statefulService1.order("userA", 10000);

		// Thread B
		statefulService2.order("userB", 20000);

		// Thread A : userA의 주문금액 조회 시, 20000원 조회됨.
		int price = statefulService1.getPrice();
		System.out.println("A price : " + price);

		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}