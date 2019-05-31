<template>
    <div>
        <head-top></head-top>

        <div>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>注意</span>
                </div>
                <div>
                    <div  class="text item">
                        1. 服务器或任何地方不会保存您的私钥，但将保存由此私钥所产生的Credential，用于用户注册时在合约上注册经销商等。
                    </div>
                    <div>
                        2. 每次服务器开关机、重启、或重新服务端程序后，您都需要配置 日常私钥， 否者此系统将不能正常运行。
                    </div>
                </div>
            </el-card>
        </div>

        <el-row style="margin-top: 20px;" >
            <transition name="form-fade" mode="in-out">
                <section class="form_contianer" v-show="showLogin">
                    <div class="manage_tip">
                    </div>
                    <el-form :model="initForm" :rules="rules" ref="initForm">
                        <el-form-item prop="privateKey">
                            <el-input placeholder="日常私钥" v-model="initForm.privateKey" show-password></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="submitForm('initForm')" class="submit_btn">确定</el-button>
                        </el-form-item>
                    </el-form>
                </section>
            </transition>
        </el-row>
    </div>
</template>

<script>

    import { systemInit } from "../untils/speak.js";
    import headTop from '../components/headTop';
    export default {
        data() {
            return {
                initForm: {
                },
                rules: {

                },
                showLogin: true,
            }
        },
        mounted(){
            this.initData();
        },
        methods: {
            async submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        return new Promise((resolve, reject) => {
                            let data = {
                                "privateKey"       : this.initForm.privateKey
                            };
                            console.log(data);
                            console.log(document.cookie);
                            systemInit(data).then(response => {
                                console.log(response);
                                if (response['data']['code'] == -1) {
                                    if (response['data']['msg'] == "permission_denied") {
                                        this.$message.error("权限不够")
                                    } else {
                                        this.$message.error("出现错误：" + response['data']['msg']);
                                    }

                                } else {
                                    this.$message.success("初始化成功!")
                                }
                            })
                        });
                    } else {

                    }
                });
            },
            initData() {

            }
        },
        components: {
            headTop,
        },
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
        //.ctp(1400px, 10px);
        padding: 25px;
        border-radius: 5px;
        text-align: center;
        background-color: #fff;
        .submit_btn{
            width: 100%;
            font-size: 16px;
        }
    }
    .form-fade-enter-active, .form-fade-leave-active {
        transition: all 1s;
    }
    .form-fade-enter, .form-fade-leave-active {
        transform: translate3d(0, -50px, 0);
        opacity: 0;
    }
</style>
