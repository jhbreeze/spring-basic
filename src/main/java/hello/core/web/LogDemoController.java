package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
//@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	private final MyLogger myLogger;

	public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
		this.logDemoService = logDemoService;
		this.myLogger = myLogger;
	}

	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		//MyLogger myLogger = myLoggerProvider.getObject(); // ObjectProvider로 인해 getObject 호출하는 시점까지 request Scope 빈 생성 지연 가능
		String url = request.getRequestURL().toString();
		myLogger.setRequestUrl(url);

		myLogger.log("controller test");
		logDemoService.logic("testId");

		return "OK";
	}
}
