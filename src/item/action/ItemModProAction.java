package item.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ItemService;
import vo.ActionForward;
import vo.ItemBean;

public class ItemModProAction implements action.Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='../member/memberLogin.us?turn=ok';");
			out.println("</script>");
		}else if(!id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='../main.im';");
			out.println("</script>");
		}else {
			ItemService itemService = new ItemService();
			String realFolder = "";
			String saveFolder = "/images";
			String encType = "UTF-8";
			int fileSize = 5*1024*1024;
			
			ServletContext context = request.getServletContext();
			realFolder = context.getRealPath(saveFolder);
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, encType, new DefaultFileRenamePolicy());
			
			String image = multi.getFilesystemName("img_path");
			String item_code = multi.getParameter("item_code");
			
			if(multi.getFilesystemName("img_path")==null) {
				image = multi.getParameter("oldImage");
			}
			
			ItemBean item = new ItemBean(
					item_code,
					multi.getParameter("item_name"),
					Integer.parseInt(multi.getParameter("price")),
					multi.getParameter("origin"),
					multi.getParameter("category"),
					image,
					Integer.parseInt(multi.getParameter("sale")),
					multi.getParameter("content"),
					0,0);
			
			boolean isModifySuccess = itemService.updateItem(item, multi.getParameter("old_code"));
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				forward= new ActionForward("./itemView.im?item_code="+item_code+"&page="+multi.getParameter("page"),true);
			}
		}
		
		return forward;
	}

}
