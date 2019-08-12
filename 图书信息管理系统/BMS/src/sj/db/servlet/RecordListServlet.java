package sj.db.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sj.db.bean.Book;
import sj.db.bean.Record;
import sj.db.dao.BookDao;
import sj.db.dao.RecordDao;

/**
 * Servlet implementation class RecordListServlet
 */
public class RecordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordListServlet() {
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
		
		RecordDao dao = new RecordDao();
		List<Record> record = dao.getRecord();
		request.setAttribute("record", record);
		request.getRequestDispatcher("/jsps/users/recordList.jsp")
			.forward(request, response);		
	}

}
