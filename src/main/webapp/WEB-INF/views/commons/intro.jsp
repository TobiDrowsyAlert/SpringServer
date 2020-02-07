
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

  	<%@ include file="../include/head2.jsp" %>
<head>
	  <!-- intro -->
	  <link rel="stylesheet" href="${path }/dist/css/test_intro.css"/>
	  <link rel="stylesheet" href="${path }/dist/css/intro_section.css"/>
  	<title>회사소개 | 로얄파트너스</title>
</head>

<body>
<header>
      <div class="left">
        <h1><a href="${path }/"></a></h1>
        <a href="#a" class="all">all menu</a>
        <div class="nav">
          <nav>
            <ul>
              <li><a href="#a">회사소개</a></li>
              <li><a href="#a">인사말</a></li>
              <li><a href="#a">조직도</a></li>
              <li><a href="#a">인재채용</a></li>
          	</ul>
          </nav>
          <div>
            <a href="${path }/admin/login"><img class="login_img" src="${path }/img/login.png" width="15px" height="15px">login</a>
          </div>
        </div>
      </div>
</header>
  <!-- section -->
  <section>
    <div class="main_text">
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