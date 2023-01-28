<template>
	<view>
		<view class="face-container">
			<camera device-position="front" flash="off" class="camera" @error="error" v-if="showCamera">
				<cover-image src="../static/face_camera/bg.png" class="bg"></cover-image>
			</camera>
			<view class="image-container" v-if="showImage">
				<image mode="widthFix" class="photo" :src="photoPath"></image>
				<view class="cover"></view>
			</view>
		</view>
		<view class="desc">
			<block v-if="mode == 'verificate'">
				<image src="../static/face_camera/tips.png" mode="widthFix" class="tips"></image>
				<text>请把面部放在圆圈内</text>
				<text>拍摄脸部来确认身份</text>
			</block>
			<block v-if="mode == 'create'">
				<image src="../static/face_camera/face.png" mode="widthFix" class="face"></image>
				<text>请把完整面部放在圆圈内</text>
				<text>拍摄脸部来保存身份识别数据</text>
			</block>
		</view>
		<button class="btn" @tap="confirmHandle">{{ mode == 'create' ? '录入面部信息' : '身份核实' }}</button>
	</view>
</template>

<script>
let dayjs = require('dayjs');
export default {
	data() {
		return {
			mode: 'verificate',
			photoPath: '',
			showCamera: true,
			showImage: false,
			audio: null
		};
	},
	methods: {
		
	},
	onLoad: function(options) {
		
	},
	onHide:function(){
		
	}
};
</script>

<style lang="less">
@import url('face_camera.less');
</style>
