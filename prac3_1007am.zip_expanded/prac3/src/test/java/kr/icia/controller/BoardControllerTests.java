package kr.icia.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration // Servlet 설정을 하겠다고 서버에 알린다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	// 웹으로 프로그램을 테스트하기 위한 객체이다.
	private MockMvc mockMvc;
	// mvc 모델 모형 객체이다.

	@Before // 테스트 실행 전에 먼저 실행하라고 알린다.
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

//	@Test
//	public void testList() throws Exception {
//		log.info(
//				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
//		/*
//		 * /board/list 요청에 대한 처리를 get 방식으로 하고, 그 결과를 andReturn()으로 받아서,
//		 * getModelAndView()를 통해 model로 전환 후, 결과를 getModelMap()으로 jsp 페이지로 출력한다.
//		 */
//	}
	// Junit으로 테스트 케이스를 만들어 세션에 어떤 작업을 할 때, MockHttpSession 객체를 생성하게 되는데,
	// 서블릿 버전 3.1
	// 이하에서는 SessionCookieConfig 클래스를 찾지 못하는 오류가 발생

//	@Test
//	public void testRegister() throws Exception {
//		String result = mockMvc
//				.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새글 제목")
//				.param("content", "테스트 새글 내용")
//				.param("writer", "user"))
//				.andReturn().getModelAndView().getViewName();
//		// 포스트 요청으로 /board/register 발생되면,
//		// 파라미터로 제목, 내용, 작성자를 전달하고,
//		// 그 결과를 받아서 전달할수 있는 mav 형태로 바꾸고,
//		// 그 객체의 이름을 표시.
//
//		log.info(result);
//	}

//	@Test
//	public void testGet() throws Exception {
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
//				.param("bno", "2")).andReturn()
//				.getModelAndView().getModelMap());
//		// 2번 게시물 내용 읽기.
//	}

//	@Test
//	public void testModify() throws Exception {
//		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", "1")
//				.param("title", "수정된 테스트 새글 제목0928").param("content", "수정된 테스트 새글 내용0928").param("writer", "user0928"))
//				.andReturn().getModelAndView().getViewName();
//
//		log.info(result);
//	}

//	@Test
//	public void testRemove() throws Exception {
//		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
//				.param("bno", "23"))
//				.andReturn().getModelAndView().getViewName();
//		// 23번 게시물 삭제 점검.
//		log.info(result);
//	}
	
	@Test
	public void testList2() throws Exception {
		log.info(
				mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				.param("pageNum","2")
				.param("amount","10")
				.param("type","TW")
				.param("keyword", "테스트")						
						)
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}


}






