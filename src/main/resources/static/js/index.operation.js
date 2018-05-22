var websocket = null; // websocket
var msgMap = {}; // 接收到的消息列表
var applyFriendMsg = {}; // 好友添加验证消息缓存
var userAccount = null; // 用户账号
var userName = null; // 用户昵称
var userImgUrl = null; // 用户头像路径
var toUser = null; // 接收消息账号
$(document).ready(function() {
	
	// 赋值
	userAccount = $('#user-account').val();
	userName = $('#user-name').val();
	userImgUrl = $('#user-img').val();
	
	// 接收好友与群组列表
	recFriendList();
	
	// 接收好友申请列表
	recFriendApply();
	
	// 接收黑名单列表
	recBlackList();
	
	$("#friend-sign").click();
	
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		var conn = "ws://" + window.location.host + "/websocket/" + userAccount;
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
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		console.log("RECEIVED MSG");
		receive(JSON.parse(event.data));
	}

	// 连接关闭的回调方法
	websocket.onclose = function() {
	}
	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	$("#convo").scrollTop($("#convo")[0].scrollHeight);
	
});

/**
 * 绑定事件 BEGIN
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
			$("#friend-signature").text(data.data.signature);
		},
		error: function() {
			// TODO
		}
	});
});

/**
 * 好友申请列表点击
 */
$(document).on('click', '.newfriend-item', function() {
	// 功能区窗口隐藏
	$(".fuc-show").hide();
	
	// 展示申请添加用户信息
	console.log($.data(applyFriendMsg));
	console.log($.data(applyFriendMsg, '99999'));
	var applyInfo = JSON.parse($.data(applyFriendMsg, $(this).attr("data-uid")));
	console.log(applyInfo);
	$("#newfriend-id").text(applyInfo.uid);
	$("#newfriend-uname").text(applyInfo.uname);
	$("#newfriend-msg").text(applyInfo.msg);
	$("#fuc-newfriend").show();
	
});

/**
 * 会话列表点击
 */
