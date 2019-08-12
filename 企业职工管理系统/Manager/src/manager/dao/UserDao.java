package manager.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import manager.domian.User;


public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	public void add(User u) {
		
		try {
			String sql = "insert into user values(?,?)";
			Object[] params= {u.getusername(),u.getpassword()};
			qr.update(sql, params);
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
	}
	
	public User findByUserName(String name) {
		User u=null;
		try {
			String sql = "select * from user where UserName=?";
			Object param = name;

			u=qr.query(sql, new BeanHandler<User>(User.class),param);
			
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return null;
		
	}
}
