<template>
	<el-dialog title="关闭订单" v-if="isAuth(['ROOT', 'ORDER:UPDATE'])" :close-on-click-modal="false" v-model="visible" width="420px">
		<el-form :model="dataForm" ref="dataForm" :rules="dataRule" label-width="80px">
			<el-form-item label="订单状态" prop="status">
				<el-select v-model="dataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
					<el-option label="事故关闭" value="11" />
					<el-option label="其他" value="12" />
				</el-select>
			</el-form-item>
			<el-form-item label="关闭原因" prop="remark"><el-input v-model="dataForm.remark" rows="4" type="textarea" size="medium" style="width:100%" clearable /></el-form-item>
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
				orderId: null,
				status: null,
				remark: null
			},
			dataRule: {
				status: [{ required: true, message: '必须选择订单状态' }],
				remark: [{ required: true, message: '必须填写备注信息' }]
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
