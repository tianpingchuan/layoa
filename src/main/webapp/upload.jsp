<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body>
	学生头像：<button type="button" class="layui-btn" id="test1">
		<i class="layui-icon">&#xe67c;</i>上传图片
	</button>
	<input type="hidden" name="stuImg" id="stuImg"/>
	<input type="hidden" id="rowId" value="33"/>
</body>
<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript">
	//页面渲染加载upload模块
	layui.use([ 'upload' ], function() {
		var upload = layui.upload;
		var $ = layui.$;
		//通过upload将button渲染成有上传功能
		upload.render({
			elem : '#test1' //绑定元素
			,
			url : 'upload/' //上传接口
			,
			field:'uploadFile'//设定文件域的字段名
			,
			//data:{"id":"22"}//上传的额外的参数
			data:{"id":function(){
				return $('#rowId').val();
			}}
			,
			acceptMime: 'image/jpeg, image/png',//规定打开文件选择框时，筛选出的文件类型
			size:100,//设置文件最大可允许上传的大小，单位 KB。不支持ie8/9
			done : function(res) {//可以理解成ajax的success
				console.log(res.data);
				//将上传成功返回的文件路径 赋值给表单中的隐藏域
				$('#stuImg').val(res.data);
				//上传完毕回调
			},
			error : function() {
				//请求异常回调
			}
		});
	});
</script>
</html>