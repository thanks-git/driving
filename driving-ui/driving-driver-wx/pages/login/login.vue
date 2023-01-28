<template>
	<view>
		<image src="../../static/login/top.png" mode="widthFix" class="top"></image>
		<image src="../../static/login/logo.png" mode="widthFix" class="logo"></image>
		<view class="desc">
			<text class="name">（司机端）</text>
			<text class="version">Ver20XX.201</text>
		</view>
		<button class="btn" @tap="login()">微信登陆</button>
		<view class="register-container">
			没有账号?
			<text class="link" @tap="toRegisterPage()">立即注册</text>
		</view>
		<text class="remark">本小程序仅限于华夏代驾签约司机使用，新司机必须先注册，经由实名认证之后方可在本小程序中接单运营。</text>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
export default {
	data() {
		return {};
	},
	methods: {
		toRegisterPage: () => {
      uni.navigateTo({
        url:'../register/register'
      })
    }
	},
	onLoad: function() {
		
	}
};
</script>

<style lang="less">
@import url('login.less');
</style>
