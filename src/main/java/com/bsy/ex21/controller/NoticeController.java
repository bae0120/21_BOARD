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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bsy.ex21.service.NoticeService;
import com.bsy.ex21.util.PageUtils;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/notice/noticeList.do";
	}
	//공지 목록
	@RequestMapping(value="/notice/noticeList.do", method = RequestMethod.GET)
	public String noticeList(Model model, HttpServletRequest request) {

		//파라미터 저장
		String column = request.getParameter("column");
		String query = request.getParameter("query");
//		String startDay = request.getParameter("startDay");
//		String endDay = request.getParameter("endDay");
		
		//map에 파라미터 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", query);
//		map.put("startDay", startDay);
//		map.put("endDay", endDay); 
		
		//전체 데이터 수
		long total = noticeService.noticeTotalRecord(map);
		
		//페이지 파라미터 기본은 1페이지로 잡기
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		long page = Long.parseLong(opt.orElse("1"));
		if(page <= 0) {
			page = 1;
		}
		
		//일반 조회와 검색 페이징 나누기 위한 패스
		String path = "";
		if(request.getParameter("column") != null) {
			path = request.getContextPath() + "/notice/noticeList.do?column=" + column + "&query=" + query;
		} else {
			path = request.getContextPath() + "/notice/noticeList.do";
		}
		
		//페이징 처리를 위해 사용
		PageUtils pageUtils = new PageUtils(total, page, path);
		
		//전체 페이지 수 출력 시 사용
		long totalPage = pageUtils.getTotalPage();
		logger.info(totalPage + "전체 페이지");
		
		//map에 페이징을 위한 변수 추가
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		logger.info(map.toString());

		//리스트에 데이터 가져오기
		List<Map<String, Object>> noticeList = noticeService.selectNoticeList(map);
		logger.info(noticeList.toString());
		logger.info(total + "");
		
		//조회된 리스트를 model에 저장해서 jsp로 가져감
		model.addAttribute("noticeList", noticeList);
		//전체 게시글 개수
		model.addAttribute("total", total);
		//순번표시를 위한 startNo
		model.addAttribute("startNo", total - (page - 1) * pageUtils.getRecordPerPage());
		//page이동에따라 페이징 계속 처리하기 위한 getPaging함수
		//model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/noticeList.do"));
		model.addAttribute("paging", pageUtils);
		return "notice/list";
		
		
	}
	
	
//	//공지 목록
//	@RequestMapping(value="/notice/noticeList.do", method = RequestMethod.GET)
//	public String noticeList(Model model, Criteria criteria) {
//		logger.info("list");
//		
//		model.addAttribute("list", noticeService.selectNoticeList(criteria));
//		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(criteria);
//		pageMaker.setTotalCount(noticeService.noticeTotalRecord());
//		
//		model.addAttribute("pageMaker", pageMaker);
//		
//		return "notice/list3";
//		
//		
//	}
	
//	@RequestMapping(value="/notice/noticeList.do", method = RequestMethod.GET)
//	public String noticeList(Model model, PagingVO vo, @RequestParam(value="nowPage", required=false)String nowPage, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
//		
//		int total = noticeService.noticeTotalRecord();
//		
//		if (nowPage == null && cntPerPage == null) {
//			nowPage = "1";
//			cntPerPage = "5";
//		} else if (nowPage == null) {
//			nowPage = "1";
//		} else if (cntPerPage == null) { 
//			cntPerPage = "5";
//		}
//		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
//		logger.info(vo.toString());
//		model.addAttribute("startNo", total - (Integer.parseInt(nowPage) - 1) * vo.setCntPage());
//		model.addAttribute("paging", vo);
//		model.addAttribute("noticeList", noticeService.selectNoticeList(vo));
//		
//		return "notice/list2";
//		
//		
//	}
	
	
	
	
	//공지 작성화면
	@RequestMapping(value="/notice/btnNoticeWrite.do", method = RequestMethod.GET)
	public String noticeWrite() {
		return "notice/write";
	}
	
//	//공지작성 1
//	@RequestMapping(value="/notice/addNotice.do", method = RequestMethod.POST)
//	public String addNotice(HttpServletRequest request, Model model) {
//		Map<String, Object> map = new HashMap<>();
//		String noticeTitle = (String) request.getParameter("noticeTitle");
//		String noticeContent = (String) request.getParameter("noticeContent");
//		
//		map.put("noticeTitle", noticeTitle);
//		map.put("noticeContent", noticeContent);
//		logger.info(map.toString());
//		
//		int res = noticeService.addNotice(map);
//		model.addAttribute("addNotice", res);
//		return "notice/result";
//	}
	
	//공지작성 2
	@RequestMapping(value="/notice/addNotice.do", method = RequestMethod.POST)
	public String addNotice(HttpServletRequest request, RedirectAttributes ra) {
		Map<String, Object> map = new HashMap<>();
		String noticeTitle = (String) request.getParameter("noticeTitle");
		String noticeContent = (String) request.getParameter("noticeContent");
		
		map.put("noticeTitle", noticeTitle);
		map.put("noticeContent", noticeContent);
		logger.info(map.toString());
		
		int res = noticeService.addNotice(map);
        ra.addFlashAttribute("addNotice",res);
		
		return "redirect:/notice/result.do"; // alert 후, 전달된 url 파라미터로 이동시키는 페이지
	}
	
	@RequestMapping(value="/notice/result.do", method = RequestMethod.GET)
	public String result() {
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
	
	//검색
//	@RequestMapping(value="/notice/noticeSearch.do", method= RequestMethod.GET)
//	public String search(HttpServletRequest request, Model model) {
//		//페이지 파라미터 기본은 1페이지로 잡기
//		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
//		long page = Long.parseLong(opt.orElse("1"));
//				
//		String column = request.getParameter("column");
//		String query = request.getParameter("query");
//		String startDay = request.getParameter("startDay");
//		String endDay = request.getParameter("endDay");
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("column", column);
//		map.put("query", query);
//		map.put("startDay", startDay);
//		map.put("endDay", endDay); 
//		
//		long findCnt = noticeService.selectFindCnt(map);
//		logger.info(findCnt + "");
//		//페이징 처리를 위해 사용
//		PageUtils pageUtils = new PageUtils(findCnt, page);
//		map.put("beginRecord", pageUtils.getBeginRecord());
//		map.put("endRecord", pageUtils.getEndRecord());
//		
//		logger.info(map.toString());
//		
//	    List<Map<String, Object>> list = noticeService.selectFindSearch(map);
//	    logger.info(list.toString());
//	    
//	    model.addAttribute("noticeList", list);
//		model.addAttribute("paging", pageUtils);
//		model.addAttribute("column", column);
//		model.addAttribute("query", query);
//		model.addAttribute("startDay", startDay);
//		model.addAttribute("endDay", endDay);
//		model.addAttribute("startNo", findCnt - (page - 1) * pageUtils.getRecordPerPage());
//		//page이동에따라 페이징 계속 처리하기 위한 getPaging함수
//		//model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/noticeList.do"));
//		
//		return "notice/list";
//	}
}
