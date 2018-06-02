$(document).ready(function() {
	// 聚焦隐藏错误信息
	$("#input_account, #input_checkinfo, #input_code, #input_password, #input_password_confirm").focus(function() {
		$($(this).next()).hide();
		$($(this).parent()).removeClass("has-error");
	});
	
	// 用户名/密码暂定不能为空
	$("#input_account, #input_checkinfo, #input_password").blur(function() {
		console.log($(this).attr("id") + " blur");
		console.log($(this).val());
		if ($(this).val() == '') {
			$($(this).next()).show();
			$($(this).parent()).addClass("has-error");
		}
	});
	
	// 两次输入密码一致
	$("#input_password_confirm").blur(function() {
		if ($(this).val() != $("#input_password").val()) {
			$($(this).next()).show();
			$($(this).parent()).addClass("has-error");
		}
	});
	
	// 点击发送验证码 
	$("#btn_check").click(function() {
		$("#input_code").focus();
		$("#input_account, #input_checkinfo").blur();
		if ($(".has-error").size() == 0) {
			$("#btn_check").attr("disabled", "disabled");
			$("#btn_check").text("发送中...");
			// 1 发送验证码
			$.ajax({
				type:"POST",
				url:"/findpwd/code",
				data:{
					account: $("#input_account").val(), 
					codeReceiver:$("#input_checkinfo").val(), 
					codeName: "CODE_FINDPWD"
				},
				success:function(data) {
					if (data.code == 1) {
						calcReSendCode(60); // 2 按钮1分钟内不可点击
					} else {
						$("#btn_check").removeAttr("disabled");
						$("#btn_check").text("发送验证码");
						showRegError(data.msg);
					}
				},
				error:function(data) {
					$("#btn_check").removeAttr("disabled");
					$("#btn_check").text("发送验证码");
					showRegError("发送失败，请检查网络连接");
				}
			});
		}
	});
	
	// 重新发送倒计时
	function calcReSendCode(time){
		$("#btn_check").text("重新发送" + time + "S");
		var i = setInterval(function() {
			if(time > 0) {
				time--;
				$("#btn_check").text("重新发送" + time + "S");
			} else {
				$("#btn_check").removeAttr("disabled");
				$("#btn_check").text("发送验证码");
				clearInterval(i);
			}
		},1000);
	}
	
	$("#btn_find").click(function() {
		$("#input_account, #input_checkinfo").blur();
		if ($(".has-error").size() == 0) {
			if(typeof($("#btn_check").attr("disabled")) == "undefined") {
				showRegError("请发送验证码");
				return;
			}
			if($("#input_code").val() == '') {
				showRegError("验证码不能为空");
				return;
			}
			$.ajax({
				type:"POST",
				url:"/user/findpwd",
				data:{
					account:$("#input_account").val(),
					inputcode:$("#input_code").val(),
					codeName: "CODE_FINDPWD"
				},
				success:function(data) {
					if (data.code == 1) { // 验证成功
						window.location.href = '/wschat/setpwd';
					} else {
						showRegError(data.msg);
					}
				},
				error:function(data) {
					showRegError("出错了，请检查网络信息");
				}
			});
			
		}
	});
	
	/**
	 * 重置密码按钮点击
	 */
	$("#btn_setpwd").click(function() {
		$("#input_password, #input_password_confirm").blur();
		if ($(".has-error").size() != 0) {
			return;
		}
		$.ajax({
			url: '/user/setnewpwd',
			type: 'POST',
			data: {
				account: $("#label_account").text(),
				password: $("#input_password").val()
			},
			success: function(data) {
				if (data.code == 1) {
					$("#modal_tologin").modal();
				}
			}
		
		});
	});
	
	function showRegError(data){
		$("#err_code_msg").text(data);
		$($("#input_code").next()).show();
		$($("#input_code").parent()).addClass("has-error");
	}
});	
