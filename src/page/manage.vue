<template>
    <div class="manage_page fillcontain">
        <el-row style="height: 100%;">
            <el-col :span="4"  style="min-height: 100%; ">
                <el-menu :default-active="defaultActive" style="min-height: 100%;" theme="dark" router>
                    <el-menu-item index="manage"><i class="el-icon-menu"></i>首页</el-menu-item>

                    <el-submenu index="4">
                        <template slot="title"><i class="el-icon-star-on"></i>声嘤</template>
                        <el-menu-item index="speak">说一句</el-menu-item>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title"><i class="el-icon-plus"></i>添加代理商</template>
                        <el-menu-item index="addAgent">邀请好友</el-menu-item>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title"><i class="el-icon-setting"></i>配置</template>
                        <el-menu-item index="percentConfig">比例配置</el-menu-item>
                        <el-menu-item index="systemInit">程序初始化</el-menu-item>
                        <el-menu-item index="deployContract">部署合约</el-menu-item>
                    </el-submenu>
                    <el-submenu index="7">
                        <template slot="title"><i class="el-icon-user-solid"></i>我的账户</template>
                        <el-menu-item index="changePwd">修改密码</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-col>
            <el-col :span="20" style="height: 100%;overflow: auto;">
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </el-col>
        </el-row>
    </div>
</template>

<script>

    export default {
        computed: {
            defaultActive: function(){
                return this.$route.path.replace('/', '');
            }
        },
    }
</script>

<style lang="less" scoped>
    @import '../style/mixin';
    .manage_page{

    }
</style>