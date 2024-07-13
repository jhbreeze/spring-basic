package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.Memeber.Grade;
import hello.core.Memeber.Member;
import hello.core.Memeber.MemberService;
import hello.core.Memeber.MemberServiceImpl;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void init() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}

	@Test
	void join() {
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);

		// when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		// then
		Assertions.assertThat(member).isEqualTo(findMember);
	}

}
