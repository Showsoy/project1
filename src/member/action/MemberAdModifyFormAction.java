package member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;
import vo.UserBean;

public class MemberAdModifyFormAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
		 	throws Exception{
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		if(id==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./member/memberLogin.us?turn=ok';");
			out.println("</script>");
		}else if(!id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='../main.im';");
			out.println("</script>");
		} else {
			String uid = request.getParameter("uid");
			UserService userService = new UserService();
			forward = new ActionForward();
			UserBean user = userService.selectUserInfo(uid);
			String email = user.getEmail();
			String emails[] = new String[2];
			emails = email.split("@");
			request.setAttribute("user", user);
			request.setAttribute("page", request.getParameter("page"));
			request.setAttribute("std", request.getParameter("std"));
			request.setAttribute("keyword", request.getParameter("keyword"));
			request.setAttribute("email1", emails[0]);
			request.setAttribute("email2", emails[1]);
			forward.setPath("./admin/userMod.jsp");
		}
		return forward;
	}

}
