package com.monk.customer.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @author Shang Pu
 * @version Date：Sep 6, 2015 5:32:44 PM
 * @param <T>
 */
public class PageVo<T> implements Serializable {
	private static final long serialVersionUID = 7216509297056542163L;

	private Integer pageCnt;// 返回的总页数
	private Integer pageSize;// 每页返回多少条数据
	private Integer pageNo;// 当前页数
	private Integer totalCnt;// 返回的总条数
	private List<T> pageData;// 数据

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageVo [pageCnt=").append(pageCnt).append(", pageSize=").append(pageSize)
				.append(", pageNo=").append(pageNo).append(", totalCnt=").append(totalCnt)
				.append(", pageData=").append(pageData).append("]");
		return builder.toString();
	}

	/**
	 * @param totalCnt used to compute pageCnt
	 * @param pageSize
	 * @param pageNo
	 * @param pageData
	 */
	public PageVo(Integer totalCnt, Integer pageSize, Integer pageNo, List<T> pageData) {
		super();
		this.pageCnt = getPageCount(totalCnt, pageSize);
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.pageData = pageData;
		this.totalCnt = totalCnt;
	}

	public PageVo() {
		super();
	}
	
	/**
	 * Access method for the pageCount property.
	 * @param count 
	 * @param pageSize 
	 *
	 * @return the current value of the pageCount property
	 */
	public int getPageCount(int count, int pageSize) {
		// 判断记录总数是否能整除页尺寸
		if (count % pageSize == 0) {
			// 整除则直接取整相除
			pageSize = (count / pageSize);
		} else {
			// 否则取整相除后加一
			pageSize = (count / pageSize) + 1;
		}
		return pageSize;
	}

	public Integer getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(Integer pageCnt) {
		this.pageCnt = pageCnt;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

}
