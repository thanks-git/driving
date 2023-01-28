<template>
<view>
		<view class="customer-container">
			<u-avatar :src="photo" mode="square"></u-avatar>
			<view class="info">
				<view class="customer-name">代驾客户（{{ title }}）</view>
				<view class="customer-tel">Tel：{{ tel }}</view>
			</view>
		</view>
		<view class="address-container">
			<view class="from">
				<text>{{ startPlace }}</text>
			</view>
			<view class="dashed-line"></view>
			<view class="to">
				<text>{{ endPlace }}</text>
			</view>
		</view>
		<view class="order-container">
			<view>【 订单号码 】 {{ orderId }}</view>
			<view>【 下单时间 】 {{ createTime }}</view>
			<view>【 客户红包 】 {{ favourFee }}元</view>
			<view>【 系统奖励 】 {{ incentiveFee }}元</view>
			<view>【 代驾车型 】 {{ carType }}</view>
			<view>【 代驾车牌 】 {{ carPlate }}</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../static/order/money.png" mode="widthFix"></image>
				<text>基础收费</text>
			</view>
			<view class="section-content">
				<view class="item">
					<view class="left">
						<text class="item-title">里程费（{{ realMileage }}公里）</text>
						<text class="item-desc">时段收费（{{ baseMileagePrice }}元{{ baseMileage }}公里，超出每公里{{ exceedMileagePrice }}元）</text>
					</view>
					<view class="right">{{ mileageFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">时长费（{{ waitingMinute }}分钟）</text>
						<text class="item-desc">免费{{ base_minute }}分钟，超出部分每分钟{{ exceedMinutePrice }}元</text>
					</view>
					<view class="right">{{ waitingFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">返程费（{{ returnMileage }}公里）</text>
						<text class="item-desc">总里程超过{{ baseReturnMileage }}公里，每公里{{ exceedReturnPrice }}元</text>
					</view>
					<view class="right">{{ returnFee }}</view>
				</view>
			</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../static/order/money.png" mode="widthFix"></image>
				<text>额外收费</text>
			</view>
			<view class="section-content">
				<view class="item">
					<view class="left">
						<text class="item-title">停车费</text>
						<text class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</text>
					</view>
					<view class="right">{{ parkingFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">路桥费</text>
						<text class="item-desc">如果代驾司机预付停车费，该费用将计入订单费用</text>
					</view>
					<view class="right">{{ tollFee }}</view>
				</view>
				<view class="item">
					<view class="left">
						<text class="item-title">其他费用</text>
						<text class="item-desc">代驾过程中产生的其他费用</text>
					</view>
					<view class="right">{{ otherFree }}</view>
				</view>
			</view>
		</view>
		<view>
			<view class="setion-title">
				<image src="../static/order/money.png" mode="widthFix"></image>
				<text>总金额</text>
			</view>
			<view class="section-content">
				<view class="content-container">
					<view class="left">
						<view class="content">
							【汇总合计】
							<text>¥ {{ total }} 元</text>
						</view>
						<view class="content">
							【减免金额】
							<text>¥ {{ voucherFee }} 元</text>
						</view>
						<view class="content">
							【实付金额】
							<text class="red">¥ {{ realPay }} 元</text>
						</view>
					</view>
					<image :src="img" mode="widthFix" class="img"></image>
				</view>
			</view>
		</view>
		<view class="operate-container" v-if="status == 6"><view class="operate" @tap="enterFeeHandle">输入相关费用</view></view>

		<view v-if="status >= 7">
			<view class="setion-title">
				<image src="../static/order/rate.png" mode="widthFix"></image>
				<text>客户评价</text>
			</view>
			<view class="section-content">
				<view class="remark-container">
					<view class="remark-rate">
						<view class="photo"><u-avatar :src="photo" size="60" /></view>
						<view class="rate">
							<u-rate :count="comment.count" v-model="comment.value" disabled="true" active-color="#FFBB2A" size="40" />
							<view v-if="comment.value<=2">我要申诉</view>
						</view>
					</view>
					<view class="remark">{{ comment.remark }}</view>
				</view>
			</view>
		</view>

		<view class="contact-container">
			<view class="contact">
				<text class="label">客服电话：</text>
				<text class="tel">0411-87143331</text>
			</view>
			<view class="contact">
				<text class="label">服务监督电话：</text>
				<text class="tel">0411-87143331</text>
			</view>
		</view>
		<u-top-tips ref="uTips"></u-top-tips>
	</view>
</template>

<script>
export default {
	data() {
		return {
			orderId: null,
			customerId:null,
			photo: '',
			title: '',
			tel: '',
			startPlace: '',
			endPlace: '',
			createTime: '',
			favourFee: '',
			incentiveFee: '',
			carPlate: '',
			carType: '',
			status: null,
			realMileage: '--',
			mileageFee: '暂无',
			baseMileagePrice: '',
			baseMileage: '',
			exceedMileagePrice: '',

			waitingFee: '暂无',
			base_minute: '',
			waitingMinute: '--',
			exceedMinutePrice: '',
			returnFee: '暂无',
			baseReturnMileage: '',
			exceedReturnPrice: '',
			returnMileage: '--',
			parkingFee: '暂无',
			tollFee: '暂无',
			otherFree: '暂无',
			total: '--',
			voucherFee: '--',
			realPay: '--',
			img: '',
			comment: {
				count: 5,
				value: 0,
				remark: '[ 客户没有评价，系统默认为好评~ ]'
			}
		};
	},
	methods: {
		
	},
	onLoad: function(options) {
		
	},
	onShow: function() {
		
	},
	onHide: function() {
		
	}
};
</script>

<style lang="less">
@import url('order.less');
</style>
