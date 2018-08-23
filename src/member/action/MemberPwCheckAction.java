package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;
import vo.Util;

public class MemberPwCheckAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
		 	throws Exception{
		HttpSession session = request.getSession();
		String pw = request.getParameter("userPass");
		String user_id = (String)session.getAttribute("id");
		
		UserService userService = new UserService();

		String salt = userService.salt(user_id);
		String passwd = Util.getPassword(pw,salt);
		
   		boolean loginResult = userService.myPwCheck(user_id,passwd);
   		
		ActionForward forward = null;
		
		if(loginResult){
			forward = new ActionForward();
	   		//forward.setRedirect(false);
	   		forward.setPath("mymod.us");
   		}else{
   			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 맞지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
	   		forward = new ActionForward();
			//forward.setRedirect(true);
			//forward.setPath("./member/myPage.jsp");
	   		}
		 
		return forward;
	}

}