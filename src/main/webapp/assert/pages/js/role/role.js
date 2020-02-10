layui.use([ 'table', 'form', 'layer' ], function() {
	var layer = layui.layer;
	// 取得layui的table元素
	var table = layui.table;
	var $ = layui.$;
	var form = layui.form;
	// 第一个实例
	table.render({
		limit : 2,
		elem : '#table_role',// 要绑定的页面元素
		//height : 500,//
		url : 'role/find' // 数据接口layui会自动封装成role/find/{page}/{limit}
		,
		where:$('#form_search').serialize(),//模拟额外的参数,搜索表单中的数据
		page : true // 开启分页
		,
		cols : [ [ // 表头
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
			templet : '#roleKindTpl'
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

	// 监听工具条
	// 对table里面的按钮进行绑定
	table.on('tool(filter_table_role)', function(obj) { // 注：tool 是工具条事件名，test 是 table
											// 原始容器的属性 lay-filter="对应的值"
		var data = obj.data; // 获得当前行数据
		var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		// var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		var rowId = data.rowId;
		switch (layEvent) {
		case 'edit':
			//打开修改的表单
			openLayerRole(rowId);
			break;
		case 'delete':
			layer.confirm('真的确定删除吗？', function() {
				$.ajax({
					url : 'role/' + rowId,
					type : 'delete',
					success : function(result) {
						if (result) {
							//layer.msg("删除成功！");
							//请求table重新加载数据table_role = <table id="table_role"/>
							table.reload('table_role');
							layer.closeAll();//疯狂模式，关闭所有弹出层
						}
					}
				});

				//layer.close(index);

				// 刷新父页面
				//window.parent.location.reload();
			});
			break;
		default:
			break;
		}
	});

	// 绑定新增按钮
	$('#button_add').off('click').on('click', function() {
		//打开新增表单
		openLayerRole();
	});
	
	function openLayerRole(rowId) {
		//弹出层标题
		var titleVal = rowId==null?'用户新增':'用户修改';
		$.ajax({
			url : 'role/goadd',
			success : function(htmlData) {
				//通过layer的open方法打开弹出层
				layer.open({
					anim : 6,// 弹出动画
					type : 1,// 弹出的是页面层
					title : titleVal,
					area : '800px',// 设置宽度，高度自适应
					content : htmlData,// 这里的content是一个DOM注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
					success : function() {// layui打开成功后的调试
						//判断需要进行的修改动作
						if(rowId) {
							//yongajax的方式再根据id查询要修改的对象的数据
							$.ajax({
								type:'get',
								url : 'role/goupdate/' + rowId,
								success : function(role) {
									//给表单赋值,自动查找赋值对应role的属性
									form.val("form_role_add_edit",role);
									//为了唯一性校验，修改的时候设置一个原来的数据
									//data('old')=<input data-old=''/>
									$('#roleName').data('old',role.roleName);
									// 让form表单渲染一下
									form.render(null, 'form_role_add_edit');
								}
							});
						} else {//进行新增动作
							// 让form表单渲染一下
							form.render(null, 'form_role_add_edit');
						}
					}
				});
			}
		});
	}
	//绑定搜索按钮
	form.on('submit(btn_search)',function(data) {
		//重新渲染table数据
		table.reload('table_role',{
			page:{
				cuur:1//重新从第一页开始
			},
			where:$('#form_search').serialize()//重新配置查询额外的数据
		},'data');
		return false;
	});

});