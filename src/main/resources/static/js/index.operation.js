
var websocket = null;
$(document).ready(function() {
			
			// 判断当前浏览器是否支持WebSocket
			if ('WebSocket' in window) {
				var conn = "ws://127.0.0.1:8088/websocket/" + $("#from").val();
				console.log(conn);
				websocket = new WebSocket(conn);
			} else {
				alert('当前浏览器 Not support websocket')
			}

			// 连接发生错误的回调方法
			websocket.onerror = function() {
				setMessageInnerHTML("WebSocket连接发生错误");
			};

			// 连接成功建立的回调方法
			websocket.onopen = function() {
				setMessageInnerHTML("WebSocket连接成功");
			}

			// 接收到消息的回调方法
			websocket.onmessage = function(event) {
				setMessageInnerHTML(event.data);
			}

			// 连接关闭的回调方法
			websocket.onclose = function() {
				setMessageInnerHTML("WebSocket连接关闭");
			}
			// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
			window.onbeforeunload = function() {
				closeWebSocket();
			}
			
			$("#convo").scrollTop($("#convo")[0].scrollHeight);
			
});

// 将消息显示在网页上
function setMessageInnerHTML(msg) {
	$(".chat-thread").append("<li class='rec-msg'>" + msg + "</li>");
	scrollSild()
}

// 关闭WebSocket连接
function closeWebSocket() {
	websocket.close();
}

//发送消息
function send() {
	// 聊天窗体显示消息
	$(".chat-thread").append("<li class='send-msg'>" + $('#msg').val() + "</li>"); 
	// 发送消息
	var message = {
		fromUid : $('#from').val(),
		toUid : $('#to').val(),
		msg : $('#msg').val()
	}
	websocket.send(JSON.stringify(message));
	// 发送框清除文字
	$('#msg').val("");
	// 聊天窗体滚动条下滑
	scrollSild();
	// 发送窗体重新聚焦
	$('#msg').focus();
	
}

function scrollSild(){ 
	$(".chat-thread").scrollTop($(".chat-thread")[0].scrollHeight);
}
