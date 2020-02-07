<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html lang="ko">

<head>
  <%@ include file="../include/head2.jsp" %>
  <title>직원관리 | 로얄파트너스</title>
  <!-- table css -->
  <link href="${path }/dist/css/table_join.css" rel="stylesheet">
</head>

<body id="page-top">
  <%@ include file="../include/main_header2.jsp" %>

  <!-- section -->
  <div class="page-section" id="about">
    <div class="container">
      <h3 class="section-heading text-uppercase">지점목록</h3>
        <table class="tg">
        <tr>
          <th class="tg-c3ow"></th>
          <th class="tg-c3ow">지점 번호</th>
          <th class="tg-c3ow">지점 이름</th>
          <th class="tg-c3ow">지점장</th>
        </tr>
	        <c:forEach var="point" items="${pointList }" >
	        <tr>
	          <td class="tg-c3ow"><input type = "checkBox" class="selectBox" value="${point.pointNo }"></td>
	          <td class="tg-c3ow">${point.pointNo }</td>
	          <td class="tg-c3ow">${point.pointName } </td>
	          <td class="tg-c3ow"><c:out value="${point.pointAdmin == 'NULL' ? '담당없음' : point.pointAdmin} "/></td>
	        </tr>
	        </c:forEach>
        </table>
      </div>
  </div>

  <!-- Footer -->
  <%@ include file="../include/main_footer2.jsp" %>


 <%@ include file="../include/plugin_js2.jsp" %>

	<script type="text/javascript">

		// 메시지 
		var result = "${msg}";
		if (result == "regSuccess") {
		    alert("게시글 등록이 완료되었습니다.");
		} else if (result == "modSuccess") {
		    alert("게시글 수정이 완료되었습니다.");
		} else if (result == "delSuccess") {
		    alert("게시글 삭제가 완료되었습니다.");
		}

		$('.pointBtn').click(function(){
			self.location = "${path }/admin/point/list";
		})
	
		$('.adminBtn').click(function(){
			self.location = "${path }/admin/list";
		})
		
		

	</script>
</body>