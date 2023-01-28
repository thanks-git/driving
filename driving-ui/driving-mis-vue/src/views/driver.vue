<template>
	<div v-if="isAuth(['ROOT', 'DRIVER:SELECT'])">
		<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
			<el-form-item prop="name"><el-input v-model="dataForm.name" placeholder="姓名" size="medium" class="input" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.sex" class="input" placeholder="性别" size="medium" clearable="clearable">
					<el-option label="男" value="男" />
					<el-option label="女" value="女" />
				</el-select>
			</el-form-item>
			<el-form-item prop="tel"><el-input v-model="dataForm.tel" placeholder="电话" size="medium" class="input" clearable="clearable" /></el-form-item>
			<el-form-item prop="pid"><el-input v-model="dataForm.pid" placeholder="身份证" size="medium" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.realAuth" class="input" placeholder="实名认证" size="medium" clearable="clearable">
					<el-option label="未认证" value="1" />
					<el-option label="已认证" value="2" />
					<el-option label="审核中" value="3" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
					<el-option label="正常" value="1" />
					<el-option label="禁用" value="2" />
					<el-option label="减少接单" value="3" />
				</el-select>
			</el-form-item>
			<el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
		</el-form>
		<el-table
			:data="dataList"
			border
			v-loading="dataListLoading"
			cell-style="padding: 4px 0"
			style="width: 100%;"
			size="medium"
			@expand-change="expand"
			:row-key="getRowKeys"
			:expand-row-keys="expands"
		>
			<el-table-column prop="content" header-align="center" align="center" type="expand">
				<template #default="scope">
					<div class="content-container">
						<div class="left">
							<table class="content-table">
								<tbody>
									<tr>
										<th>开车驾龄</th>
										<th>生日</th>
										<th>邮箱</th>
										<th>收信地址</th>
										<th>身份证住址</th>
									</tr>
									<tr>
										<td>{{ content.year }}年</td>
										<td>{{ content.birthday }}</td>
										<td>{{ content.email }}</td>
										<td>{{ content.mailAddress }}</td>
										<td>{{ content.idcardAddress }}</td>
									</tr>
								</tbody>
							</table>
							<ul class="credentials-list">
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.idcardFront" :preview-src-list="content.idcardFrontList" fit="cover" />
									<p>【 身份证正面 】</p>
								</li>
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.idcardBack" :preview-src-list="content.idcardBackList" fit="cover" />
									<p>【 身份证背面 】</p>
								</li>
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.idcardHolding" :preview-src-list="content.idcardHoldingList" fit="cover" />
									<p>【 手持身份证 】</p>
								</li>
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.drcardFront" :preview-src-list="content.drcardFrontList" fit="cover" />
									<p>【 驾驶证正面 】</p>
								</li>
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.drcardBack" :preview-src-list="content.drcardBackList" fit="cover" />
									<p>【 驾驶证背面 】</p>
								</li>
								<li class="item">
									<el-image style="width: 350px; height: 220px" :src="content.drcardHolding" :preview-src-list="content.drcardHoldingList" fit="cover" />
									<p>【 手持驾驶证 】</p>
								</li>
							</ul>
						</div>
						<div class="right" v-if="scope.row.realAuth == '已认证'">
							<div class="summary-panel">
								<h3 class="title">【 代驾数据摘要 】</h3>
								<ul class="monthly-list">
									<li class="item">
										<p class="section">月订单金额</p>
										<p class="value">
											<span>￥</span>
											{{ content.total }}
										</p>
									</li>
									<li class="item">
										<p class="section">月订单分账</p>
										<p class="value">
											<span>￥</span>
											{{ content.income }}
										</p>
									</li>
									<li class="item">
										<p class="section">代扣个税</p>
										<p class="value">
											<span>￥</span>
											{{ content.tax }}
										</p>
									</li>
								</ul>
								<ul class="statistics-list">
									<li class="item">
										<p class="section">总代驾单数</p>
										<p class="value">{{ content.count }}</p>
									</li>
									<li class="item">
										<p class="section">好评总数</p>
										<p class="value">{{ content.ct1 }}</p>
									</li>
									<li class="item">
										<p class="section">差评总数</p>
										<p class="value">{{ content.ct2 }}</p>
									</li>
									<li class="item">
										<p class="section">差评总数</p>
										<p class="value">{{ content.ct3 }}</p>
									</li>
								</ul>
							</div>
							<div id="chart"></div>
						</div>
					</div>
				</template>
			</el-table-column>
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="name" header-align="center" align="center" min-width="100" label="姓名" />
			<el-table-column prop="sex" header-align="center" align="center" min-width="60" label="性别" />
			<el-table-column prop="tel" header-align="center" align="center" min-width="130" label="电话" />
			<el-table-column prop="pid" header-align="center" align="center" min-width="190" label="身份证" />
			<el-table-column prop="contactName" header-align="center" align="center" min-width="130" label="紧急联络人" />
			<el-table-column prop="contactTel" header-align="center" align="center" min-width="130" label="联络人电话" />
			<el-table-column prop="realAuth" header-align="center" align="center" min-width="130" label="实名认证" />
			<el-table-column prop="status" header-align="center" align="center" min-width="100" label="状态" />
			<el-table-column header-align="center" align="center" width="250" label="操作">
				<template #default="scope">
					<el-button type="text" size="medium" v-if="isAuth(['ROOT', 'USER:UPDATE'])" @click="showApproveModel(scope.row.id)" :disabled="scope.row.realAuth != '审核中'">
						审批认证
					</el-button>
					<el-button type="text" size="medium" v-if="isAuth(['ROOT', 'USER:UPDATE'])" @click="showRepealModel(scope.row.id)" :disabled="scope.row.realAuth != '已认证'">
						撤销认证
					</el-button>
					<el-button type="text" size="medium" v-if="isAuth(['ROOT', 'USER:UPDATE'])" @click="showUpdatePanel(scope.row.id)">设置状态</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			@size-change="sizeChangeHandle"
			@current-change="currentChangeHandle"
			:current-page="pageIndex"
			:page-sizes="[10, 20, 50]"
			:page-size="pageSize"
			:total="totalCount"
			layout="total, sizes, prev, pager, next, jumper"
		></el-pagination>
		<el-dialog v-model="approveModelVisible" title="提示信息" width="400px" center>
			<div class="notice">你确定已经核准该代驾司机的实名认证？</div>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="approveHandle(false)">拒绝</el-button>
                    <el-button type="primary" @click="approveHandle(true)">批准</el-button>
				</span>
			</template>
		</el-dialog>
		<el-dialog v-model="repealModelVisible" title="提示信息" width="400px" center>
			<div class="notice">你确定取消该代驾司机的实名认证？</div>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="repealModelVisible = false">取消</el-button>
					<el-button type="primary" @click="repealHandle">确定</el-button>
				</span>
			</template>
		</el-dialog>
		<update ref="update" @refreshDataList="loadDataList"></update>
	</div>
