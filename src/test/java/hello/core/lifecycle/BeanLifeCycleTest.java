package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = context.getBean(NetworkClient.class);
		context.close();
	}

	@Configuration
	static class LifeCycleConfig {

		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://127.0.0.1:8080"); // 객체를 생성한 다음에 수정자 주입
			return networkClient;
		}

	}
}
