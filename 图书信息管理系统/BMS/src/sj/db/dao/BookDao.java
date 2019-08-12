package sj.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import sj.db.bean.Book;
import sj.db.util.JDBCUtils;

public class BookDao {
	//查询所有图书
	public List<Book> getBook(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Book";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("Book_id"));
				book.setBook_name(rs.getString("Book_name"));
				book.setBook_author(rs.getString("Book_author"));
				book.setBook_price(rs.getDouble("Book_price"));
				book.setBook_type(rs.getString("Book_type"));
				book.setBook_isbn(rs.getString("Book_isbn"));
				book.setBook_publisher(rs.getString("Book_publisher"));
				book.setBook_allnum(rs.getInt("Book_allnum"));
				book.setBook_surplus(rs.getInt("Book_surplus"));
				bookList.add(book);
			}
			return bookList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	//通过id查询图书
	public List<Book> findById(String Book_id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from book where Book_id='" + Book_id + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("Book_id"));
				book.setBook_name(rs.getString("Book_name"));
				book.setBook_author(rs.getString("Book_author"));
				book.setBook_price(rs.getDouble("Book_price"));
				book.setBook_type(rs.getString("Book_type"));
				book.setBook_isbn(rs.getString("Book_isbn"));
				book.setBook_publisher(rs.getString("Book_publisher"));
				book.setBook_allnum(rs.getInt("Book_allnum"));
				book.setBook_surplus(rs.getInt("Book_surplus"));
				bookList.add(book);
			}
			return bookList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(stmt, conn);
		}
		return null;
	}
	
	//按条件查询图书
	public List<Book> findByCondition(String Book_id, String Book_name,
			String Book_author, String Book_type, String Book_isbn,
			String Book_minPrice, String Book_maxPrice){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<Object>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Book where 1=1";
			QueryRunner runner = new QueryRunner();
			if(Book_id != null && Book_id.trim().length() > 0) {
				sql += " and Book_id=?";
				list.add(Book_id);
			}
			if(Book_name != null && Book_name.trim().length() > 0) {
				sql += " and Book_name =?";
				list.add(Book_name);
			}
			if(Book_author != null && Book_author.trim().length() > 0) {
				sql += " and Book_author =?";
				list.add(Book_author);
			}
			if(Book_type != null && Book_type.trim().length() > 0) {
				sql += " and Book_type =?";
				list.add(Book_type);
			}
			if(Book_isbn != null && Book_isbn.trim().length() > 0) {
				sql += " and Book_isbn =?";
				list.add(Book_isbn);
			}
			if(Book_minPrice != null && Book_minPrice.trim().length() > 0
					&& Book_maxPrice != null && Book_maxPrice.trim().length() > 0) {
				sql += " and Book_price between ? and ?";
				list.add(Book_minPrice);
				list.add(Book_maxPrice);
			}
//			System.out.println(sql);
//			System.out.println(list.toString());
			return runner.query(conn, sql, new BeanListHandler<Book>(Book.class), list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
	
	//删除图书
	public void deleteById(String Book_id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "delete from book where Book_id='" + Book_id + "'";
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	
	//添加图书
	public boolean addBook(Book book) {
		String sql = "insert into book values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			QueryRunner runner = new QueryRunner();
			int num = runner.update(conn, sql, book.getBook_id(), book.getBook_name(),
					book.getBook_author(), book.getBook_price(), book.getBook_type(),
					book.getBook_isbn(), book.getBook_publisher(), book.getBook_allnum(),
					book.getBook_surplus());
			if(num > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(stmt, conn);
		}
		return false;
	}
	
	//编辑图书
	public void editBook(Book book) {
		Connection conn = null;
		Statement stmt = null;
		List<Object> obj = new ArrayList<Object>();

		obj.add(book.getBook_name());
		obj.add(book.getBook_author());
		obj.add(book.getBook_price());
		obj.add(book.getBook_type());
		obj.add(book.getBook_isbn());
		obj.add(book.getBook_publisher());
		obj.add(book.getBook_allnum());
		obj.add(book.getBook_surplus());
		obj.add(book.getBook_id());
		
		String sql = "update book set" +
				" Book_name='" + book.getBook_name() + "',"
				+ " Book_author='" + book.getBook_author() + "',"
				+ " Book_price='"+ book.getBook_price() + "',"
				+ " Book_type='" + book.getBook_type() + "',"
				+ " Book_isbn='"+ book.getBook_isbn() +"',"
				+ " Book_publisher='" + book.getBook_publisher() + "',"
				+ " Book_allnum='" + book.getBook_allnum() + "',"
				+ " Book_surplus='" + book.getBook_surplus() + "'" 
				+ " where Book_id='" + book.getBook_id() + "'";
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(stmt, conn);
		}
	}
	
	//判断图书编号是否重复
	public boolean judgeBookId(String Book_id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select Book_id from book where Book_id='" + Book_id + "'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}
}
