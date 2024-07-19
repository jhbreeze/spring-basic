package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.Memeber.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		// 1. 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();
		MemberService memberService2 = appConfig.memberService();

		// 2. 참조값이 다른 것을 확인
		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		// isSameAs : 자바 참조 비교(두 개의 참고자 동일한 객체를 가리키는지)
		// equals : 자바 객체의 값을 비교(두 개의 다른 인스턴스가 같은 값을 가지고 있는지)
		assertThat(singletonService1).isSameAs(singletonService2);
	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// 1. Bean이 싱글톤으로 생성됨
		// 클라이언트 요청올 때마다 객체(memberService)를 생성하는 것이 아니라 이미 만들어진 객체(memberService)를 재사용
		MemberService memberService1 = context.getBean("memberService", MemberService.class);
		MemberService memberService2 = context.getBean("memberService", MemberService.class);

		// 2. 참조값이 같은 것을 확인
		assertThat(memberService1).isSameAs(memberService2);
	}


}
