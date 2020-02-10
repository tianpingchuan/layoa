//layui.use,当页面渲染成功后，加载配置的模块
	layui.use([ 'layer', 'form','table' ], function() {
		//通过一个变量将加载的模块取出
		var layer = layui.layer, form = layui.form;
		//layui内置jQuery
		var $ = layui.$;
		var table = layui.table;
		
		//表单的自定义校验
		form.verify({
			checkrolename:function(value,item){//value：表单的值、item：表单的DOM对象
				var msg;
				//$.data 解析的是<input data-old = ""/>
				var oldVal = $(item).data('old');
				if(oldVal!=null && oldVal == value) {
					return msg;
				} else {//否则进行唯一性校验
					$.ajax({
						type:'get',
						async:false,//为了让layui可以做唯一性的校验，需要将ajax的异步提交关闭
						url:'role/checkname',
						data:{"roleName":value},
						success:function(result) {
							if(result==1) {
								//return '此名称已有人使用';
								msg = '此名称已有人使用';
							}
						}
					});
				}
				
				//在ajax的外面return此次检验的结果，
				//如果return的数据有值的话，则layui认为是校验结果不通过
				//如果return的数据是空，则layui认为校验通过
				return msg;
			}
		});

		//绑定提交按钮
		form.on('submit(btn_submit)', function(data) {
			var formData = $(data.form).serialize();
			//console.log(formData);
			
			var rowId = data.field.rowId;
			var type='post';
			if(rowId) {//如果rowId有值，执行修改
				type='put';
			}
			$.ajax({
				type : type,
				url : 'role',
				data : formData,
				success : function(result) {
					if (result) {
						//关闭弹出层
						layer.closeAll();
						//请求重新加载table数据
						table.reload('table_role');
					}
				}
			});
			
			//如果success成功后，将弹出层关闭。
			//如果想关闭最新的弹出层，直接layui.index即可
			//它获取的始终是最新弹出的某个层，值是由layer内部动态递增计算的
			//layer.close(layer.index);
			
			//让表格自动刷新
			//table.render();

			//将按钮自带的提交动作屏蔽
			return false;
		});
	});