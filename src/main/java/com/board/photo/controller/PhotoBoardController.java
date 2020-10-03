package com.board.photo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.photo.service.PhotoBoardService;
import com.board.photo.vo.PhotoBoardVO;

@Controller
public class PhotoBoardController {

	@Autowired
	private PhotoBoardService pbService;
	
	@GetMapping("/pb/list")
	public @ResponseBody List<PhotoBoardVO> getPhotoBoardList(@ModelAttribute PhotoBoardVO pb) {
		return pbService.doSelectPhotoBoardList(pb);
	}
	@GetMapping("/pb/view")
	public @ResponseBody PhotoBoardVO getPhotoBoard(@ModelAttribute PhotoBoardVO pb) {
		return pbService.doSelectPhotoBoard(pb);
	}
	@PostMapping("/pb/insert")
	public @ResponseBody Map<String,Object> insertPhotoBoard(@ModelAttribute PhotoBoardVO pb,@RequestParam("pbfile") MultipartFile file){
		return pbService.doInsertPhotoBoard(pb, file);
	}
	@PostMapping("/pb/update")
	public @ResponseBody Map<String,Object> updatePhotoBoard(@ModelAttribute PhotoBoardVO pb,@RequestParam("pbfile") MultipartFile file){
		return pbService.doUpdatePhotoBoard(pb, file);
	}
	@PostMapping("/pb/delete")
	public @ResponseBody Map<String,Object> deletePhotoBoard(@RequestParam("pbNums") int[] pbNums){
		return pbService.doDeletePhotoBoard(pbNums);
	}
}
