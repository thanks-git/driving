<template>
	<view class="page">
		<view class="notice">
			<u-icon name="info-circle-fill" color="#AC9146" size="30" top="3" />
			<text>准确填写个人信息，可享受每份代驾订单人身意外险</text>
		</view>
		<view class="credentials-container">
			<view class="credentials">
				<image :src="cardBackground[0]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">身份证正面</text>
					<ocr-navigator @onSuccess="scanIdcardFront" certificateType="idCard" :opposite="false">
						<button class="camera"></button>
					</ocr-navigator>
				</view>
			</view>
			<view class="credentials">
				<image :src="cardBackground[1]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">身份证背面</text>
					<ocr-navigator @onSuccess="scanIdcardBack" certificateType="idCard" :opposite="true">
						<button class="camera"></button>
					</ocr-navigator>
				</view>
			</view>
			<view class="credentials">
				<image :src="cardBackground[2]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">手持身份证</text>
					<button class="camera" @tap="takePhoto('idcardHolding')"></button>
				</view>
			</view>
			<view class="credentials">
				<image :src="cardBackground[3]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">驾驶证正面</text>
<!-- 					<ocr-navigator @onSuccess="scanDrcardFront" certificateType="driverslicense">
						<button class="camera"></button>
					</ocr-navigator> -->
				</view>
			</view>
			<view class="credentials">
				<image :src="cardBackground[4]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">驾驶证背面</text>
					<button class="camera" @tap="takePhoto('drcardBack')"></button>
				</view>
			</view>
			<view class="credentials">
				<image :src="cardBackground[5]" class="bg"></image>
				<view class="cover">
					<image src="../static/filling/card.png" mode="widthFix" class="card"></image>
					<text class="desc">手持驾驶证</text>
					<button class="camera" @tap="takePhoto('drcardHolding')"></button>
				</view>
			</view>
		</view>

		<view class="title">个人信息</view>
		<view class="list">
			<u-cell-group border="false">
				<u-cell-item title="真实姓名" :value="idcard.name" :value-style="style" :arrow="false" />
				<u-cell-item title="性别" :value="idcard.sex" :value-style="style" :arrow="false" />
				<u-cell-item title="生日" :value="idcard.birthday" :value-style="style" :arrow="false" />
				<u-cell-item title="身份证号" :value="idcard.pid" :value-style="style" :arrow="false" />
				<u-cell-item title="身份证地址" :value="idcard.shortAddress" :value-style="style" @click="showAddressContent" />
				<u-cell-item title="身份证有效期" :value="idcard.expiration" :value-style="style" :arrow="false" />
			</u-cell-group>
		</view>
		<view class="title">联系方式</view>
		<view class="list">
			<u-cell-group border="false">
				<u-cell-item title="手机号码" :value="contact.tel" :value-style="style" @click="enterContent('手机号码', 'tel')" />
				<u-cell-item title="电子信箱" :value="contact.shortEmail" :value-style="style" @click="enterContent('电子信箱', 'email')" />
				<u-cell-item
					title="收信地址"
					:value="contact.shortMailAddress"
					:value-style="style"
					@click="enterContent('收信地址', 'mailAddress')"
				/>
				<u-cell-item
					title="紧急联系人"
					:value="contact.contactName"
					:value-style="style"
					@click="enterContent('紧急联系人', 'contactName')"
				/>
				<u-cell-item
					title="紧急联系人电话"
					:value="contact.contactTel"
					:value-style="style"
					@click="enterContent('紧急联系人电话', 'contactTel')"
				/>
			</u-cell-group>
		</view>
		<view class="title">驾驶证</view>
		<view class="list">
			<u-cell-group border="false">
				<u-cell-item title="驾驶证类型" :value="drcard.carClass" :value-style="style" :arrow="false" />
				<u-cell-item title="驾驶证有效期" :value="drcard.validTo" :value-style="style" :arrow="false" />
				<u-cell-item title="初次领证日期" :value="drcard.issueDate" :value-style="style" :arrow="false" />
			</u-cell-group>
		</view>

		<button class="btn" @tap="save" :disabled="realAuth == 3">保存信息</button>
		<view class="remark">您每次提交实名信息之后，都需要工作人员严格审查，请等候1~3天，这期间您将无法接单，特此声明！</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
let dayjs = require('dayjs');
export default {
	data() {
		return {
			mode: 'fill',
			style: {
				color: '#FF9900'
			},
			cardBackground: [
				'../static/filling/credentials-bg.jpg',
				'../static/filling/credentials-bg.jpg',
				'../static/filling/credentials-bg.jpg',
				'../static/filling/credentials-bg.jpg',
				'../static/filling/credentials-bg.jpg',
				'../static/filling/credentials-bg.jpg'
			],
			idcard: {
				pid: '',
				name: '',
				sex: '',
				address: '',
				shortAddress: '',
				birthday: '',
				expiration: '',
				idcardFront: '',
				idcardBack: '',
				idcardHolding: ''
			},
			contact: {
				tel: '',
				email: '',
				shortEmail: '',
				mailAddress: '',
				shortMailAddress: '',
				contactName: '',
				contactTel: ''
			},
			drcard: {
				issueDate: '',
				carClass: '',
				validFrom: '',
				validTo: '',
				drcardFront: '',
				drcardBack: '',
				drcardHolding: ''
			},
			cosImg: [],
			currentImg: {},
			realAuth: uni.getStorageSync('realAuth')
		};
	},
	methods: {
		
	},
	onLoad: function(options) {
		let that = this;
    that.mode = options.mode;
	}
};
</script>

<style lang="less">
@import url('filling.less');
</style>
