var admin_id=getCookie("admin_id");
var boo=true;
if(admin_id==null){
	boo=false;
	window.location.href="login.html";
}

var admin;

$(function(){
	if(boo){
		$.ajax({
			url:"http://localhost:8080/JsCUCC/findByIdAdmin.do",
			type:"post",
			data:{"id":admin_id},
			datatype:"json",
			success:function(result){
				admin = result.data;
				if(admin.leve=='S'){
					var role='<i></i><a href="#" id="role_list">角色管理</a>';
					var add='<i></i><a href="#" id="add_admin1">添加管理</a>';
					$("#role").append(role);
					$("#add-admin").append(add);
				}
			},
			error:function(){
				alert("系统错误");
			}
		});
	}
	
	//退出登陆
	$("#exit").click(function(){
		delCookie("admin_id");
		window.location.href="login.html";
	});
	

	//角色管理，查看所有管理账号
	$(document).on('click','#role_list',function(){
		$("#infoh2").html("");
		$("#infoh2").html("管理员账号信息");
		$("#tb").html("");
		$("#th").html("");
		$("#fy").html("");
		$("#d").html("");
		$.ajax({
			url:"http://localhost:8080/JsCUCC/findAdminRole.do",
			type:"post",
			data:{"":""},
			dataType:"json",
			success:function(result){
				var adminList = result.data;
				var th="<tr><th>Id</th>" +
						"<th>Account</th>" +
						"<th>leve</th>" +
						"<th>Phone</th>" +
						"<th>Operation</th></tr>";
				$("#th").append(th);
				var tb="";
				for(var i=0;i<adminList.length;i++){
					tb+="<tr><td >"+adminList[i].id+"</td>" +
							"<td class='edit-content'>"+adminList[i].account+"</td>" +
							"<td class='edit-content'>"+adminList[i].leve+"</td>" +
							"<td class='edit-content'>"+adminList[i].phone+"</td>"+
							"<td><span>操作</span>" +
								"<div class='clearfix'>" +
									"<a class='edit' onclick='updateadmin(this)'>修改</a>" +
									"<a href='#' onclick='deladmin(this)'>删除</a>"+
								"</div>"	
							+"</td></tr>";
				}
				$("#tb").append(tb);
			},
			error:function(){
				alert("系统错误");
			}
		});
		
	});
	
	
	//查看管理员个人信息
	$("#adminInfo").click(function(){
		$("#infoh2").html("");
		$("#tb").html("");
		$("#th").html("");
		$("#d").html("");
		$("#infoh2").html("个人信息");
		$.ajax({
			url:"http://localhost:8080/JsCUCC/findEmpInfo.do",
			type:"post",
			data:{"account":admin.account},
			datatype:"json",
			success:function(result){
				if(result.status==0){
					var adminInfo = result.data;
//					var adinfo="<p>编&nbsp;&nbsp;号：<input type='text' " +
//							"id='infoId' value="+adminInfo.id+" readonly='readonly'></p>"+
//							"<br><p>姓&nbsp;&nbsp;名：<input type='text' id='infoName' " +
//							"value="+adminInfo.name+" ></p>"+
//							"<br><p>身份证：<input type='text' id='infoCard' " +
//							"value="+adminInfo.idCard+"></p>"+
//							"<br><p>年&nbsp;&nbsp;龄：<input type='text' id='infoAge' " +
//							"value="+adminInfo.age+"></p>"+
//							"<br><p>账&nbsp;&nbsp;号：<input type='text' id='infoAccount' "+
//							"value="+adminInfo.account+" readonly='readonly'></p>"+
//							"<br><p>手&nbsp;&nbsp;机：<input type='text' id='infoPhone' " +
//							"value="+adminInfo.phone+"></p>"+
//							"<br><p>地&nbsp;&nbsp;址：<input type='text' id='infoAddress' " +
//							"value="+adminInfo.address+"></p>"+
//							"<br><p><button id='bcxg'>保存修改</button>" +
//							"&nbsp;&nbsp;&nbsp;&nbsp;" +
//							"<button id='qxxg'>取消修改</button></p>"+
//							"<p id='infoError'></p>";
					var trs ="<tr><td>编号</td><td><input type='text' " +
							"id='infoId' value="+adminInfo.id+" readonly='readonly'></td></tr>"+
							"<tr><td>姓名</td><td><input type='text' " +
							"id='infoName' value="+adminInfo.name+"></td></tr>"+
							"<tr><td>身份证</td><td><input type='text' " +
							"id='infoCard' value="+adminInfo.idCard+" ></td></tr>"+
							"<tr><td>年龄</td><td><input type='text' " +
							"id='infoAge' value="+adminInfo.age+" ></td></tr>"+
							"<tr><td>账号</td><td><input type='text' " +
							"id='infoAccount' value="+adminInfo.account+" readonly='readonly'></td></tr>"+
							"<tr><td>手机</td><td><input type='text' " +
							"id='infoPhone' value="+adminInfo.phone+" ></td></tr>"+
							"<tr><td>地址</td><td><input type='text' " +
							"id='infoAddress' value="+adminInfo.address+" ></td></tr>"+
							"<tr><td colspan='2'><p id='infoError'></p></td></tr>"+
							"<tr><td colspan='2'><span>操作</span>" +
								"<div class='clearfix'>" +
									"<a class='edit' id='bcxg'>保存修改</a>" +
									"<a href='#' id='qxxg'>取消修改</a>"+
								"</div>" +
							"</td></tr>";
							
							
					$("#tb").append(trs);
				}else{
					var adinfo="<p>个人信息不详!</p>";
					$("#d").append(adinfo);
				}
				
			},
			error:function(){
				alert("系统错误");
			}
		});
		
	});
	
	
	//取消个人信息修改
	$(document).on('click','#qxxg',function(){
		$("#d").html("");
		$("#tb").html("");
		$("#infoh2").html("欢迎登陆");
	});
	
	
	//修改管理员信息
	$(document).on('click','#bcxg',function(){
		var boo=true;
		var name = $("#infoName").val().trim();
		var idcard = $("#infoCard").val().trim();
		var age = $("#infoAge").val().trim();
		var account = $("#infoAccount").val().trim();
		var phone = $("#infoPhone").val().trim();
		var address = $("#infoAddress").val().trim();
		
		if(name==""){
			$("#infoError").html("姓名不能为空");
			boo=false;
		}
		if(idcard==""||idcard.length<17){
			$("#infoError").html("身份证号码填写错误");
			boo=false;
		}
		if(age==""||age<1||age>100){
			$("#infoError").html("年龄填写错误");
			boo=false;
		}
		if(phone==""||(phone.length!=13&&phone.length!=11)){
			$("#infoError").html("手机号填写错误");
			boo=false;
		}
		if(address==""){
			$("#infoError").html("地址不能为空");
			boo=false;
		}

		if(boo){
			$.ajax({
				url:"http://localhost:8080/JsCUCC/updateEmpInfo.do",
				type:"post",
				data:{"account":account,"name":name,"idCard":idcard,"age":age,"phone":phone,"address":address},
				datatype:"json",
				success:function(result){
					if(result.status==0){
						$("#infoError").html("");
						$("#infoError").append(result.msg);
					}else{
						$("#infoError").html("修改失败");
					}
				},
				error:function(){
					alert("系统错误");
				}
			});
		}
	});
	
	
	
	
	//展现修改密码界面
	$("#updatePwd").click(function(){
		$("#infoh2").html("修改密码");
		$("#d").html("");
		$("#th").html("");
		$("#tb").html("");
		var dv="<tr><td><p class='pwdp'>旧密码：<input type='password' id='oldpwd' class='modifypwd'></p></td></tr>"+
				"<tr><td><p class='pwdp'>新密码：<input type='password' id='newpwd' class='modifypwd'></p></td></tr>"+
				"<tr><td><p class='pwdp'>再输一次：<input type='password' id='newpwd2' class='modifypwd'></p></td></tr>"+
				"<tr><td><p class='pwdp'><button id='cmt' class='button1'>提交</button>" +
				"&nbsp;&nbsp;&nbsp;<button id='qx' class='button1'>取消</button></p></td></tr>"+
				"<tr><td><p id='newpwdmsg'></p></td></tr>";
		$("#tb").append(dv);	
		
		//提交密码修改
		$("#cmt").click(function(){
			var oldpwd = $("#oldpwd").val().trim();
			var newpwd = $("#newpwd").val().trim();
			var newpwd2 = $("#newpwd2").val().trim();
			$("#newpwdmsg").html("");
			var boo=true;
			if(newpwd.length<6||newpwd==""){
				$("#newpwdmsg").html("密码不能为空，长度最少6位！");
				boo=false;
			}
			if(newpwd!=newpwd2){
				$("#newpwdmsg").html("两次密码输入不一致");
				boo=false;
			}
			if(oldpwd==newpwd){
				$("#newpwdmsg").html("新密码和旧密码一样，请重新输入！");
				boo=false;
			}
			
			if(boo){
				$.ajax({
					url:"http://localhost:8080/JsCUCC/updatepwd.do",
					type:"post",
					data:{"account":admin.account,"newpwd":newpwd,"oldpwd":oldpwd},
					datatype:"json",
					success:function(result){
						if(result.status==0){
							alert(result.msg);
							//清除cookie
							delCookie("admin_id");
							//跳转到首页
							window.location.href="login.html";
						}else if(result.status==1){
							$("#newpwdmsg").html(result.msg);
						}
					},
					error:function(){
						alert("系统错误");
					}
				});
			}
			
		});
		
		
		//取消密码修改
		$("#qx").click(function(){
			$("#d").html("");
			$("#tb").html("");
			$("#infoh2").html("欢迎登陆");
		});
	});
	
	
	
	//添加管理员
	$(document).on('click',"#add_admin1",function(){
		
		var aCancle = $('#alert-box a');
		
		$('#alert-box').width("100%");
		$('#alert-box').height("100%");
		$('#alert-box').fadeIn(500);
		
		//点击取消按钮，关闭窗口
		aCancle[0].onclick=function(){
			$("#addErr").html("&nbsp;");
			$("#addAccount").val("");
			$("#addPwd").val("");
			$("#addLevel").val("");
			$("#addPhone").val("");
			closeAlertBox();
		}
		
		
		// 关闭窗口
		function closeAlertBox() {
			$('#alert-box').fadeOut(1000);
			setTimeout(function () {
				$('#alert-box').width("0%");
				$('#alert-box').height("0%");
			}, 500);
		}
		
		//OK 按钮
		aCancle[1].onclick=function(){
			var account = $("#addAccount").val().trim();
			var password = $("#addPwd").val().trim();
			var leve = $("#addLevel").val().trim();
			var phone = $("#addPhone").val().trim();
			var boo=true;
			$("#addErr").html("&nbsp;");
			$("#addErr").css("color","red");
			if(account==""){
				$("#addErr").html("账号不能为空");
				boo=false;
			}else if(password.length<6){
				$("#addErr").html("密码至少输入6位");
				boo=false;
			}else if(leve!="S"&&leve!="A"){
				$("#addErr").html("leve只能输入S或者A");
				boo=false;
			}else if(phone.length!=11&&phone.length!=13){
				$("#addErr").html("输入的手机号不正确");
				boo=false;
			}
			
			
			if(boo){
				$.ajax({
					url:"http://localhost:8080/JsCUCC/addAdmin.do",
					type:"post",
					data:{"account":account,"password":password,"leve":leve,"phone":phone},
					datatype:"json",
					success:function(result){
						if(result.status==0){
							$("#addErr").html(result.msg);
							$("#addAccount").val("");
							$("#addPwd").val("");
							$("#addLevel").val("");
							$("#addPhone").val("");
						}else if(result.status==1){
							$("#addErr").html(result.msg);
						}
					},
					error:function(){
						alert("系统错误");
					}
				});
			}
		}
		
	});
		
	
	
});

