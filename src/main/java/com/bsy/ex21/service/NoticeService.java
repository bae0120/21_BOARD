package com.bsy.ex21.service;

import java.util.List;
import java.util.Map;

import com.bsy.ex21.model.Criteria;
import com.bsy.ex21.model.PagingVO;

public interface NoticeService {
	
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map);
	public long noticeTotalRecord(Map<String, Object> map);
	
//	
//	public List<Map<String, Object>> selectNoticeList(Criteria criteria);
//	public long noticeTotalRecord();
	
	public int addNotice(Map<String, Object> map);

	
	public Map<String, Object> noticeDetail(int seqNum);
	
	public int modifyNotice(Map<String, Object> map);
	
	public int deleteNotice(int seqNum);
	
	public long selectFindCnt(Map<String, Object> map);
	
	public List<Map<String, Object>> selectFindSearch(Map<String, Object> map);
	
}
