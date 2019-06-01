import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login  			= r => require.ensure([], () => r(require('@/page/login')),    			'login');
const manage 			= r => require.ensure([], () => r(require('@/page/manage')),   			'manage');
const home   			= r => require.ensure([], () => r(require('@/page/home')),     			'home');
const systemManage	    = r => require.ensure([], () => r(require('@/page/systemManage')),			'changePwd');
const speak				= r => require.ensure([], () => r(require('@/page/speak')),				'speak');
const videoPorn			= r => require.ensure([], () => r(require('@/page/video/porn')),		'videoPorn')
const SetMeta			= r => require.ensure([], () => r(require('@/page/meta/SetMeta')),				'meta')

const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/manage',
		component: manage,
		name: '',
		children: [
			{path: '', 					component: home, 			meta: []},
			{path: '/speak',			component: speak,			meta: ['声嘤','说花']},
			{path: '/systemManage',		component: systemManage,	meta: ['系统管理','系统管理']},
			{path: '/videoPorn', 		component: videoPorn,		meta: ['视频', '有趣']},
			{path: '/meta',				component: SetMeta,			meta: ['配置', '配置']},
		]
	}
]

export default new Router({
	routes,
})
