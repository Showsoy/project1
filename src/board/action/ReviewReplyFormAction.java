package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.BoardService;
import svc.OrderService;
import vo.ActionForward;

public class ReviewReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			request.setAttribute("act", "login");
		}else if(!id.equals("admin")) {
			request.setAttribute("act", "user");
		}else {
			String item_code = request.getParameter("item_code");
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			BoardService boardService = new BoardService();
			boolean has_re = boardService.hasReply("review_board", item_code, bnum);
			if(has_re) request.setAttribute("act", "dupl");
			else {
				request.setAttribute("item_code", item_code);
				request.setAttribute("rgroup", bnum);
			}
		}
		forward= new ActionForward("./rereform.jsp",false);	
		return forward;
	}

}