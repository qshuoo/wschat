var websocket = null; // websocket
var msgMap = {}; // 接收到的消息列表
var userAccount = null; // 用户账号
var toUser = null; // 接收消息账号
$(document).ready(function() {
	
	// 赋值
	userAccount = $('#account').val();
	
	// 接收好友与群组列表
	recFriendList();
	
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		var conn = "ws://" + window.location.host + "/websocket/" + $("#account").val();
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
//		setMessageInnerHTML("WebSocket连接成功");
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		console.log("RECEIVED MSG");
		receive(JSON.parse(event.data));
	}

	// 连接关闭的回调方法
	websocket.onclose = function() {
//		setMessageInnerHTML("WebSocket连接关闭");
	}
	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	$("#convo").scrollTop($("#convo")[0].scrollHeight);
	
});

/**
 * 绑定事件
 * @param msg
 * @returns
 */

/**
 * 好友列表点击
 */
$(document).on('click', '.friend-item', function() {
	// 功能区窗口隐藏
	$(".fuc-show").hide();
	
	console.log("friend-item click");
	
	$("#fuc-friend").show();
	$.ajax({
		url: '/user/search/' + $(this).attr("data-uid"),
		type: 'GET',
		success: function(data){
			$("#friend-id").text(data.data.uid);
			$("#friend-uname").text(data.data.uname);
			$("#friend-email").text(data.data.email);
			$("#friend-phone").text(data.data.phone);
			$("#friend-sign").text(data.data.signature);
		},
		error: function() {
			// TODO
		}
	});
});

/**
 * 发送消息按钮点击事件
 */
$(document).on('click', '#begin-chat', function() {
	console.log("begin-chat btn click");
	// 列表组切换至会话
	$("#chat-sign").click();
	
	// 生成置顶会话列表
	var fromUid = $("#friend-id").text();
	var li = $("#chat-" + fromUid); // 获取对应会话
	if (!li[0]) { // 会话不再列表中
		console.log("not in list");
		// 获取好友
		li = $("#friend-" + fromUid).clone();
		// 更改所在列表
		li.attr("id", "chat-" + fromUid);
		li.removeClass("friend-item");
		li.addClass("chat-item");
		
	} else {
		li.remove(); // 移除会话
	}
	
	// 添加至列表开头
	li.prependTo($("#chat-list"));
	
	// 列表被点击
	li.click();

});

/**
 * 会话列表点击
 */
$(document).on('click', '.chat-item', function() {
	// 功能区窗口隐藏
	$(".fuc-show").hide();
	
	// 如果已经被选中  什么都不做
	if ($(this).hasClass("item-selected")) {
		return;
	}
	
	// 修改显示样式
	$(".item-selected").removeClass("item-selected");
	$(this).addClass("item-selected");
	$(this).siblings().css("background","#e9e9e4");
	$(this).css("background", "#b0b0b0");
	
	// 消除离线消息数量提示
	$($(this).children(".badge")[0]).text("");
	
	// 消息会话赋值
	toUser = $(this).attr("data-uid");
	
	// 打开聊天窗口
	$("#to-username").text($($(this).children("span")[0]).text());
	$("#fuc-chat").show();
	
	// 清空聊天内容  (暂时)
	$(".chat-thread").empty();
	
	// 加载未读消息
	console.log($.data(msgMap, toUser));
	if (typeof($.data(msgMap, toUser)) == "undefined") { // 没有缓存 return
		return;
	}
	var list = JSON.parse($.data(msgMap, toUser));
	for (let i = 0; i < list.length; i++) {
		setMessageInnerHTML(list[i]);
	}
	
	// 清除未读缓存
	$.removeData(msgMap, toUser);
});

$(document).on('mouseover', '.list-group-item', function() {
	if (!$(this).hasClass("item-selected"))
		$(this).css("background", "#c5c5c5");
});

$(document).on('mouseout', '.list-group-item', function() {
	if (!$(this).hasClass("item-selected"))
		$(this).css("background", "#eeeeee");
});

$(document).on('click', '.li-sign', function() {
	$(this).css("background", "");
});

/**
 * 搜索
 * @returns
 */