$(document).on('click', '.chat-item', function() {
	// 功能区窗口隐藏
	$(".fuc-show").hide();
	
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
 * 接受好友添加按钮点击事件
 * @returns
 */
$(document).on('click', '#btn-newfriend-agree', function() {
	
	// 接受好友添加
	$.ajax({
		url: '/friend/deal',
		type: 'POST',
		async: false,
		data: {aimUid: userAccount, applyUid: $("#newfriend-id").text(), type: 'agree'},
		success: function(data) {
			if (data.code == 1) {
				alert("添加成功");
			} else {
				alert("添加失败，请刷新重试");
			}
		},
		error: function(){}
	});
	
	// 添加至好友展示列表并展示新好友
	var li = $("#newfriend-" + $("#newfriend-id").text());
	var newli = li.clone();
	newli.attr("id", "friend-" + li.attr("data-uid"));
	newli.removeClass("newfriend-item");
	newli.addClass("friend-item");
	newli.prependTo($("#friend-list"));
	$("#friend-sign").click();
	newli.click();
	
	// 从好友申请列表删除
	li.remove();
	
	// 发送招呼消息
	var message = {
		fromUid : userAccount,
		toUid : newli.attr("data-uid"),
		msg : 'Hi, 我们现在是好友拉,打声招呼吧'
	};
	websocket.send(JSON.stringify(message));
});

$(document).on('mouseover', '.list-group-item', function() {
	if (!$(this).hasClass("item-selected"))
		$(this).css("background", "#c5c5c5");
});

$(document).on('mouseout', '.list-group-item', function() {
	if (!$(this).hasClass("item-selected"))
		$(this).css("background", "#eeeeee");
});

$(document).on('click', '.list-group-item', function() {
	// 如果已经被选中  什么都不做
	if ($(this).hasClass("item-selected")) {
		return;
	}
	
	// 修改显示样式
	$(".item-selected").removeClass("item-selected");
	$(this).addClass("item-selected");
	$(this).siblings().css("background","#e9e9e4");
	$(this).css("background", "#b0b0b0");
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
	
	// TODO 此用户已存在列表中
	
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

/**
 * 添加好友按钮点击事件
 */
$(document).on('click', '#btn-add-friend', function() {
	var aimAccount = $("#input_search").val(); // 添加账号目标
	var addMsg = $("#text-add-msg").val(); // 添加验证信息
	$.ajax({
		url: '/friend/add',
		type: 'POST',
		data: {applyUid: userAccount, aimUid: aimAccount, msg:addMsg},
		success: function(data){
			if (data.code == 1) {
				// 发送系统消息
				var message = {
					fromUid : 99999,
					toUid : aimAccount,
					msg : {
						code: 1,
						uid: userAccount,
						uname: userName,
						img: userImgUrl,
						msg: addMsg
					}
				}
				websocket.send(JSON.stringify(message));
				// 添加成功提示
				alert("申请已发送");
				// 添加按钮不可点击
				$("#btn-add-friend").text("申请已发送");
				$("#btn-add-friend").attr("disabled", "disabled");
			} else {
				alert(data.msg);
				$("#btn-add-friend").attr("disabled", "disabled");
			}
		},
		error: function() {
			// TODO
		}
	});
});

/**
 * 拒绝好友申请
 */
$(document).on('click', '#btn-newfriend-refuse', function() {
	$.ajax({
		url: '/friend/deal',
		type: 'POST',
		async: false,
		data: {aimUid: userAccount, applyUid: $("#newfriend-id").text(), type: 'refuse'},
		success: function(data) {
			if (data.code == 1) {
				refuseFriendApply($("#newfriend-id").text());
			} else {
				alert("拒绝失败，请刷新重试");
			}
		},
		error: function(){}
	});
})
/**
 * 好友删除事件点击
 */
$(document).on('click', '#btn-del-friend', function() {
	if (!confirm('确认删除该好友吗？')) {
		return;
	}
	var aimAccount = $("#friend-id").text();
	$.ajax({
		url: '/friend/del',
		type: 'POST',
		data: {applyUid: userAccount, aimUid: aimAccount},
		success: function(data) {
			if (data.code == 1) {
				delFriend(aimAccount);
			}
		}
	});
} );

/**
 * 加入黑名单事件点击
 */
$(document).on('click', '.btn-add-blist', function() {
	if (!confirm('确定加入黑名单吗？')) {
		return;
	}
	var aimAccount = $($(".item-selected")[0]).attr("data-uid");
	console.log(aimAccount);
	
	$.ajax({
		url: '/blist/add',
		type: 'POST',
		data: {applyUid: userAccount, aimUid: aimAccount},
		success: function(data) {
			if (data.code == 1) {
				addToBlackList(aimAccount);
			} else {
				alert("添加失败");
			}
		}
	});
});

/**
 * 绑定事件 END
 */

/**
 * 将消息显示在聊天列表
 * @param msg
 * @returns
 */
function setMessageInnerHTML(msg) {
	$(".chat-thread").append("<li class='rec-msg'>" + msg + "</li>");
	scrollSild();
}

/**
 * 关闭WebSocket连接
 */
function closeWebSocket() {
	websocket.close();
}

/**
 * 接收消息
 * @param recMsg
 * @returns
 */
function receive(recMsg) {
	
	var fromUid = recMsg.fromUid.toString();
	
	// 系统消息
	if (fromUid == '99999') {
		var sysMsg = JSON.parse(recMsg.msg);
		dealSystemMsg(sysMsg);
		return;
	}

	// 普通消息
	// 会话提示消息
	if( !$("#chat-sign").hasClass("active")) {
		$("#chat-sign").css("background", "#e0570bc4");
	}
	
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
 * 发送普通会话消息
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
 * 处理系统消息
 * @param msg
 * @returns
 */
function dealSystemMsg(data) {
	console.log("===== " + JSON.stringify(data));
	if(data.code == 1) { // 收到新朋友添加请求
		console.log("System msg for add friend...");
		dealAddFriend(data);
	}
}

/**
 * 处理系统好友添加通知
 * @param data
 * @returns
 */
function dealAddFriend(data) {
	// 新朋友通知
	if( !$("#newfriend-sign").hasClass("active")) {
		$("#newfriend-sign").css("background", "#e0570bc4");
	}
	
	// 添加缓存至applyFriendMsg
	$.data(applyFriendMsg, data.uid.toString(), JSON.stringify(data));
	
	// 添加新朋友至列表
	var li = drewUserLi(data.uid, data.img, data.uname, 'newfriend');
	$(li).prependTo($("#newfriend-list"));
}

/**
 * 拒绝好友申请
 * @param id
 * @returns
 */
function refuseFriendApply(id) {
	// 提示拒绝成功
	alert('好友拒绝成功');
	// 清除功能区展示
	$(".fuc-show").hide();
	// 删除好友申请列表对应账号
	var friendli = $("#newfriend-" + id);
	friendli.remove();
}

/**
 * 删除好友
 * @param id
 * @returns
 */
function delFriend(id) {
	// 提示删除成功 TODO 暂时alert()
	alert('好友删除成功');
	
	// 功能框置空 
	$(".fuc-show").hide();
	
	// 好友展示列表 删除对应好友
	var friendli = $("#friend-" + id);
	friendli.remove();

	// 会话列表 删除对应好友
	var chatli = $("#chat-" + id);
	if (chatli) {
		chatli.remove();
	}
}

/**
 * 添加至黑名单
 * @param id
 * @returns
 */
function addToBlackList(id) {
	// 提示加入成功 TODO 暂时alert()
	alert('加入黑名单成功');
	
	// 功能框置空 
	$(".fuc-show").hide();
	
	// 从对应列表删除 并添加至黑名单
	var li = $($(".item-selected")[0]);
	var newli = li.clone();
	
	newli.attr("id", "blist-" + li.attr("data-uid"));
	newli.removeClass("newfriend-item");
	newli.removeClass("friend-item");
	newli.removeClass("item-selected");
	newli.addClass("blist-item");
	newli.prependTo($("#blist-list"));
	li.remove();
	
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
					var res = drewUserLi(list[i].uid.toString(), list[i].img, list[i].uname, 'friend');
					$("#friend-list").append(res);
				}
			}
		}
	});
}

