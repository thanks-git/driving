<template>
	<div v-if="isAuth(['ROOT', 'ORDER:SELECT'])">
		<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
			<el-form-item prop="orderId"><el-input v-model="dataForm.orderId" placeholder="订单编号" size="medium" clearable="clearable" /></el-form-item>
			<el-form-item prop="driverId"><el-input v-model="dataForm.driverId" placeholder="司机编号" size="medium" clearable="clearable" /></el-form-item>
			<el-form-item prop="customerId"><el-input v-model="dataForm.customerId" placeholder="客户编号" size="medium" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-date-picker v-model="dataForm.date" type="daterange" range-separator="~" start-placeholder="开始日期" end-placeholder="结束日期" size="medium"></el-date-picker>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
					<el-option label="等待接单" value="1" />
					<el-option label="已接单" value="2" />
					<el-option label="司机已到达" value="3" />
					<el-option label="开始代驾" value="4" />
					<el-option label="结束代驾" value="5" />
					<el-option label="未付款" value="6" />
					<el-option label="已付款" value="7" />
					<el-option label="订单已结束" value="8" />
					<el-option label="顾客撤单" value="9" />
					<el-option label="司机撤单" value="10" />
					<el-option label="事故关闭" value="11" />
					<el-option label="其他" value="12" />
				</el-select>
			</el-form-item>
			<el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
		</el-form>
		<el-table
			:data="dataList"
			border
			v-loading="dataListLoading"
			cell-style="padding: 4px 0"
			style="width: 100%;"
			size="medium"
			@expand-change="expand"
			:row-key="getRowKeys"
			:expand-row-keys="expands"
		>
			<el-table-column prop="panel" header-align="center" align="center" type="expand">
				<template #default="scope">
					<div class="content-container">
						<div class="left">
							<div class="order-table">
								<div class="row">
									<div class="th">客户编号</div>
									<div class="td">{{ panel.customer.id }}</div>
									<div class="th">客户性别</div>
									<div class="td">{{ panel.customer.sex }}</div>
									<div class="th">客户电话</div>
									<div class="td">{{ panel.customer.tel }}</div>
								</div>
								<div class="row">
									<div class="th">车型</div>
									<div class="td">{{ panel.order.carType }}</div>
									<div class="th">车牌</div>
									<div class="td">{{ panel.order.carPlate }}</div>
									<div class="th">车辆隶属</div>
									<div class="td">{{ panel.order.city }}</div>
								</div>
								<div class="row">
									<div class="th">司机编号</div>
									<div class="td">{{ panel.driver.id }}</div>
									<div class="th">司机姓名</div>
									<div class="td">{{ panel.driver.name }}</div>
									<div class="th">司机电话</div>
									<div class="td">{{ panel.driver.tel }}</div>
								</div>
								<div class="row">
									<div class="th">接单时间</div>
									<div class="td">{{ panel.order.acceptTime }}</div>
									<div class="th">到达时间</div>
									<div class="td">{{ panel.order.arriveTime }}</div>
									<div class="th">代驾等时</div>
									<div class="td">{{ panel.order.waitingMinute }}</div>
								</div>
								<div class="row">
									<div class="th">开始时间</div>
									<div class="td">{{ panel.order.startTime }}</div>
									<div class="th">结束时间</div>
									<div class="td">{{ panel.order.endTime }}</div>
									<div class="th">代驾时长</div>
									<div class="td">{{ panel.order.driveMinute }}</div>
								</div>
								<div class="row">
									<div class="th">代驾里程</div>
									<div class="td">{{ panel.order.realMileage }}</div>
									<div class="th">订单费用</div>
									<div class="td">{{ panel.order.realFee }}</div>
									<div class="th">状态</div>
									<div class="td">{{ panel.order.status }}</div>
								</div>
								<div class="row">
									<div class="th">费用规则</div>
									<div class="td">{{ panel.order.chargeRule }}</div>
									<div class="th">取消规则</div>
									<div class="td">{{ panel.order.cancelRule }}</div>
									<div class="th">分账规则</div>
									<div class="td">{{ panel.order.profitsharingRule }}</div>
								</div>
							</div>
						</div>
						<div class="right"><div :id="panel.id" class="map"></div></div>
					</div>
				</template>
			</el-table-column>
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="id" header-align="center" align="center" min-width="200" label="订单编号" />
			<el-table-column prop="startPlace" header-align="center" align="center" min-width="220" show-overflow-tooltip="true" label="代驾起点" />
			<el-table-column prop="endPlace" header-align="center" align="center" min-width="220" show-overflow-tooltip="true" label="代驾终点" />
			<el-table-column prop="realMileage" header-align="center" align="center" min-width="130" label="实际里程" />
			<el-table-column prop="realFee" header-align="center" align="center" min-width="130" label="实际金额" />
			<el-table-column prop="createTime" header-align="center" align="center" min-width="150" label="日期时间" />
			<el-table-column prop="status" header-align="center" align="center" min-width="100" label="状态" />
			<el-table-column header-align="center" align="center" width="150" label="操作">
				<template #default="scope">
					<el-button
						type="text"
						size="medium"
						v-if="isAuth(['ROOT', 'ORDER:SELECT'])"
						:disabled="!['结束代驾', '未付款', '已付款', '订单已结束'].includes(scope.row.status)"
						@click="showOrderBill(scope.row.id)"
					>
						账单详情
					</el-button>
					<el-button
						type="text"
						size="medium"
						v-if="isAuth(['ROOT', 'ORDER:UPDATE'])"
						:disabled="!['等待接单', '已接单', '司机已到达', '开始代驾'].includes(scope.row.status)"
						@click="showUpdatePanel(scope.row.id)"
					>
						关闭订单
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
		<order-bill ref="orderBill"></order-bill>
		<order-close ref="orderClose" @refreshDataList="loadDataList"></order-close>
	</div>
</template>

<script>
import OrderBill from './order_bill.vue';
import OrderClose from './order-close.vue';

import $ from 'jquery';
import { calculateCarPlateCity } from '../utils/car_plate.js';
let status = {
	'1': '等待接单',
	'2': '已接单',
	'3': '司机已到达',
	'4': '开始代驾',
	'5': '结束代驾',
	'6': '未付款',
	'7': '已付款',
	'8': '订单已结束',
	'9': '顾客撤单',
	'10': '司机撤单',
	'11': '事故关闭',
	'12': '其他'
};
export default {
	components: {
		OrderBill,
		OrderClose
	},
	data() {
		return {
			dataForm: {
				orderId: null,
				driverId: null,
				customerId: null,
				date: [],
				status: null
			},
			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataRule: {
				orderId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '订单编号格式错误' }],
				driverId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '司机编号格式错误' }],
				customerId: [{ required: false, pattern: '^[1-9]\\d{17}$', message: '客户编号格式错误' }]
			},
			expands: [],
			panel: {
				id: null,
				customer: {
					id: null,
					sex: null,
					tel: null
				},
				driver: {
					id: null,
					name: null,
					tel: null
				},
				order: {
					carPlate: null,
					carType: null,
					city: null,
					acceptTime: null,
					arriveTime: null,
					startTime: null,
					endTime: null,
					waitingMinute: null,
					driveMinute: null,
					realMileage: null,
					realFee: null,
					status: null,
					chargeRule: null,
					cancelRule: null,
					profitsharingRule: null
				}
			},
			getRowKeys(row) {
				return row.id;
			},
			gpsTimer: null
		};
	},
	methods: {
		
	},
	created: function() {
		
	}
};
</script>
<style lang="less" scoped="scoped">
@import url('order.less');
</style>
