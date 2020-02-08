<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body>
	<button type="button" class="layui-btn" id="button_add" style="float: right;">新增</button>
	<table id="demo" lay-filter="test"></table>

	<script type="text/html" id="userKindTpl">
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
<script type="text/javascript">
	layui.use(['table','form','layer'], function() {
		var layer = layui.layer;
		//取得layui的table元素
		var table = layui.table;
		var $ = layui.$;
		var form = layui.form;
		//第一个实例
		table.render({
			limit:2,
			elem : '#demo',//要绑定的页面元素
			height : 500,//
			url : 'role/find' //数据接口
			,
			page : true //开启分页
			,
			cols : [ [ //表头
			{
				field : 'rowId',
				title : 'ID',
				width : 80,
				sort : true,
				fixed : 'left'
			}, {
				field : 'roleCode',
				title : '角色编号',
				width : 180
			}, {
				field : 'roleName',
				title : '角色名称',
				width : 180,
				sort : true
			}, {
				field : 'roleKind',
				title : '角色等级',
				width : 180,
				templet : '#userKindTpl'
			}, {
				field : 'roleInfo',
				title : '角色介绍',
				width : 177
			}, {
				fixed : 'right',
				width : 150,
				align : 'center',
				toolbar : '#roleBtn',
				title : '操作',
				width : 177
			} ] ]
		});

		//监听工具条 
		//对table里面的按钮进行绑定
		table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			//var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

			switch (layEvent) {
			case 'edit':
				var rowId = data.rowId;
				$.ajax({
					url:'role/goupdate/'+rowId,
					success:function(htmlData){
						layer.open({
							anim: 6,//弹出动画
							type:1,//弹出的是页面层
							title:'新增角色',
							area:'800px',//设置宽度，高度自适应
							content:htmlData,//这里的content是一个DOM注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
							success:function(){//layui打开成功后的调试
								//让form表单渲染一下
								form.render(null,'form_role_add_edit');
							}
						});
					}
				});
				break;
			case 'delete':
				var rowId = data.rowId;
				layer.confirm('真的确定删除吗？',function(index){
					$.ajax({
						url : 'role/' + rowId,
						type:'delete',
						dataType : 'json',
						success : function(result) {
							if (result) {
								layer.msg("删除成功！");
							}
						}
					});
				
					layer.close(index);
					
					//刷新父页面
					window.parent.location.reload();
				});
				break;
			default:
				break;
			}
		});
		
		//绑定新增按钮
		$('#button_add').on('click',function(){
			$.ajax({
				url:'role/goadd',
				success:function(htmlData){
					layer.open({
						anim: 6,//弹出动画
						type:1,//弹出的是页面层
						title:'新增角色',
						area:'800px',//设置宽度，高度自适应
						content:htmlData,//这里的content是一个DOM注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
						success:function(){//layui打开成功后的调试
							//让form表单渲染一下
							form.render(null,'form_role_add_edit');
						}
					});
				}
			});
		});

	});
</script>
</html>