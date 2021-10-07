package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import dao.BbsDao;
import dto.BbsDto;

public class BbsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}	
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("BbsController doProcess()");		
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		
		if(param.equals("bbslist")) {
			System.out.println("BbsController bbslist");
			
			String choice = req.getParameter("choice");	// 제목, 내용, 작성자
			String search = req.getParameter("search"); // 검색어
			if(search == null || choice == null || search.equals("")){
				search = "";
				choice = "";
			}
			
			// 현재 페이지
			String spageNumber = req.getParameter("pageNumber");
			int pageNumber = 0;
			if(spageNumber != null){
				pageNumber = Integer.parseInt(spageNumber);
			}
			
			BbsDao dao = BbsDao.getInstance();
			
			// 글의 총수 
			int len = dao.getAllBbs(choice, search);
			System.out.println("글의 총수:" + len);

			// 페이지 수
			Integer bbsPage = len / 10;		// 29 / 10 -> 2
			if((len % 10) > 0){
				bbsPage = bbsPage + 1;
			}
			
			// 게시판 목록
			List<BbsDto> list = dao.getBbsPagingList(choice, search, pageNumber);
			
			/*
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("search", search);
			map.put("choice", choice);
			map.put("bbsPage", bbsPage);
			map.put("list", list);
			*/
			// 짐싸!
			// req.setAttribute("map", map);
			
			// search
			req.setAttribute("search", search);
			// choice
			req.setAttribute("choice", choice);
			// bbsPage
			req.setAttribute("bbsPage", bbsPage);
			// pageNumber
			req.setAttribute("pageNumber", pageNumber);
			// list
			req.setAttribute("list", list);
						
			// 갖고 이동해야 합니다 -> 			
			forward("bbslist.jsp", req, resp);		// 하단의 함수 사용 
			
		//	RequestDispatcher dispatcher = req.getRequestDispatcher("bbslist.jsp"); 
		//	dispatcher.forward(req, resp);			// 함수없이 
		}
		
		else if(param.equals("bbswrite")){
			resp.sendRedirect("bbswrite.jsp");
		}
		
		else if(param.equals("bbswriteAf")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			//db에 추가
			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.writeBbs(new BbsDto(id, title, content));
			
			if(isS == false){
				resp.sendRedirect("bbswrite.jsp");
			}
			resp.sendRedirect("bbs?param=bbslist");
		}
		
		else if(param.equals("bbsdetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			//db에추가
			BbsDao dao = BbsDao.getInstance();
			
			dao.readcount(seq);	// 조회수 늘리기 
			BbsDto dto = dao.getBbs(seq);
			
			req.setAttribute("bbs", dto);
			forward("bbsdetail.jsp", req, resp);
		}
		
		else if(param.equals("bbsupdate")) {
			System.out.println("BbsController bbsupdate");
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq.trim());

			BbsDao dao = BbsDao.getInstance();
			BbsDto dto = dao.getBbs(seq);
			
			req.setAttribute("bbs", dto);
			
			forward("bbsupdate.jsp", req, resp);
		}
		else if(param.equals("bbsupdateAf")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq.trim());

			String title = req.getParameter("title");
			String content = req.getParameter("content");

			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.updateBbs(seq, title, content);			
			if(isS==false) {
				resp.sendRedirect("bbs?param=bbsupdate&seq=" + sseq);
			}
			resp.sendRedirect("bbs?param=bbslist");
		}
		
		else if(param.equals("bbsdelete")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			System.out.println("seq:" + seq);

			BbsDao dao = BbsDao.getInstance();
			dao.deleteBbs(seq);
			
			resp.sendRedirect("bbs?param=bbslist");
		}
		
		else if(param.equals("bbsanswer")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			BbsDto dto = BbsDao.getInstance().getBbs(seq);
			
			req.setAttribute("bbs", dto);
			
			forward("bbsanswer.jsp", req, resp);
		}
		else if(param.equals("bbsanswerAf")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			BbsDao dao = BbsDao.getInstance();

			boolean isS = dao.answer(seq, new BbsDto(id, title, content));
			if(isS == false){
				resp.sendRedirect("bbs?param=answer&seq=" + seq);
			}
			resp.sendRedirect("bbs?param=bbslist");
		}
	}
	
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(arg); 
		dispatcher.forward(req, resp);
	}
	
}