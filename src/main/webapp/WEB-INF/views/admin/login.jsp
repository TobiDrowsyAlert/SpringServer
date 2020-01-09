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
                        <h1 class="m-0 text-dark">로그인</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">로그인</li>
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
                    <form fole="form" id="writeForm" method="post" action="${path}/admin/loginPOST">
	                    <div class="card-header">
	                    	<h3 class="card-title">로그인 </h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">
	                    	<div class="form-group">
	                    		<label for="adminId">아이디</label>
	                    		<!-- 아이디 기억 시 처리 -->
	                    		<c:if test="${not empty adminId }">
	                    		 <input class="form-control" id="adminId" name="adminId" placeholder="아이디" value="${adminId}">
	                    		</c:if>
	                    		<c:if test="${empty adminId }">
	                    		 <input class="form-control" id="adminId" name="adminId" placeholder="아이디">
	                    		</c:if>
	                    	</div>
	                    	
	                    	<div class="form-group">
	                    		<label for="adminPw">비밀번호</label>
	                    		<input class="form-control" id="adminPw" name="adminPw" placeholder="비밀번호">
	                    	</div>
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<div class="checkbox icheck float-right">
	                    		<label>
	                    			<input type="checkbox" name="useCookie">로그인유지
	                    		</label>
	                    	</div>
	                    	<button type="submit" class="btn btn-primary"><i class="fa fa-save"></i>로그인</button>
	                    	<a href="${path }/admin/find">계정찾기</a>
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
<script type="text/javascript">

	var result = '${msg}';
	console.log("result : " + result);
	
	if (result == "REGISTERED"){
		alert("회원가입이 완료되었습니다.");
	}
	else if(result == "FAIL"){
		alert("작업이 실패했습니다.");
	}
	else if(result == "AUTHFAIL"){
		alert("${email}" + "이메일을 확인해주세요.");
	}

</script>
</body>
</html>