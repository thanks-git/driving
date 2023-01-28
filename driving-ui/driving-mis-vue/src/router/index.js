import {
	createRouter,
	createWebHashHistory
}
from 'vue-router'
import Login from '../views/login.vue'
import Main from "../views/main.vue"
import Home from "../views/home.vue"
import Role from "../views/role.vue"
import User from "../views/user.vue"
import Dept from "../views/dept.vue"
import Driver from "../views/driver.vue"
import DriverWallet from "../views/driver_wallet.vue"
import DriverFine from "../views/driver_fine.vue"
import DriverLockdown from "../views/driver_lockdown.vue"
import Order from "../views/order.vue"
import Monitoring from "../views/monitoring.vue"

import NotFound from "../views/404.vue"


const routes = [{
		path: '/login',
		name: 'Login',
		component: Login
	},
	{
		path: '/',
		name: 'Main',
		component: Main,
		children: [{
				path: '/home',
				name: 'Home',
				component: Home,
				meta: {
					title: '首页',
				}
			},
			{
				path: "/role",
				name: "Role",
				component: Role,
				meta: {
					title: "角色管理",
					isTab: true
				}
			},
			{
				path: '/user',
				name: 'User',
				component: User,
				meta: {
					title: '用户管理',
					isTab: true
				}
			},
			{
				path: '/dept',
				name: 'Dept',
				component: Dept,
				meta: {
					title: '部门管理',
					isTab: true
				}
			},
			{
				path: '/driver',
				name: 'Driver',
				component: Driver,
				meta: {
					title: '司机信息',
					isTab: true
				}
			},
			{
				path: '/driver_wallet',
				name: 'DriverWallet',
				component: DriverWallet,
				meta: {
					title: '司机钱包',
					isTab: true
				}
			},
			{
				path: '/driver_fine',
				name: 'DriverFine',
				component: DriverFine,
				meta: {
					title: '司机罚款',
					isTab: true
				}
			},
			{
				path: '/driver_lockdown',
				name: 'DriverLockdown',
				component: DriverLockdown,
				meta: {
					title: '禁止接单',
					isTab: true
				}
			},
			{
				path: '/order',
				name: 'Order',
				component: Order,
				meta: {
					title: '订单信息',
					isTab: true
				}
			}, {
				path: '/monitoring',
				name: 'Monitoring',
				component: Monitoring,
				meta: {
					title: '安全监控',
					isTab: true
				}
			}
		]
	},
	{
		path: "/404",
		name: "NotFound",
		component: NotFound
	},
	{
		path: '/:pathMatch(.*)*',
		redirect: "/404"
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})
router.beforeEach((to, from, next) => {
	if (to.name != "Login") {
		let permissions = localStorage.getItem("permissions")
		let token = localStorage.getItem("token")
		if (permissions == null || permissions == "" || token == null || token == "") {
			next({
				name: 'Login'
			})
		}
	}
	return next()
})

export default router
