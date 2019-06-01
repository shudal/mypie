<template>
    <div>
        <div>
            <head-top></head-top>
        </div>
        <div>
            <el-row style="margin-top: 20px;" >
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>
                            系统操作
                        </span>
                    </div>
                    <el-button type="danger" @click="shutdown()" plain>关机</el-button>
                    <el-button type="danger" @click="reboot" plain>重启</el-button>
                </el-card>
            </el-row>
        </div>
    </div>
</template>

<script>
    import {changePwd} from "../untils/user";
    import {shutdown, reboot} from "../untils/systemManage";
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
            },
            async shutdown() {
                shutdown().then(response => {
                    console.log(response);
                    if (response['data']['code'] == -1) {
                        this.$message.error("出现未知错误");
                    } else {
                        this.$message.success("修改成功");
                    }
                })
            },
            async reboot() {
                reboot().then(response => {
                    console.log(response);
                    if (response['data']['code'] == -1) {
                        this.$message.error("出现未知错误");
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
    .text {
        font-size: 14px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
</style>