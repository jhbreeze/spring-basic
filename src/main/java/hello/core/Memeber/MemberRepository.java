package hello.core.Memeber;

public interface MemberRepository {
	void save(Member member);
	Member findById(Long id);
}
