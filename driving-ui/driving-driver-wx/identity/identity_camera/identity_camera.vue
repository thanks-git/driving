<template>
	<view>
		<camera device-position="back" flash="off" class="camera" @error="error" v-if="showCamera"></camera>
		<image mode="widthFix" class="image" :src="photoPath" v-if="showImage"></image>
		<view class="operate-container">
			<button type="primary" class="btn" @tap="clickBtn">{{ btnText }}</button>
			<button type="warn" class="btn" @tap="afresh">重拍</button>
		</view>
		<view class="notice-container">
			<text class="notice">注意事项</text>
			<text class="desc">
				<block v-if="type!='driverCardBack'">拍照的时候，必须把证件拍摄清晰并完整，否则影响识别结果。持证拍照的时候不许戴帽子、墨镜和面纱等遮挡五官的饰物，只拍摄上半身即可。手持证件，五官与证件必须同时拍摄到照片中。</block>
				<block v-if="type=='driverCardBack'">拍照的时候，必须把驾驶证背面拍摄的清晰完整，否则影响认证结果。</block>
			</text>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			type: null,
        	photoPath: '',
        	btnText: '拍照',
        	showCamera: true,
        	showImage: false
		};
	},
	onLoad:function(options){
		this.type = options.type;
	},
	methods: {
		
	}
};
</script>

<style lang="less">
@import url('identity_camera.less');
</style>
