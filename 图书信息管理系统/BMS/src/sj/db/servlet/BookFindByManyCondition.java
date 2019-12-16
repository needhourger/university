package sj.db.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Book;
import sj.db.dao.BookDao;

/**
 * Servlet implementation class BookFindeByManyCondition
 */
public class BookFindByManyCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookFindByManyCondition() {
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
		
		String Book_id = request.getParameter("Book_id");
		String Book_name = request.getParameter("Book_name");
		String Book_author = request.getParameter("Book_author");
		String Book_type = request.getParameter("Book_type");
		String Book_isbn = request.getParameter("Book_isbn");
		String Book_minPrice = request.getParameter("Book_minPrice");
		String Book_maxPrice = request.getParameter("Book_maxPrice");
		
		BookDao dao = new BookDao();
		List<Book> book = dao.findByCondition(Book_id, Book_name,
				Book_author, Book_type, Book_isbn, Book_minPrice, Book_maxPrice);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/jsps/books/bookList.jsp")
			.forward(request, response);
	}

}
