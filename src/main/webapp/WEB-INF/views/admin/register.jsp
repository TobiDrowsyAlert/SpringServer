<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<title>회원가입 | 로얄파트너스</title>
<%@ include file="../include/head2.jsp"%>
<!-- login css -->
<link href="${path }/dist/css/join_sub.css" rel="stylesheet">
</head>

<body id="page-top">

	<!-- nav-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="${path }/"><img
				src='${path }/img/logos/main_logo.png' width="200px" height="50px"></a>
		</div>
	</nav>

	<!-- header -->
	<div class="page-section"></div>

	<!-- section -->
	<section class="section_join">
		<div class="join_container">
			<h2 class="section-heading text-uppercase">회원가입</h2>
			<h5 class="section-heading_sub text-uppercase">정보입력</h5>
			<form role="form" id="join_subForm" name="sentinfo" action="${path }/admin/registerPOST" method="post">
				<div class="join_sub-form" id="join_sub_ids">
					<div class="form-boxs_js clearfix">
						<span class="js_sub">*</span>아이디
					</div>
					<input type="text" id="adminId" name="adminId" class="form-box_js clearfix"
						placeholder="영문, 숫자만 입력가능합니다." required="required">
				</div>
				<div class="join_sub-form" id="join_sub_births">
					<span class="form-boxs_js"><span class="js_sub">*</span>비밀번호</span>
					<input type="tel" id="adminPw" name="adminPw" class="form-box_js" minlength="6"
						maxlength="12" placeholder="영문과 숫자를 혼합해 6~12자리로 입력해주세요."
						required="required">
				</div>
				<div class="join_sub-form" id="join_sub_birthscheck">
					<span class="form-boxs_js"><span class="js_sub">*</span>비밀번호
						확인</span> <input type="password" id="adminCheckPw" class="form-box_js"
						minlength="6" maxlength="12" placeholder="비밀번호를 한번 더 입력해주세요."
						required="required">
				</div> 
				<div class="join_sub-form" id="join_sub_names">
					<span class="form-boxs_js"><span class="js_sub">*</span>이름</span> <input
						type="text" id="adminName" name="adminName" class="form-box_js" maxlength="6"
						placeholder="공백 없이 입력해주세요." required="required">
				</div>
				<div class="join_sub-form" id="join_sub_emails">
					<span class="form-boxs_js"><span class="js_sub">*</span>이메일</span>
					<input type="email" id="adminEmail" name="adminEmail" class="form-box_js adminEmail"
						placeholder="아이디와 비밀번호 분실시 입력하신 이메일로 변경 또는 찾으실 수 있습니다."
						required="required">
				</div>
				<div class="join_sub-form" id="join_sub_tels">
					<span class="form-boxs_js">전화번호</span> <input type="tel"
						id="adminHomePhone" name="adminHomePhone" class="form-box_js" maxlength="12"
						placeholder="'-'없이 입력해주세요.">
				</div> 
				<div class="join_sub-form" id="join_sub_phones">
					<span class="form-boxs_js"><span class="js_sub">*</span>휴대폰</span>
					<input type="tel" id="adminPhone" name="adminPhone" class="form-box_js"
						maxlength="12" placeholder="'-'없이 입력해주세요." required="required">
				</div>
				<div class="join_sub-form" id="join_sub_locates">
					<span class="form-boxs_js"><span class="js_sub">*</span>지점</span> 
					<select
						class="form-box_js" id="pointList" name="adminPoint" required="required">
					</select>
				</div>
	          <div class="join_sub-form" id="join_sub_position">
	            <span class="form-boxs_js"><span class="js_sub">*</span>직책</span>
	            <select class="form-box_js adminPosition" id="join_sub_position" name="adminPosition" required="required">
	              <option>관리자</option>
	              <option>지점장</option>
	              <option>사원</option>
	            </select>
	          </div>
				<button class="btns_join" id="btn_join" type="button" onclick="">가입하기</button>
			</form>
		</div>
	</section>

	<!-- Footer -->
	<footer class="footer_join">
		<div>&copy; 2020 Royal_partners. All Rights Reserved.</div>
	</footer>

	<%@ include file="../include/main_footer2.jsp"%>
	
	<%@ include file = "../include/plugin_js2.jsp" %>

	<script type="text/javascript">

	var adminPw = "";
	var adminCheckPw = "";
	var adminEmail = "";
	var msg = "";
	var formObj = $("form[role='form']");
	var op = new Option();
	
	var reId = /^[a-zA-Z0-9]/;
	var rePw = /^[a-zA-Z0-9.,`~!@#$%^&*()_+-]{6,12}$/; // 아이디와 패스워드가 적합한지 검사할 정규식
	var reEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일이 적합한지 검사할 정규식
	var rePhone = /^[0-9]+$/;
		
	   var ajaxListPoint = function(){
		     $.ajax({
			       url : "${path }/admin/listPoint", // HTTP요청을 보낼 URL 주소
			       method : "GET", // 요청 메소드
			       dataType : "json", // 서버에서 보내줄 데이터 타입
			       success : function(data){ // 성공 시 처리
			         var data_key = Object.keys(data);
			           for(var i = 0; i < data_key.length; i++){
				         var op = new Option();
				         op.value = data[data_key[i]].pointNo;
				         op.text = data[data_key[i]].pointName;
				         document.getElementById("pointList").add(op);
			         }
			       },
			       error : function(){
			         alert('point 불러오기 실패');
			       }
			     });
		   }

	  
	  ajaxListPoint();
		

	function ajaxCheckId(){
		return new Promise(function(resolve, reject){
		    var adminId = $('#adminId').val();
		    $.ajax({
		      url : "${path }/admin/checkId",
		      type : "get",
		      data : {"adminId" : adminId},
		      success : function(data){
			      resolve();
		          if(data==1){
		            // 1: 중복
		            msg = "이미 등록된 아이디입니다. ";
		            alert(msg);
		          }
		      }
		    });
		});
	}

	function ajaxCheckEmail(){
		return new Promise(function(resolve, reject){
		    var adminEmail = $('#adminEmail').val();
		    $.ajax({
		      url : "${path }/admin/checkEmail"+"?adminEmail=" + adminEmail,
		      type : "get",
		      success : function(data){
			      resolve();
		        if(data>=1){
		          msg = "이미 등록된 이메일입니다. ";
		          alert(msg);
		        }
		      }
		    });
		});
	}

	function ajaxCheckPosition(){
		return new Promise(function(resolve, reject){
		    var adminPosition = $('.adminPosition').val();
		    var promise = $.ajax({
		      url : "${path }/admin/checkPosition"+"?adminPosition=" + adminPosition,
		      type : "get",
		      success : function(data){
			      resolve();
			        if(data>=1){
			          msg = "직책을 다시 확인해주십시오. ";
			          alert(msg);
			        }
			  }
		    });
		});

	}

	function submitForm(){
		return new Promise(function(resolve, reject){
			if(msg != ""){
				alert(msg);
				return;
			}
			formObj.submit();
		});

	}
	$('#btn_join').click(function(){
		msg="";
		
		ajaxCheckId()
		.then(function() {
			return ajaxCheckEmail();
		})
		.then(function(){
			return ajaxCheckPosition();
		})
		.then(function(){
			return validate();
		})
		.then(function(){
			return submitForm();
		});

	})
		
	var validate = function(){

		adminId = $("#adminId").val();
		adminPw = $('#adminPw').val();
		adminEmail = $('#adminEmail').val();
		adminCheckPw = $('#adminCheckPw').val();
		adminName = $('#adminName').val();
		adminPhone = $('#adminPhone').val();
		adminHomePhone = $('#adminHomePhone').val();	
		adminPosition = $('.adminPosition').val();
		var checkNumber = adminPw.search(/[0-9]/g);
		var checkEnglish = adminPw.search(/[a-z]/ig);
		
		/* ajaxCheckPosition();
		ajaxCheckId();
		ajaxCheckEmail(); */
		
		if(!reId.test(adminId)){
			msg = "아이디는 영문과 숫자만 가능합니다.";
		}
		if(!rePw.test(adminPw) || (checkNumber < 0 || checkNumber < 0)  ){
			msg = "비밀번호는 영문과 숫자 혼합 6~12자리만 가능합니다.";
		}
		if(!reEmail.test(adminEmail)){
			msg = "이메일을 다시 확인해주세요. ";
		}
		if(adminPw != adminCheckPw){
			msg = "비밀번호 확인을 다시 입력하세요. ";
		}
		if((adminPhone == "")){
			msg = "휴대번호를 확인해주세요.";
		}
		if(!rePhone.test(adminPhone)){
			msg = "휴대번호는 숫자를 입력해야합니다.";
		}
		if(!(adminHomePhone == "") && !rePhone.test(adminHomePhone)){
			msg = "전화번호를 확인해주세요."; 
		}

	
	}

    function joinMSG() {
/*       var chk1 = document.getElementById("join_ag1");
      var chk2 = document.getElementById("join_ag2");
      if (chk1.checked==false&&chk2.checked==false){
        alert("필수사항을 체크해주세요")}
      else if (chk1.checked==false){
        alert("이용약관 동의에 체크해주세요")}
      else if (chk2.checked==false){
        alert("개인정보수집 및 이용동의에 체크해주세요")} */
      }
  
	  $('#adminId').blur(function(){
		    ajaxCheckId();
	  });



	  $('.adminEmail').blur(function(){
		  ajaxCheckEmail();
	  });


	  $('.adminPosition').blur(function(){
		  ajaxCheckPosition();
	  });


  </script>

</body>

</html>