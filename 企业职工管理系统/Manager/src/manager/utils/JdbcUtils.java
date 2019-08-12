package manager.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
    private static ComboPooledDataSource dataSource =
    		new ComboPooledDataSource();
    //事务专用连接
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    /**
     * 使用连接池返回一个连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection()throws SQLException{
    	Connection con = tl.get();
    	if(con!=null) return con;
    	return dataSource.getConnection();
    }
    /**
     * 返回连接池对象
     * @return
     */
    public static DataSource getDataSource() {
    	
    	return dataSource;
    }
    
    public static void beginTransaction() throws SQLException {
    	Connection con = tl.get();
    	if(con!=null) throw new SQLException("已经开启了事务，就不要重复开启了");
    	con = getConnection();
    	con.setAutoCommit(false);
    	tl.set(con);
    }
    public static void commitTransaction() throws SQLException {
    	Connection con = tl.get();
    	if(con==null) throw new SQLException("还没有开启事务，不能提交");
    	con.commit();
    	con.close();
    	tl.remove();
    }
    public static void rollbackTransaction() throws SQLException {
    	Connection con = tl.get();
    	if(con==null) throw new SQLException("还没有开启事务，不能回滚");
    	con.rollback();
    	con.close();
    	tl.remove();
    }
    public static void releaseConnection(Connection connect) throws SQLException {
    	Connection con = tl.get();
    	if(con==null) connect.close();
    	if(con!=connect) connect.close();
    
    }
}
