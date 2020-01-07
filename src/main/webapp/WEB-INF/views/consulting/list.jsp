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
	                                		<input type="checkbox" name = "selectItem" value="${consulting.consultingNo}">
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
                       <div class="float-right">
                           <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                               <i class="fa fa-pencil"></i> 글쓰기
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
<script>

    var result = "${msg}";
    if (result == "regSuccess") {
        alert("게시글 등록이 완료되었습니다.");
    } else if (result == "modSuccess") {
        alert("게시글 수정이 완료되었습니다.");
    } else if (result == "delSuccess") {
        alert("게시글 삭제가 완료되었습니다.");
    }


</script>
</body>
</html>