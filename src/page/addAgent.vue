<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
				<el-row>
					<el-card :body-style="{ padding: '0px' }">
						<qriously :value="qrcodeValue" size="160" /><div style="padding: 14px;">
						<span>
				让朋友扫码一下或是右键保存二维码到本地后分享吧！</span>
						<br><span>( 也可通过链接分享： <el-link type="underline">{{ qrcodeValue }}</el-link></span>
						<div class="bottom clearfix">
						</div>
					</div>
					</el-card>
				</el-row>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
	// TODO: 验证地址合法性

    import headTop from '@/components/headTop'
    import {baseUrl, baseImgPath} from '@/config/env'
	import {getCookie} from "../untils/util";
    import VueQriously from 'vue-qriously';
	import Vue from 'vue'
    Vue.use(VueQriously);

	import VueCookies from 'vue-cookies';
	Vue.use(VueCookies);
	console.log(window.location.origin);
    export default {
    	data(){
    		return {
				currentDate: new Date(),
    			qrcodeValue: window.location.origin + "/#/register/?pid=" + getCookie("id"),
    			city: {},
    			formData: {
					name: '', //店铺名称
					address: '', //地址
					username: '',
					password: '',
		        },
		        rules: {
					name: [
						{ required: true, message: '请输入名字', trigger: 'blur' },
					],
					address: [
						{ required: true, message: '请输入收款地址', trigger: 'blur' }
					],
					username: [
						{ required: true, message: '请输入登录名' },
					],
					password: [
						{ required: true, message: '请输入密码'}
					],
				},
			    baseUrl,
			    baseImgPath,
    		}
    	},
    	components: {
    		headTop,
    	},
		mounted(){
			this.init();
		},
		methods: {
    		init() {
    			console.log("cookies:" + document.cookie);
			},
			async submitForm(formName) {
				this.$refs[formName].validate(valid => {
					if (valid) {
						console.log("fuck");
						return new Promise((resolve, reject) => {
							let data = {"name": this.formData.name, "username": this.formData.username, "password": this.formData.password, "address": this.formData.address, "parent_id": getCookie("id")};
							console.log(data);
							userLogin(data).then(response => {
								console.log(response);
								if (response['data']['code'] == -1) {
									console.log("add failed");


								} else {
									console.log("add success");
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

<style lang="less">
	@import '../style/mixin';
	.button_submit{
		text-align: center;
	}
	.avatar-uploader .el-upload {
	    border: 1px dashed #d9d9d9;
	    border-radius: 6px;
	    cursor: pointer;
	    position: relative;
	    overflow: hidden;
	}
	.avatar-uploader .el-upload:hover {
	    border-color: #20a0ff;
	}
	.avatar-uploader-icon {
	    font-size: 28px;
	    color: #8c939d;
	    width: 120px;
	    height: 120px;
	    line-height: 120px;
	    text-align: center;
	}
	.avatar {
	    width: 120px;
	    height: 120px;
	    display: block;
	}
	.el-table .info-row {
	    background: #c9e5f5;
	}

	.el-table .positive-row {
	    background: #e2f0e4;
	}

	.time {
		font-size: 13px;
		color: #999;
	}

	.bottom {
		margin-top: 13px;
		line-height: 12px;
	}

	.button {
		padding: 0;
		float: right;
	}

	.image {
		width: 100%;
		display: block;
	}

	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";
	}

	.clearfix:after {
		clear: both
	}
</style>
