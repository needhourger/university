<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="manager.domian.Employee"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
   Employee em= (Employee)request.getAttribute("Employee");
%>
<body>
<h1 align="center" style="color: red;">修改人员档案</h1>
<table bordercolor="gray" border="0" cellspacing="0" cellpadding="10" >
    <tr>
       <td>员工编号：</td>
       <td><input type="text" name="employeeID" value=<%=em.getEmployeeID() %>>*</td>
       <td>姓名：</td>
       <td><input type="text" name="userName" value=<%=em.getUserName() %>>*</td>
    </tr>
    <tr>
       <td>性别</td> 
       <td>
         <select name="sex">
            <option selected="selected">-请选择-</option>
            <option value="男">男</option>
            <option value="女">女</option>
         </select>*
       </td>
       <td>所属部门：</td>
       <td>
           <select name="branch">
              <option selected="selected">-请选择-</option>
              <option value="总经理办公室">总经理办公室</option>
              <option value="行政部">行政部</option>
              <option value="人力资源部">人力资源部</option>
              <option value="财务部">财务部</option>
              <option value="生产技术部">生产技术部</option>
              <option value="计划营销部">计划营销部</option>
              <option value="安全监察部">安全监察部</option>
              <option value="营运部">营运部</option>
           </select>*
       </td> 
    </tr>
    
    <tr>
       <td>出生日期：</td>
       <td><input type="date" name="birthday">*</td>
       <td>籍贯：</td>
       <td><input type="text" name="nativePlace">*</td>
    </tr>
    <tr>
        <td>婚姻状况：</td>
        <td>
            <select name="marriage">
              <option selected="selected">-请选择-</option>
              <option value="未婚">未婚</option>
              <option value="已婚">已婚</option>
            </select>*
        </td>
        <td>身份证号：</td>
        <td><input type="text" name="identifyID"/>*</td>
    </tr>
    <tr>
        <td>政治面貌：</td>
        <td>
           <select name="politics">
              <option selected="selected">-请选择-</option>
              <option value="党员">党员</option>
              <option value="团员">团员</option>
              <option value="群众">群众</option>
           </select>*
        </td>
        <td>民族：</td>
        <td><input type="text" name="folk"/>*</td>
    </tr>
    <tr>
        <td>学历：</td>
        <td>
            <select name="education">
              <option selected="selected">-请选择-</option>
              <option value="本科">本科</option>
              <option value="研究生">研究生</option>
              <option value="博士">博士</option>
           </select>
        </td>
        <td>专业：</td>
        <td><input type="text" name="department"/></td>
    </tr>
    <tr>
       <td>毕业日期：</td>
       <td><input type="date" name="graduateDate"></td>
       <td>毕业院校：</td>
       <td><input type="text" name="university"></td>
    </tr>
    <tr>
       <td>公积金账号：</td>
       <td><input type="text" name="accumulateID">*</td>
       <td>行政级别：</td>
       <td>
           <select name="adminstrationLevel">
              <option selected="selected">-请选择-</option>
              <option value="总裁级">总裁级</option>
              <option value="总监级">总监级</option>
              <option value="经理级">经理级</option>
              <option value="科长级">科长级</option>
              <option value="专员级">专员级</option>
              <option value="拉长级">拉长级</option>
              <option value="组长级">组长级</option>
              <option value="员工级">员工级</option>
           </select>*
       </td>
    </tr>
    <tr>
       <td>职务：</td>
       <td>
         <select name="duty">
             <option selected="selected">-请选择-</option>
             <option value="经理">经理</option>
             <option value="主管">主管</option>
             <option value="员工">员工</option>
         </select>*
       </td>
       
       <td>职称：</td>
       <td>
           <select name="position">
               <option selected="selected">-请选择-</option>
               <option value="初级工程师">初级工程师</option>
               <option value="中级工程师">中级工程师</option>
               <option value="高级工程师">高级工程师</option>
           </select>*
       </td>
    </tr>
    <tr>
       <td>外语语种：</td>
       <td>
          <select name="foreignLanuage">
               <option selected="selected">-请选择-</option>
               <option value="英语">英语</option>
               <option value="韩语">韩语</option>
               <option value="日语">日语</option>
               <option value="韩语">韩语</option>
               <option value="德语">德语</option>
           </select>
       </td>
       <td>外语水平：</td>
       <td>
          <select name="fLevel">
               <option selected="selected">-请选择-</option>
               <option>一</option>
               <option>二</option>
               <option>三</option>
               <option>四</option>
               <option>五</option>
           </select>
       </td>
    </tr>
     <tr>
       <td>计算机水平：</td>
       <td>
           <select name="computerLevel">
               <option selected="selected">-请选择-</option>
               <option value="一级">一级</option>
               <option value="二级">二级</option>
               <option value="三级">三级</option>
               <option value="四级">四级</option>
           </select>
       </td>
       <td>联系电话：</td>
       <td><input type="text" name="phone"></td>
    </tr>
    <tr>
       <td>手机号码：</td>
       <td><input type="text" name="mobilePhone"></td>
       <td>家庭住址：</td>
       <td><input type="text" name="address"></td>
    </tr>
    <tr>
       <td>在职情况</td>
       <td>
           <select name="incumbency">
               <option selected="selected">-请选择-</option>
               <option value="在职">在职</option>
               <option value="离职">离职</option>
           </select>*
       </td>
       <td>用工形式</td>
       <td>
         <select name="incumbencyType">
               <option selected="selected">-请选择-</option>
               <option value="正式工">正式工</option>
               <option value="兼职工">兼职工</option>
               <option value="临时工">临时工</option>
           </select>*
       </td>
    </tr>
    <tr>
       <td>个人简历：</td>
       <td colspan="3"><textarea rows="10" cols="50" name="resume"></textarea></td>
    </tr>
</table>

</body>
</html>