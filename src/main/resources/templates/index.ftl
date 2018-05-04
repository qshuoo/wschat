
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WSChat</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/js/index.operation.js"></script>
<link rel="stylesheet" href="/css/index.style.css">
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-11">
				<h2>WSChat</h2>
			</div>
		</div>
		<div class="row clearfix"
			style="border-style: solid; border-color: gray">
			<div id="fucli" class="col-md-4">
				<div class="info-display">
					<img class="circle" alt="" src="/img/default.jpg">
					<div class="form-group" style="padding-left: 20px;">
						<input id="input_search" type="text" class="form-control"
							placeholder="Search">
					</div>
					<button id="btn_search" type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span>
					</button>
					<input id="account" value="${user.uid?c}" style="display: none">
					<input id="to" value="99999" style="display: none">
				</div>
				<div class="tabbable" id="tabs-503102">
					<ul class="nav nav-tabs">
						<li class="active"><a id="chat-sign" class="li-sign"
							href="#panel-121381" data-toggle="tab">会话</a></li>
						<li><a class="li-sign" href="#panel-121382" data-toggle="tab">好友</a></li>
						<li><a class="li-sign" href="#panel-121383" data-toggle="tab">群组</a></li>
						<li><a class="li-sign" href="#panel-121384" data-toggle="tab">新朋友</a></li>
						<li><a class="li-sign" href="#panel-121385" data-toggle="tab">黑名单</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-121381">
							<ul id="chat-list" class="list-group">
							</ul>
						</div>
						<div class="tab-pane" id="panel-121382">
							<ul id="friend-list" class="list-group">
							</ul>
						</div>
						<div class="tab-pane" id="panel-121383">
							<ul class="list-group">
							</ul>
						</div>
						<div class="tab-pane" id="panel-121384">
							<ul class="list-group">
							</ul>
						</div>
						<div class="tab-pane" id="panel-121385">
							<ul class="list-group">
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- 好友展示 -->
			<div class="col-md-8" id="fuc-friend" style="background: #b0b0b0;height: 566px;padding-top: 30px">
				<div class="col-md-3"></div>
				<div class="col-md-6" align="center" style="border: solid 2px gray;background: white;padding: 0px 0px 0px 0px;">
					<div style="background:#b3d4d4;height:150px;padding-top: 50px">
						<img class="circle" src="/img/default.jpg"><br>
					</div>
					<div>
						<label style="color: gray">账号 </label><label>账号</label><br>
						<label style="color: gray">昵称 </label><label>账号</label><br>
						<label style="color: gray">邮箱 </label><label>账号</label><br>
						<label style="color: gray">手机 </label><label>账号</label><br>
						<label style="color: gray">签名  </label><label>账号</label><br>
					</div>
					<button type="submit" class="btn btn-default">发送消息</button>
					<button type="submit" class="btn btn-default">删除</button>
					<button type="submit" class="btn btn-default">加入黑名单</button>
				</div>
				<div class="col-md-3"></div>
			</div>

			<!-- 聊天展示 -->
			<div class="col-md-8" id="fuc-chat" style="display: none;">
				<div class="info-display">
					<h3 id="to-username"></h3>
				</div>
				<form role="form">
					<div id="convo" data-from="Sonu Joshi">
						<ul class="chat-thread">
						</ul>
					</div>

					<div id="snovo">
						<div class="form-group">
							<textarea id="msg" class="form-control input_text_area" rows="4"
								style="resize: none; background-color: white; border: none; outline: medium;"></textarea>
						</div>
						<div id="btn_send">
							<button type="button" class="btn btn-default btn-sm "
								onclick="send()">发送</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column"></div>
		</div>


		<!-- 展示用户信息 -->
		<div class="modal fade" id="modal_user" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div id="modal_display" class="modal-content">
					<div class="modal-header" align=center>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body" align="center"></div>
					<div class="modal-footer" align="center" style="text-align: center">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>