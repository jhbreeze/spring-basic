package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
			ComponentFilterAppConfig.class);

		BeanA beanA = context.getBean("beanA", BeanA.class);
		assertThat(beanA).isNotNull();

		assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanB", BeanB.class));
	}

	@Configuration
	@ComponentScan(
		includeFilters = @Filter(classes = MyIncludeComponent.class),
		excludeFilters = @Filter(classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig {

	}

}
