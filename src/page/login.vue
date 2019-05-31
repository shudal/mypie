<template> 
	<div class="login_page fillcontain">
		<transition name="form-fade" mode="in-out">
			<section class="form_contianer" v-show="showLogin">
				<div class="manage_tip">
					<p>My Pie!</p>
				</div>
			<el-form :model="loginForm" :rules="rules" ref="loginForm">
				<el-form-item prop="username">
					<el-input v-model="loginForm.username" placeholder="用户名"><span>dsfsf</span></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" placeholder="密码" v-model="loginForm.password"></el-input>
				</el-form-item>
				<el-form-item>
				<el-button type="primary" @click="submitForm('loginForm')" class="submit_btn">登陆</el-button>
				</el-form-item>
			</el-form>
			<p class="tip">温馨提示:</p>
			<p class="tip">用户名为字母、数字、特殊符号等的结合</p>
			</section>
		</transition>
	</div>
</template>
<script>
import { userLogin } from "../untils/user.js";
import {setCookie} from "../untils/util";
import Vue from 'vue';
import VueCookies from 'vue-cookies';
Vue.use(VueCookies);

export default {
  data() {
	  return {
		  loginForm: {
			  username: '',
			  password: '',
		  },
		  rules: {
			  username: [
				  {required: true, message: '请输入用户名', trigger: 'blur'},
			  ],
			  password: [
				  {required: true, message: '请输入密码', trigger: 'blur'},
			  ],
		  },
		  showLogin: true,
	  }
  },
  methods: { 
	  async submitForm(formName) {
	  	this.$refs[formName].validate(valid => {
	  		if (valid) {
	  			console.log("fuck");
	  			return new Promise((resolve, reject) => {
	  				let data = {"username": this.loginForm.username, "password": this.loginForm.password};
	  				console.log(data);
					userLogin(data).then(response => {
						console.log(response);
						if (response['data']['code'] == -1) {
							console.log("login failed");

							let theTip = "出现错误";

							if (response['data']['msg'] == "password_wrong") {
								theTip = "密码错误";
							}

							if (response['data']['msg'] == "user_not_exist") {
								theTip = "用户不存在";
							}


							if (response['data']['msg'] == "login_already") {
								this.$message.error("已经登录过了");
							} else {
								this.$alert(theTip, '失败', {
									confirmButtonText: '确定',
								});
							}
						} else {
							console.log("login success");


							setCookie("id", 	response['data']['data']['id']);
							setCookie("name",	response['data']['data']['name']);
							/*
							setCookie("role",	response['data']['data']['role']);
							*/

							/*
							this.$cookies.set("id", 	response['data']['data']['id']);
							this.$cookies.set("name",	response['data']['data']['name']);
							this.$cookies.set("role",	response['data']['data']['role']);
							 */
							this.$router.push('manage');
						}
						resolve();
					})
							.catch(error => {
								reject(error);
							});
				});
			} else {
	  			console.log("error submit");
	  			return false;
			}
		});
	  }
  }
}
</script>
<style lang="less" scoped>
	// TODO： 	1)限制ip登录次数
	// 			2)验证码
	//			3)验证账号密码长度
	@import '../style/mixin';
	.login_page{
		background-color: #324057;
	}
	.manage_tip{
		position: absolute;
		width: 100%;
		top: -100px;
		left: 0;
		p{
			font-size: 34px;
			color: #fff;
		}
	}
	.form_contianer{
		.wh(320px, 210px);
		.ctp(320px, 210px);
		padding: 25px;
		border-radius: 5px;
		text-align: center;
		background-color: #fff;
		.submit_btn{
			width: 100%;
			font-size: 16px;
		}
	}
	.tip{
		font-size: 12px;
		color: red;
	}
	.form-fade-enter-active, .form-fade-leave-active {
	  	transition: all 1s;
	}
	.form-fade-enter, .form-fade-leave-active {
	  	transform: translate3d(0, -50px, 0);
	  	opacity: 0;
	}
</style>

