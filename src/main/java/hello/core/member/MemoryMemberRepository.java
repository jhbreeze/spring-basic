package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); // HashMap은 동시성 이슈가 발생할 수 있으므로, ConcurrentHashMap 사용 권장

	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
