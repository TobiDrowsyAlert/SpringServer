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