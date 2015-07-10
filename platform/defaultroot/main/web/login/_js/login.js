(function($) {
	var options = {
			mainUrl : '../main.jsp'
		};
	
	$.fn.login = function(ops) {
		options = $.extend(options, ops);
	}
	
	$("#login_button").live("click",function(){login()});
	
	$("input").live("keypress",function(e){
		if(e.keyCode==13)
			login();
	});
	
	function login(){
		var inputs = $("#login_form input")
		var params = {
			'objectEvent':'Login'
		};
		inputs.each(function(i, n) {
			var name = $(n).attr("name");
			var val = $(n).val();
			params[name] = val;
		});

		jQuery.ajax({
			type : 'POST',
			dataType : 'json',
			url : $.wx.getContextPath() + '/login.do',
			data : params,
			success : function(r) {
				if (r)
					window.location.href = options.mainUrl;
				else{
					alert("用户名或密码错误");
					$("input[name=userPass]").select();
				}
			}
		});
	}
})(jQuery);