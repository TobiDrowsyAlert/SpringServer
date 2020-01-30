
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

  	<%@ include file="../include/head2.jsp" %>
<head>
	  <!-- intro -->
	  <link rel="stylesheet" href="../dist/css/test_intro.css"/>
	  <link rel="stylesheet" href="../dist/css/intro_section.css"/>
  	<title>회사소개 | 로얄파트너스</title>
</head>

<body>
  <!-- header -->
  <header>
      <div class="left">
        <h1><a href="/"></a></h1>
        <a href="#a" class="all">all menu</a>
        <div class="nav">
          <nav>
            <ul>
              <li><a href="#a">회사소개</a></li>
              <li><a href="#a">인사말</a></li>
              <li><a href="#a">조직도</a></li>
              <li><a href="#a">인재채용</a></li>
          </nav>
          <div>
            <a href="${path }/admin/login"><img class="login_img" src="${imgPath }img/login.png" width="15px" height="15px">login</a>
          </div>
        </div>
      </div>
      <ul>
      	<c:if test="${empty login }">
	      <li><a href="${path }/admin/login">login</a></li>
	      <li><a href="#a">회원가입</a></li>
	    </c:if>
	    <c:if test="${not empty login }">
	      <li><a href="${path }/consulting/list">consulting</a></li>
	    </c:if>
      </ul>
  </header>
  <!-- section -->
  <section>
    <div class="main_text">
		${login } 로그인값
    </div>
  </section>
  <section>
  </section>
  <!-- footer -->
  <%@ include file="../include/main_footer2.jsp" %>
  <!-- script -->
  
  <%@ include file="../include/plugin_js2.jsp" %>
  <script>
  </script>
</body>