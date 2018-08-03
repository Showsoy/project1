package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int board_num = Integer.parseInt(request.getParameter("bnum"));
		BoardService boardService = new BoardService();
		BoardBean board = boardService.selectNotice(board_num);
		request.setAttribute("board", board);
		if (id!=null&&!id.equals("admin")) {
			
		}else {
			boardService.updateReadCount(board_num);
		}
		forward = new ActionForward("./noView.jsp", false);
		return forward;
	}

}
