package com.gn.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gn.spring.board.model.service.BoardService;
import com.gn.spring.board.model.service.UploadFileService;
import com.gn.spring.board.model.vo.Board;

@Controller
public class BoardApiController {
	
//	private static final Logger LOGGER
//		= LogManager.getLogger(BoardApiController.class);
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	BoardService boardService;
	
	@ResponseBody
	// 위 해시태그를 통해 json 형태로 create.jsp에  보내줌.
	@PostMapping("/board")
	public Map<String, String> createBoard(Board vo,
			@RequestParam("file") MultipartFile file){
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "게시글 등록 중 오류가 발생하였습니다.");

//		LOGGER.info("file 데이터: " + file.getOriginalFilename());
		
		// 2. 게시글 정보, 파일 정보를 DB에 등록(insert)
		String savedFileName = uploadFileService.upload(file);
		
		if("".equals(savedFileName)== false) {
			// 3. 결과를 json 형태로 화면에 전달
			
			vo.setOri_thumbnail(file.getOriginalFilename());
			vo.setNew_thumbnail(savedFileName);
			
//			LOGGER.info("Board 데이터: " + vo);
			
			int result = boardService.createBoard(vo);
			if(result > 0) {
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", "게시글이 성공적으로 등록되었습니다.");
			}
		}
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/board/{board_no}")
	public Map<String, String> updateBoard(Board vo,
			@RequestParam("file") MultipartFile file) {
		
		String savedFileName = uploadFileService.upload(file);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("res_code", "");
		resultMap.put("res_msg", "게시글 수정 중 오류가 발생하였습니다.");
		
		if(file != null && !"".equals(file.getOriginalFilename())) {
			vo.setOri_thumbnail(file.getOriginalFilename());
			vo.setNew_thumbnail(savedFileName);
			int result = boardService.updateBoard(vo);
			
			if(result > 0) {
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", "게시글이 성공적으로 수정되었습니다.");
			}
			
		}else {
			int result = boardService.updateBoard(vo);
			
			if(result > 0) {
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", "게시글이 성공적으로 수정되었습니다.");
			}
		}
		return resultMap;
	}
	
	@ResponseBody
	@DeleteMapping("/board/{board_no}")
	public Map<String, String> deleteBoard(@PathVariable int board_no){
		Map<String, String> map = new HashMap<String, String>();
		map.put("res_code", "");
		map.put("res_msg", "게시글 삭제 중 오류가 발생하였습니다.");
		
		
		
		
		return map;
	}
	
	
	
	
	
}
