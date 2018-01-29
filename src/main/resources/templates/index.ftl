
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WSChat</title>
<link rel="stylesheet" href="/css/index.style.css">
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/js/index.operation.js"></script>
<script src="/js/websocket.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1>TO DO</h1>
			</div>
		</div>
		<div style="border-style:solid;border-color:gray">
		<div class="row clearfix">
		<!-- <div> -->
			<div id="user_msg" class="col-md-12 column">
				<span id="from">${username}</spans>
			</div>
			<div style="display:none">
				<span id="to">99999</span>
			</div>
		<!-- </div> -->
			<div class="col-md-4 column">
				<div class="tabbable" id="tabs-503102">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-620501" data-toggle="tab">好友</a></li>
						<li><a href="#panel-250831" data-toggle="tab">群组</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-620501">
							<div class="list-group">
								<ul class="list-group">
									<li class="list-group-item">TODO</li>
									<li class="list-group-item">TODO</li>
									<li class="list-group-item">TODO</li>
									<li class="list-group-item"><span class="badge">1</span> TODO</li>
									<li class="list-group-item">TODO</li>
									<li class="list-group-item"><span class="badge">2</span> TODO</li>
								</ul>
							</div>
						</div>
						<div class="tab-pane" id="panel-250831">
							<div class="list-group">
								<ul class="list-group">
									<li class="list-group-item">TODO2</li>
									<li class="list-group-item">TODO2</li>
									<li class="list-group-item">TODO2</li>
									<li class="list-group-item"><span class="badge"
										style="background-color: red; color: white;">1</span> TODO</li>
									<li class="list-group-item">TODO2</li>
									<li class="list-group-item"><span class="badge"
										style="background-color: red; color: white;">2</span> TODO</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="col-md-8 column">
			<form role="form">
				<div class="form-group">
					<textarea id="message" class="form-control show_msg_area" rows="18" readonly></textarea>
				</div>
				<div class="form-group">
					<textarea id="msg" class="form-control input_text_area" rows="4"></textarea>
				</div>
				<div>
					<button type="button" class="btn btn-default btn-sm" style="float:right" onclick="send()">发送</button>
				</div>
			</form>
			
			</div>
		</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column"></div>
		</div>
	</div>
</body>
</html>