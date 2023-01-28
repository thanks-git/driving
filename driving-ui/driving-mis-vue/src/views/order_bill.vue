<template>
	<el-dialog title="代驾账单详情" v-if="isAuth(['ROOT', 'ORDER:SELECT'])" :close-on-click-modal="false" v-model="visible" width="620px" top="3vh" destroy-on-close>
		<el-scrollbar height="800px" ref="scroll">
			<div>
				<div>
					<div class="setion-title">
						<img src="../assets/order/money.png" />
						<span>基础收费</span>
					</div>
					<div class="section-content">
						<div class="item">
							<div class="left">
								<span class="item-title">里程费（{{ realMileage }}公里）</span>
								<span class="item-desc">时段收费（{{ baseMileagePrice }}元{{ baseMileage }}公里，超出每公里{{ exceedMileagePrice }}元）</span>
							</div>
							<div class="right">{{ mileageFee }}</div>
						</div>
						<div class="item">
							<div class="left">
								<span class="item-title">时长费（{{ waitingMinute }}分钟）</span>
								<span class="item-desc">免费{{ baseMinute }}分钟，超出部分每分钟{{ exceedMinutePrice }}元</span>
							</div>
							<div class="right">{{ waitingFee }}</div>
						</div>
						<div class="item">
							<div class="left">
								<span class="item-title">返程费（{{ returnMileage }}公里）</span>
								<span class="item-desc">总里程超过{{ baseReturnMileage }}公里，每公里{{ exceedReturnPrice }}元</span>
							</div>
							<div class="right">{{ returnFee }}</div>
						</div>
					</div>
				</div>
				<div>
					<div class="setion-title">
						<img src="../assets/order/money.png" />
						<span>额外收费</span>
					</div>
					<div class="section-content">
						<div class="item">
							<div class="left">
								<span class="item-title">停车费</span>
								<span class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</span>
							</div>
							<div class="right">{{ parkingFee }}</div>
						</div>
						<div class="item">
							<div class="left">
								<span class="item-title">路桥费</span>
								<span class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</span>
							</div>
							<div class="right">{{ tollFee }}</div>
						</div>
						<div class="item">
							<div class="left">
								<span class="item-title">其他费用</span>
								<span class="item-desc">代驾过程中产生的其他费用</span>
							</div>
							<div class="right">{{ otherFree }}</div>
						</div>
					</div>
				</div>
				<div>
					<div class="setion-title">
						<img src="../assets/order/money.png" />
						<span>奖励费</span>
					</div>
					<div class="section-content">
						<div class="item">
							<div class="left">
								<span class="item-title">客户好处费</span>
								<span class="item-desc">代驾客户赠与的红包奖励</span>
							</div>
							<div class="right">{{ favourFee }}</div>
						</div>
						<div class="item">
							<div class="left">
								<span class="item-title">系统奖励费</span>
								<span class="item-desc">华夏代驾系统激励代驾司机的奖励</span>
							</div>
							<div class="right">{{ incentiveFee }}</div>
						</div>
					</div>
				</div>
				<div>
					<div class="setion-title">
						<img src="../assets/order/money.png" />
						<span>总金额</span>
					</div>
					<div class="section-content">
						<div class="content-container">
							<div class="left">
								<div class="content">
									【汇总合计】
									<span class="red">¥ {{ total }} 元</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</el-scrollbar>
	</el-dialog>
</template>

<script>
export default {
	data: function() {
		return {
			visible: false,
			favourFee: null,
			incentiveFee: null,
			realMileage: null,
			mileageFee: null,
			baseMileagePrice: null,
			baseMileage: null,
			exceedMileagePrice: null,
			waitingFee: null,
			baseMinute: null,
			waitingMinute: null,
			exceedMinutePrice: null,
			returnFee: null,
			baseReturnMileage: null,
			exceedReturnPrice: null,
			returnMileage: null,
			parkingFee: null,
			tollFee: null,
			otherFree: null,
			total: null
		};
	},

	methods: {
		init: function(id) {
			
		}
	}
};
</script>

<style lang="less" scoped="scoped">
@import url('order_bill.less');
</style>
