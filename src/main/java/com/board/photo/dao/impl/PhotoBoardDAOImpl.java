package com.board.photo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.photo.dao.PhotoBoardDAO;
import com.board.photo.vo.PhotoBoardVO;

@Repository
public class PhotoBoardDAOImpl implements PhotoBoardDAO {
	
	@Autowired
	private SqlSessionFactory ssf;
	
	@Override
	public List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectList("pb.selectPhotoBoardList",pb);
		}
	}

	@Override
	public PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectOne("pb.selectPhotoBoard",pb);
		}
	}

	@Override
	public int insertPhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.insert("pb.insertPhotoBoard",pb);
		}
	}

	@Override
	public int updatePhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.update("pb.updatePhotoBoard",pb);
		}
	}

	@Override
	public int deletePhotoBoards(int[] pbNums) {
		try(SqlSession ss = ssf.openSession()){
			int cnt = 0;
			for(int pbNum:pbNums) {
				ss.delete("pb.deletePhotoBoard",pbNum);
				cnt++;
			}
			if(cnt!=pbNums.length) {
				ss.rollback();
				return 0;
			}
			return cnt;
		}
	}
	@Override
	public int deletePhotoBoard(int pbNum) {
		try(SqlSession ss = ssf.openSession()){
			return ss.delete("pb.deletePhotoBoard",pbNum);
		}
	}

	@Override
	public int selectPhotoBoardTotalRow() {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectOne("pb.selectPhotoBoardTotalRow");
		}
	}

	@Override
	public PhotoBoardVO selectPhotoBoardForDelete(int pbNum) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectOne("pb.selectPhotoBoardForDelete",pbNum);
		}
	}

}
