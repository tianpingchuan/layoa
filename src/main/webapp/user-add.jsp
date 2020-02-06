<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户新增</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body>
	<form class="layui-form" lay-filter="form-student">
		<div class="layui-form-item">
			<label class="layui-form-label">学生名称</label>
			<div class="layui-input-block">
				<!-- lay-verify校验 -->
				<input type="text" name="stuName" required lay-verify="required|checkstuname" placeholder="请输入学生名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生年龄</label>
			<div class="layui-input-inline">
				<input type="text" name="stuAge" required lay-verify="required|number" placeholder="请输入学生年龄" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生性别</label>
			<div class="layui-input-block">
				<input type="radio" name="stuSex" value="1" title="男"> <input type="radio" name="stuSex" value="0" title="女" checked>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生生日</label>
			<div class="layui-input-inline">
				<input name="stuBirthday" id="stuBirthday" required lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生爱好</label>
			<div class="layui-input-block">
				<input type="checkbox" name="stuLikes" title="写作" value="写作"> 
				<input type="checkbox" name="stuLikes" title="阅读" checked value="阅读"> 
				<input type="checkbox" name="stuLikes" title="发呆" value="发呆">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</body>
<!-- 
	按模块开发
	layui.all.js 全部加载
	layui.js 按需加载模块
 -->
<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript">
	//layui.use,当页面渲染成功后，加载配置的模块
	layui.use([ 'layer', 'form', 'laydate' ], function() {
		//通过一个变量将加载的模块取出
		var layer = layui.layer, form = layui.form;
		var laydate = layui.laydate;
		//layui内置jQuery
		var $ = layui.$;
		//layer.msg('Hello World');
		//渲染日历
		laydate.render({
			elem : '#stuBirthday' //指定元素
		});
		
		//表单的自定义校验
		form.verify({
			checkstuname:function(value,item){//value：表单的值、item：表单的DOM对象
				console.log(value);
				var msg;
				$.ajax({
					type:'get',
					async:false,//为了让layui可以做唯一性的校验，需要将ajax的异步提交关闭
					url:'student/checkname',
					data:{"stuName":value},
					success:function(result) {
						if(result==0) {
							//return '此名称已有人使用';
							msg = '此名称已有人使用';
						}
					}
				});
				//在ajax的外面return此次检验的结果，
				//如果return的数据有值的话，则layui认为是校验结果不通过
				//如果return的数据是空，则layui认为校验通过
				return msg;
			}
		});

		//绑定提交按钮
		form.on('submit(formDemo)', function(data) {
			//layer.msg(JSON.stringify(data.field));
			//var data = form.val('form-student');
			var formData = $(data.form).serialize();
			console.log(formData);

			//尝试使用ajax的方式提交到后台
			$.ajax({
				type : 'post',
				url : 'student',
				data : formData,
				success : function(result) {
					if (result) {
						layer.msg("提交成功！");
					}
				}
			});

			//将按钮自带的提交动作屏蔽
			return false;
		});
	});
</script>
</html>