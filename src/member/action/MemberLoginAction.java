package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;

public class MemberLoginAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		ActionForward forward = null;
		HttpSession session=request.getSession();
		String id = request.getParameter("userID");
		String turn = request.getParameter("turn");
		String returnURI = request.getParameter("returnURI");
		UserService userService = new UserService();
		int token = userService.login(id, request.getParameter("userPass"));
		if (token > 0) {
			session.setAttribute("id", id);
			if (turn != null && turn.equals("ok")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("history.go(-2);");
				out.println("</script>");
			} else if (returnURI != null) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("location.href='" + returnURI + "';");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("../main.im");
			}
		} else if (token == 0) {
			request.setAttribute("type", "passError");
			request.setAttribute("returnURI", returnURI);
			forward = new ActionForward("login.jsp", false);

		} else if (token < 0) {
			request.setAttribute("type", "nullID");
			request.setAttribute("returnURI", returnURI);
			forward = new ActionForward("login.jsp", false);
		}

   		return forward;
	}
		
}
	
