/**
* 登录校验
*/
$(document).ready(function() {
	
	$("#input_account,#input_password").focus(function() {
		$($(this).next()).hide();
		$($(this).parent()).removeClass("has-error");
	});
	
	$("#input_account,#input_password").blur(function() {
		if ($(this).val() == '') {
			$($(this).next()).show();
			$($(this).parent()).addClass("has-error");
		}
	});
	
	$("#btn_login").click(function() {
		$("#input_account,#input_password").blur();
		if ($(".has-error").size() == 0) {
			// console.log("yes");
			var account_val = $("#input_account").val();
			var password_val = $("#input_password").val();
			$.ajax({
				url : "/login/check",
				type : "post",
				data : {account : account_val, password : password_val},
				success : function(data) {
					console.log(data);
					// location.href = "/login/check";
				},
				error : function(data) {
					
				}
			});
		}
	})
});

