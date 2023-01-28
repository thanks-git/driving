<template>
	<el-dialog
		:title="!dataForm.id ? '新增' : '修改'"
		v-if="isAuth(['ROOT', 'DRIVER_LOCKDOWN:INSERT', 'DRIVER_LOCKDOWN:UPDATE'])"
		:close-on-click-modal="false"
		v-model="visible"
		width="420px"
	>
		<el-form :model="dataForm" ref="dataForm" :rules="dataRule" label-width="80px">
			<el-form-item label="司机编号" prop="driverId">
				<el-input v-model="dataForm.driverId" :disabled="dataForm.id" size="medium" style="width:100%" clearable />
			</el-form-item>
			<el-form-item label="订单编号" prop="orderId"><el-input v-model="dataForm.orderId" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="具体原因" prop="reason"><el-input v-model="dataForm.reason" :rows="2" type="textarea" size="medium" style="width:100%" clearable /></el-form-item>
			<el-form-item label="起始日期" prop="startDate"><el-date-picker v-model="dataForm.startDate" type="date" placeholder="起始日期" /></el-form-item>
			<el-form-item label="截止日期" prop="endDate"><el-date-picker v-model="dataForm.endDate" type="date" placeholder="截止日期" /></el-form-item>
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
import dayjs from 'dayjs';
export default {
	data: function() {
		return {
			visible: false,
			dataForm: {
				id: null,
				driverId: null,
				orderId: null,
				startDate: null,
				endDate: null,
				reason: null
			},
			dataRule: {
				driverId: [{ required: true, pattern: '^[1-9]\\d{17}$', message: '司机编号格式错误' }],
				orderId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '订单编号格式错误' }],
				startDate: [{ required: true, message: '起始日期必填' }],
				endDate: [{ required: true, message: '截止日期必填' }],
				reason: [
					{
						required: true,
						message: '原因不能为空'
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
