/**
* 注册
*/
$(document).ready(function() {
	// 聚焦隐藏错误信息
	$("#input_username,#input_password,#input_password_confirm,#input_checkinfo,#input_code").focus(function() {
		$($(this).next()).hide();
		$($(this).parent()).removeClass("has-error");
	});
	
	// 用户名/密码暂定不能为空
	$("#input_username,#input_password").blur(function() {
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
	
	// 手机号暂定以1开头的11位数字
	$("#input_checkinfo").blur(function() {
		var pattern = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
		if (!pattern.test($(this).val())) {
			$($(this).next()).show();
			$($(this).parent()).addClass("has-error");
		}
		
	});
	
	// 点击发送验证码 
	$("#btn_check").click(function() {
		$("#input_code").focus();
		$("#input_username,#input_password,#input_password_confirm,#input_checkinfo").blur();
		if ($(".has-error").size() == 0) {
			$("#btn_check").attr("disabled", "disabled");
			$("#btn_check").text("发送中...");
			// 1 发送验证码
			$.ajax({
				type:"POST",
				url:"/register/code",
				data:{codeReceiver:$("#input_checkinfo").val(), codeName: "CODE_REG"},
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
	
	// 点击注册
	$("#btn_reg").click(function() {
		$("#input_username,#input_password,#input_password_confirm,#input_checkinfo").blur();
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
				url:"/user/register",
				data:{username:$("#input_username").val(),
					password:$("#input_password").val(),
					checkinfo:$("#input_checkinfo").val(),
					inputcode:$("#input_code").val(),
					codeName: "CODE_REG"
					},
				success:function(data) {
					if (data.code == 1) { // 注册成功
						window.location.href = '/wschat/regsucc/' + data.data;
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
	
	function showRegError(data){
		$("#err_code_msg").text(data);
		$($("#input_code").next()).show();
		$($("#input_code").parent()).addClass("has-error");
	}
});