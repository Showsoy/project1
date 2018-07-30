package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CartService;
import vo.ActionForward;

public class CartRemoveAction implements action.Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String[] kindArray = request.getParameterValues("icheck");
		CartService cartService = new CartService();
		cartService.cartRemove(request, kindArray);
		ActionForward forward = new ActionForward("./cartList.im",true);
		return forward;

	}

}
