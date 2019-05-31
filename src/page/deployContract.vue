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
                        1. 服务器或任何地方不会保存您的私钥
                    </div>
                    <div>
                        2. 此私钥对系统有着绝对的控制权。请妥善保管。
                    </div>
                    <div>
                        3. 点击确定后，请您耐心等待服务器的响应。
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

                        <el-form-item prop="privateKey">
                            <el-input placeholder="厂家私钥" v-model="initForm.privateKey" show-password></el-input>
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

    import { deployContract, getMainContractAddress } from "../untils/speak.js";
    import headTop from '../components/headTop';
    export default {
        data() {
            return {
                initForm: {
                },
                rules: {
                    percentOfFirst: [
                        {required: true, message:'请输入一级分销商所得比例', trigger: 'blur'}
                    ],
                    percentOfSecond: [
                        {required: true, message:'请输入二级分销商所得比例', trigger: 'blur'}
                    ],
                    percentOfThird: [
                        {required: true, message:'请输入三级分销商所得比例', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ],

                },
                showLogin: true
            }
        },
        mounted(){
          this.initData();
        },
        methods: {
            async queryDeployStatus() {
                getMainContractAddress().then(response => {
                    console.log(response);
                    if (response['data']['code'] == -1) {
                        if (response['data']['msg'] == "receipt_null") {
                            this.$message.error("暂未查到, 请稍后重试");
                        } else {
                            this.$message.error("出现位置错误:" + response['data']['msg']);
                        }
                    } else {
                        this.$message.success("成功获取合约地址：" + response['data']['data']);
                    }
                })
            },
            async submitForm(formName) {
                this.$refs[formName].validate(valid => {
                   if (valid) {
                       return new Promise((resolve, reject) => {
                          let data = {
                              "percentOfFirst"      : this.initForm.percentOfFirst,
                              "percentOfSecond"     : this.initForm.percentOfSecond,
                              "percentOfThird"      : this.initForm.percentOfThird,
                              "privateKey"       : this.initForm.privateKey
                          };
                          console.log(data);
                          console.log(document.cookie);
                          deployContract(data).then(response => {
                              console.log(response);
                              if (response['data']['code'] == -1) {
                                  if (response['data']['msg'] == "permission_denied") {
                                      this.$message.error("权限不够");
                                  } else if (response['data']['msg'] == "meta_invalid") {
                                      this.$message.error("分销商所得填写错误！");
                                  } else if (response['data']['msg'] == "address_invalid") {
                                      this.$message.error("地址格式错误");
                                  } else if (response['data']['msg'] == 'tx_failed') {
                                      this.$message.error("部署失败");
                                  } else {
                                      this.$message.error("出现错误!");
                                  }
                              } else {
                                  this.$message.success("部署成功!交易Hash为" + response['data']['data']);
                                  console.log(response['data']['data']);
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
