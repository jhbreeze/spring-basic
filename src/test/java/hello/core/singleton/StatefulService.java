package hello.core.singleton;

public class StatefulService {

	private int price; // 싱글톤에서 상태 유지 필드 -> 이렇게 하면 안된다!

	public void order(String name, int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}


}
