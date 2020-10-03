package com.board.photo.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("PageVO")
public class PageVO {
	private int pageNum;
	private int totalRow;
	private int rowSize;
	private int startRow;
	private int endRow;
	private int blockSize;
	private int startBlock;
	private int endBlock;
	private int maxBlock;
}