/**
 * 接收好友申请
 */
function recFriendApply() {
	$.ajax({
		url: "/friend/new",
		type: "GET",
		async: false,
		data: {account: userAccount},
		success: function(data) {
			if (data.code == 1) {
				var list = data.data;
				for (let i = 0; i < list.length; i++) {
					
					// 添加申请信息至缓存
					$.data(applyFriendMsg, list[i].uid.toString(), JSON.stringify(list[i]));
					
					// 加载申请列表
					var res = drewUserLi(list[i].uid.toString(), list[i].img, list[i].uname, 'newfriend');
					$("#newfriend-list").append(res);
				}
			}
		},
		error: function() {
			// TODO
		}
	});
}

/**
 * 接收黑名单
 */
function recBlackList(){
	$.ajax({
		url: "/user/blist",
		type: "GET",
		async: false,
		data: {account: userAccount},
		success: function(data) {
			if (data.code == 1) {
				var list = data.data;
				for (let i = 0; i < list.length; i++) {
					// 加载黑名单列表
					var res = drewUserLi(list[i].uid.toString(), list[i].img, list[i].uname, 'blist');
					$("#blist-list").append(res);
				}
			}
		},
		error: function() {
			// TODO
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
function drewUserLi(account, imgurl, username, type) { // 一好友显示的模块
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
			+ "<textarea id='text-add-msg' class='form-control input_text_area'"
			+ "style='resize: none;' rows='2' placeholder='我是...'></textarea>"
			+ "<button id='btn-add-friend' type='button' class='form-control btn btn-default'>添加好友</button>"
	return str;
}

/**
 * 滚动轮滚动至底端
 * @returns
 */
function scrollSild() {
	$(".chat-thread").scrollTop($(".chat-thread")[0].scrollHeight);
}