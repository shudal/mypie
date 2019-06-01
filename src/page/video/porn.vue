<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
			<el-pagination
					background
					layout="prev, pager, next"
					@current-change="handleCurrentChange"
					:page-count="pageTotal"
					:current-page="currentPage"
			>
			</el-pagination>
  			<el-col :span="12" :offset="4">
				<el-row>
					<ol>
						<li v-for="item in vs">
							<video :src="baseVideoPath + 'porn/' + item" height="150px" controls="controls"></video>
						</li>
					</ol>
				</el-row>
  			</el-col>
  		</el-row>
    </div>
</template>

<script>
	// TODO: 验证地址合法性

    import headTop from '@/components/headTop'
	import {getVideo} from "../../untils/api/happy";
	import {baseVideoPath} from "@/config/env";

    export default {
    	data(){
    		return {
    			vs: [],
				baseVideoPath: baseVideoPath,
				pageTotal: 1,
				currentPage: 1
    		}
    	},
    	components: {
    		headTop,
    	},
		mounted(){
			this.init();
		},
		methods: {
			async init() {
				getVideo("porn", 10, 1).then(response => {
					console.log(response);
					if (response['data']['code'] == -1) {
						console.log("request failed");
						this.$message.error("加载失败");
					} else {
						console.log("request success");
						this.vs = response['data']['data']['videos'];
						this.pageTotal = response['data']['data']['totalPages'];
					}
				})
			},
			handleCurrentChange(val) {
				console.log(val);
				getVideo("porn", 10, val).then(response => {
					console.log(response);
					if (response['data']['code'] == -1) {
						console.log("request failed");
						this.$message.error("加载失败");
					} else {
						console.log("request success");
						this.vs = response['data']['data']['videos'];
						//this.pageTotal = response['data']['data']['totalPages'];
					}
				})
			}
		}

    }
</script>

<style lang="less">
	@import '../../style/mixin';
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
