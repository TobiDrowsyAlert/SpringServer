<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>

  <!-- header -->
	<header>
	      <div class="left">
		        <h1><a href="${path }/"></a></h1>
	        <div class="nav">
	          <nav>
	            <ul>
	            <c:if test="${empty login }">
	              <li><a href="${path }/admin/login">로그인</a></li>
	              <li><a href="${path }/admin/register">회원가입</a></li>
	            </c:if>
	            <c:if test="${login.adminPosition eq '관리자' }">
	              <li><a href="${path }/admin/list">직원관리</a></li>
	              <li><a href="${path }/admin/point/list">지점목록</a></li>
	            </c:if>
	            <c:if test="${not empty login }">
	              <li><a href="${path }/consulting/list">고객상담</a></li>
	              <li><a href="${path }/admin/account?adminId=${login.adminId }">개인정보</a></li>
	              <li><a href="${path }/admin/logout">로그아웃</a></li>
	            </c:if>
	          	</ul>
	          </nav>
	        </div>
	      </div>
	</header>