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
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">회원가입</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="card card-primary card-outline">
                    <form id="writeForm" method="post" action="${path}/user/register">
	                    <div class="card-header">
	                    	<h3 class="card-title">회원가입</h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">
	                    	<div class="form-group">
	                    		<label for="userId">아이디</label>
	                    		<input class="form-control" id="userId" name="userId" placeholder="아이디">
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="userPw">비밀번호</label>
	                    		<input class="form-control" id="userPw" name="userPw" placeholder="비밀번호">
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="userPwCheck">비밀번호 확인</label>
	                    		<input class="form-control" id="userPwCheck" name="userPwCheck" placeholder="비밀번호">
	                    	</div>
	                    	
	                    	<div class="form-group">
	                    		<label for="userPw">이름</label>
	                    		<input class="form-control" id="userName" name="userName" placeholder="이름">
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="userPw">이메일</label>
	                    		<input class="form-control" id="userEmail" name="userEmail" placeholder="이메일">
	                    	</div>
	                    	
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<button type="submit" class="btn btn-primary"><i class="fa fa-save"></i>회원가입</button>
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