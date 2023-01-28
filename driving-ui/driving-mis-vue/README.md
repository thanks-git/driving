# README

## 下载依赖

推荐使用`cnpm`方式进行依赖安装 :)

```shell
npm install
```

## 配置前端地址

修改配置文件`driving-mis-vue/vite.config.js`

```javascript
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
```

## 配置后端地址

修改配置文件`driving-mis-vue/src/main.js`
```javascript
// 后端项目的URL根路径
let baseUrl = "http://127.0.0.1:8200/mis/"
```

## 登录

用户名: admin

默认密码: admin123