<template>
	<div v-if="isAuth(['ROOT', 'DRIVER_FINE:SELECT'])">
		<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
			<el-form-item prop="orderId"><el-input v-model="dataForm.orderId" placeholder="订单编号" size="medium" clearable="clearable" /></el-form-item>
			<el-form-item prop="tel"><el-input v-model="dataForm.tel" placeholder="司机电话" size="medium" class="input" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-date-picker v-model="dataForm.date" type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期" size="medium"></el-date-picker>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
					<el-option label="未缴纳" value="1" />
					<el-option label="已缴纳" value="2" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button size="medium" type="primary" @click="searchHandle()">查询</el-button>
				<el-button size="medium" type="primary" :disabled="!isAuth(['ROOT', 'DEPT:INSERT'])" @click="addHandle()">新增</el-button>
				<el-button size="medium" type="danger" :disabled="!isAuth(['ROOT', 'DEPT:DELETE'])" @click="deleteHandle()">批量删除</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle" cell-style="padding: 4px 0" size="medium" style="width: 100%;">
			<el-table-column type="selection" :selectable="selectable" header-align="center" align="center" width="50" />
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="name" header-align="center" align="center" label="司机姓名" min-width="130" />
			<el-table-column prop="orderId" header-align="center" align="center" label="订单编号" min-width="200" />
			<el-table-column prop="amount" header-align="center" align="center" label="罚款金额" min-width="130" />
			<el-table-column prop="remark" header-align="center" align="center" label="备注信息" min-width="300" />
			<el-table-column prop="status" header-align="center" align="center" label="状态" min-width="130" />
			<el-table-column prop="createTime" header-align="center" align="center" label="日期时间" min-width="200" />
			<el-table-column header-align="center" align="center" width="150" label="操作">
				<template #default="scope">
					<el-button type="text" size="medium" :disabled="!isAuth(['ROOT', 'DRIVER_FINE:UPDATE']) || scope.row.status == '已缴纳'" @click="updateHandle(scope.row.id)">
						修改
					</el-button>
					<el-button type="text" size="medium" :disabled="!isAuth(['ROOT', 'DRIVER_FINE:DELETE']) || scope.row.status == '已缴纳'" @click="deleteHandle(scope.row.id)">
						删除
					</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			@size-change="sizeChangeHandle"
			@current-change="currentChangeHandle"
			:current-page="pageIndex"
			:page-sizes="[10, 20, 50]"
			:page-size="pageSize"
			:total="totalCount"
			layout="total, sizes, prev, pager, next, jumper"
		></el-pagination>
		<add-or-update ref="addOrUpdate" @refreshDataList="loadDataList"></add-or-update>
	</div>
</template>

<script>
import AddOrUpdate from './driver_fine-add-or-update.vue';
export default {
	components: {
		AddOrUpdate
	},
	data: function() {
		return {
			dataForm: {
				orderId: null,
				tel: null,
				status: null,
				date: []
			},
			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataListSelections: [],
			dataRule: {
				orderId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '订单编号格式错误' }],
				tel: [{ required: false, pattern: '^1\\d{10}$', message: '电话格式错误' }]
			}
		};
	},
	methods: {
		
		
	},
	created: function() {
		
	}
};
</script>

<style></style>
