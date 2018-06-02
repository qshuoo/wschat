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
					<div style="color:red;padding-top:3%;"><strong>${errMsg!''}</strong></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">账号</label>
						
						<div class="col-sm-8">
							<label id="label_account" class="col-sm-2 control-label">${(account?c)!}</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="password" id="input_password" />
							<div style="color:red;padding-top:3%;display:none;"><strong>密码不能为空</strong></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="input_password_confirm" />
							<div style="color:red;padding-top:3%;display:none;"><strong>确认密码错误</strong></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-8">
							<a id="btn_setpwd" class="btn btn-default">确定</a>
							<span style="color:red;padding-left:5%;"><strong id="login_err_tip"></strong></span>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 提示修改成功 -->
	<div class="modal fade" id="modal_tologin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 700px;">
				<div class="modal-content">
					<div class="modal-header" align=center>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body" align="center">
						<label>修改成功！</label>
					</div>
					<div class="modal-footer" align="center" style="text-align: center">
						<a onclick="window.location.href='/wschat/login'" class="btn btn-default">前往登陆</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>