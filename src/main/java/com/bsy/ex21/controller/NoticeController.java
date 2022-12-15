package com.bsy.ex21.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bsy.ex21.service.NoticeService;
import com.bsy.ex21.util.PageUtils;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	//공지 목록
	@RequestMapping(value="/notice/noticeList.do", method = RequestMethod.GET)
	public String noticeList(Model model, HttpServletRequest request) {
		long total = noticeService.noticeTotalRecord();
		
		//페이지 파라미터 기본은 1페이지로 잡기
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		//페이징 처리를 위해 사용
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(total, page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		logger.info(map.toString());
		
		List<Map<String, Object>> noticeList = noticeService.selectNoticeList(map);
		logger.info(noticeList.toString());
		logger.info(total + "");
		
		//조회된 리스트를 model에 저장해서 jsp로 가져감
		model.addAttribute("noticeList", noticeList);
		//순번표시를 위한 startNo
		model.addAttribute("startNo", total - (page - 1) * pageUtils.getRecordPerPage());
		//page이동에따라 페이징 계속 처리하기 위한 getPaging함수
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/noticeList.do"));
		
		return "notice/list";
		
		
	}
	//공지 작성화면
	@RequestMapping(value="/notice/btnNoticeWrite.do", method = RequestMethod.GET)
	public String noticeWrite() {
		return "notice/write";
	}
	//공지작성
	@RequestMapping(value="/notice/addNotice.do", method = RequestMethod.POST)
	public String addNotice(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<>();
		String noticeTitle = (String) request.getParameter("noticeTitle");
		String noticeContent = (String) request.getParameter("noticeContent");
		
		map.put("noticeTitle", noticeTitle);
		map.put("noticeContent", noticeContent);
		logger.info(map.toString());
		
		int res = noticeService.addNotice(map);
		model.addAttribute("addNotice", res);
		return "notice/result";
	}
	
	
	//공지 상세
	@RequestMapping(value="/notice/detail.do", method = RequestMethod.GET)
	public String detail(String noticeSeq, Model model) {
		Optional<String> opt = Optional.ofNullable(noticeSeq);
		int seqNum = Integer.parseInt(opt.orElse("0"));
		Map<String, Object> map = noticeService.noticeDetail(seqNum);
		logger.info(map.toString());
		model.addAttribute("noticeListOne", map);
		return "notice/detail";
	}
	
	//수정
	@RequestMapping(value="/notice/modify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeSeq"));
		int seqNum = Integer.parseInt(opt.orElse("0"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		Map<String, Object> map = new HashMap<>();
		map.put("noticeSeq", seqNum);
		map.put("noticeTitle", noticeTitle);
		map.put("noticeContent", noticeContent);
		logger.info(map.toString());
		
		int res = noticeService.modifyNotice(map);
		logger.info(res + "왜안돼");
		model.addAttribute("modifyNotice", res);
		return "notice/result";
	}
	
	//삭제
	@RequestMapping(value="/notice/delete.do", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, Model model) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeSeq"));
		int seqNum = Integer.parseInt(opt.orElse("0"));
		int res = noticeService.deleteNotice(seqNum);
		logger.info(res + "삭제완?");
		model.addAttribute("deleteNotice", res);
		return "notice/result";
	}
}
