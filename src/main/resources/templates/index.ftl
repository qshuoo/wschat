
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
<script src="/js/bootstrap-fileinput.js"></script>
<link rel="stylesheet" href="/css/bootstrap-fileinput.css">
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
					<img id="img-pinfo" class="circle" src="${user.img!''}">
					<div class="form-group" style="padding-left: 20px;">
						<input id="input_search" type="text" class="form-control"
							placeholder="Search">
					</div>
					<button id="btn_search" type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span>
					</button>
					<button id="btn_secret" type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-heart-empty"></span>
					</button>
				</div>
				<div class="tabbable" id="tabs-503102">
					<ul class="nav nav-tabs">
						<li class="active"><a id="chat-sign" class="li-sign"
							href="#panel-121381" data-toggle="tab">会话</a></li>
						<li><a id="friend-sign" class="li-sign" href="#panel-121382"
							data-toggle="tab">好友</a></li>
						<li><a id="newfriend-sign" class="li-sign"
							href="#panel-121384" data-toggle="tab">新朋友</a></li>
						<li><a id="blist-sign" class="li-sign" href="#panel-121385"
							data-toggle="tab">黑名单</a></li>
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
						<div class="tab-pane" id="panel-121384">
							<ul id="newfriend-list" class="list-group">
							</ul>
						</div>
						<div class="tab-pane" id="panel-121385">
							<ul id="blist-list" class="list-group">
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- 好友展示 -->
			<div class="col-md-8 fuc-show" id="fuc-friend"
				style="background: #b0b0b0; height: 566px; padding-top: 50px; display: none;">
				<div class="col-md-3"></div>
				<div class="col-md-6" align="center"
					style="border: solid 2px gray; background: white; padding: 0px 0px 0px 0px;">
					<div style="background: #b3d4d4; height: 150px; padding-top: 50px">
						<img class="circle" src="/img/default.jpg"><br>
					</div>
					<div align="left" style="padding-top: 50px; padding-left: 100px">
						<label style="color: gray">账号&nbsp;</label><label id="friend-id"></label><br>
						<label style="color: gray">昵称&nbsp;</label><label
							id="friend-uname"></label><br> <label style="color: gray">邮箱&nbsp;</label><label
							id="friend-email"></label><br> <label style="color: gray">手机&nbsp;</label><label
							id="friend-phone"></label><br> <label style="color: gray">签名&nbsp;</label><label
							id="friend-signature"></label><br>
					</div>
					<div style="padding-top: 30px">
						<button type="submit" id="begin-chat" class="btn btn-default">发送消息</button>
						<button type="submit" id="btn-del-friend" class="btn btn-default">删除</button>
						<button type="submit" class="btn btn-default btn-add-blist">加入黑名单</button>
						<div style="padding-top: 30px"></div>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>

			<!-- 好友申请展示 -->
			<div class="col-md-8 fuc-show" id="fuc-newfriend"
				style="background: #b0b0b0; height: 566px; padding-top: 50px; display: none;">
				<div class="col-md-3"></div>
				<div class="col-md-6" align="center"
					style="border: solid 2px gray; background: white; padding: 0px 0px 0px 0px;">
					<div style="background: #b3d4d4; height: 150px; padding-top: 50px">
						<img class="circle" src="/img/default.jpg"><br>
					</div>
					<div align="left" style="padding-top: 50px; padding-left: 100px">
						<label style="color: gray">账号&nbsp;</label><label
							id="newfriend-id"></label><br> <label style="color: gray">昵称&nbsp;</label><label
							id="newfriend-uname"></label><br> <label style="color: gray">验证消息&nbsp;</label><label
							id="newfriend-msg"></label><br>
					</div>
					<div style="padding-top: 30px">
						<button type="submit" id="btn-newfriend-agree"
							class="btn btn-default">接受</button>
						<button type="submit" id="btn-newfriend-refuse"
							class="btn btn-default">拒绝</button>
						<button type="submit" class="btn btn-default btn-add-blist">加入黑名单</button>
						<div style="padding-top: 30px"></div>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>

			<!-- 黑名单展示 -->
			<div class="col-md-8 fuc-show" id="fuc-blist"
				style="background: #b0b0b0; height: 566px; padding-top: 50px; display: none;">
				<div class="col-md-3"></div>
				<div class="col-md-6" align="center"
					style="border: solid 2px gray; background: white; padding: 0px 0px 0px 0px;">
					<div style="background: #b3d4d4; height: 150px; padding-top: 50px">
						<img class="circle" src="/img/default.jpg"><br>
					</div>
					<div align="left" style="padding-top: 50px; padding-left: 100px">
						<label style="color: gray">账号&nbsp;</label><label id="blist-id"></label><br>
						<label style="color: gray">昵称&nbsp;</label><label id="blist-uname"></label><br>
					</div>
					<div style="padding-top: 30px">
						<button type="submit" id="btn-del-blist" class="btn btn-default">移除黑名单</button>
						<div style="padding-top: 30px"></div>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>

			<!-- 聊天展示 -->
			<div class="col-md-8 fuc-show" id="fuc-chat" style="display: none;">
				<div class="info-display">
					<h3 id="to-username"></h3>
				</div>
				<form role="form">
					<div id="convo" data-from="Sonu Joshi">
						<ul class="chat-thread">
						</ul>
					</div>

					<div id="snovo">
						<div class="form-group" style="margin-bottom: 0px;">
							<textarea id="msg" class="form-control input_text_area" rows="4"
								style="resize: none; background-color: white; border: none; outline: medium;"></textarea>
						</div>
						<div id="btn_send">
							<button id="send-msg" type="button"
								class="btn btn-default btn-sm " onclick="send()">发送</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column"></div>
		</div>


		<!-- 添加用户 -->
		<div class="modal fade" id="modal_user" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div class="modal-header" align=center>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div id="modal-search-body" class="modal-body" align="center"></div>
					<div id="modal-search-footer" class="modal-footer" align="center"
						style="text-align: center"></div>
				</div>
			</div>
		</div>

		<!-- 匿名聊天 -->
		<div class="modal fade" id="modal_secret" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 700px;">
				<div class="modal-content">
					<div id="modal-screct-header" class="modal-header" align=center>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div id="modal-screct-body" class="modal-body" align="center">
					</div>
					<div id="modal-screct-footer" class="modal-footer" align="center"
						style="text-align: center"></div>
				</div>
			</div>
		</div>
		<!-- 		个人信息 -->
		<div class="modal fade" id="modal_pinfo" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div id="modal-pinfo-header" class="modal-header" align=center>
						<input id="user-img" value="${user.img!''}" style="display: none" />

						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div id="modal-pinfo-body" class="modal-body" align="left">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">头像</label>
								<div class="col-md-8">
									<div class="fileinput fileinput-new" data-provides="fileinput"
										id="uploadImageDiv">
										<div class="fileinput-new thumbnail"
											style="width: 60px; height: 60px;">
											<img id="img-upload" src="${user.img!''}" alt="" />
										</div>
										<div class="fileinput-preview fileinput-exists thumbnail"
											style="max-width: 200px; max-height: 150px;"></div>
										<div>
											<span class="btn default btn-file"> <span
												class="fileinput-new">上传头像</span> <span
												class="fileinput-exists">更改</span> <input type="file"
												name="uploadImage" id="uploadImage" /></span>
										</div>
									</div>
									<div id="titleImageError" style="color: #a94442"></div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">账号</label>
								<div class="col-sm-10">
									<label id="user-account" class="form-control-static">${user.uid?c}</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">昵称</label>
								<div class="col-sm-10">
									<input id="user-uname" type="text" class="form-control"
										value="${user.uname!''}">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-10">
									<div class="input-group">
										<fieldset disabled>
											<input id="user-email" type="text" class="form-control"
												value="${user.email!''}">
										</fieldset>
										<span class="input-group-btn">
											<button class="btn btn-default" type="button">修改</button>
										</span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">手机</label>
								<div class="col-sm-10">
									<input id="user-phone" type="text" class="form-control"
										value="${user.phone!''}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">签名</label>
								<div class="col-sm-10">
									<input id="user-sign" type="text" class="form-control"
										value="${user.signature!''}">
								</div>
							</div>
						</form>
					</div>
					<div id="modal-pinfo-footer" class="modal-footer" align="center"
						style="text-align: center">
						<button id="btn-change-info" type="button" class="btn btn-default">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div style="display: none">
			<form role="form">
				<div id="secert-convo" data-from="Sonu Joshi">
					<ul class="secert-chat-thread">
					</ul>
				</div>

				<div id="secert-snovo">
					<div class="form-group" style="margin-bottom: -10px;">
						<textarea id="secert-msg" class="form-control input_text_area"
							rows="4"
							style="resize: none; background-color: white; border: none; outline: medium;"></textarea>
					</div>
					<div id="btn_send">
						<button id="secert-send-msg" type="button"
							class="btn btn-default btn-sm ">发送</button>
					</div>
				</div>
			</form>
		</div>

	</div>
</body>
</html>