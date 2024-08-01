package hello.core.scope;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientBean.class,
			PrototypeBean.class);

		ClientBean clientBean = context.getBean(ClientBean.class);
		int count1 = clientBean.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = context.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(2);

	}

	@Scope("singleton")
	static class ClientBean {
		private final PrototypeBean prototypeBean;

		@Autowired
		public ClientBean(PrototypeBean prototypeBean) { // ClientBean 생성시점에 PrototypeBean 주입
			this.prototypeBean = prototypeBean;
		}

		public int logic() {
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destory() {
			System.out.println("PrototypeBean.destory ");
		}
	}
}
