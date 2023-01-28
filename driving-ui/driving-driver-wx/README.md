# README

## 下载依赖

推荐使用`cnpm`安装依赖

```shell
npm install
```

## 配置微信AppID以及插件

修改`driving-driver-wx/manifast.json`文件:

```json
{
  "mp-weixin": {
    // 填写app id
    "appid": "",
    "optimization": {
      "subPackages": true
    },
    "setting": {
      "urlCheck": false,
      "minified": true
    },
    "usingComponents": true,
    "plugins": {
      //OCR证件识别
      // "ocr-plugin": {
      //   "version": "",
      //   "provider": ""
      // },
      //文字转语音播报
      // "WechatSI": {
      //   "version": "",
      //   "provider": ""
      // },
      //路线规划
      // "routePlan": {
      //   "version": "",
      //   "provider": ""
      // },
      //地图选点
      // "chooseLocation": {
      //   "version": "",
      //   "provider": ""
      // }
    }
  }
}
```

## 配置后端地址

修改`driving-driver-wx/main.js`文件:

```javascript
// 配置服务地址
let baseUrl = "http://172.19.32.1:8200/driver"

Vue.prototype.url = {

}
```