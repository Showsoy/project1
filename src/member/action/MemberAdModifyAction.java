package member.action;

import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;
import vo.UserBean;
import vo.Util;

public class MemberAdModifyAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String uid = (String) request.getParameter("uid");

		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./member/memberLogin.us?turn=ok';");
			out.println("</script>");
		} else if (!id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='../main.im';");
			out.println("</script>");
		} else {
			UserService userService = new UserService();
			Util util = new Util();
			Date birth = util.transformDate(request.getParameter("userBirth"));
			String email = (request.getParameter("userEmailId") + "@" + request.getParameter("userEmailAd"));
			UserBean user = new UserBean(uid, request.getParameter("userName"), request.getParameter("userPhone"),
					birth, request.getParameter("userGen"), request.getParameter("userAddr1"),
					request.getParameter("userAddr2"), request.getParameter("userAddr3"), email, 
					request.getParameter("userGrade"), null);
			boolean isRegistSuccess = userService.modifyUsers(user);

			if (isRegistSuccess) {
				String path = "./userView.us?page="+request.getParameter("page")+"&user_id="+uid+"&std="+request.getParameter("std")+"&keyword="+request.getParameter("keyword");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정되었습니다.');");
				out.println("location.href='"+path+"';");
				out.println("</script>");
			} else {
				try {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정실패');");
					out.println("history.back();");
					out.println("</script>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return forward;
	}

}
