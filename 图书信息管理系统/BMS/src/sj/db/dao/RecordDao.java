package sj.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sj.db.bean.Book;
import sj.db.bean.Record;
import sj.db.util.JDBCUtils;

public class RecordDao {
	//存储操作记录
	public void setRecord(Record r) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into record(Record_id,Admin_id,Book_id,Record_msg)"
					+ "values("
					+ null
					+ ",'"
					+ r.getAdmin_id()
					+ "','"
					+ r.getBook_id()
					+ "','"
					+ r.getRecord_msg()
					+ "')";
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询所有操作记录
	public List<Record> getRecord(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Record> reList = new ArrayList<Record>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from record";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Record record = new Record();
				record.setRecord_id(rs.getString("Record_id"));
				record.setAdmin_id(rs.getString("Admin_id"));
				record.setBook_id(rs.getString("Book_id"));
				record.setRecord_msg(rs.getString("Record_msg"));
				reList.add(record);
			}
			return reList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}
}
