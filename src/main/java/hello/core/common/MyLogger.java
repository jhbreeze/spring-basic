package hello.core.common;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

@Component
@Scope(value = "request") // http 요청당 하나씩 빈이 생성됨.
public class MyLogger {

	private String uuid;

	@Setter
	private String requestUrl;

	public void log(String message) {
		System.out.println("[" + uuid + "] " + "[" + requestUrl + "] " + "[" + message + "]");
	}

	@PostConstruct
	public void init() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: " + this);
	}

	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close: " + this);
	}
}