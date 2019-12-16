package manager.service;
import manager.service.UserException;
import manager.dao.UserDao;
import manager.domian.User;

public class UserService {
     private UserDao userDao= new UserDao();
     public void regist(User user) throws UserException {
    	 User u = userDao.findByUserName(user.getusername());
    	 if(u!=null) {
    		 throw new UserException("用户名"+user.getusername()+"已被注册过了");
    	 }
    	 userDao.add(user);
     }
     public User login(User form) throws UserException {
    	 User user = userDao.findByUserName(form.getusername());
    	 if(user==null) {
    		
				throw new UserException("用户名不存在！");
			
    	 }
    	 if(!form.getpassword().equals(user.getpassword())) {
    		 throw new UserException("密码错误！");
    	 }
    	 return user;
     }
}
