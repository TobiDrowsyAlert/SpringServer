<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="../include/head2.jsp"%>
<body class="">

<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="../include/main_header2.jsp"%>

    <!-- Left side column. contains the logo and sidebar -->
		<!-- Content Wrapper. Contains page content -->
		<div class="container">

	        <!-- Main content -->
	        <div class="content container-fluid">
	            <div class="col-lg-12">
	                <div class="card card-primary card-outline">
	                <form id="updateForm" method="post" action="${path}/admin/updatePw" onsubmit = "submitFunction()">
		                <input type="text" name="adminId" value="${param.adminId }" hidden=true>
	                    <div class="card-header">
	                    	<h3 class="card-title">비밀번호 변경 </h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">
	                    	<div class="form-group">
	                    		<label for="adminPw">비밀번호</label>
	                    		<input class="form-control" id="adminPw" name="adminPw" minlength="6" maxlength="12" required="required" placeholder="영문과 숫자를 혼합해 6~12자리로 입력해주세요.">
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="adminPwDuplicate">비밀번호 확인</label>
	                    		<input class="form-control" type="password" id="adminPwDuplicate" required="required" placeholder="비밀번호를 한번 더 입력해주세요.">
	                    	</div>
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<button id="submitBtn" type="submit" class="btn btn-primary">비밀번호 변경</button>
	                    </div><!--/.card-footer-->
		            </form>
	                </div>
	            </div>
	        </div>
        <!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../include/main_footer2.jsp"%>
</div>
<!-- ./wrapper -->
<%@ include file="../include/plugin_js.jsp"%>
<script>
		var adminPw = "";
		var adminPwDuplicate = "";
		var adminEmail = "";
		var msg = "";
		var formObj = $("#updateForm");
		
		var rePw = /^[a-zA-Z0-9.,`~!@#$%^&*()_+-]{6,12}$/; // 아이디와 패스워드가 적합한지 검사할 정규식

		function submitFunction(){
			adminPw = $('#adminPw').val();
			adminPwDuplicate = $('#adminPwDuplicate').val();
			var checkNumber = adminPw.search(/[0-9]/g);
			var checkEnglish = adminPw.search(/[a-z]/ig);
			
			if(!rePw.test(adminPw) || (checkNumber < 0 || checkNumber < 0) ){
				alert("영문과 숫자를 혼합해 6~12자리로 입력해주세요.");
				return false;
			}
			if(!rePw.test(adminPw)){
				alert("영문과 숫자를 혼합해 6~12자리로 입력해주세요.");
				return false;
			}
			if(adminPw != adminPwDuplicate){
				alert('비밀번호 확인을 다시 입력해주세요.');
				return false;
			}

			return true;
		}
</script>
</body>
</html>