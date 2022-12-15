package com.bsy.ex21.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsy.ex21.dao.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO dao;

	@Override
	public long noticeTotalRecord() {
		// TODO Auto-generated method stub
		return dao.noticeTotalRecord();
	}

	@Override
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) {

		return dao.selecNoticetList(map);
	}

	@Override
	public int addNotice(Map<String, Object> map) {

		return dao.addNotice(map);
	}

	@Override
	public Map<String, Object> noticeDetail(int seqNum) {
		
		return dao.detailNotice(seqNum);
	}
	
	@Override
	public int modifyNotice(Map<String, Object> map) {
		
		return dao.modifyNotice(map);
	}
	
	@Override
	public int deleteNotice(int seqNum) {
		
		return dao.deleteNotice(seqNum);
	}
}
