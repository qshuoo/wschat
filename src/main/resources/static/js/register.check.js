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
		// var pattern = /^1[34578][\d]{9}$/;
		/*if (!pattern.test($(this).val())) {
			$($(this).next()).show();
			$($(this).parent()).addClass("has-error");
		}*/
	});
	
	// 点击发送验证码 
	$("#btn_check").click(function() {
		$("#input_username,#input_password,#input_password_confirm,#input_checkinfo").blur();
		if ($(".has-error").size() == 0) {
			$("#btn_check").attr("disabled", "disabled");
			$("#btn_check").text("发送中...");
			// 1 发送验证码
			$.ajax({
				type:"POST",
				url:"/register/code",
				data:{codeReceiver:$("#input_checkinfo").val()},
				success:function(data) {
					if (data.code == 1) {
						calcReSendCode(60); // 2 按钮1分钟内不可点击
					} else {
						$("#err_code_msg").text(data.msg);
					}
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
			if($("#input_code").val() == '') {
				$("#err_code_msg").text("验证码不能为空");
				$($("#input_code").next()).show();
				$($("#input_code").parent()).addClass("has-error");
			} else {
				$.ajax({
					type:"POST",
					url:"/user/register",
					data:{username:$("#input_username").val(),
						  password:$("#input_password").val(),
						  checkinfo:$("#input_checkinfo").val(),
						  inputcode:$("input_code").val()
						  },
					success:function(data) {
						if (data.code == 1) {
							console.log(data.data.toString());
							window.location.href = '/wschat/login';
						} else {
							$("#err_code_msg").text(data.msg);
						}
					}
				});
			}
			
		}
	});
});