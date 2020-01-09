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
                        <h5 class="m-0">글 수정</h5>
                    </div>
                    <form role="form" id="writeForm" method="post" action="${path}/article/modifyPaging">
                        <input type="hidden" name="articleNo" value="${article.articleNo}">
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                        <input type="hidden" name="keyword" value="${criteria.keyword}">
                        <input type="hidden" name="searchType" value="${criteria.searchType}">
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
	                                    <td><input type="text" readonly value="${consulting.adminId}"/></td>
	                                    <td><input type="text"  value="${consulting.consultingName}"/></td>
	                                    <td><input type="text" value="${consulting.consultingEmail}"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingSex}"/></td>
	                                    <td><input type="text" readonly value="<fmt:formatDate value="${consulting.consultingBirthday }" pattern="yyyy-MM-dd"></fmt:formatDate>"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingKinds}"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingType}"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingIsCall}"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingIsEnd}"/></td>
	                                    <td><input type="text" readonly value="<fmt:formatDate value="${consulting.consultingRegDate}" pattern="yyyy-MM-dd a HH:mm"/>"/></td>
	                                    <td><input type="text" readonly value="${consulting.consultingRemarks}"/></td>
	                                </tr>
	                           </c:forEach>
                           </tbody>
                        </table>
                           <div class="form-group">
                                <label for="title">제목</label>
                                <input class="form-control" id="title" name="title" placeholder="내용을 입력하세요"
                                    value="${article.title}">
                            </div> <!--/.form-group -->

                            <div class="form-group">
                                <label for="content">내용</label>
                                <textarea class="form-control" id="content" name="content" rows="30"
                                          placeholder="내용을 입력하세요">${article.content}</textarea>
                            </div> <!--/.form-group -->
                            <div class="form-group">
                                <label for="writer">작성자</label>
                                <input class="form-control" id="writer" name="writer" value="${article.writer}" readonly>
                            </div> <!--/.form-group -->
                        </div><!--/.card-body-->
                        <div class="card-footer">
                            <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                            <div class="float-right">
                                <button type="button" class="btn btn-warning cancleBtn"><i class="fa fa-reply"></i>취소</button>
                                <button type="submit" class="btn btn-success modBtn"><i class="fa fa-save"></i>저장</button>
                            </div><!--/.float-right -->
                        </div><!--/.card-footer-->
                    </form><!--/.form-->
                </div>
            </div>

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
    $(document).ready(function () {
       var formObj = $("form[role='form']");
       console.log(formObj);

       $(".listBtn").on("click", function () {
           // formObj.attr 형식도 가능은 하지만, content나 title 도 파라미터에 같이 실린다.
           // 따라서 self.location으로 처리할 것
           self.location = "/article/list?page=${criteria.page}&perPageNum=${criteria.perPageNum}"
           + "&searchType=${criteria.searchType}&keyword=${criteria.keyword}";
       });

       $(".cancleBtn").on("click", function () {
          history.go(-1); // 뒤로가기
       });

       $(".modBtn").on("click", function () {
           //hidden으로 선언된 각종 저장할 애트리뷰트들도 같이 전달된다.
           formObj.submit();
       });

    });
</script>
</body>
</html>