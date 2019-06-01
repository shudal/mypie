<template>
    <div>
        <el-row style="margin-top: 20px;" >
            <transition name="form-fade" mode="in-out">
                <section class="form_contianer">
                    <el-form :model="myForm"   ref="myForm">
                        <el-form-item prop="baseVideoSrc">
                            <el-input v-model="myForm.baseVideoSrc" placeholder="视频路径"><span>dsfsf</span></el-input>
                        </el-form-item>
                        <el-form-item prop="basePythonDir">
                            <el-input placeholder="Python程序路径" v-model="myForm.basePythonDir"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('myForm')" class="submit_btn">修改</el-button>
                        </el-form-item>
                    </el-form>
                </section>
            </transition>
        </el-row>
    </div>
</template>

<script>
    import {getMeta, setMeta} from "../../untils/api/meta";

    export default {
        data() {
            return {
                myForm: {
                    baseVideoSrc: '',
                    basePythonDir: '',
                }
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            async init() {
                getMeta().then(response => {
                   if (response['data']['code'] == -1) {
                       this.$message.error("获取配置信息失败");
                   } else {
                       this.myForm.baseVideoSrc    = response['data']['data']['baseVideoSrc'];
                       this.myForm.basePythonDir   = response['data']['data']['basePythonDir'];
                   }
                });
            },
            async submitForm(formName) {
                let data = {
                  'baseVideoSrc': this.myForm.baseVideoSrc,
                  'basePythonDir': this.myForm.basePythonDir
                };
                setMeta(data).then(response => {
                    console.log(response);
                    if (response['data']['code'] == -1) {
                        this.$message.error("设置失败");
                    } else {
                        this.$message.success("修改成功");
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
    @import '../../style/mixin';
    .form_contianer{
        .wh(320px, 210px);
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