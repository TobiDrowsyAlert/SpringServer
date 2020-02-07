<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
  <div class="page-section" id="about" style="display:block">
    <div class="container">
      <h3 class="section-heading text-uppercase">직원관리</h3>
        <table class="tg">
        <tr>
          <th class="tg-c3ow"></th>
          <th class="tg-c3ow">성함</th>
          <th class="tg-c3ow">직책</th>
          <th class="tg-c3ow">지점</th>
          <th class="tg-c3ow">휴대폰 번호</th>
          <th class="tg-c3ow">메일주소</th>
        </tr>
	        <c:forEach var="admin" items="${adminList }" >
	        <tr>
	          <td class="tg-c3ow"><input type = "checkBox" class="selectBox" value="${admin.adminId }"></td>
	          <td class="tg-c3ow">${admin.adminName }</td>
	          <td class="tg-c3ow">${admin.adminPosition } </td>
	          <td class="tg-c3ow tablePoint" id="tablePoint">${admin.adminPoint }: ${pointList[admin.adminPoint - 1].pointName }</td>
	          <td class="tg-c3ow">${admin.adminPhone }</td>
	          <td class="tg-c3ow">${admin.adminEmail }</td>
	        </tr>
	        </c:forEach>
        </table>

      </div>
  </div>
  <div class="row">
  	<div class="container">
	    <div class="float-right">
	 			<button class="btn btn-primary deleteBtn" >직원삭제</button>
	 	</div>
  	</div>
  </div>

  <!-- Footer -->
  <%@ include file="../include/main_footer2.jsp" %>


 <%@ include file="../include/plugin_js2.jsp" %>

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
	
		// 삭제 이벤트
		$('.deleteBtn').click(function(){
			// 확인 alert
			var confirm_val = confirm('정말 삭제하시겠습니까?');
	
			if(confirm_val){
				var checkArr = new Array();
	
				$("input[class='selectBox']:checked").each(function(){
					checkArr.push($(this).attr("value"));
					alert("checkArr push : " + $(this).attr("value") );
				});
	
				$.ajax({
					url : "${path }/admin/list/delete",
					type : "post",
					data : {chkbox : checkArr},
				    success : function(){
					    self.location = "${path }/admin/list";
				    }
				});
			}
		});
		

	</script>
</body>

</html>
