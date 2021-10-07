package controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.MemberDto;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

public class MemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	
	// doGet이든 doPost든 뭘로 해도 상관없이 편하게 할 수 있는 함수 작성 
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("MemberController doProcess()");		
		req.setCharacterEncoding("utf-8");	// doPost에서는 한글이 깨짐으로 추가
		
		String param = req.getParameter("param");
		
		if(param.equals("login")) {
			resp.sendRedirect("login.jsp");
		}
		else if(param.equals("regi")) {
			resp.sendRedirect("regi.jsp");
		}
		else if(param.equals("getId")) {
			String id = req.getParameter("id");	// regi.jsp ajax부분에서 data로 아이디를 넘겨주면 그걸 받아서 String id로 받음
			System.out.println("id:" + id);
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.getId(id);
			
			String str = "";
			if(b == true){
				str = "NO";	
			}else{
				str = "YES";
			}
			
			JSONObject jobj = new JSONObject();
			jobj.put("msg", str);
			
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);			
		}
		else if(param.equals("regiAf")) {
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			// db에 추가
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.addMember(new MemberDto(id, pwd, name, email, 0));
			
			// 이동
			String msg = "OK";
			if(b == false) {
				msg = "NO";
			}
			
			resp.sendRedirect("message.jsp?msg=" + msg);
		}
		
		else if(param.equals("loginAf")) {
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.login(id, pwd);
			
			req.getSession().setAttribute("login", dto);
			req.getSession().setMaxInactiveInterval(60 * 60 * 2);
			
			if(dto != null) {
				resp.sendRedirect("bbs?param=bbslist");
			}else {
				System.out.println("login 정보 확인");
				resp.sendRedirect("member?param=login");
			}
		}
	}
}









