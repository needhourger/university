package sj.db.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Book;
import sj.db.bean.Record;
import sj.db.dao.AdminDao;
import sj.db.dao.BookDao;
import sj.db.dao.RecordDao;
import sj.db.util.GenerateCode;

/**
 * Servlet implementation class BookAddServlet
 */
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAddServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String Book_id = GenerateCode.getBook_id();
		BookDao dao = new BookDao();
		Boolean flag = dao.judgeBookId(Book_id);
		while(flag) {
			Book_id = GenerateCode.getBook_id();
			flag = dao.judgeBookId(Book_id);
		}
		
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
		re.setRecord_msg("添加图书");
		
		if(dao.addBook(book)) {
			RecordDao rd = new RecordDao();
			rd.setRecord(re);
			request.getRequestDispatcher("/jsps/books/bookList.jsp")
				.forward(request, response);
		} else {
			out.print("添加失败");
		}
	}

}
