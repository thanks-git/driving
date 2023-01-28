<template>
	<div v-if="isAuth(['ROOT', 'USER:SELECT'])">
		<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
			<el-form-item prop="name"><el-input v-model="dataForm.name" placeholder="姓名" size="medium" class="input" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.sex" class="input" placeholder="性别" size="medium" clearable="clearable">
					<el-option label="男" value="男" />
					<el-option label="女" value="女" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.role" class="input" placeholder="角色" size="medium" clearable="clearable">
					<el-option v-for="one in roleList" :label="one.roleName" :value="one.roleName" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.deptId" class="input" placeholder="部门" size="medium" clearable="clearable">
					<el-option v-for="one in deptList" :label="one.deptName" :value="one.id" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-select v-model="dataForm.status" class="input" placeholder="状态" size="medium" clearable="clearable">
					<el-option label="在职" value="1" />
					<el-option label="离职" value="2" />
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button size="medium" type="primary" @click="searchHandle()">查询</el-button>
				<el-button size="medium" type="primary" :disabled="!isAuth(['ROOT', 'USER:INSERT'])" @click="addHandle()">新增</el-button>
				<el-button size="medium" type="danger" :disabled="!isAuth(['ROOT', 'USER:DELETE'])" @click="deleteHandle()">批量删除</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle" cell-style="padding: 4px 0" style="width: 100%;" size="medium">
			<el-table-column type="selection" header-align="center" align="center" width="50" />
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="name" header-align="center" align="center" min-width="100" label="姓名" />
			<el-table-column prop="sex" header-align="center" align="center" min-width="60" label="性别" />
			<el-table-column prop="tel" header-align="center" align="center" min-width="130" label="电话" />
			<el-table-column prop="email" header-align="center" align="center" min-width="240" label="邮箱" :show-overflow-tooltip="true" />
			<el-table-column prop="roles" header-align="center" align="center" min-width="150" label="角色" :show-overflow-tooltip="true" />
			<el-table-column prop="dept" header-align="center" align="center" min-width="120" label="部门" />
			<el-table-column prop="status" header-align="center" align="center" min-width="100" label="状态" />
			<el-table-column header-align="center" align="center" width="150" label="操作">
				<template #default="scope">
					<el-button type="text" size="medium" v-if="isAuth(['ROOT', 'USER:UPDATE'])" @click="updateHandle(scope.row.id)">修改</el-button>
					<el-button
						type="text"
						size="medium"
						v-if="isAuth(['ROOT', 'USER:UPDATE'])"
						:disabled="scope.row.status == '离职' || scope.row.root"
						@click="dimissHandle(scope.row.id)"
					>
						离职
					</el-button>
					<el-button type="text" size="medium" :disabled="scope.row.root" v-if="isAuth(['ROOT', 'USER:DELETE'])" @click="deleteHandle(scope.row.id)">删除</el-button>
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
		<add-or-update ref="addOrUpdate" @refreshDataList="loadDataList"></add-or-update>

	</div>
</template>

<script>
import AddOrUpdate from './user-add-or-update.vue';

export default {
	components: {
		AddOrUpdate
	},
	data() {
		return {
			dataForm: {
				name: null,
				sex: null,
				role: null,
				deptId: null,
				status: null
			},
			dataList: [],
			roleList: [],
			deptList: [],
			pageIndex: 1,
			pageSize: 10,
			totalCount: 0,
			dataListLoading: false,
			dataListSelections: [],
			dataRule: {
				name: [{ required: false, pattern: '^[\u4e00-\u9fa5]{1,10}$', message: '姓名格式错误' }]
			}
		};
	},
	methods: {
		loadDataList: function() {
			let that = this;
			that.dataListLoading = true;
			let data = {
				page: that.pageIndex,
				length: that.pageSize,
				name: that.dataForm.name == '' ? null : that.dataForm.name,
				sex: that.dataForm.sex == '' ? null : that.dataForm.sex,
				role: that.dataForm.role == '' ? null : that.dataForm.role,
				deptId: that.dataForm.deptId == '' ? null : that.dataForm.deptId,
				status: that.dataForm.status == '' ? null : that.dataForm.status
			};

			that.$http('user/searchUserByPage', 'POST', data, true, function(resp) {
				let page = resp.page;
				let list = page.list;
				for (let one of list) {
					if (one.status == 1) {
						one.status = '在职';
					} else if (one.status == 2) {
						one.status = '离职';
					}
				}
				that.dataList = list;
				that.totalCount = Number(page.totalCount);
				that.dataListLoading = false;
			});
		},
		sizeChangeHandle(val) {
			this.pageSize = val;
			this.pageIndex = 1;
			this.loadDataList();
		},
		currentChangeHandle(val) {
			this.pageIndex = val;
			this.loadDataList();
		},
		loadRoleList: function() {
			let that = this;
			that.$http('role/searchAllRole', 'GET', null, true, function(resp) {
				that.roleList = resp.list;
			});
		},
		loadDeptList: function() {
			let that = this;
			that.$http('dept/searchAllDept', 'GET', null, true, function(resp) {
				that.deptList = resp.list;
			});
		},
		selectionChangeHandle: function(val) {
			this.dataListSelections = val;
		},
		searchHandle: function() {
			this.$refs['dataForm'].validate(valid => {
				if (valid) {
					this.$refs['dataForm'].clearValidate();
					if (this.dataForm.name == '') {
						this.dataForm.name = null;
					}
					if (this.pageIndex != 1) {
						this.pageIndex = 1;
					}
					this.loadDataList();
				} else {
					return false;
				}
			});
		},
		addHandle: function() {
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init();
			});
		},
		updateHandle: function(id) {
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init(id);
			});
		},
		deleteHandle: function(id) {
			let that = this;
			let ids = id
				? [id]
				: that.dataListSelections.map(item => {
						return item.id;
				  });
			if (ids.length == 0) {
				that.$message({
					message: '没有选中记录',
					type: 'warning',
					duration: 1200
				});
			} else {
				that.$confirm('确定要删除选中的记录？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					that.$http('user/deleteUserByIds', 'POST', { ids: ids }, true, function(resp) {
						if (resp.rows > 0) {
							that.$message({
								message: '操作成功',
								type: 'success',
								duration: 1200
							});
							that.loadDataList();
						} else {
							that.$message({
								message: '未能删除记录',
								type: 'warning',
								duration: 1200
							});
						}
					});
				});
			}
		}
	},
	created: function() {
		this.loadDataList();
		this.loadRoleList();
		this.loadDeptList();
	}
};
</script>

<style lang="less" scoped="scoped"></style>
