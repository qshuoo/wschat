
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
<link rel="stylesheet" href="/css/index.style.css">
<script src="/js/index.operation.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h1>TO DO</h1>
			</div>
		</div>
		<div style="border-style: solid; border-color: gray">
			<div class="row clearfix">
				<!-- <div> -->
				<div class="col-md-12 column">
					<h1>TO DO</h1>
					<input id="from" value="${username}" style="display:none">
					<input id="to" value="99999" style="display:none">
				</div>
				<!-- </div> -->
				<div id="fucli" class="col-md-4 column">
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
										<li class="list-group-item"><span class="badge">1</span>
											TODO</li>
										<li class="list-group-item">TODO</li>
										<li class="list-group-item"><span class="badge">2</span>
											TODO</li>
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
						<div id="convo" data-from="Sonu Joshi">
							<ul class="chat-thread">
							</ul>
						</div>

						<div id="snovo">
							<div class="form-group">
								<textarea id="msg" class="form-control input_text_area" rows="4"></textarea>
							</div>
							<div>
								<button id="button_send" type="button" class="btn btn-default btn-sm " onclick="send()">发送</button>
							</div>
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