<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 2019-12-23
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

  
  <!-- Navbar -->
   <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
          </li>
          <li class="nav-item d-none d-sm-inline-block">
            <a href="${path }/" class="nav-link">Home</a>
          </li>
     </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <!-- Notifications Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <c:if test="${empty login}">
          <i class="fas fas-bars"></i>회원가입 및 로그인
          </c:if>
          <c:if test="${not empty login}">
          <i class="fas fas-bars"></i>메뉴
          </c:if>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
        <c:if test="${empty login }">
         <a href="${path}/admin/register" class="dropdown-item">
           <i class="fas fa-envelope mr-2"></i> 회원가입
         </a>
         <div class="dropdown-divider"></div>
         <a href="${path}/admin/login" class="dropdown-item">
           <i class="fas fa-user mr-2"></i> 로그인
         </a>
        </c:if>
        <c:if test="${not empty login }">
         <h5>회원 아이디 : ${login.adminId } </h5>
         <a href="${path}/consulting/list?sortOrder=DESC" class="dropdown-item">
           <i class="fas fa-envelope mr-2"></i> 처리 페이지
         </a>
         <div class="dropdown-divider"></div>
         <a href="${path}/admin/account?adminId=${login.adminId }" class="dropdown-item">
           <i class="fas fa-user mr-2"></i> 개인 정보
         </a>
         <a href="${path}/admin/logout" class="dropdown-item">
           <i class="mr-2"></i>로그아웃
         </a>
        </c:if>
        </div> <!-- dropdown menu -->
      </li>
    </ul>
  </nav>
