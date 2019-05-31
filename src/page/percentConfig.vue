<template>
    <div>
        <head-top></head-top>

        <div>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>注意</span>
                </div>
                <div>
                    <div>
                        1. 当您设置这些分配比例时，您配置的日常私钥需要与部署合约私钥相同。
                    </div>
                    <div>
                        2. 点击确定之后，请耐心等待服务器的响应。
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
                        <el-form-item prop="percentOfFirst">
                            <el-input placeholder="一级分销商所得的比例，为0～100的整数" v-model="initForm.percentOfFirst"></el-input>
                        </el-form-item>
                        <el-form-item prop="percentOfSecond">
                            <el-input placeholder="二级分销商所得的比例，为0～100的整数" v-model="initForm.percentOfSecond"></el-input>
                        </el-form-item>
                        <el-form-item prop="percentOfThird">
                            <el-input placeholder="三级分销商所得的比例，为0～100的整数" v-model="initForm.percentOfThird"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="submitForm('initForm')" class="submit_btn">确定</el-button>
                        </el-form-item>

                        <el-form-item>
                            <el-button  v-show="showQueryButton">点击查询部署状态</el-button>
                        </el-form-item>
                    </el-form>
                </section>
            </transition>
        </el-row>
    </div>
</template>

<script>
    import {setPercents} from "../untils/MainContract";
    import headTop from '../components/headTop';
    export default {
        data () {
            return {
                initForm: {

                },
                showLogin: true,
                showQueryButton: false
            }
        },
        components: {
            headTop,
        },
        methods: {
            async submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        return new Promise((resolve, reject) => {
                            let data = {
                                "percentOfFirst"      : this.initForm.percentOfFirst,
                                "percentOfSecond"     : this.initForm.percentOfSecond,
                                "percentOfThird"      : this.initForm.percentOfThird
                            };
                            console.log(data);
                            setPercents(data).then(response => {
                                console.log(response);
                                if (response['data']['code'] == -1) {
                                    if (response['data']['msg'] == "permission_denied") {
                                        this.$message.error("权限不够");
                                    } else if (response['data']['msg'] == "meta_invalid") {
                                        this.$message.error("分销商分配比例填写错误！");
                                    } else if (response['data']['msg'] == "ts_failed") {
                                        this.$message.error("修改失败");
                                    } else {
                                        this.$message.error("出现错误!");
                                    }
                                } else {
                                    this.$message.success("修改成功!" + response['data']['data']);

                                }
                            })
                        });
                    } else {

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
