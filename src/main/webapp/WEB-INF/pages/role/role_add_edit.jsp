<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_role_add_edit">
	<div class="layui-form-item">
		<label class="layui-form-label">角色编号</label>
		<div class="layui-input-block">
			<!-- lay-verify校验 -->
			<input type="hidden" value="${role.rowId}" name="rowId" id="rowId"/>
			<input type="text" value="${role.roleCode}" name="roleCode" required lay-verify="required" placeholder="请输入角色编号" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色名称</label>
		<div class="layui-input-block">
			<!-- lay-verify校验 -->
			<input type="text" value="${role.roleName}" name="roleName" required lay-verify="required${role.rowId==null?'|checkrolename':''}" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色类型</label>
		<div class="layui-input-block">
			<input type="radio" name="roleKind" value="1" title="超级角色" ${role.roleKind==1?'checked':''}> 
			<input type="radio" name="roleKind" value="0" title="普通角色" ${role.roleKind==0?'checked':''}>
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">角色简介</label>
		<div class="layui-input-block">
			<textarea name="roleInfo" required lay-verify="required" placeholder="请输入内容" class="layui-textarea">${role.roleInfo}</textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
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
			//console.log(formData);
			
			var rowId = $('#rowId').val();
			
			if(rowId) {//如果主键有值，则为修改
				$.ajax({
					type : 'put',
					url : 'role',
					data : formData,
					success : function(result) {
						if (result) {
							layer.msg("修改成功！");
						}
					}
				});
			} else {//如果主键没值，则为新增
				//尝试使用ajax的方式提交到后台
				$.ajax({
					type : 'post',
					url : 'role',
					data : formData,
					success : function(result) {
						if (result) {
							layer.msg("新增成功！");
						}
					}
				});
			}

			
			//如果success成功后，将弹出层关闭。
			//如果想关闭最新的弹出层，直接layui.index即可
			//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
			layer.close(layer.index);
			
			//让表格自动刷新
			table.render();

			//将按钮自带的提交动作屏蔽
			return false;
		});
	});
</script>