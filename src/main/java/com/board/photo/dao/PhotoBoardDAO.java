package com.board.photo.dao;

import java.util.List;

import com.board.photo.vo.PhotoBoardVO;

public interface PhotoBoardDAO {
	List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb);
	PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb);
	int insertPhotoBoard(PhotoBoardVO pb);
	int updatePhotoBoard(PhotoBoardVO pb);
	int deletePhotoBoards(int[] pbNums);
	int deletePhotoBoard(int pbNum);
	int selectPhotoBoardTotalRow();
	PhotoBoardVO selectPhotoBoardForDelete(int pbNum);
}
