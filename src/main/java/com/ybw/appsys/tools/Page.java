package com.ybw.appsys.tools;
/**
 * ��ҳ������
 * @author wxg
 *
 */
public class Page {
	private int pageNo;	// ��ǰҳ��
	private int pageSize;	// ÿҳ��ʾ����
	private int beginIndex;	// ��ʼλ��
	private int totalCount;	// ����������
	private int totalPageCount;	// ��ҳ��
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	// ��ȡ��ʼλ�õ��±�
	public int getBeginIndex() {
		return (this.pageNo-1)*this.pageSize;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	// ��ȡ��ҳ��
	public int getTotalPageCount() {
		return (this.totalCount%this.pageSize==0)?(this.totalCount/this.pageSize):(this.totalCount/this.pageSize+1);
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
}
