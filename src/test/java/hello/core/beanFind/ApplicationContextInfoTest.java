package hello.core.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("스프링에 등록된 모든 bean 정보 조회 ")
	void getAllBeanList() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = context.getBean(beanDefinitionName); // getBean : bean 이름으로 bean 객체 조회
			System.out.println(" name is = " + beanDefinitionName + ", object is = " + bean);
		}

	}

	@Test
	@DisplayName("애플리케이션 bean 출력하기 ")
	void findApplicationBean() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

			// ROLE_APPLICATION = 직접 등록한 빈인지 확인
			// ROLE_INFRASTRUCTURE = 스프링 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = context.getBean(beanDefinitionName);
				System.out.println("name is = " + beanDefinitionName + ", object is = " + bean);
			}
		}

	}

}
