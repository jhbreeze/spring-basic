package hello.core.singleton;

public class SingletonService {

	// 1. static 선언 : static 영역에 객체 1개만 생성
	private static final SingletonService instance = new SingletonService();

	// 2. 객체 인스턴스가 필요하면 getInstance()를 통해서만 조회하도록 허용 : 항상 같은 인스턴스를 반환한다.
	public static SingletonService getInstance() {
		return instance;
	}

	// 3. 생성자를 private 선언해서 외부에서 New 키워드를 통해 객체 생성 못하게 막음.
	private SingletonService() {

	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}

}
