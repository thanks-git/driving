const path = require('path')
import vue from '@vitejs/plugin-vue'
import viteSvgIcons from 'vite-plugin-svg-icons';



module.exports = {
	base: '/mis',
	server: {
		//前端服务启动端口
		port: 3000,
		//是否弹出浏览器
		open: true,
		//允许跨域
		cors: true
	},
	plugins: [
		vue(),
		//引入SVG图标素材文件
		viteSvgIcons({
			iconDirs: [path.resolve(process.cwd(), 'src/icons/svg')],
			symbolId: '[name]',
		})
	]
}
