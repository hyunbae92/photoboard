package com.board.photo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.photo.dao.PhotoBoardDAO;
import com.board.photo.service.PhotoBoardService;
import com.board.photo.vo.PageVO;
import com.board.photo.vo.PhotoBoardVO;

@Service
public class PhotoBoardServiceImpl implements PhotoBoardService {
	private final String uploadPath = "E:\\Java\\java_home_study\\photoBoard\\src\\main\\webapp\\resources\\img\\";
	@Autowired
	private PhotoBoardDAO pbDAO;
	Map<String, Object> rMap = new HashMap<>();
	
	@Override
	public List<PhotoBoardVO> doSelectPhotoBoardList(PhotoBoardVO pb) {
		pb.setPageVO(setPageVO(pb.getPageVO()));
		return pbDAO.selectPhotoBoardList(pb);
	}

	@Override
	public PhotoBoardVO doSelectPhotoBoard(PhotoBoardVO pb) {
		return pbDAO.selectPhotoBoard(pb);
	}

	@Override
	public Map<String, Object> doInsertPhotoBoard(PhotoBoardVO pb,MultipartFile file) {
		rMap.put("msg", "게시물 등록이 실패하였습니다.");
		if(file.getSize()==0) {
			rMap.put("result",pbDAO.insertPhotoBoard(pb));
			rMap.put("msg", "게시물이 등록 되었습니다.");
			return rMap;
		}
		String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = System.nanoTime() + extName;
		pb.setPbImgPath(fileName);
		int cnt = pbDAO.insertPhotoBoard(pb);
		if(cnt==1) {
			File f = new File(uploadPath+fileName);
			try {
				file.transferTo(f);
				rMap.put("result",cnt);
				rMap.put("msg", "게시물이 등록 되었습니다.");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rMap;
	}

	@Override
	public Map<String, Object> doUpdatePhotoBoard(PhotoBoardVO pb,MultipartFile file) {
		rMap.put("msg", "수정되지 않았습니다.");
		if(file.getSize()==0) {
			rMap.put("result", pbDAO.updatePhotoBoard(pb));
			rMap.put("msg", "수정되었습니다.");
		}else {
			String imgPath = pbDAO.selectPhotoBoardForDelete(pb.getPbNum()).getPbImgPath();
			String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			pb.setPbImgPath(System.nanoTime()+extName);
			int cnt = pbDAO.updatePhotoBoard(pb);
			rMap.put("result", cnt);
			if(cnt==1) {
				File f = new File(uploadPath+pb.getPbImgPath());
				try {
					file.transferTo(f);
					rMap.put("msg", "수정되었습니다.(파일등록 완료)");
					f = new File(uploadPath+imgPath);
					if(f.exists()) {
						f.delete();
						rMap.put("msg", "수정되었습니다.(파일등록 완료,이전파일 삭제 완료)");
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return rMap;
	}

	@Override
	public Map<String, Object> doDeletePhotoBoard(int[] pbNums) {
		rMap.put("msg", "삭제가 실패하였습니다.");
		if(pbNums.length==1) {
			rMap.put("result",pbDAO.deletePhotoBoard(pbNums[0]));
			rMap.put("msg", "삭제되었습니다.");
		}else {
			int cnt = 0;
			List<String> pathList = new ArrayList<>();
			for(int pbNum:pbNums) {
				pathList.add(pbDAO.selectPhotoBoardForDelete(pbNum).getPbImgPath());
			}
			if(pbDAO.deletePhotoBoards(pbNums)!=0) {
				for(String path:pathList) {
					File f = new File(uploadPath+path);
					if(f.exists()) {
						f.delete();
						cnt++;
					}
				}
			}
			rMap.put("result", cnt);
			rMap.put("msg","삭제되었습니다. ("+cnt+"개의 파일)");
		}
		return rMap;
	}

	@Override
	public PageVO setPageVO(PageVO pageVO) {
		if(pageVO.getPageNum()==0) {
			pageVO.setPageNum(1);
			pageVO.setRowSize(10);
			pageVO.setBlockSize(10);
		}
		int totalRow = pbDAO.selectPhotoBoardTotalRow();
		int pageNum = pageVO.getPageNum();
		int rowSize = pageVO.getRowSize();
		int blockSize = pageVO.getBlockSize();
		int startRow = (pageNum-1) * rowSize + 1;
		int endRow = startRow +rowSize - 1;
		int startBlock = ((pageNum-1)/blockSize) * blockSize +1;
		int endBlock = startBlock + blockSize - 1;
		int maxBlock = totalRow/rowSize + 1;
		pageVO.setStartRow(startRow);
		pageVO.setEndRow(endRow);
		pageVO.setStartBlock(startBlock);
		pageVO.setEndBlock(endBlock);
		pageVO.setMaxBlock(maxBlock);
		pageVO.setTotalRow(totalRow);
		return pageVO;
	}
	
	

}
