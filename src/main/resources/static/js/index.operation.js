
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
	$(".chat-thread").append("<li class='send-msg'>" + $('#msg').val() + "</li>");
	var message = {
		from : $('#from').val(),
		to : $('#to').val(),
		msg : $('#msg').val()
	}
	websocket.send(JSON.stringify(message));
	$('#msg').val("");
	scrollSild();
	
}

function scrollSild(){
//	var div = document.getElementById("convo");
//	div.scrollTop = div.scrollHeight - div.offsetHeight;
//	div.scrollIntoView(); 
//	$("#convo").scrollTop($("#convo")[0].scrollHeight);
	$("#convo").scrollTop(100000);
}

