package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.BoardBean;

public class BoardDAO {
	Connection conn;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {
		
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public static BoardDAO getInstance() {
		if(boardDAO==null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	public int selectNoticeCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM notice";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public int searchNoticeCount(String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM notice WHERE content LIKE '%"+keyword+"%' OR subject LIKE '%"+keyword+"%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public int selectListCount(String bName) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM "+bName+" WHERE rstep=1";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public int selectListCount(String bName, String item_code) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM "+bName+" WHERE rstep=1 AND item_code=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public int searchListCount(String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cs_board WHERE rstep=1 AND content LIKE '%"+keyword+"%' OR subject LIKE '%"+keyword+"%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public String selectWriter(String bName, int board_num) {
		String user_id = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM "+bName+" WHERE bnum = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return user_id;
	}
	public ArrayList<BoardBean> selectNoticeList(int page){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice ORDER BY bnum DESC LIMIT ?,10";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), null, null, 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						0, rs.getDate("ndate"), rs.getInt("readcount"), 0, 0);
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	public ArrayList<BoardBean> searchNoticeList(String keyword, int page){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE content LIKE '%"+keyword+"%' OR subject LIKE '%"+keyword+"%' ORDER BY bnum DESC LIMIT ?,10";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), null, null, 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						0, rs.getDate("ndate"), rs.getInt("readcount"), 0, 0);
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	public ArrayList<BoardBean> selectCsBoardList(int page){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT bnum, hide, substring(user_id,1,3) as user_id, content, subject, img_path, has_re, cdate, rgroup, rstep FROM cs_board WHERE rstep=1 ORDER BY rgroup DESC, rstep ASC LIMIT ?,10";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), rs.getString("hide"), rs.getString("user_id"), 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						rs.getInt("has_re"), rs.getDate("cdate"), 0, rs.getInt("rgroup"), rs.getInt("rstep"));
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	public ArrayList<BoardBean> searchCsBoardList(String keyword, int page){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT bnum, hide, substring(user_id,1,3) as user_id, content, subject, img_path, has_re, cdate, rgroup, rstep FROM cs_board WHERE rstep=1 AND content LIKE '%"+keyword+"%' OR subject LIKE '%"+keyword+"%' ORDER BY rgroup DESC, rstep ASC LIMIT ?,10";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), rs.getString("hide"), rs.getString("user_id"), 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						rs.getInt("has_re"), rs.getDate("cdate"), 0, rs.getInt("rgroup"), rs.getInt("rstep"));
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	public ArrayList<BoardBean> selectReviewList(int page, String item_code){
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		//10 8 7 7 5 /4 / 2 1  
		String sql1 = "SELECT has_re FROM review_board WHERE item_code = ? AND rstep=1 ORDER BY rgroup DESC, rstep ASC LIMIT ?,5";
		String sql2 = "SELECT bnum, item_code, substring(user_id,1,3) as user_id, content, subject, img_path, has_re, rdate, order_id, rgroup, rstep FROM review_board WHERE item_code = ? ORDER BY rgroup DESC, rstep ASC LIMIT ?,?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*5;
		int before = (page-2)*5;
		int limit = 5;
		
		try {
			if(page>1) {
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, item_code);
				pstmt1.setInt(2, before);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next()) startrow += rs1.getInt(1);
			}
			pstmt2 = conn.prepareStatement(sql1);
			pstmt2.setString(1, item_code);
			pstmt2.setInt(2, startrow);
			rs2 = pstmt2.executeQuery();
			while(rs2.next()) limit += rs2.getInt(1);
			
			pstmt3 = conn.prepareStatement(sql2);
			pstmt3.setString(1, item_code);
			pstmt3.setInt(2, startrow);
			pstmt3.setInt(3, limit);
			rs3 = pstmt3.executeQuery();
			
			while(rs3.next()) {
				board = new BoardBean(rs3.getInt("bnum"), rs3.getString("item_code"), rs3.getString("user_id"), 
						rs3.getString("content"), rs3.getString("subject"), rs3.getString("img_path"), 
						rs3.getInt("has_re"), rs3.getDate("rdate"), rs3.getInt("order_id"), rs3.getInt("rgroup"), rs3.getInt("rstep"));
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs1!=null) close(rs1);
			if(rs2!=null) close(rs2);
			if(rs3!=null) close(rs3);
			if(pstmt1!=null) close(pstmt1);
			if(pstmt2!=null) close(pstmt2);
			if(pstmt3!=null) close(pstmt3);
		}
		
		return articleList;
	}
	public ArrayList<BoardBean> selectQnAList(int page, String item_code, String user_id){
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		//10 8 7 7 5 /4 / 2 1  
		String sql1 = "SELECT has_re FROM qna_board WHERE item_code = ? AND rstep=1 ORDER BY rgroup DESC, rstep ASC LIMIT ?,5";
		String sql2 = "SELECT * FROM qna_board WHERE item_code = ? ORDER BY rgroup DESC, rstep ASC LIMIT ?,?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*5;
		int before = (page-2)*5;
		int limit = 5;
		
		try {
			if(page>1) {
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, item_code);
				pstmt1.setInt(2, before);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next()) startrow += rs1.getInt(1);
			}
			pstmt2 = conn.prepareStatement(sql1);
			pstmt2.setString(1, item_code);
			pstmt2.setInt(2, startrow);
			rs2 = pstmt2.executeQuery();
			while(rs2.next()) limit += rs2.getInt(1);
			
			pstmt3 = conn.prepareStatement(sql2);
			pstmt3.setString(1, item_code);
			pstmt3.setInt(2, startrow);
			pstmt3.setInt(3, limit);
			rs3 = pstmt3.executeQuery();
			
			while(rs3.next()) {
				if(rs3.getInt("rstep")==1&&rs3.getInt(11)>0&&(user_id==null||!user_id.equals(rs3.getString("user_id")))) {
					board = new BoardBean(rs3.getInt(1), rs3.getString(2), rs3.getString(3).substring(0, 3), 
							"숨김 글입니다.", rs3.getString(5), rs3.getString(6), 
							rs3.getInt(7), rs3.getDate(8), 1, rs3.getInt(9), rs3.getInt(10));
				}else {
					board = new BoardBean(rs3.getInt(1), rs3.getString(2), rs3.getString(3).substring(0, 3), 
							rs3.getString(4), rs3.getString(5), rs3.getString(6), 
							rs3.getInt(7), rs3.getDate(8),0, rs3.getInt(9), rs3.getInt(10));
				}
				articleList.add(board);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs1!=null) close(rs1);
			if(rs2!=null) close(rs2);
			if(rs3!=null) close(rs3);
			if(pstmt1!=null) close(pstmt1);
			if(pstmt2!=null) close(pstmt2);
			if(pstmt3!=null) close(pstmt3);
		}
		
		return articleList;
	}
	public BoardBean selectNotice(int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE bnum = ?";
		BoardBean board = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), null, null, 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						0, rs.getDate("ndate"), rs.getInt("readcount"), 0, 0);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	public BoardBean selectCsBoard(int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM cs_board WHERE bnum = ?";
		BoardBean board = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), rs.getString("hide"), rs.getString("user_id"), 
						rs.getString("content"), rs.getString("subject"), rs.getString("img_path"), 
						rs.getInt("has_re"), rs.getDate("cdate"), 0, rs.getInt("rgroup"), rs.getInt("rstep"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	public BoardBean selectArticle(String bName, int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+bName+" WHERE bnum = ?";
		BoardBean board = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getInt(7), rs.getDate(8), 0, rs.getInt(9), rs.getInt(10));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	public BoardBean selectReply(String bName, int rgroup) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+bName+" WHERE rgroup = ? AND rstep=2";
		String date = bName.substring(0,1)+"date";
		BoardBean board = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rgroup);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean(rs.getInt("bnum"), null, rs.getString("user_id"), 
						rs.getString("content"), rs.getString("subject"), null, 
						rs.getInt(7), rs.getDate(date), 0, rgroup, 2);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	public int searchBNum(String bName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MAX(bnum) FROM "+bName;
		int bnum = 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) bnum = rs.getInt(1)+1;
			else bnum = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return bnum;
	}
	public int searchBNum(String bName, String item_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MAX(bnum) FROM "+bName+" WHERE item_code = ?";
		int bnum = 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item_code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) bnum = rs.getInt(1)+1;
			else bnum = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null)close(pstmt);
		}
		return bnum;
	}
	public int writeNotice(BoardBean board) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO notice VALUES(?,?,?,?,?,now())";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(4, board.getImg_path());
			pstmt.setInt(5, board.getReadcount());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	public int writeCsBoard(BoardBean board) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO cs_board VALUES(?,?,?,?,?,?,now(),?,?,?)";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getUser_id());
			pstmt.setString(3, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(4, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, board.getImg_path());
			pstmt.setInt(6, board.getHas_re());
			pstmt.setInt(7, board.getRgroup());
			pstmt.setInt(8, board.getRstep());
			pstmt.setString(9, board.getCode());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	public boolean testReviewBoard(String item_code, String id, int order_id) {
		boolean result = true;
		String sql = "SELECT order_id FROM review_board WHERE item_code = '"+item_code+"' AND user_id = '"+id+"' AND order_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			rs = pstmt.executeQuery();
			if(rs.next()) result = false;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		
		return result;
	}
	public int findOrderId(ArrayList<Integer> list, String item_code, String id) {
		int result = 0;
		String sql = "SELECT order_id FROM review_board WHERE item_code = '"+item_code+"' AND user_id = '"+id+"' AND order_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				pstmt.setInt(1, list.get(i));
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result=0;continue;
				}else {
					result=list.get(i);break;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		
		return result;
	}
	public int writeReview(BoardBean board) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO review_board VALUES(?,?,?,?,?,?,?,now(),?,?,?)";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getCode());
			pstmt.setString(3, board.getUser_id());
			pstmt.setString(4, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, board.getImg_path());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getRgroup());
			pstmt.setInt(9, board.getRstep());
			pstmt.setInt(10, board.getReadcount());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	public int writeQnA(BoardBean board) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO qna_board VALUES(?,?,?,?,?,?,?,now(),?,?,?)";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getCode());
			pstmt.setString(3, board.getUser_id());
			pstmt.setString(4, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, board.getImg_path());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, board.getRgroup());
			pstmt.setInt(9, board.getRstep());
			pstmt.setInt(10, board.getReadcount());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	public int writeArticle1(String id, BoardBean board) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "INSERT INTO qna_board VALUES(?,?,?,?,?,?,?,now(),?,?)";
		int insertCount = 0;
		
		try {
			pstmt = conn.prepareStatement("SELECT max(bnum) from qna_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num = 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getCode());
			pstmt.setString(3, id);
			pstmt.setString(4, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, board.getImg_path());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, num);
			pstmt.setInt(9, 0);
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return insertCount;
	}
	// qna 답변삭제
	public int deleteQnaReplyArticle(String bnum){
		PreparedStatement pstmt = null;
		String sql="DELETE from qna_board WHERE bnum=?";
		int deleteCount = 0;

		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bnum);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("에러: " + ex);	
		}finally{
			close(pstmt);
		}
		
		return deleteCount;
	}
	// qna 답변삭제 후 원글 has_re 0으로 되돌리기
	public int resetQnaReplyArticle(String rgroup) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update qna_board set has_re=0 where rgroup=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rgroup);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	// qna 원글삭제
	public int deleteQnaArticle(String rgroup){
		PreparedStatement pstmt = null;
		String sql="DELETE from qna_board WHERE rgroup=?";
		int deleteCount = 0;

		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, rgroup);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("에러: " + ex);	
		}finally{
			close(pstmt);
		}
		
		return deleteCount;
	}
	

	// 상품문의 글 리스트 개수
		public int qnaListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = conn.prepareStatement("select count(*) from qna_board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	// 상품문의 글 리스트 불러오기
		public ArrayList<BoardBean> qna_list(int page,int limit){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String qna_list_sql="select * from qna_board order by rgroup desc, rstep asc limit ?,5";
			ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
			BoardBean boardBean = null;
			int startrow=(page-1)*5; //읽기 시작할 row 번호..
			
			try{
				pstmt = conn.prepareStatement(qna_list_sql);
				pstmt.setInt(1, startrow);
				rs = pstmt.executeQuery();

				while(rs.next()){
					
					boardBean = new BoardBean(
					rs.getInt("bnum"),
					rs.getInt("rgroup"),
					rs.getInt("rstep"),
					rs.getString("subject"),
					rs.getString("content"),
					rs.getString("user_id"),
					rs.getString("img_path"),
					rs.getInt("has_re"),
					rs.getDate("qdate"));
					
					articleList.add(boardBean);	
				}
			}catch(Exception ex){
				System.out.println("에러 : " + ex);
			}finally{
				close(rs);
				close(pstmt);
			}

			return articleList;
		}
				

		public int updateReadCount(int board_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE notice SET readcount=readcount+1 WHERE bnum=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	public int replyCsBoard(BoardBean board) {
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = "";
		int insertCount = 0;
		int rgroup = board.getRgroup();
		
		try {
			sql = "UPDATE cs_board SET has_re = has_re+1 WHERE rgroup = ?";
			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, rgroup);
			int updateCount = pstmt1.executeUpdate();
			
			if(updateCount>0) {
				commit(conn);
			}
			
			sql = "INSERT INTO cs_board VALUES(?,'admin',?,?,null,0,now(),?,?,?)";
			
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, board.getBoard_num());
			pstmt2.setString(2, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt2.setString(3, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt2.setInt(4, rgroup);
			pstmt2.setInt(5, 2);
			pstmt2.setString(6, board.getCode());
			insertCount = pstmt2.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardReply에러 : "+e);
		}finally {
			if(pstmt1!=null) close(pstmt1);
			if(pstmt2!=null) close(pstmt2);
		}
		return insertCount;
	}
	public int replyArticle(String bName, BoardBean board) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
		int num = 0;
		int rgroup = board.getRgroup();
		int rstep = board.getRstep();
		
		try {
			sql = "UPDATE "+bName+" SET has_re = has_re+1 WHERE rgroup = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rgroup);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(conn);
			}
			
			pstmt = conn.prepareStatement("SELECT max(bnum) from qna_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			
			rstep += 1;
			sql = "INSERT INTO "+bName+" VALUES(?,?,'관리자',?,?,?,0,now(),?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getCode());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getImg_path());
			pstmt.setInt(6, rgroup);
			pstmt.setInt(7, rstep);
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	public int replyReview(BoardBean board) {
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = "";
		int insertCount = 0;
		int rgroup = board.getRgroup();
		
		try {
			sql = "UPDATE review_board SET has_re = has_re+1 WHERE item_code = ? AND rgroup = ?";
			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, board.getCode());
			pstmt1.setInt(2, rgroup);
			int updateCount = pstmt1.executeUpdate();
			
			if(updateCount>0) {
				commit(conn);
			}
			
			sql = "INSERT INTO review_board VALUES(?,?,'관리자',?,?,null,0,now(),?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, board.getBoard_num());
			pstmt2.setString(2, board.getCode());
			pstmt2.setString(3, board.getContent());
			pstmt2.setString(4, board.getSubject());
			pstmt2.setInt(5, rgroup);
			pstmt2.setInt(6, board.getRstep());
			pstmt2.setInt(7, board.getReadcount());
			insertCount = pstmt2.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt1!=null) close(pstmt1);
			if(pstmt2!=null) close(pstmt2);
		}
		return insertCount;
	}
	public int replyQnA(BoardBean board) {
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
		int rgroup = board.getRgroup();
		int qhide = 0;
		
		try {
			sql = "UPDATE qna_board SET has_re = has_re+1 WHERE item_code = ? AND rgroup = ?";
			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, board.getCode());
			pstmt1.setInt(2, rgroup);
			int updateCount = pstmt1.executeUpdate();
			
			if(updateCount>0) {
				commit(conn);
			}
			sql = "SELECT qhide FROM qna_board WHERE item_code = ? AND rgroup = ?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setString(1, board.getCode());
			pstmt2.setInt(2, rgroup);
			rs = pstmt2.executeQuery();
			if(rs.next()) qhide = rs.getInt(1);
			
			sql = "INSERT INTO qna_board VALUES(?,?,'관리자',?,?,null,0,now(),?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, board.getBoard_num());
			pstmt3.setString(2, board.getCode());
			pstmt3.setString(3, board.getContent());
			pstmt3.setString(4, board.getSubject());
			pstmt3.setInt(5, rgroup);
			pstmt3.setInt(6, board.getRstep());
			pstmt3.setInt(7, qhide);
			insertCount = pstmt3.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt1!=null) close(pstmt1);
			if(pstmt2!=null) close(pstmt2);
			if(pstmt3!=null) close(pstmt3);
		}
		return insertCount;
	}
	public int modifyNotice(BoardBean board) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE notice SET content=?, subject=?, img_path=? WHERE bnum = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, board.getImg_path());
			pstmt.setInt(4, board.getBoard_num());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	public int removeNotice(int board_num) {
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		String sql = "DELETE FROM notice WHERE bnum=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	public int removeCsBoard(int board_num) {
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		String sql = "DELETE FROM cs_board WHERE rgroup=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	public int removeArticle(String bName, int board_num, String item_code) {
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		String sql = "DELETE FROM "+bName+" WHERE rgroup=? AND item_code=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, item_code);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	public boolean hasReply(String bName, String item_code, int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT has_re FROM "+bName+" WHERE item_code=? AND bnum=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, item_code);
			pstmt.setInt(2, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) if(rs.getInt(1)>0) result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) close(rs);
			if(pstmt!=null) close(pstmt);
		}
		
		return result;
	}
}
