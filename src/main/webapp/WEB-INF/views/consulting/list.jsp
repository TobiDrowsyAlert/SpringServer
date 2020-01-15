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
            <div class="col-xl-12">
                <div class="card card-primary card-outline">
                    <div class="card-header">
                        <h5 class="m-0">게시판 목록</h5>
                    </div>
                    <div class="card-body">
                    
                        <table class="table table-bordered">
                            <tbody>
	                            <tr>
	                            	<th class="table_attribute"><input type="checkbox" id="allCheck" name="allCheck"></th>
						            <th class="table_attribute" style="width: 30px" id="table_sort">No</th>
						            <th class="table_attribute">담당자</th>
						            <th class="table_attribute">이름</th>
						            <th class="table_attribute">이메일</th>
						            <th class="table_attribute">성별</th>
						            <th class="table_attribute">생년월일</th>
						            <th class="table_attribute">상담종류</th>
						            <th class="table_attribute">상담타입</th>
						            <th class="table_attribute">1차 전화</th>
						            <th class="table_attribute">상담 완료</th>
						            <th class="table_attribute">신청 날짜</th>
                                <th>비고</th>
	                            </tr>
	                            <c:forEach items="${consultings}" var="consulting" begin = "0" varStatus="status">
	                                <tr>
	                                	<td>
	                                		<input class="chkbox" type="checkbox" name = "selectItem" value="${consulting.consultingNo}">
	                                    </td>
	                                    <td>${consulting.consultingNo }</td>
	                                    <td>${consulting.adminId}</td>
	                                    <td>${consulting.consultingName}</td>
	                                    <td>${consulting.consultingEmail}</td>
	                                    <td>${consulting.consultingSex}</td>
	                                    <td><fmt:formatDate value="${consulting.consultingBirthday }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	                                    <td>${consulting.consultingKinds}</td>
	                                    <td>${consulting.consultingType}</td>
									    <td><input type="checkbox" <c:out value="${consulting.consultingIsCall == 'true' ? 'checked' : '' }"/> onclick="return false;" ></td>
									    <td><input type="checkbox" <c:out value="${consulting.consultingIsEnd == 'true' ? 'checked' : '' }"/> onclick="return false;" ></td>
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
                            		<li class="page-item <c:out value="${pageMaker.criteria.page == idx ? 'active' : '' }"/>">
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

                   		
                    	<div class="row">
							<select id="sortType">
							  <option value="none" <c:out value="${criteria.sortType == null ? 'selected' : '' }"/>>===정렬===</option>
							  <option value="no" <c:out value="${criteria.sortType == 'no' ? 'selected' : ''}"/>>번호</option>
							  <option value="sex" <c:out value="${criteria.sortType == 'sex' ? 'selected' : ''}"/>>성별</option>
							  <option value="kinds" <c:out value="${criteria.sortType == 'kinds' ? 'selected' : ''}"/>>상담종류</option>
							  <option value="type" <c:out value="${criteria.sortType == 'type' ? 'selected' : ''}"/>>상담타입</option>
							  <option value="call" <c:out value="${criteria.sortType == 'call' ? 'selected' : ''}"/>>1차콜</option>
							  <option value="end" <c:out value="${criteria.sortType == 'end' ? 'selected' : ''}"/>>최종확인</option>
							  <option value="reg" <c:out value="${criteria.sortType == 'reg' ? 'selected' : ''}"/>>등록일</option>
							</select>
	                   		<select id="sortOrder">
	                   			<option value="DESC" <c:out value="${criteria.sortOrder == 'DESC' ? 'selected' : '' }"/>>내림차순</option>
	                   			<option value="ASC" <c:out value="${criteria.searchType == 'ASC' ? 'selected' : '' }"/>>오름차순</option>
	                   		</select>
	                   	</div>
	                   	<div class="form-group col-sm-2">
                    		<select id="searchType">
                    			<option value="none" <c:out value="${criteria.searchType == null ? 'selected' : '' }"/>>===선택===</option>
                    			<option value="i" <c:out value="${criteria.searchType == 'i' ? 'selected' : '' }"/>>담당자</option>
                    			<option value="n" <c:out value="${criteria.searchType == 'n' ? 'selected' : '' }"/>>신청자명</option>
                    		</select>
                    	</div>
                    	<div class="form-group col-sm-10">
                    		<input type="text" class="form-control" name="keyword" id="keyword" value="${criteria.keyword }" placeholder="검색어"/>
                    	</div>
                    	<button class="btn btn-primary searchBtn">검색</button>
                    	<button class="btn btn-danger sorting">테이블 정렬</button>
                        <div class="float-right">
                        <select id="memberList">
                        	<option>===담당자===</option>
                        </select>
                           <button type="button" class="btn btn-secondary btn-flat" id="moveBtn">
                               <i class="fa fa-pencil"></i> 담당
                           </button>
                           <button type="button" class="btn btn-success btn-flat" id="modifyBtn">
                               <i class="fa fa-pencil"></i> 삭제
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


