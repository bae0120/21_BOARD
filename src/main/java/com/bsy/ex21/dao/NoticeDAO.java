package com.bsy.ex21.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsy.ex21.model.Criteria;
import com.bsy.ex21.model.PagingVO;

@Repository
public class NoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//전체 공지 개수
	public long noticeTotalRecord(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.noticeTotalRecord", map);
	}
	
	//공지 목록
	public List<Map<String, Object>> selecNoticetList(Map<String, Object> map){
		return sqlSessionTemplate.selectList("mybatis.mapper.notice.noticeList", map);
	}
	
	
//	//전체 공지 개수
//	public long noticeTotalRecord() {
//		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.noticeTotalRecord");
//	}
//	
//	//공지 목록
//	public List<Map<String, Object>> selecNoticetList(Criteria criteria){
//		return sqlSessionTemplate.selectList("mybatis.mapper.notice.noticeList", criteria);
//	}
	
	
	
	
	//공지 작성
	public int addNotice(Map<String, Object> map) {
		return sqlSessionTemplate.insert("mybatis.mapper.notice.addNotict", map);
	}
	
	//공지 상세
	public Map<String, Object> detailNotice(int seqNum){
		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.detailNotict", seqNum);
	}
	
	//공지 수정
	public int modifyNotice(Map<String, Object> map) {
		return sqlSessionTemplate.update("mybatis.mapper.notice.modifyNotice", map);
	}
	
	//공지 삭제
	public int deleteNotice(int seqNum) {
		return sqlSessionTemplate.delete("mybatis.mapper.notice.deleteNotice", seqNum);
	}
	
	//공지 검색 개수
	public long selectFindCnt(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.selectFindCnt", map);
	}

	//공지 검색 목록
	public List<Map<String, Object>> selectFindSearch(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("mybatis.mapper.notice.selectFindSearch", map);
	}
}
