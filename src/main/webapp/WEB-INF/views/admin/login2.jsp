<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<%@ include file="../include/head2.jsp" %>

<body id="page-top">

  <!-- nav-->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="/"><img src='${imgPath }/img/logos/main_logo.png' width="200px" height="50px"></a>
    </div>
  </nav>

  <!-- header -->
  <div class="page-section">
  </div>

  <!-- section -->
  <section class="page-section" id="about" style="display:block">
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
              <select class="form-box" id="info_locate" required="required">
                <option selected style="color:#ffffff;">-지점선택-</option>
                <option>금천</option>
                <option>영등포</option>
                <option>구로</option>
                <option>부평</option>
                <option>대전</option>
                <option>대구</option>
                <option>부산</option>
                <option>광주</option>
                <option>울산</option>
              </select>
            </div>
            <div class="finds">
                <a class="find" href=email style="color:#000000;"> 아이디/비밀번호 찾기 <b>〉</b></a>
            </div>
            <button id="login" class="btns " name="button" type="submit" onclick="#">로그인</button>
        </form>
            <button id="join" onclick="#">회원가입</button>
      </div>
  </section>

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

</script>

</html>
