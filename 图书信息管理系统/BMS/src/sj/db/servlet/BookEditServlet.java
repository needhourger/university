package sj.db.servlet;

import java.io.IOException;
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
 * Servlet implementation class BookEidtServlet
 */
public class BookEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookEditServlet() {
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
		String Book_name = request.getParameter("Book_name");
		String Book_author = request.getParameter("Book_author");
		double Book_price = Double.parseDouble(request.getParameter("Book_price"));
		String Book_type = request.getParameter("Book_type");
		String Book_isbn = request.getParameter("Book_isbn");
		String Book_publisher = request.getParameter("Book_publisher");
		int Book_allnum = Integer.parseInt(request.getParameter("Book_allnum"));
		int Book_surplus = Integer.parseInt(request.getParameter("Book_surplus"));
		
		Book book = new Book();
		book.setBook_id(Book_id);
		book.setBook_name(Book_name);
		book.setBook_author(Book_author);
		book.setBook_price(Book_price);
		book.setBook_type(Book_type);
		book.setBook_isbn(Book_isbn);
		book.setBook_publisher(Book_publisher);
		book.setBook_allnum(Book_allnum);
		book.setBook_surplus(Book_surplus);
		
		Record re = new Record();//记录管理员操作
		re.setAdmin_id("1");//默认root登录
		re.setBook_id(Book_id);
		re.setRecord_msg("编辑图书");
		
		try {
			BookDao dao = new BookDao();
			dao.editBook(book);
			RecordDao rd = new RecordDao();
			rd.setRecord(re);
			request.getRequestDispatcher("/BookListServlet")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
