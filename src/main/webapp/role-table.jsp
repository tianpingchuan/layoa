<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body style="background-color: #F2F2F2;">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						角色管理
						<!-- 新增按钮 -->
						<button type="button" class="layui-btn" id="button_add" style="float: right;">新增</button>
					</div>
					<div class="layui-card-body">
						<!-- 搜索表单开始 -->
						<form class="layui-form" id="form_search">
							<div class="layui-search-form">
								<div class="layui-inline">
									<input type="text" name="roleName" placeholder="角色名称" autocomplete="off" class="layui-input">
								</div>
								<div class="layui-inline">
									<select name="roleKind">
										<option value="">角色类型</option>
										<option value="1">超级角色</option>
										<option value="0">普通角色</option>
									</select>
								</div>
								<div class="layui-inline">
									<button class="layui-btn layui-btn-primary layui-btn-sm" lay-submit lay-filter="btn_search">
										<i class="layui-icon layui-icon-search"></i>
									</button>
									<button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">
										<i class="layui-icon layui-icon-refresh"></i>
									</button>
								</div>
							</div>
						</form>
						<!-- 搜索表单结束 -->
						<table id="table_role" lay-filter="filter_table_role"></table>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/html" id="roleKindTpl">
	{{#  if(d.roleKind == 1){ }}
    <span class="layui-badge layui-bg-cyan">超级用户</span>
  	{{#  } else { }}
    <span class="layui-badge layui-bg-green">普通用户</span>
  	{{#  } }}
</script>

	<!-- 修改是删除按钮 -->
<script type="text/html" id="roleBtn">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	{{#  if(d.roleKind == 1){ }}
	<a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled" >删除</a>
	{{#  } else { }}
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
	{{#  } }}
</script>
</body>
<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript" src="assert/pages/js/role/role.js"></script>
</html>