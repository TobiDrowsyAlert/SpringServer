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
                        <h3 class="card-title">${article.title}</h3>
                    </div><!-- card-header-->
                    <div class="card-body" style="height: 700px">
                        <h3>${article.content}</h3>
                    </div><!--/.card-body-->
                    <div class="card-footer">
                        <div class="user-block">
                            <span class="username">
                                <a href="#">${article.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-mm-dd a HH:mm" value="${article.regDate}"/></span>
                        </div>
                    </div><!--/.card-footer-->
                    <div class="card-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="articleNo" value="${article.articleNo}">
                            <input type="hidden" name="page" value="${criteria.page}">
                            <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                            <input type="hidden" name="searchType" value="${criteria.searchType}">
                            <input type="hidden" name="keyword" value="${criteria.keyword}">
                        </form>
                        <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                        <div class="float-right">
                            <button type="button" class="btn btn-warning modBtn"><i class="fa fa-reply"></i>수정</button>
                            <button type="submit" class="btn btn-success delBtn"><i class="fa fa-save"></i>삭제</button>
                        </div><!--/.float-right -->
                    </div>
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
        var formObj =$("form[role='form']");
        console.log(formObj);

        $(".modBtn").on("click", function () {
            formObj.attr("action", "/article/modifyPaging");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".delBtn").on("click", function () {
            formObj.attr("action", "/article/deletePaging");
            formObj.attr("method", "post");
            formObj.submit();
        });

        $(".listBtn").on("click", function () {
            formObj.attr("action", "/article/list");
            formObj.attr("method", "get");
            formObj.submit();
        });
    });
</script>
</body>
</html>