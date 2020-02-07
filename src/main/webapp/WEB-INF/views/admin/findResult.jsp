<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="../include/head2.jsp"%>
<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="../include/main_header2.jsp"%>



        <!-- Main content -->
        <div class="content container-fluid">
            <div class="col-lg-12">
                <div class="card card-primary card-outline">
                    <form fole="form" id="writeForm" method="get" action="${path}/admin/login">
	                    <div class="card-header">
	                    	<h3 class="card-title">계정찾기 </h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">
	                    	<div class="form-group">
	                    		<h5>"${adminEmail}"</h5> 로 요청한 계정정보를 보냈습니다.
	                    		<p>
	                    			해당 이메일을 확인하시고, 비밀번호 변경이 필요하신 경우 해당 이메일을 통해 변경 가능합니다.
	                    		</p>
	                    	</div>
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<button type="submit" class="btn btn-primary">로그인</button>
	                    </div><!--/.card-footer-->
	                </form> 
                </div>
            </div>
        </div>
        <!-- /.content -->


    <!-- Main Footer -->
    <%@ include file="../include/main_footer2.jsp"%>
	</div>
<!-- ./wrapper -->
<%@ include file="../include/plugin_js.jsp"%>
<script>

</script>
</body>
</html>