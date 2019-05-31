import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login  			= r => require.ensure([], () => r(require('@/page/login')),    			'login');
const manage 			= r => require.ensure([], () => r(require('@/page/manage')),   			'manage');
const home   			= r => require.ensure([], () => r(require('@/page/home')),     			'home');
const addAgent 			= r => require.ensure([], () => r(require('@/page/addAgent')), 			'addAgent');
const register  		= r => require.ensure([], () => r(require('@/page/register')), 			'register');
const deployContract 	= r => require.ensure([], () => r(require('@/page/deployContract')),	'deployContract');
const systemInit		= r => require.ensure([], () => r(require('@/page/systemInit')), 		'systemInit');
const percentConfig 	= r => require.ensure([], () => r(require('@/page/percentConfig')), 	'percentConfig');
const changePwd			= r => require.ensure([], () => r(require('@/page/changePwd')),			'changePwd');

const speak				= r => require.ensure([], () => r(require('@/page/speak')),				'speak');
const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/register',
		name: 'register',
		component: register
	},
	{
		path: '/manage',
		component: manage,
		name: '',
		children: [
			{path: '', 					component: home, 			meta: []},
			{path: '/addAgent',			component: addAgent,		meta: ['添加代理商', '邀请代理商']},
			{path: '/deployContract', 	component: deployContract, 	meta: ['配置', '部署合约']},
			{path: '/systemInit', 		component: systemInit,		meta: ['配置', '程序初始化']},
			{path: '/percentConfig',	component: percentConfig,   meta: ['配置',	'比例配置']},
			{path: '/speak',			component: speak,			meta: ['声嘤','说花']},
			{path: '/changePwd',		component: changePwd,		meta: ['我的账户','修改密码']}
		]
	}
]

export default new Router({
	routes,
})
