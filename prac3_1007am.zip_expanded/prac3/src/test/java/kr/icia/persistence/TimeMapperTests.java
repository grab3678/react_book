package kr.icia.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.icia.mapper.TimeMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j // 아래 클래스 파일을 처리하면서 로그가 발생되면 log4j 이용.
public class TimeMapperTests {
	// 테스트 클래스에서는 jUnit4 와 xml 설정값을 가져와서 테스트, 그러므로
	// @RunWith 와 @ContextConfiguration 는 테스트 코드에 따라 붙는 규칙이 있음.

	// @가 붙는 부분은 어노테이션 이라고 합니다.
	// 야구로 비유하면 투수와 감독의 사인 같은 거에요.

	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	// @Setter : set000 메소드를 자동으로 만들어줌.
	// @Autowired : 필드를 초기화할 객체를 자동으로 연결 시켜줌.

//	@Test // 이 메소드는 테스트용 이다.
//	public void testGetTime() {
//		log.info(timeMapper.getTime());
//		// 소문자 엘로 시작하는 log
//	}

	@Test
	public void testGetTime3() {
		log.info("xml 이용 : " + timeMapper.getTime3());
	}
	// xml 에 mybatis 쿼리 만들고,
	// 인터페이스에 매핑 되는 메소드 만들고,
	// 테스트에서 해당 메소드 호출.

}
