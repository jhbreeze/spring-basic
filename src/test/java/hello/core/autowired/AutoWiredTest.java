package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Member;
import jakarta.annotation.Nullable;

public class AutoWiredTest {

	@Test
	void AutoWiredOption() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean {

		// required = false : 자동 주입할 대상이 없으므로 수정자 메서드 자체가 호출 안됨.
		@Autowired(required = false)
		public void setNoBean1(Member member) {
			System.out.println("setNoBean 1 = " + member);
		}

		// @Nullable : 자동 주입할 대상이 없으면 Null 입력됨.
		@Autowired
		public void setNoBean2(@Nullable Member member) {
			System.out.println("setNoBean 2 = " + member);
		}

		// Optional : 자동 주입할 대상이 없으면 Optional.empty 입력됨.
		@Autowired
		public void setNoBean3(Optional<Member> member) {
			System.out.println("setNoBean 3 = " + member);
		}
	}
}
