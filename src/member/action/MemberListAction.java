package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.Action;
import svc.UserService;
import vo.ActionForward;
import vo.PageInfo;
import vo.UserViewBean;

public class MemberListAction implements Action{
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
		}else {
		   	ArrayList<UserViewBean> userList = new ArrayList<UserViewBean>();
		   	UserService userService = new UserService();
			int page= request.getParameter("page")==null ? 1: Integer.parseInt(request.getParameter("page"));
			int limit=10;
			int listCount = 0;
			String keyword = request.getParameter("keyword")==null ? "all" : request.getParameter("keyword");
			String std = request.getParameter("std")==null ? "edate" : request.getParameter("std");
			
			if(std.equals("user_id")){
				listCount = userService.getSearchList(keyword);
				userList = userService.searchId(page, keyword);
			}else {
				if(keyword.equals("all")) {
					listCount = userService.getListCount();
					userList = userService.userList(page, std);
				}else {
					listCount = userService.gradeListCount(keyword);
					userList = userService.getGradeList(page, keyword, std);
				}
			}
			
			request.setAttribute("std", std);
			request.setAttribute("keyword", keyword);
			
			int maxPage=(int)((double)listCount/limit+0.95);
		   	int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		    int endPage = startPage+10-1;
		    
			if (endPage > maxPage) endPage = maxPage;

			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
		
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("userList", userList);
			forward = new ActionForward();
			forward.setPath("/admin/userList.jsp");
		}
		return forward;
	}
}

