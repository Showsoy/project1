package member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;
import vo.Util;

public class MemberLostPwModifyAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
		 	throws Exception{
		
		HttpSession session = request.getSession();
		UserService userService = new UserService();
		
		String user_id = (String)session.getAttribute("user_id_forPw");
		
		String salt = Util.getSalt();
		
		ActionForward forward = null;	
		boolean modifyResult = userService.modifyPw(user_id, request.getParameter("userPassre"), salt);
			   	
		if(modifyResult){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 변경되었습니다.');");
			out.println("location.href='./member/login.jsp';");
			out.println("</script>");
	  	}
		return forward;
	}

}
