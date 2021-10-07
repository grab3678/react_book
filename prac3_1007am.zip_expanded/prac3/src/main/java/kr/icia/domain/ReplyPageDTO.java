package kr.icia.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {
	private int replyTotalCnt;//덧글의 갯수
	private List<ReplyVO> list;// 덧글의 목록
	
}
