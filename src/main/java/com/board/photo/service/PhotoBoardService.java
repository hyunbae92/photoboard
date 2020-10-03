package com.board.photo.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.board.photo.vo.PageVO;
import com.board.photo.vo.PhotoBoardVO;

public interface PhotoBoardService {
	List<PhotoBoardVO> doSelectPhotoBoardList(PhotoBoardVO pb);
	PhotoBoardVO doSelectPhotoBoard(PhotoBoardVO pb);
	Map<String,Object> doInsertPhotoBoard(PhotoBoardVO pb,MultipartFile file);
	Map<String,Object> doUpdatePhotoBoard(PhotoBoardVO pb,MultipartFile file);
	Map<String,Object> doDeletePhotoBoard(int[] pbNums);
	PageVO setPageVO(PageVO pageVO);
}
