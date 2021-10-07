package kr.icia.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		// locale 은 현재 지역 정보. ko.
		
		Date date = new Date();// 현재 날짜와 시간.
		DateFormat dateFormat 
		= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		// 표시할 방법을 지정.
		
		String formattedDate = dateFormat.format(date);
		// 포맷에 날짜를 적용하여 문자열로 리턴.
		
		model.addAttribute("serverTime", formattedDate );
		// 만들어진 날짜시간을 serverTime 이라는 이름으로 전달 하겠다.
		// home.jsp
		
		return "home";
		// /WEB-INF/views/home.jsp
	}
	
}
