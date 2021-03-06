package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.BoardService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		String keyword = "";
		int page = 1;
		int limit = 10;
		int limitPage = 10;
		int listCount = 10;
		
		if(request.getParameter("page")!=null&&!request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardService boardService = new BoardService();
		
		if(request.getParameter("keyword")!=null) {
			keyword = request.getParameter("keyword");
			listCount = boardService.searchNoticeCount(keyword);
			boardList = boardService.searchNoticedList(keyword, page);
			request.setAttribute("keyword", keyword);
		}else {
			listCount = boardService.selectNoticeCount();
			boardList = boardService.selectNoticeList(page);
		}
		
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/limitPage+0.9))-1) *limitPage +1;
		int endPage = startPage+limitPage-1;
		if(endPage>maxPage) endPage = maxPage;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		forward= new ActionForward("../common/notice.jsp",false);
		return forward;
	}

}
