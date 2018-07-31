package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.CartService;
import vo.ActionForward;

public class CartChangeQtyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String item_code = request.getParameter("item_code");
		System.out.println(request.getParameter(item_code+"_qty"));
		int qty = Integer.parseInt(request.getParameter(item_code+"_qty"));
		System.out.println(qty+" "+item_code);
		CartService cartService = new CartService();
		cartService.changeCartQty(request, item_code, qty);
		ActionForward forward = new ActionForward("./cartList.ct",true);
		return forward;
	}

}
