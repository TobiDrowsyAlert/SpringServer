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
                    <form role="form" id="writeForm" method="post" action="${path}/admin/findPOST">
	                    <div class="card-header">
	                    	<h3 class="card-title">계정찾기 </h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">
	                    	<div class="form-group">
	                    		<label for="adminEmail">이메일</label>
	                    		<input class="form-control" id="adminEmail" name="adminEmail" placeholder="이메일" type="email">
	                    	</div>
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<button type="submit" class="btn btn-primary">계정찾기</button>
	                    	<button type="reset" class="btn btn-primary" id="resetBtn">취소</button>
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
 result="${msg}";
 if(result=="FAIL"){
		alert('이메일을 다시 확인해주세요.');
	}

</script>
</body>
</html>