//修改管理员操作
 function updateadmin(btn){
	 //获取修改按钮的爷爷和爷爷所有的兄弟
	 var $tds = $(btn).parent().parent().siblings();
	 var id =$tds.eq(0).html();
	 var account = $tds.eq(1).html();
	 var leve = $tds.eq(2).html();
	 var phone = $tds.eq(3).html();
	 
	 var aCancle = $('#alert-box5 a');
		
	 $('#alert-box5').width("100%");
	 $('#alert-box5').height("100%");
	 $('#alert-box5').fadeIn(500);
	 
	 $("#adminId").val(id);
	 $("#adminAccount").val(account);
	 $("#adminLeve").val(leve);
	 $("#adminPhone").val(phone);
	 
	 
		//点击取消按钮，关闭窗口
		aCancle[0].onclick=function(){
			$("#addErr5").html("&nbsp;");
			$("#adminId").val("");
			$("#adminAccount").val("");
			$("#adminLeve").val("");
			$("#adminPhone").val("");
			closeAlertBox();
		}
		
		
		// 关闭窗口
		function closeAlertBox() {
			$('#alert-box5').fadeOut(1000);
			setTimeout(function () {
				$('#alert-box5').width("0%");
				$('#alert-box5').height("0%");
			}, 500);
		}
	 
		//提交按钮
		aCancle[1].onclick=function(){
			var adminId=$("#adminId").val().trim();
			var adminAccount=$("#adminAccount").val().trim();
			var adminLeve = $("#adminLeve").val().trim();
			var adminPhone = $("#adminPhone").val().trim();
			var boo=true;
			var reg = /^[0-9]*$/;
			//将级别转为大写字母
			adminLeve=adminLeve.toUpperCase();
			//如果旧数据和提交新数据相同，
			//就直接关闭窗口
			if(account==adminAccount
					&&leve==adminLeve
					&&phone==adminPhone){
				$("#addErr5").html("&nbsp;");
				$("#adminId").val("");
				$("#adminAccount").val("");
				$("#adminLeve").val("");
				$("#adminPhone").val("");
				boo=false;
				closeAlertBox();	
			}
			if(adminAccount==""){
				$("#addErr5").html("账号不能为空");
				boo=false;
			}else if(adminLeve!="S"&&adminLeve!="A"){
				$("#addErr5").html("等级，只能填A或者S");
				boo=false;
			}else if(!reg.test(adminPhone)
					  || (adminPhone.length!=11
						    &&adminPhone.length!=13)){
				$("#addErr5").html("手机号填写错误");
				boo=false;
			}
			
			if(boo){
				$.ajax({
					url:"http://localhost:8080/JsCUCC/updateAdmin.do",
					type:"post",
					data:{"id":adminId,"account":adminAccount,"leve":adminLeve,"phone":adminPhone},
					datatype:"json",
					success:function(result){
						if(result.status==0){
							alert(result.msg);
							$tds.eq(1).html($("#adminAccount").val());
							$tds.eq(2).html($("#adminLeve").val());
							$tds.eq(3).html($("#adminPhone").val());
							$("#adminId").val("");
							$("#adminAccount").val("");
							$("#adminLeve").val("");
							$("#adminPhone").val("");
							closeAlertBox();
						}else{
							$("#addErr5").html(result.msg);
						}
					},
					error:function(){
						alert("系统错误");
					}
				});
			}
		}
 }
 
 
 
 //删除管理员
 function deladmin(btn){
	 //获取当前点击按钮的爷爷和爷爷所有的兄弟
	 var $tds = $(btn).parent().parent().siblings();
	 //根据下标获取 account
	 var account =$tds.eq(1).html();
	 if(account==admin.account){
		 alert("不能自己删除自己的账号");
	 }else{
		 $.ajax({
			 url:"http://localhost:8080/JsCUCC/delAdmin.do",
			 type:"post",
			 data:{"account":account},
			 datatype:"json",
			 success:function(result){
				 if(result.status==0){
					 alert(result.msg);
					 $(btn).parent().parent().parent().remove();
				 }else{
					 alert("删除失败了");
				 }
			 },
			 error:function(){
				 alert("系统错误");
			 }
		 });
	 }

 }
 
 
 
 