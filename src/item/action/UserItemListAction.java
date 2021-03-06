package item.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.ItemService;
import vo.ActionForward;
import vo.ItemViewBean;
import vo.PageInfo;

public class UserItemListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		
		ArrayList<ItemViewBean> itemList = new ArrayList<ItemViewBean>();
		int page = 1;
		int limit = 9;
		int limitPage = 10;
		int listCount = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String category = request.getParameter("category");
		String standard = request.getParameter("std");
		
		if(standard==null||standard.equals("new"))	standard = "vdate";
		else if(standard.equals("high"))  standard = "price";
		else if(standard.equals("pc"))  standard = "purchase";
		else if(standard.equals("rc"))  standard = "readcount";
		
		ItemService itemService = new ItemService();
		listCount = itemService.itemListCountIn(category);
		itemList = itemService.userItemList(page, category, standard);
		
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
		
		//Cookie load
		Cookie[] cookieArray = request.getCookies();
		Map<String,String> todayImageMap = new HashMap<String,String>();
		//길이가 8이면 7부터 -> 7 6 5 4 3, 2전까지 2는 길이 - 6
		int count = 0;
		if (cookieArray != null) {
			for (int i = cookieArray.length - 1; i >= 0; i--) {
				if (count == 5)
					break;
				if (cookieArray[i].getName().startsWith("today")) {
					todayImageMap.put(cookieArray[i].getName().replaceAll("today", ""), cookieArray[i].getValue());
					count++;
				}
			}
		}
		request.setAttribute("todayImageMap", todayImageMap);
		
		request.setAttribute("category", category);
		request.setAttribute("std", standard);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("itemList", itemList);
		forward= new ActionForward();
		forward.setPath("item/uitemList.jsp");
		return forward;
	}

}
