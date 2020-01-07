<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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
						<form fole="form" id="writeForm" method="post"
							action="${path}/admin/register">
							<div class="card-header">
								<h3 class="card-title">회원가입</h3>
							</div>
							<!--/.card-header-->
							<div class="card-body">
								<div class="form-group">
									<label for="adminId">아이디</label> <input class="form-control"
										id="adminId" name="adminId" placeholder="아이디">
								</div>
								<div class="form-group">
									<label for="adminPw">비밀번호</label> <input type="password" class="form-control"
										id="adminPw" name="adminPw" placeholder="비밀번호">
								</div>
								<div class="form-group">
									<label for="adminName">이름</label> <input class="form-control"
										id="adminName" name="adminName" placeholder="이름">
								</div>
								<div class="form-group">
									<label for="adminEmail">이메일</label> <input type="email" class="form-control"
										id="adminEmail" name="adminEmail" placeholder="이메일">
								</div>
								<div class="form-group">
									<label for="adminPosition">직책</label>
									<select class="form-control" id="adminPosition" name="adminPosition">
										<option>지부장</option>
										<option>직원</option>
									</select>
								</div>
							</div>
							<!--/.card-body-->
							<div class="card-footer">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-save"></i>회원가입
								</button>
							</div>
							<!--/.card-footer-->
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

		var result = "${msg}";
		if (result == "REGISTERED") {
			alert("회원가입이 완료되었습니다.");
		}
		
	</script>
</body>
</html>