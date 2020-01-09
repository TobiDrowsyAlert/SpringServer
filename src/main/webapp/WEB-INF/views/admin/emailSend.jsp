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
								<li class="breadcrumb-item active">...</li>
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
                <form action="${path }/admin/emailSend" method="post">
                	<input type="text" name="adminEmail" value="${adminEmail }" hidden="true"> 
                    <div class="card-header">
                    	<h3 class="card-title">계정찾기 </h3>
                    </div><!--/.card-header-->
                    <div class="card-body">
                    	이메일을 확인해주세요. 이메일 : ${adminEmail }
                    	이메일 재전송을 원한다면 해당 버튼을 클릭하세요.
                    </div><!--/.card-body-->
                    <div class="card-footer">
                    	<button class="btn btn-primary" type="submit">재전송</button>
                    </div><!--/.card-footer-->
	            </form>
                </div>
            </div>
        </section>
        <!-- /.content -->


    <!-- Main Footer -->
    <%@ include file="../include/main_footer.jsp"%>
</div>
<!-- ./wrapper -->
<%@ include file="../include/plugin_js.jsp"%>
<script>

</script>
</body>
</html>