</template>
<script>
import $ from 'jquery';
import Update from './driver-update.vue';
export default {
	components: {
		Update
	},
	data() {
		return {
			dataForm: {
				name: null,
				sex: null,
				tel: null,
				pid: null,
				realAuth: null,
				status: null
			},
			chart: '',
			dataList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataRule: {
				name: [{ required: false, pattern: '^[\u4e00-\u9fa5]{1,10}$', message: '姓名格式错误' }],
				tel: [{ required: false, pattern: '^1\\d{10}$', message: '电话格式错误' }],
				pid: [{ required: false, pattern: '^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$', message: '身份证格式错误' }]
			},
			expands: [],
			content: {
				year: null,
				birthday: null,
				email: null,
				mailAddress: null,
				idcardAddress: null,
				idcardFront: null,
				idcardFrontList: [],
				idcardBack: null,
				idcardBackList: [],
				idcardHolding: null,
				idcardHoldingList: [],
				drcardFront: null,
				drcardFrontList: [],
				drcardBack: null,
				drcardBackList: [],
				drcardHolding: null,
				drcardHoldingList: [],
				total: null,
				income: null,
				tax: null,
				count: null,
				ct1: null,
				ct2: null,
				ct3: null
			},
			approveModelVisible: false,
			repealModelVisible: false,
			driverId: null,
			getRowKeys(row) {
				return row.id;
			}
		};
	},
	methods: {
		
	},
	created: function() {
		
	}
};
</script>
<style lang="less" scoped="scoped">
@import url('driver.less');
</style>
