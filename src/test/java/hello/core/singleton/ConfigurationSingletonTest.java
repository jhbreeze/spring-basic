package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = context.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = context.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = context.getBean("memberRepository", MemberRepository.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("orderService -> memberRepository = " + memberRepository2);
		System.out.println("real memberRepository = " + memberRepository);

		// 모두 같은 인스턴스를 참고하고 있음.
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

	}

	@Test
	void configurationDeep() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = context.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
	}
}
