<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<%@ include file="../include/head2.jsp" %>

<head>
  <!-- login css -->
  <link href="${path }/dist/css/log_in.css" rel="stylesheet">
</head>
  
<body id="page-top">

  <!-- nav-->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="${path }/"><img src='${imgPath }/img/logos/main_logo.png' width="200px" height="50px"></a>
    </div>
  </nav>

  <!-- header -->
  <div class="page-section">
  </div>

  <!-- section -->
  <div class="page-section" id="about" style="display:block">
    <div class="container">
          <h2 class="section-heading text-uppercase">로그인</h2>
      <form id="infoForm" name="sentinfo" action="${path }/admin/loginPOST" method="post">
            <div class="info-form" id="infoid">
              <input type="text" id="info_id" name="adminId" class="form-box" required="required" placeholder="아이디">
            </div>
            <div class="info-form" id="infopw">
              <input type="password" id="info_pw" name="adminPw" class="form-box" maxlength="12" placeholder="비밀번호" required="required">
            </div>
            <div class="info-form" id="infolocate">
              <select class="form-box" id="pointList" name="adminPoint" required="required">
              </select>
            </div>
            <div class="finds">
                <a class="find" href="${path }/admin/find" style="color:#000000;"> 아이디/비밀번호 찾기 <b>〉</b></a>
            </div>
            <button id="login" class="btns " name="button" type="submit" onclick="#">로그인</button>
        </form>
            <button id="join" class="btnRegister" onclick="#">회원가입</button>
      </div>
  </div>

  <!-- Footer -->
	<%@ include file="../include/main_footer2.jsp"%>
  <!-- Scripts -->
  <%@ include file="../include/plugin_js2.jsp" %>
</body>

<script type="text/javascript">
	var msg = '${msg}';
	console.log("result : " + msg);

	if(msg != ""){
		alert(msg);
	}

	$('.btnRegister').click(function(){
		self.location = "${path }/admin/register";
	});

	var loadPoint =  function(){
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
  loadPoint();
</script>

</html>
