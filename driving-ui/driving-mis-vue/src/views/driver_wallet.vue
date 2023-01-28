<template>
	<div v-if="isAuth(['ROOT', 'DRIVER:SELECT'])">
		<div class="form">
			<el-form :inline="true" :model="incomeDataForm" :rules="dataRule" ref="incomeDataForm" v-if="walletType == '入账数据'">
				<el-form-item prop="tel"><el-input v-model="incomeDataForm.tel" placeholder="司机电话" size="medium" class="input" clearable="clearable" /></el-form-item>
				<el-form-item>
					<el-date-picker
						v-model="incomeDataForm.date"
						type="daterange"
						range-separator="~"
						start-placeholder="开始日期"
						end-placeholder="结束日期"
						size="medium"
					></el-date-picker>
				</el-form-item>
				<el-form-item>
					<el-select v-model="incomeDataForm.type" class="input" placeholder="类别" size="medium" clearable="clearable">
						<el-option label="充值" value="1" />
						<el-option label="奖励" value="2" />
						<el-option label="补贴" value="3" />
					</el-select>
				</el-form-item>
				<el-form-item>
					<el-select v-model="incomeDataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
						<el-option label="未支付" value="1" />
						<el-option label="已支付" value="2" />
						<el-option label="已到账" value="3" />
					</el-select>
				</el-form-item>
				<el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
			</el-form>
			<el-form :inline="true" :model="paymentDataForm" :rules="dataRule" ref="paymentDataForm" v-if="walletType == '出账数据'">
				<el-form-item prop="tel"><el-input v-model="paymentDataForm.tel" placeholder="司机电话" size="medium" class="input" clearable="clearable" /></el-form-item>
				<el-form-item>
					<el-date-picker
						v-model="paymentDataForm.date"
						type="daterange"
						range-separator="~"
						start-placeholder="开始日期"
						end-placeholder="结束日期"
						size="medium"
					></el-date-picker>
				</el-form-item>
				<el-form-item>
					<el-select v-model="paymentDataForm.type" class="input" placeholder="类别" size="medium" clearable="clearable">
						<el-option label="话费" value="1" />
						<el-option label="罚款" value="2" />
						<el-option label="抽奖" value="3" />
						<el-option label="缴费" value="3" />
						<el-option label="其他" value="3" />
					</el-select>
				</el-form-item>
				<el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
			</el-form>
			<el-radio-group v-model="walletType" @change="changeWalletType">
				<el-radio-button label="入账数据"></el-radio-button>
				<el-radio-button label="出账数据"></el-radio-button>
			</el-radio-group>
		</div>
		<el-table :data="dataList" border v-loading="dataListLoading" cell-style="padding: 4px 0;height:44px" style="width: 100%;" size="medium" v-if="walletType == '入账数据'">
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="id" header-align="center" align="center" min-width="200" label="编号" />
			<el-table-column prop="name" header-align="center" align="center" min-width="130" label="持有人" />
			<el-table-column prop="amount" header-align="center" align="center" min-width="130" label="入账金额" />
			<el-table-column prop="type" header-align="center" align="center" min-width="130" label="类型" />
			<el-table-column prop="remark" header-align="center" align="center" min-width="300" label="备注" />
			<el-table-column prop="status" header-align="center" align="center" min-width="130" label="状态" />
			<el-table-column prop="createTime" header-align="center" align="center" min-width="200" label="日期时间" />
		</el-table>
		<el-table :data="dataList" border v-loading="dataListLoading" cell-style="padding: 4px 0;height:44px" style="width: 100%;" size="medium" v-if="walletType == '出账数据'">
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="id" header-align="center" align="center" min-width="200" label="编号" />
			<el-table-column prop="name" header-align="center" align="center" min-width="130" label="持有人" />
			<el-table-column prop="amount" header-align="center" align="center" min-width="130" label="出账金额" />
			<el-table-column prop="type" header-align="center" align="center" min-width="130" label="类型" />
			<el-table-column prop="remark" header-align="center" align="center" min-width="300" label="备注" />
			<el-table-column prop="createTime" header-align="center" align="center" min-width="200" label="日期时间" />
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
	data() {
		return {
			walletType: '入账数据',
			incomeDataForm: {
				tel: null,
				type: null,
				status: null,
				date: []
			},
			paymentDataForm: {
				tel: null,
				type: null,
				date: []
			},

			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataRule: {
				tel: [{ required: false, pattern: '^1\\d{10}$', message: '电话格式错误' }]
			},
			getRowKeys(row) {
				return row.taskId;
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
