package com.board.photo.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pbVO")
public class PhotoBoardVO {
	private int pbNum;
	private int[] pbNums;
	private String pbTitle;
	private String pbContent;
	private String pbImgPath;
	private String credat;
	private String cretim;
	private PageVO pageVO;	
}