$(document).on('click', '#btn_search', function() {
	var account = $("#input_search").val();
	if (account == "") { // 没输入内容返回
		return;
	}
	$("#modal_user").modal();
	$.ajax({
		url : '/user/search/' + account,
		type : 'GET',
		beforeSend:function() {  
            $(".modal-body").html("正在查找中...");
        },
		success : function(data) {
			if (typeof(data.data) == "undefined") {
				$(".modal-body").html("没有找到相关用户");
				return;
			}
			console.log(data.data);
			var str = drawSearchUser(data.data.uid.toString(), data.data.img, data.data.uname);
			$(".modal-body").html(str);
		},
		error : function() {
			// TODO
		}
	});
	
});

// 将消息显示在聊天列表
function setMessageInnerHTML(msg) {
	$(".chat-thread").append("<li class='rec-msg'>" + msg + "</li>");
	scrollSild();
}

// 关闭WebSocket连接
function closeWebSocket() {
	websocket.close();
}

/**
 * 接收消息
 * @param recMsg
 * @returns
 */
function receive(recMsg) {
	
	// 会话提示消息
	if( !$("#chat-sign").hasClass("active")) {
		$("#chat-sign").css("background", "#e0570bc4");
	}
	
	var fromUid = recMsg.fromUid.toString();
	var li = $("#chat-" + fromUid); // 获取对应会话
	if (!li[0]) { // 会话不再列表中
		console.log("not in list");
		// 获取好友
		li = $("#friend-" + fromUid).clone();
		// 更改所在列表
		li.attr("id", "chat-" + fromUid);
		
	} else {
		li.remove(); // 移除会话
	}
	
	// 添加至列表开头
	li.prependTo($("#chat-list"));
	
	// 会话被选中 直接显示消息 返回
	if (li.hasClass("item-selected")) {
		setMessageInnerHTML(recMsg.msg);
		return;
	}
	
	// 会话未被选中 好友列表中离线消息数量+1 
	var cntShow = $(li.children(".badge")[0]);
	if (cntShow.text() == "") { // 没有未读消息 
		cntShow.text(1);
	} else {
		cntShow.text((Number(cntShow.text()) + 1));
	}
	
	// 缓存消息 
	var msgList = $.hasData(msgMap, fromUid);
	var list = [];
	if (msgList) {
		list = JSON.parse($.data(msgMap, fromUid));
	}
	list.push(recMsg.msg);
	$.data(msgMap, fromUid, JSON.stringify(list));
	console.log($.data(msgMap, fromUid));
}

/**
 * 发送消息
 * @returns
 */
function send() {
	// 聊天窗体显示消息
	$(".chat-thread").append(
			"<li class='send-msg'>" + $('#msg').val() + "</li>");
	// 发送消息
	var message = {
		fromUid : userAccount,
		toUid : toUser,
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

/**
 * 接收好友列表
 * @returns
 */
function recFriendList() {
	$.ajax({
		url:"/user/friend",
		type:"get",
		async:false, // 不能异步
		data:{account:userAccount},
		success:function(data) {
			if (data.code == 1) {
				var list = data.data;
				for (let i = 0; i < list.length; i++) {
					var res = drewFriendList(list[i].uid.toString(), list[i].img, list[i].uname, 'friend');
					$("#friend-list").append(res);
				}
			}
		}
	});
}

/**
 * 写入列表
 * @param account
 * @param imgurl
 * @param username
 * @returns
 */
function drewFriendList(account, imgurl, username, type) { // 一好友显示的模块
	var str = "<li id='" + type + "-" + account + "' data-uid='" + account + "' class='list-group-item " +type+ "-item'>"
			+ "<img src='" + imgurl + "' class='circle' onerror=\"this.src='/img/default.jpg'\"></img>"
			+ "<span class = 'user-name'>" + username + "</span>"
			+ "<span class='badge'></span>" + "</li>"
	return str;
}

/**
 * 写查询好友结果
 * @param imgurl
 * @param account
 * @param username
 * @returns
 */
function drawSearchUser(account, imgurl, username) {
	var str = "<img class='circle' src='" + imgurl + "' onerror=\"this.src='/img/default.jpg'\"/>"
			+ "<h4>账号：" + account + "</h4>"
			+ "<h4>用户名：" + username + "</h4>"
			+ "<textarea class='form-control input_text_area'"
			+ "style='resize: none;' rows='2' placeholder='我是...'></textarea>"
			+ "<button type='button' class='form-control btn btn-default'>添加好友</button>"
	return str;
}

/**
 * 滚动轮滚动至底端
 * 
 * @returns
 */
function scrollSild() {
	$(".chat-thread").scrollTop($(".chat-thread")[0].scrollHeight);
}