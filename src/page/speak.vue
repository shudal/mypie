<template>
    <div>
        <head-top></head-top>

        <el-row style="margin-top: 20px;" >
            <transition name="form-fade" mode="in-out">
                <section class="form_contianer" >
                    <div class="manage_tip">
                    </div>
                    <el-form :model="myForm" :rules="rules" ref="initForm">
                        <el-form-item prop="content">
                            <el-input placeholder="嘤一句" v-model="myForm.content"></el-input>
                        </el-form-item>
                        人物:
                        <div>
                            <el-radio-group v-model="myForm.per">
                                <el-radio-button label="女声"></el-radio-button>
                                <el-radio-button label="男声"></el-radio-button>
                                <el-radio-button label="度逍遥"></el-radio-button>
                                <el-radio-button label="度丫丫"></el-radio-button>
                            </el-radio-group>
                        </div>
                        语速:
                        <div class="block">
                            <el-slider
                                    v-model="myForm.speed"
                                    show-input
                                    :max="15">
                            </el-slider>
                        </div>
                        嘤量:
                        <div class="block">
                            <el-slider
                                    v-model="myForm.vol"
                                    show-input
                                    :max="15">
                            </el-slider>
                        </div>
                        嘤调:
                        <div class="block">
                            <el-slider
                                    v-model="myForm.pit"
                                    show-input
                                    :max="15">
                            </el-slider>
                        </div>
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

    import { saySomething} from "../untils/speak.js";
    import headTop from '../components/headTop';
    export default {
        data() {
            return {
                myForm: {
                    speed: 5,
                    vol:   5,
                    pit:   5,
                    per: '度丫丫',
                },
                rules: {

                },
            }
        },
        methods: {
            async submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        return new Promise((resolve, reject) => {
                            let data = {
                                "content"       : this.myForm.content,
                                "per"           : this.myForm.per,
                                "speed"         : this.myForm.speed,
                                "vol"           : this.myForm.vol,
                                "pit"           : this.myForm.pit,
                            };
                            console.log(data);
                            saySomething(data).then(response => {
                                console.log(response);
                                if (response['data']['code'] == -1) {
                                    if (response['data']['msg'] == "permission_denied") {
                                        this.$message.error("权限不够")
                                    } else {
                                        this.$message.error("出现错误：" + response['data']['msg']);
                                    }

                                } else {
                                    this.$message.success("成功")
                                }
                            })
                        });
                    } else {

                    }
                });
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
