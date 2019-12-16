package sj.db.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Book;
import sj.db.bean.Record;
import sj.db.dao.AdminDao;
import sj.db.dao.BookDao;
import sj.db.dao.RecordDao;

/**
 * Servlet implementation class BookDeleteServlet
 */
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Book_id = request.getParameter("Book_id");
		
		Record re = new Record();//记录管理员操作
		re.setAdmin_id("1");//默认root登录
		re.setBook_id(Book_id);
		re.setRecord_msg("删除图书");
		
		BookDao dao = new BookDao();
		dao.deleteById(Book_id);
		
		RecordDao rd = new RecordDao();
		rd.setRecord(re);
		request.getRequestDispatcher("/BookListServlet").forward(request, response);
	}

}
