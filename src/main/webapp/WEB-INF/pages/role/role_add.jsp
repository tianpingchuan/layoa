<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色新增页</title>
<jsp:include page="/base.jsp"></jsp:include>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body>
	<form class="layui-form" lay-filter="form-role">
		<div class="layui-form-item">
			<label class="layui-form-label">角色编号</label>
			<div class="layui-input-block">
				<!-- lay-verify校验 -->
				<input type="text" name="roleCode" required lay-verify="required" placeholder="请输入角色编号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
				<!-- lay-verify校验 -->
				<input type="text" name="roleName" required lay-verify="required|checkrolename" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色类型</label>
			<div class="layui-input-block">
				<input type="radio" name="roleKind" value="1" title="超级角色"> 
				<input type="radio" name="roleKind" value="0" title="普通角色" checked>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">角色简介</label>
			<div class="layui-input-block">
				<textarea name="roleInfo" required lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
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
<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript">
	//layui.use,当页面渲染成功后，加载配置的模块
	layui.use([ 'layer', 'form' ], function() {
		//通过一个变量将加载的模块取出
		var layer = layui.layer, form = layui.form;
		//layui内置jQuery
		var $ = layui.$;
		
		//表单的自定义校验
		form.verify({
			checkrolename:function(value,item){//value：表单的值、item：表单的DOM对象
				var msg;
				$.ajax({
					type:'get',
					async:false,//为了让layui可以做唯一性的校验，需要将ajax的异步提交关闭
					url:'role/checkname',
					data:{"roleName":value},
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
			var formData = $(data.form).serialize();
			console.log(formData);

			//尝试使用ajax的方式提交到后台
			$.ajax({
				type : 'post',
				url : 'role',
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