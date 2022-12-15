package com.bsy.ex21.service;

import java.util.List;
import java.util.Map;

public interface NoticeService {
	
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map);
	
	public int addNotice(Map<String, Object> map);

	public long noticeTotalRecord();
	
	public Map<String, Object> noticeDetail(int seqNum);
	
	public int modifyNotice(Map<String, Object> map);
	
	public int deleteNotice(int seqNum);
}