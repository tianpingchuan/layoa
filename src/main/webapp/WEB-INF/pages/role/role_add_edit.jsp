<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="layui-form" lay-filter="form_role_add_edit">
	<div class="layui-form-item">
		<label class="layui-form-label">角色编号</label>
		<div class="layui-input-block">
			<!-- lay-verify校验 -->
			<input type="hidden" name="rowId" id="rowId"/>
			<input type="text" name="roleCode" required lay-verify="required" placeholder="请输入角色编号" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色名称</label>
		<div class="layui-input-block">
			<!-- lay-verify校验 -->
			<input type="text" id="roleName" name="roleName" required lay-verify="requiredcheckrolename" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色类型</label>
		<div class="layui-input-block">
			<select name="roleKind">
				<option value="1">超级角色</option>
				<option value="0">普通角色</option>
			</select>
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
			<button class="layui-btn" lay-submit lay-filter="btn_submit">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="assert/pages/js/role/role_add_edit.js"></script>