<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WSChat</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/js/findpwd.check.js"></script>
</head>
<body>
	<div class="container" >
		<!-- 介绍 -->
		<div class="row clearfix" style="padding-top:8%">
			<div class="col-md-6 column">
				<div style="padding-top:10%">
					<h2>欢迎登陆WSChat</h2>
					<div style="padding-top:5%"></div>
					<p>Recently a little bit of teeth, not spicy food, eating can only eat with the right, particularly uncomfortable</p>
					<div style="padding-top:5%"></div>
					<p>
						<a class="btn btn-primary btn-large" href="#">加入我们</a>
					</p>
				</div>
			</div>
			<!-- 登陆表单 -->
			<div class="col-md-6 column">
				<!-- 占块 -->
				<div style="padding-top:8%">
				</div>
				<h1 style="float:right"><strong>WSChat</strong></h1>
				<div style="padding-top:20%">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">账号</label>
						
						<div class="col-sm-8">
							<input type="text" class="form-control" id="input_account" />
							<div style="color:red;padding-top:3%;display:none;"><strong>账号不能为空</strong></div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="checkinfo" id="input_checkinfo" />
							<div style="color:red;padding-top:3%;display:none;"><strong>邮箱输入错误</strong></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="code" id="input_code" />
							<div style="color:red;padding-top:3%;display:none;"><strong id = "err_code_msg"></strong></div>
						</div>
						<div class="col-sm-3">
							<a id="btn_check" class="btn btn-default">发送验证码</a>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-8">
							<a id="btn_find" class="btn btn-default">确定</a>
							<a href="login.html" style="float:right;padding-top:15px">返回登陆</a>
							<span style="color:red;padding-left:5%;"><strong id="login_err_tip"></strong></span>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>