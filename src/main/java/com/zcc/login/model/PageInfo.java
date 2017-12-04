package com.zcc.login.model;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/12/4.
 */
@CommonsLog
@Data
public class PageInfo {
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_PAGE_NO = 1;

	private int pageNo;
	private int pageSize;
	private int totalRows;
	private int totalPage;
	private String pageNoDisp;       //可以显示的页号(分隔符"|"，总页数变更时更新)

	public PageInfo() {
		pageNo = 1;
		pageSize = DEFAULT_PAGE_SIZE;
		totalRows = 0;
		totalPage = 0;
		pageNoDisp = "";
	}

	//	public static PageInfo newBuilder(int pageNo, int pageSize){
	//		PageInfo page = new PageInfo();
	//		page.setPageNo(pageNo);
	//		page.setPageSize(pageSize);
	//		return page;
	//	}

	/**
	 * 总件数变化时，更新总页数并计算显示样式
	 */
	private void refreshPage() {
		//总页数计算
		totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : (totalRows / pageSize + 1);
		//防止超出最末页（浏览途中数据被删除的情况）
		if (pageNo > totalPage && totalPage != 0) {
			pageNo = totalPage;
		}
		pageNoDisp = computeDisplayStyleAndPage();
	}

	private String computeDisplayStyleAndPage() {
		List<Integer> pageDisplays = Lists.newArrayList();
		if (totalPage <= 11) {
			for (int i = 1; i <= totalPage; i++) {
				pageDisplays.add(i);
			}
		} else if (pageNo < 7) {
			for (int i = 1; i <= 8; i++) {
				pageDisplays.add(i);
			}
			pageDisplays.add(0);// 0 表示 省略部分(下同)
			pageDisplays.add(totalPage - 1);
			pageDisplays.add(totalPage);
		} else if (pageNo > totalPage - 6) {
			pageDisplays.add(1);
			pageDisplays.add(2);
			pageDisplays.add(0);
			for (int i = totalPage - 7; i <= totalPage; i++) {
				pageDisplays.add(i);
			}
		} else {
			pageDisplays.add(1);
			pageDisplays.add(2);
			pageDisplays.add(0);
			for (int i = pageNo - 2; i <= pageNo + 2; i++) {
				pageDisplays.add(i);
			}
			pageDisplays.add(0);
			pageDisplays.add(totalPage - 1);
			pageDisplays.add(totalPage);
		}
		return Joiner.on("|").join(pageDisplays.toArray());
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		refreshPage();
	}
}
