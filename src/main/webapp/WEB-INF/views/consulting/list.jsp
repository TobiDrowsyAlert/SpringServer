<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <!-- Main content -->
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h5 class="m-0">게시판 목록</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-bordered">
                            <tbody>
	                            <tr>
	                                <th style="width: 30px">#</th>
	                                <th>담당자</th>
	                                <th>이름</th>
	                                <th>이메일</th>
	                                <th>성별</th>
	                                <th>생년월일</th>
	                                <th>상담종류</th>
	                                <th>상담타입</th>
	                                <th>1차 전화</th>
	                                <th>상담 완료</th>
	                                <th>신청 날짜</th>
	                                <th>비고</th>
	                            </tr>
	                            <c:forEach items="${consultings}" var="consulting" begin = "0" varStatus="status">
	                                <tr>
	                                	<td>
	                                		<input class="chkbox" type="checkbox" name = "selectItem" value="${consulting.consultingNo}">
		                                    <a href="${path }/consulting/read&consultingNo=${consulting.consultingNo}">
			                                   	${consulting.consultingNo}
		                                    </a>
	                                    </td>
	                                    <td>${consulting.adminId}</td>
	                                    <td>${consulting.consultingName}</td>
	                                    <td>${consulting.consultingEmail}</td>
	                                    <td>${consulting.consultingSex}</td>
	                                    <td><fmt:formatDate value="${consulting.consultingBirthday }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	                                    <td>${consulting.consultingKinds}</td>
	                                    <td>${consulting.consultingType}</td>
									     <td>${consulting.consultingIsCall}</td>
									     <td>${consulting.consultingIsEnd}</td>
	                                    <td><fmt:formatDate value="${consulting.consultingRegDate}" pattern="yyyy-MM-dd a HH:mm"/></td>
	                                    <td>${consulting.consultingRemarks}</td>
	                                </tr>
	                           </c:forEach>
                           </tbody>
                        </table>
                    </div><!--/.card-body-->
                    <div class="card-footer">
                        <div class="text-center">
                            <ul class="pagination justify-content-center">
                            	<c:if test="${pageMaker.prev }">
                            		<li class="page-item">
                            			<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
                            		</li>
                            	</c:if>
                            	<c:forEach begin= "${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
                            		<li class="page-item">
                            			<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(idx)}">${idx}</a>
                            		</li>
                            	</c:forEach> 
                            	<c:if test="${pageMaker.next }">
                            		<li class="page-item">
                            			<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
                            		</li>
                            	</c:if>
                            </ul><!-- pagenation -->
                        </div><!--/.text-center-->
                    </div><!--/.card-footer-->
                    <div class="card-footer">
                    	<div>
                    		<input type="checkbox" id="allCheck" name="allCheck"> <label for="allCheck">모두선택</label>
                    	</div>
                        <div class="float-right">
                           <button type="button" class="btn btn-success btn-flat" id="modifyBtn">
                               <i class="fa fa-pencil"></i> 처리
                           </button>
                        </div><!-- float-right -->
                    </div><!--/.card-footer-->
                </div><!-- /.card card-primary card-outline-->
            </div><!-- /.col-lg-12 -->
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

    var result = "${msg}";
    if (result == "regSuccess") {
        alert("게시글 등록이 완료되었습니다.");
    } else if (result == "modSuccess") {
        alert("게시글 수정이 완료되었습니다.");
    } else if (result == "delSuccess") {
        alert("게시글 삭제가 완료되었습니다.");
    }

    $("#allCheck").click(function(){
		var chk = $("#allCheck").prop("checked");
		if(chk){
			$(".chkbox").prop("checked", true); // 모든 체크박스 true
		} else {
			$(".chkbox").prop("checked", false); // 모든 체크박스 false
		}
    });

	// chkbox 클릭되면 allCheck는 풀림,
    $("chkbox").click(function(){
    	$("#allCheck").prop("checked", false);
    });

	$('#modifyBtn').click(function(){
		var confirm_val = confirm('처리 확인');

		if(confirm_val){
			var checkArr = new Array();

			$("input[class='chkbox']:checked").each(function(){
				checkArr.push($(this).attr("value"));
			});

			$.ajax({
				url : "/consulting/delete",
				type : "post",
				data : {chkbox : checkArr},
				success : function(){
					location.href = "/consulting/list";
				}
			});
		}
	});

	 $("#modifyBtn").click(function(){
	  var confirm_val = confirm("정말 삭제하시겠습니까?");
	  
	  if(confirm_val) {
	   var checkArr = new Array();
	   
	   $("input[class='chkbox']:checked").each(function(){
	    checkArr.push($(this).attr("value"));
	   });
	    
	   $.ajax({
	    url : "/consulting/delete",
	    type : "post",
	    data : { chkbox : checkArr },
	    success : function(result){
		    if(result == 1)
	     		location.href = "/consulting/list";
	    }
	   });
	  } 
	 });

	alert('javaScript 동작 확인');
	
</script>
</body>
</html>