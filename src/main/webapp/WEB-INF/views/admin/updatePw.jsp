<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="../include/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="../include/main_header.jsp"%>

    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="../include/left_column.jsp"%>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0 text-dark">sample</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">회원가입</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

        <!-- Main content -->
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="card card-primary card-outline">
                <form method="post" action="${path }/admin/updatePwPOST">
                <input type="text" name="adminId" value="${param.adminId }">
                <input type="text" name="authKey" value="${param.authKey }">
                    <div class="card-header">
                    	<h3 class="card-title">비밀번호 변경 </h3>
                    </div><!--/.card-header-->
                    <div class="card-body">
                    	<div class="form-group">
                    		<label for="adminPw">비밀번호</label>
                    		<input class="form-control" id="adminPw" name="adminPw" placeholder="비밀번호">
                    	</div>
                    	<div class="form-group">
                    		<label for="adminPwDuplicate">비밀번호 확인</label>
                    		<input class="form-control" id="adminPwDuplicate" name="adminPwDuplicate" placeholder="비밀번호 확인">
                    	</div>
                    </div><!--/.card-body-->
                    <div class="card-footer">
                    	<button type="submit" class="btn btn-primary">비밀번호 변경</button>
                    </div><!--/.card-footer-->
	            </form>
                </div>
            </div>
        </section>
        <!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../include/main_footer.jsp"%>
</div>
<!-- ./wrapper -->
<%@ include file="../include/plugin_js.jsp"%>
<script>

</script>
</body>
</html>