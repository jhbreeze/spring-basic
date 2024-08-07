package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class LogDemoService {

	public LogDemoService(MyLogger myLogger) {
		this.myLogger = myLogger;
	}

	private final MyLogger myLogger;

	public void logic(String id) {
		//MyLogger myLogger = myLoggerObjectProvider.getObject();
		myLogger.log("service id = " + id);
	}
}
