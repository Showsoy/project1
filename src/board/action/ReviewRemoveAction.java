package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.BoardService;
import vo.ActionForward;

public class ReviewRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./member/memberLogin.us?turn=ok';");
			out.println("</script>");
		} else {
			String item_code = request.getParameter("item_code");
			int page = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
			BoardService boardService = new BoardService();
			String nums[];
			String codes[];
			int board_num;
			int deleteCount = 0;
			
			//여러개 삭제 미구현
			if (request.getParameter("bnum") == null) {
				if (!id.equals("admin")) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('권한이 없습니다.');");
					out.println("history.back();");
					out.println("</script>");
				} else {
				nums = request.getParameterValues("icheck");
				codes = request.getParameterValues("codes");
				for (int i = 0; i < nums.length; i++) {
					deleteCount = boardService.removeArticle("review_board", Integer.parseInt(nums[i]), codes[i]);
				}
			}
			} else {
				board_num = Integer.parseInt(request.getParameter("bnum"));
				String writer = boardService.selectWriter("review_board", board_num, item_code);
				if (id.equals("admin") || id.equals(writer)) {
					deleteCount = boardService.removeArticle("review_board", board_num, item_code);
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('권한이 없습니다.');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
	
			if (deleteCount == 0) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				if(request.getParameter("r_page")==null) {
					String path = "./reList.bo?page="+page;
					path = (request.getParameter("std") == null) ? path : path + "&std=" + request.getParameter("std");
					path = (request.getParameter("keyword") == null) ? path
							: path + "&keyword=" + request.getParameter("keyword");
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("location.href=encodeURI('"+path+"');");
					out.println("</script>");
				}else {
					String r_page = request.getParameter("r_page");
					String q_page = request.getParameter("q_page");
					forward = new ActionForward("./uitemView.im?item_code="+item_code+"&page="+page+"&r_page="+r_page+"&q_page="+q_page, true);
				}
			}
		}
		return forward;
	}

}
