package hello.core.common;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

@Component
@Scope(value = "request")
public class MyLogger {

	private String uuid;

	private String requestUrl;

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

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