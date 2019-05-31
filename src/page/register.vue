<template>
    <div class="login_page fillcontain">
        <transition name="form-fade" mode="in-out">
            <section class="form_contianer" v-show="showLogin">
                <div class="manage_tip">
                    <p>加入</p>
                </div>
                <el-form :model="registerForm" :rules="rules" ref="registerForm">
                    <el-form-item prop="name">
                        <el-input v-model="registerForm.name" placeholder="名字"><span>dsfsf</span></el-input>
                    </el-form-item>
                    <el-form-item prop="username">
                        <el-input v-model="registerForm.username" placeholder="用户名"><span>dsfsf</span></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="address">
                        <el-input placeholder="收款地址" v-model="registerForm.address"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('registerForm')" class="submit_btn">创建</el-button>
                        <div v-show="showQueryButton"> <br>
                        <el-button @click="queryStatus()"  type="info" class="submit_btn">查询创建状态</el-button>
                        </div>
                        </el-form-item>
                </el-form>
                <p class="tip">温馨提示:</p>
                <p class="tip">用户名为字母、数字、特殊符号等的结合</p>
            </section>
        </transition>
    </div>
</template>

<script>
    // TODO: 校验输入合法性

    import {getUrlKey, setCookie, getCookie} from "../untils/util";
    import { userRegister, createStatus } from "../untils/user.js";

    console.log("register");
    export default {
        mounted:function() {
          this.init();
        },
        data() {
            return {
                registerForm: {
                    username: '',
                    password: '',
                },
                rules: {
                    name: [
                        {required: true, message: '请输入名字', trigger: 'blur'},
                    ],
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ],

                },
                showLogin: true,
                showQueryButton: false,
            }
        },
        methods : {
            init : function() {
                this.$message({
                    message: '您受到代理商id为' + getUrlKey("pid") + "的邀请",
                    type: 'success'
                });
            },

            async submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        return new Promise((resolve, reject) => {
                            let data = {
                                "name"      : this.registerForm.name,
                                "username"  : this.registerForm.username,
                                "password"  : this.registerForm.password,
                                "address"   : this.registerForm.address,
                                "parent_id"       : getUrlKey("pid")
                            };
                            console.log(data);
                            userRegister(data).then(response => {
                                console.log(response);
                                if (response['data']['code'] == -1) {
                                    if (response['data']['msg'] == "username_exists") {
                                        this.$message.error('用户名已经存在');
                                    } else if (response['data']['msg'] == "address_invalid") {
                                        this.$message.error('地址不合法');
                                    } else {
                                        this.$message.error('创建失败');
                                    }
                                } else {
                                    this.$message({
                                        message: "创建即将成功， 请等待片刻后查询创建状态",
                                        type: 'success'
                                    });

                                    setCookie("registerTransactionHash", response['data']['data']);
                                    this.showQueryButton = true;
                                }
                            });
                        });
                    }
                });
            },
            async queryStatus() {
                let transactionHash = getCookie("registerTransactionHash");
                console.log(transactionHash);
                createStatus(transactionHash).then(response => {
                    console.log(response);
                    if (response['data']['code'] == -1) {
                        this.$message.error("失败，请稍候查询");
                    } else {
                        this.$message.success("创建成功");
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
        .wh(320px, 360px);
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
