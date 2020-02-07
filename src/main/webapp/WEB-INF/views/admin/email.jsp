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
                <form action="${path }/admin/email" method="post">
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