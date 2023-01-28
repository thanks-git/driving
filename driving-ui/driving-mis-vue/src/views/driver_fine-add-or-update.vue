<template>
	<el-dialog
		:title="!dataForm.id ? '新增' : '修改'"
		v-if="isAuth(['ROOT', 'DRIVER_FINE:INSERT', 'DRIVER_FINE:UPDATE'])"
		:close-on-click-modal="false"
		v-model="visible"
		width="420px"
	>
		<el-form :model="dataForm" ref="dataForm" :rules="dataRule" label-width="80px">
			<el-form-item label="司机编号" prop="driverId"><el-input v-model="dataForm.driverId" :disabled="dataForm.id" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="订单编号" prop="orderId"><el-input v-model="dataForm.orderId" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="罚款金额" prop="amount"><el-input v-model="dataForm.amount" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="备注信息" prop="remark"><el-input v-model="dataForm.remark" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="付款状态" prop="status">
				<el-radio-group v-model="dataForm.status">
					<el-radio-button label="未缴纳"></el-radio-button>
					<el-radio-button label="已缴纳"></el-radio-button>
				</el-radio-group>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button size="medium" @click="visible = false">取消</el-button>
				<el-button type="primary" size="medium" @click="dataFormSubmit">确定</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script>
export default {
	data: function() {
		return {
			visible: false,
			dataForm: {
				id: null,
				driverId: null,
				orderId: null,
				amount: null,
				remark: null,
				status: '未缴纳'
			},
			dataRule: {
				driverId: [{ required: true, pattern: '^[1-9]\\d{17}$', message: '司机编号格式错误' }],
				orderId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '订单编号格式错误' }],
				amount: [
					{
						required: true,
						pattern: '^[1-9]\\d*\\.\\d{1,2}$|^0\\.\\d{1,2}$|^[1-9]\\d*$',
						message: '金额格式错误'
					}
				],
				remark: [
					{
						required: true,
						message: '备注不能为空'
					}
				]
			}
		};
	},

	methods: {
		init: function(id) {
			
		},
		dataFormSubmit: function() {
			
		}
	}
};
</script>

<style lang="less" scoped="scoped"></style>
