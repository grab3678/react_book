package kr.icia.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("select sysdate from dual")
	public String getTime();
	// @Select : 데이터 검색과 관련된 쿼리를 사용하겠다.

	// 과거의 경우, 요청 > 요청을 컨트롤러로 전달 > 디비 커넥션 생성하고,
	// 쿼리를 담아서 커넥션을 통해서 디비에 전달.
	// 전달 받은 결과를 가져와서 > 컨트롤러 통하고 > 뷰를 통해서 결과 표시.

	// getTime()를 호출하면 쿼리를 담아서 오라클에 전달하고 그 결과를 호출한 곳으로 리턴.
	
	public String getTime3();
}
