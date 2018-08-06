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
		String sql = "SELECT * FROM cs_board WHERE rstep=1 ORDER BY rgroup DESC, rstep ASC LIMIT ?,10";
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
		String sql = "SELECT * FROM cs_board WHERE rstep=1 AND content LIKE '%"+keyword+"%' OR subject LIKE '%"+keyword+"%' ORDER BY rgroup DESC, rstep ASC LIMIT ?,10";
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
	public ArrayList<BoardBean> selectArticleList(String bName, int page){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+bName+" WHERE rstep=1 ORDER BY rgroup DESC, rstep ASC LIMIT ?,5";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getInt(7), rs.getDate(8), 0, rs.getInt(9), rs.getInt(10));
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
			close(rs);
			close(pstmt);
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
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getSubject());
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
	public int writeArticle(String id, BoardBean board) {
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
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	// 상품문의 글 리스트
		public int qnaListCount() {

			int listCount= 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try{

				pstmt=conn.prepareStatement("select count(*) from qna_board");
				rs = pstmt.executeQuery();

				if(rs.next()){
					listCount=rs.getInt(1);
				}
			}catch(Exception ex){
				System.out.println("에러: " + ex);			
			}finally{
				close(rs);
				close(pstmt);
			}

			return listCount;

		}
	
		// 상품문의 글 리스트_2
				public ArrayList<BoardBean> qna_list(int page,int limit){
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					Connection conn = getConnection();
					String qna_list_sql="select * from qna_board order by user_id asc limit ?,5";
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
							rs.getString("subject"),
							rs.getString("user_id"),
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
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
		int rgroup = board.getRgroup();
		
		try {
			sql = "UPDATE cs_board SET has_re = has_re+1 WHERE rgroup = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rgroup);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(conn);
			}
			
			sql = "INSERT INTO cs_board VALUES(?,'admin',?,?,null,0,now(),?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getSubject());
			pstmt.setInt(4, rgroup);
			pstmt.setInt(5, 2);
			pstmt.setString(6, board.getCode());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardReply에러 : "+e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	public int replyArticle(String bName, BoardBean board) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
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
			
			rstep += 1;
			sql = "INSERT INTO "+bName+" VALUES(null,?,'관리자',?,?,null,0,now(),?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_num());
			pstmt.setString(2, board.getCode());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getSubject());
			pstmt.setInt(5, rgroup);
			pstmt.setInt(6, rstep);
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	public int modifyNotice(BoardBean board) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE notice SET content=?, subject=? WHERE bnum = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, board.getSubject().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setInt(3, board.getBoard_num());
			
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
	public int removeArticle(String bName, int board_num) {
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		String sql = "DELETE FROM "+bName+" WHERE rgroup=?";
		
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
}
