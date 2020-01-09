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
                        <h3 class="card-title">${consulting.consulting_no}</h3>
                    </div><!-- card-header-->
                    <div class="card-body">
                        <h3>${consulting.consultingNo}</h3>
                        <h3>${consulting.consultingName}</h3>
                        <h3>${consulting.consultingEmail}</h3>
                        <h3>${consulting.consultingSex}</h3>
                        <h3><fmt:formatDate value="${consulting.consultingBirthday }" pattern="yyyy-MM-dd"></fmt:formatDate></h3>
                        <h3>${consulting.consultingKinds}</h3>
                        <h3>${consulting.consultingType}</h3>
					    <h3>${consulting.consultingIsCall}</h3>
					    <h3>${consulting.consultingIsEnd}</h3>
                        <h3><fmt:formatDate value="${consulting.consultingRegDate}" pattern="yyyy-MM-dd a HH:mm"/></h3>
                        <h3>${consulting.consultingRemarks}</h3>
                    </div><!--/.card-body-->
                    <div class="card-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="articleNo" value="${consulting.consultingNo}">
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
            formObj.attr("action", "/consulting/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".delBtn").on("click", function () {
            formObj.attr("action", "/consulting/delete");
            formObj.attr("method", "post");
            formObj.submit();
        });

        $(".listBtn").on("click", function () {
            formObj.attr("action", "/consulting/list");
            formObj.attr("method", "get");
            formObj.submit();
        });
    });
</script>
</body>
</html>