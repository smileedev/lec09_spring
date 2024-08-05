package com.gn.spring.board.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gn.spring.board.model.service.BoardService;
import com.gn.spring.board.model.vo.Board;

@Controller
public class BoardViewController {
	
	@Autowired
	BoardService boardService;
	
//	private static final Logger LOGGER = LogManager.getLogger(BoardViewController.class);
	
	
		// 화면단에 데이터 보내는 방법 2가지
		// 1. ModelAndView -> 잘 안씀.
//	@GetMapping("/board")
//	public ModelAndView selectBoardList() {
//		List<Board> resultList = boardService.selectBoardList();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("resultList", resultList);
//		mav.setViewName("/board/list");
//		return mav;
//	}4
	
	
	@GetMapping("/board")
	public String selectBoardList(Model model, Board option) {
		
		option.setTotalData(boardService.selectBoardCount(option));
		
		List<Board> resultList = boardService.selectBoardList(option);
		
//		LOGGER.info(resultList);
		
		// 2. Model(매개변수 꼭 설정!)
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", option);
		
		// WEB-INF/views/board/list.jsp
		return "/board/list";
	}
	
	@GetMapping("/board/create")
	public String createBoardPate() {
		// 아래는 /WEB-INF/veiws/board/create.jsp를 지칭함.
		// 위는 주소?를 지칭함.
		return "/board/create";
	}
	
	@GetMapping("/board/{board_no}")
	public String selectBoardOne(@PathVariable("board_no") int board_no,
			Model model) {
//		LOGGER.info("게시글 PK : "+ board_no);
		
		Board vo = boardService.selectBoardOne(board_no);
		model.addAttribute("vo", vo);
		// WEB-INF/views/board/detail.jsp
		return "/board/detail";
	}
	
	
	@GetMapping("/board/update/{board_no}")
	public String updateBoard(@PathVariable("board_no") int board_no,
			Model model) {
		Board vo = boardService.selectBoardOne(board_no);
		model.addAttribute("vo", vo);
		return "/board/update"
;	}
	
	
}
