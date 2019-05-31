<template>
    <div>
        <div>
            <head-top></head-top>
        </div>
        <div>
            <el-row style="margin-top: 20px;" >
                <transition name="form-fade" mode="in-out">
                    <section class="form_contianer" >
                        <div class="manage_tip">
                        </div>
                        <el-form :model="initForm" :rules="rules" ref="initForm">
                            <el-form-item prop="oldPwd">
                                <el-input placeholder="旧密码" v-model="initForm.oldPwd" show-password></el-input>
                            </el-form-item>
                            <el-form-item prop="newPwd">
                                <el-input placeholder="新密码" v-model="initForm.newPwd" show-password></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button type="primary" @click="submitForm('initForm')" class="submit_btn">确定</el-button>
                            </el-form-item>
                        </el-form>
                    </section>
                </transition>
            </el-row>
        </div>
    </div>
</template>

<script>
    import {changePwd} from "../untils/user";
    import headTop from '../components/headTop';
    export default {
        data() {
            return {
                initForm: {

                },
                rules: {

                }
            }
        },
        methods: {
            async submitForm(formName) {
                let data = {
                  "oldPwd": this.initForm.oldPwd,
                  "newPwd": this.initForm.newPwd
                };
                changePwd(data).then(response => {
                    if (response['data']['code'] == -1) {
                        if (response['data']['msg'] == "password_wrong") {
                            this.$message.error("旧密码错误");
                        } else {
                            this.$message.error("出现未知错误");
                        }
                    } else {
                        this.$message.success("修改成功");
                    }
                })
            }
        },
        components: {
            headTop,
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