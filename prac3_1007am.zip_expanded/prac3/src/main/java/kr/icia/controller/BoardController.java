package kr.icia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.icia.domain.BoardVO;
import kr.icia.domain.Criteria;
import kr.icia.domain.PageDTO;
import kr.icia.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;

	// list(model,cri);
	// 자바에서는 전달값을 주면서 호출해야 했지만,
	// 스프링의 컨트롤러 에서는
	// 매개변수에 사용하겠다고 명시하면, 알아서 만들어 줌.
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		// Criteria cri 는 기본 생성자로 1,10을 가지고 있음.
		log.info("list");
		model.addAttribute("list", service.getList(cri));
		// java 코드에서 생성된 결과를 jsp 페이지로 전달.

		// 컨트롤러 >> 서비스 >> 매퍼 >> mybatis
//		model.addAttribute("pageMaker", new PageDTO(cri, 190));
		// 총 게시물 수를 임의로 190 설정.
		int total = service.getTotal(cri);
		log.info("total : "+ total);
		model.addAttribute("pageMaker", new PageDTO(cri,total));

	}

	// 글쓰기 버튼을 누르면, 게시물 입력폼 보이기.
	@GetMapping("/register")
	public void register() {
		// 이동할 주소를 리턴하지 않는다면, 요청한 이름으로의 jsp 파일을 찾음.
	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		// @Controller 어노테이션이 붙고,
		// 컴포넌트 스캔에 패키지가 지정되어 있다면,
		// 매개변수 인자들은 스프링이 자동으로 생성 할당 함.
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		// 리다이렉트 시키면서 1회용 값을 전달.

		return "redirect:/board/list";
	}

	// 제목 링크를 클릭하여 글 상세보기 - get 방식.
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		// @ModelAttribute("cri") 는 자동으로 객체 할당 전달.
		// 자동으로 생성된 Criteria cri를 모델값으로 저장하는데,
		// 저장명이 cri
		// 즉, model.addAttribute("cri", cri);// bno, pageNum, amount

		// @RequestParam : 요청 전달값으로 글번호 이용.
		log.info("/get");
		model.addAttribute("board", service.get(bno));
		// 전달값으로 명시만 하면 스프링이 자동 처리.
		// 사용하는 부분만 추가 구현.
	}

	// post 요청으로 /modify 가 온다면, 아래 메소드 수행.
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {

		log.info("modify:" + board);
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		// 수정이 성공하면 success 메세지가 포함되어 이동.
		// 실패해도 메세지 빼고 이동.
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		// addFlashAttribute : 1회성, url 표시창에 전달되지 않음.
		// addAttribute : 지속, url 표시됨.
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri,
			RedirectAttributes rttr) {

		log.info("remove..." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}

}
