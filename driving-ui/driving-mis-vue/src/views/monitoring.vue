<template>
	<div v-if="isAuth(['ROOT', 'MONITORING:SELECT'])">
		<div class="form">
			<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
				<el-form-item prop="orderId"><el-input v-model="dataForm.orderId" placeholder="订单编号" size="medium" clearable="clearable" /></el-form-item>
				<el-form-item>
					<el-date-picker
						v-model="dataForm.date"
						type="daterange"
						range-separator="~"
						start-placeholder="开始日期"
						end-placeholder="结束日期"
						size="medium"
					></el-date-picker>
				</el-form-item>
				<el-form-item>
					<el-select v-model="dataForm.safety" class="input" placeholder="内容评估" size="medium" clearable="clearable">
						<el-option label="安全" value="common" />
						<el-option label="有害" value="danger" />
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-select v-model="dataForm.needReview" class="input" placeholder="人工审核" size="medium" clearable="clearable">
						<el-option label="需要审核" value="true" />
						<el-option label="不需要审核" value="false" />
					</el-select>
				</el-form-item>
				<el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
			</el-form>
			<el-radio-group v-model="dataForm.alarm" @change="changeWalletType">
				<el-radio-button label="全部"></el-radio-button>
				<el-radio-button label="未报警"></el-radio-button>
				<el-radio-button label="已报警"></el-radio-button>
			</el-radio-group>
		</div>
		<el-table :data="dataList" border v-loading="dataListLoading" cell-style="padding: 4px 0" size="medium" style="width: 100%;">
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="orderId" header-align="center" align="center" label="订单编号" min-width="200" />
			<el-table-column prop="status" header-align="center" align="center" label="代驾状态" min-width="140" />
			<el-table-column prop="records" header-align="center" align="center" label="录音数量" min-width="140" />
			<el-table-column prop="safety" header-align="center" align="center" label="安全等级" min-width="140">
				<template #default="scope">
					<el-tag :type="scope.row.safety == '安全' ? 'success' : 'danger'">{{ scope.row.safety }}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="reviews" header-align="center" align="center" label="待审核" min-width="140" />
			<el-table-column prop="createTime" header-align="center" align="center" label="日期" min-width="140" />
			<el-table-column header-align="center" align="center" width="300" label="操作">
				<template #default="scope">
					<el-button size="small" type="primary" :disabled="!isAuth(['ROOT', 'DEPT:UPDATE'])" @click="updateHandle(scope.row.id)">呼叫司机</el-button>
					<el-button size="small" type="primary" :disabled="!isAuth(['ROOT', 'DEPT:DELETE']) || scope.row.emps > 0" @click="deleteHandle(scope.row.id)">
						呼叫客户
					</el-button>
					<el-button size="small" type="danger">报警</el-button>
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
	</div>
</template>

<script>
export default {
	data: function() {
		return {
			dataForm: {
				orderId: null,
				date: [],
				safety: null,
				needReview: null,
				alarm: '全部'
			},
			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataRule: {
				orderId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '订单编号格式错误' }]
			}
		};
	},
	methods: {
		
	},
	created: function() {
		
	}
};
</script>

<style lang="less" scoped="scoped">
.form {
	display: flex;
	justify-content: space-between;
}
</style>
