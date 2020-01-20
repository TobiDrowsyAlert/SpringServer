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
						</div><!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">회원가입</li>
							</ol>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.container-fluid -->
			</div><!-- /.content-header -->

			<!-- Main content -->
			<section class="content container-fluid">
				<div class="col-lg-12">
					<div class="card card-primary card-outline">
						<form role="form" id="writeForm" method="post"
							action="${path}/admin/registerPOST">
							<div class="card-header">
								<h3 class="card-title">회원가입</h3>
							</div>
							<!--/.card-header-->
					        <div class="card-body">
				              <div class="form-group">
				                <label for="adminId">아이디</label> <input class="form-control"
				                  id="adminId" name="adminId" placeholder="아이디">
				                  <div class="check_font" id="idCheck">아이디체크</div>
				              </div>
				              <div class="form-group">
				                <label for="adminPw">비밀번호</label> <input type="password" class="form-control"
				                  id="adminPw" name="adminPw" placeholder="비밀번호">
				                  <div class="check_font" id="pwCheck">비밀번호체크</div>
				              </div>
				              <div class="form-group">
				                <label for="adminPw">비밀번호 확인</label> <input type="password" class="form-control"
				                  id="adminDuplicatePw" name="adminDuplicatePw" placeholder="비밀번호 확인">
				                  <div class="check_font" id="pwDuplicateCheck">중복 체크</div>
				              </div>
				              <div class="form-group">
				                <label for="adminName">이름</label> <input class="form-control"
				                  id="adminName" name="adminName" placeholder="이름">
				                  <div class="check_font" id="nameCheck">이름 체크</div>
				              </div>
				              <div class="form-group">
				                <label for="adminEmail">이메일</label> <input type="email" class="form-control"
				                  id="adminEmail" name="adminEmail" placeholder="이메일">
				                  <div class="check_font" id="emailCheck">이메일체크</div>
				              </div>
				              <div class="form-group">
				                <label for="adminPosition">직책</label>
				                <select class="form-control" id="adminPosition" name="adminPosition">
				                  <option>직원</option>
				                </select>
				              </div>
				              <div class="form-group">
				              	<label for="adminPoint">지부</label>
				              	<select class="form-control" id="adminPoint" name="adminPoint">
				              		<c:forEach items="${points }" var="point" >
				              			<option value='${point.pointNo }'>${point.pointName}</option>
				              		</c:forEach>
				              	</select>
				              </div>
				            </div>
				            <!--/.card-body-->
							<div class="card-footer">
								<button type="submit" class="btn btn-primary" id="btnSubmit">
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
	
	<script type="text/javascript">

	$(document).ready(function(){
	  var adminPw = "";
	  var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
	  var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일이 적합한지 검사할 정규식

	  var validate = function(){
	    let idCheck = $('#idCheck').text();
	    let pwCheck = $('#pwCheck').text();
	    let emailCheck = $('#emailCheck').text();
	    let nameCheck = $('#nameCheck').text();
	    let pwDuplicateCheck = $('#pwDuplicateCheck').text();

	    if(idCheck != "" || pwCheck != "" || emailCheck != "" || nameCheck != "" || pwDuplicateCheck != ""){
	      $('#btnSubmit').attr("disabled", true);
	      return;
	    }

	    $('#btnSubmit').attr("disabled", false);
	  }
	  
	  $('#adminId').blur(function(){
	    var adminId = $('#adminId').val();
	    var adminVO ={
			"adminId" : adminId
	    };
	    $.ajax({
	      //url : "http://localhost:8080/admin/checkId",
	      url : "http://localhost:8080/checkId",
	      type : "GET",
	      data : {"adminVO", adminVO},
	      success : function(data){
	          if(data==1){
	            // 1: 중복
	            $('#idCheck').text("사용중인 아이디입니다.");
	            $('#idCheck').css("color", "red");
	          }else{
	            // 0: 중복아님
	            //check(re, what, message)
	            if(!re.test(adminId)){
	              $('#idCheck').text("아이디는 소문자와 숫자 4~12자리만 가능합니다.");
	              $('#idCheck').css("color", "red");
	            }else if(adminId == ""){
	              $('#idCheck').text("아이디를 입력해주세요.");
	              $('#idCheck').css("color", "blue");
	            }else{
	              $('#idCheck').text("");
	            }
	          }
	       validate();
	      }
	    });
	  });


	  $('#adminEmail').blur(function(){
	    var adminEmail = $('#adminEmail').val();
	    $.ajax({
	      url : "http://localhost:8080/admin/checkEmail"+"?adminEmail=" + adminEmail,
	      type : "get",
	      success : function(data){
	        if(data>=1){
	          $('#emailCheck').text("사용중인 이메일입니다.");
	          $('#emailCheck').css("color", "red");
	        }else{
	          if(!re2.test(adminEmail)){
	            $('#emailCheck').text("이메일 형식이 잘못되었습니다.");
	            $('#emailCheck').css("color", "red");
	          }else if(adminEmail == ""){
	            $('#emailCheck').text("이메일을 입력해주세요.");
	            $('#emailCheck').css("color", "blue");
	          }else{
	            $('#emailCheck').text("");
	          }
	        }
	      validate();
	      }
	    });
	  });


	  $('#adminName').blur(function(){
	    var adminName = $('#adminName').val();
	    if(adminName == ""){
	      $('#nameCheck').text("이름을 입력하세요.");
	      $('#nameCheck').css("color", "blue");
	    }else{
	      $('#nameCheck').text("");
	    }
	    validate();
	  });

	  $('#adminPw').blur(function(){
	    adminPw = $('#adminPw').val();

	    if(!re.test(adminPw)){
	      $('#pwCheck').text("비밀번호는 소문자와 숫자 4~12자리만 가능합니다.");
	      $('#pwCheck').css("color", "red");
	    }else if(adminPw == ""){
	      $('#pwCheck').text("비밀번호를 입력하세요.");
	      $('#pwCheck').css("color", "blue");
	    }else{
	      $('#pwCheck').text("");
	    }
	    validate();
	  });

	  $('#adminDuplicatePw').blur(function(){
	    var adminDuplicatePw = $('#adminDuplicatePw').val();
	    if (adminDuplicatePw == ""){
	     $('#pwDuplicateCheck').text("비밀번호 중복 체크를 해주세요..");
	     $('#pwDuplicateCheck').css("color", "red");
	     // 버튼 비활성화 ,$('#버튼아이디').attr("disabled", true);
	    }else if(adminPw == adminDuplicatePw){
	      $('#pwDuplicateCheck').text("");
	    }else {
	      $('#pwDuplicateCheck').text("비밀번호를 다시 확인해주세요.");
	      $('#pwDuplicateCheck').css("color", "red");
	      // 버튼 비활성화 ,$('#버튼아이디').attr("disabled", true);
	    }
	  validate();
	  });

	});

	</script>

</body>
</html>