$(document).ready(function(){
  alert('자바스크립트 동작');

  var table_array = $(".table_attribute");
  
  var result = "${msg}";
  if (result == "regSuccess") {
      alert("게시글 등록이 완료되었습니다.");
  } else if (result == "modSuccess") {
      alert("게시글 수정이 완료되었습니다.");
  } else if (result == "delSuccess") {
      alert("게시글 삭제가 완료되었습니다.");
  }


  var searchLocation = function(){
    self.location =
        "/consulting/list${pageMaker.makeQuery(1)}"
     + "&sortType=" + $("#sortType option:selected").val()
     + "&sortOrder=" + $("#sortOrder option:selected").val()
     + "&searchType=" +$("#searchType option:selected").val()
     + "&keyword=" + encodeURIComponent($('#keyword').val());
  }
  var searchPageLocation = function(page){
	    self.location =
	        "/consulting/list${pageMaker.makeQuery(1)}"
	     + "&sortType=" + $("#sortType option:selected").val()
	     + "&sortOrder=" + $("#sortOrder option:selected").val()
	     + "&searchType=" +$("#searchType option:selected").val()
	     + "&keyword=" + encodeURIComponent($('#keyword').val());
	  }
  
	$('.searchBtn').click(function(){
		searchLocation();
	});

	
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
		var confirm_val = confirm('정말 삭제하시겠습니까?');

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
			    	searchLocation();
				    //self.location="/consulting/list?page=${criteria.page }";
			    }
			});
		}
	});

	$('.sorting').click(function(){
		var consultings = "${consultings}";
		alert(consultings);
		consultings.each(function(index, item){
			alert("index : " + index + ", item : " + item);
		});

	});


	$('#moveBtn').click(function(){
		var adminId = $("#memberList option:selected").val();
		var checkArr = new Array();

		$("input[class='chkbox']:checked").each(function(){
			checkArr.push($(this).attr("value"));
		});
		alert(adminId);
	  $.ajax({
		    url : "/consulting/updateAdmin",
		    method : "GET",
		    dataType : "json",
		    data : {chkbox:checkArr, "adminId":adminId},
		    success : function(data){
		      if(data == 1){
		        alert('success');
		        self.location = "/consulting/list?page=${criteria.page}";
		      }
		    },
		    error : function(){
		      alert('admin 수정 실패');
		    }
		  });
	});
	
  var loadAdmin =  function(){
	    $.ajax({
	      url : "/admin/listAdmin", // HTTP요청을 보낼 URL 주소
	      method : "GET", // 요청 메소드
	      dataType : "json", // 서버에서 보내줄 데이터 타입
	      success : function(data){ // 성공 시 처리
	        var data_key = Object.keys(data);
	        for(var i = 0; i < data_key.length; i++){
				var op = new Option();
				op.value = data_key[i];
				op.text = data[data_key[i]];
				document.getElementById("memberList").add(op);
		    }
	      },
	      error : function(){
	        alert('admin 불러오기 실패');
	      }
	    });
    }
  loadAdmin();

    
});




	
</script>
</body>
</html>