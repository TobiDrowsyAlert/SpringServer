<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">

<%@ include file="../include/head2.jsp" %>

<head>
  <title>고객상담 | 로얄파트너스</title>
</head>

<body id="page-top">
  <!-- nav-->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="/"><img src='${imgPath }/img/logos/main_logo.png' width="200px" height="50px"></a>
      <a href="${path }/admin/logout" style="color:#000000;">logout</a>
    </div>
  </nav>

  <!-- header -->
  <div class="page-section">
  </div>

  <!-- section -->
  <section class="page-section" id="about">
    <div class="container">
      <h3 class="section-heading text-uppercase">고객상담 (관리자용)</h3>
        <table class="tg">
	        <tr>
	          <td class="tg-c3ow"><input class="selectAllBox" value="${consulting.consultingNo }" type="checkbox"/></td>
	          <th class="tg-c3ow">시간</th>
	          <th class="tg-c3ow">성함</th>
	          <th class="tg-c3ow">생년월일</th>
	          <th class="tg-c3ow">직업</th>
	          <th class="tg-c3ow">연락처</th>
	          <th class="tg-c3ow">통화가능시간</th>
	          <th class="tg-c3ow">관심분야</th>
	          <th class="tg-c3ow">1차콜</th>
	          <th class="tg-c3ow">담당자</th>
	          <th class="tg-c3ow">상담지역</th>
	          <th class="tg-c3ow">상담내용</th>
	          <th class="tg-c3ow">상담완료</th>
	        </tr>
	        
		   <c:forEach items="${consultings}" var="consulting" begin = "0" varStatus="status">
	        <tr class="tableRow">
	          <td class="tg-0pky"><input class="selectBox" value="${consulting.consultingNo }" type="checkbox"/></td>
	          <td class="tg-0pky"><fmt:formatDate value="${consulting.consultingRegDate }" pattern="MM-dd HH:mm"></fmt:formatDate></td>
	          <td class="tg-0pky">${consulting.consultingName}</td>
	          <td class="tg-0pky">${consulting.consultingBirthday }</td>
	          <td class="tg-0pky">${consulting.consultingJob }</td>
	          <td class="tg-0pky">${consulting.consultingPhone}</td>
	          <td class="tg-0pky">${consulting.consultingCallTime}</td>
	          <td class="tg-0pky">${consulting.consultingFavoriteType}</td>
		      <td class="tg-0pky"><input type="checkbox" <c:out value="${consulting.consultingIsCall == 'true' ? 'checked' : '' }"/>></td>
		      <td class="tg-0pky">${consulting.adminId }</td>
	          <td class="tg-0pky">${consulting.consultingRegion }</td>
	          <td class="tg-0pky"><textarea class="form-control remarks" rows="1">${consulting.consultingRemarks} </textarea></td>
			  <td class="tg-0pky"><input class="endCheckBox" value="${consulting.consultingNo }" type="checkbox" name = "consultingIsEnd" <c:out value="${consulting.consultingIsEnd == 'true' ? 'checked' : '' }"/>></td>
			</tr>
		  </c:forEach>
        </table>
      </div>
  </section>
  
  <!-- pagenation -->
	<div>
		<div class="text-center">
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item">
						<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
					</li>
				</c:if>
				<c:forEach begin= "${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
					<li class="page-item <c:out value="${pageMaker.criteria.page == idx ? 'active' : '' }"/>">
						<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(idx)}" id="selectPage ${idx }">${idx}</a>
					</li>
				</c:forEach> 
				<c:if test="${pageMaker.next }">
					<li class="page-item">
						<a class="page-link" href="${path }/consulting/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a>
					</li>
				</c:if>
			</ul><!-- pagenation -->
		</div><!--/.text-center-->
	</div>

	<div class="container">
	  <div class="row">
	    <div class="form-group">
			<button type="button" class="btn btn-primary btn-flat" id="modifyBtn">갱신</button>
	    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#assignModal">배정</button>
	    </div>
	  </div>
	  <div class="row">
	    <div class="form-group col-sm-4">
	      <select id="sortType" class="custom-select">
	        <option value="" <c:out value="${criteria.sortType == null ? 'selected' : '' }"/>>===정렬===</option>
	        <option value="call" <c:out value="${criteria.sortType == 'call' ? 'selected' : ''}"/>>1차콜</option>
	        <option value="reg" <c:out value="${criteria.sortType == 'reg' ? 'selected' : ''}"/>>등록일</option>
	        <option value="end" <c:out value="${criteria.sortType == 'end' ? 'selected' : ''}"/>>최종확인</option>
	      </select>
	    </div>
	    <div class="form-group col-sm-4">
	      <select id="sortOrder" class="custom-select">
	        <option value="DESC" <c:out value="${criteria.sortOrder == 'DESC' ? 'selected' : '' }"/>>내림차순</option>
	        <option value="ASC" <c:out value="${criteria.sortOrder == 'ASC' ? 'selected' : '' }"/>>오름차순</option>
	      </select>  
	    </div>
	  </div><!-- /.row -->
	  <div class="row">
	    <div class="form-group col-sm-2" >
	      <select id="searchType" class="custom-select">
	        <option value="none" <c:out value="${criteria.searchType == null ? 'selected' : '' }"/>>===선택===</option>
	        <option value="i" <c:out value="${criteria.searchType == 'i' ? 'selected' : '' }"/>>담당자</option>
	        <option value="n" <c:out value="${criteria.searchType == 'n' ? 'selected' : '' }"/>>신청자명</option>
	      </select>
	    </div>
	    <div class="form-group col-sm-8">
	      <input type="text" class="form-control" name="keyword" id="keyword" value="${criteria.keyword }" placeholder="검색어"/>
	    </div>
	    <div class="form-group col-sm-2">
	      <button class="btn btn-primary searchBtn">검색</button>
	      <button type="button" class="btn btn-primary btn-flat" id="deleteBtn">삭제</button>
	    </div>
	  </div><!-- /.row -->
	</div><!--/.card-footer-->

  <%@ include file="../include/main_footer2.jsp" %>
  <%@ include file = "../include/plugin_js2.jsp" %>


<!-- 추후 별도 파일로 뺄 것 -->
			<!-- The Modal -->
			<div class="modal" id="assignModal">
			  <div class="modal-dialog">
			    <div class="modal-content">
			
			      <!-- Modal Header -->
			      <div class="modal-header">
			        <h4 class="modal-title">담당 배정</h4>
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			      </div>
			
			      <!-- Modal body -->
			      <div class="modal-body">
			      
                    <select id="pointList" class="custom-select">
                    	<option>===지사===</option>
                    </select>
                    <select id="memberList" class="custom-select">
                    	<option value="">===담당자===</option>
                    </select>
			      </div>
			
			      <!-- Modal footer -->
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary btn-flat" id="assignBtn">확인</button>
			        <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			      </div>
			    </div>
			  </div>
			</div><!--  /.Modal -->
	            
			<!-- The Modal -->
			<div class="modal" id="modifyModal">
			  <div class="modal-dialog">
			    <div class="modal-content">
			
			      <!-- Modal Header -->
			      <div class="modal-header">
			        <h4 class="modal-title">갱신</h4>
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			      </div>
			
			      <!-- Modal body -->
			      <div class="modal-body">
			      	<input type="text" name="remarks" id="remarks" class="form-control" placeholder="비고">
			      	<select id="checkType" class="custom-select">
			      		<option value="consultingNothing">===== 선택 =====</option>
			      		<option value="consultingIsCall">1차 전화</option>
			      		<option value="consultingIsEnd">최종 확인</option>
			      	</select>
			      </div>
			
			      <!-- Modal footer -->
			      <div class="modal-footer">
			        <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			      </div>
			    </div>
			  </div>
			</div><!--  /.Modal -->

		            


<script type="text/javascript">
	$(document).ready(function(){
	
	  // 메시지 
	  var result = "${msg}";
	  if (result == "regSuccess") {
	      alert("게시글 등록이 완료되었습니다.");
	  } else if (result == "modSuccess") {
	      alert("게시글 수정이 완료되었습니다.");
	  } else if (result == "delSuccess") {
	      alert("게시글 삭제가 완료되었습니다.");
	  }
	
	  // 일괄 선택/해제 이벤트
	    $(".selectAllBox").click(function(){
			var chk = $(".selectAllBox").prop("checked");
			if(chk){
				$(".selectBox").prop("checked", true); // 모든 체크박스 true
			} else {
				$(".selectBox").prop("checked", false); // 모든 체크박스 false
			}
	    });
		
	  // 검색 후 동작 url
	  var searchLocation = function(){
	    self.location =
	        "/consulting/list${pageMaker.makeQuery(1)}"
	     + "&sortType=" + $("#sortType option:selected").val()
	     + "&sortOrder=" + $("#sortOrder option:selected").val()
	     + "&searchType=" +$("#searchType option:selected").val()
	     + "&keyword=" + encodeURIComponent($('#keyword').val());
	  }
	
	  // 작업 처리 후 페이지 유지를 위한 동작 url
	  var searchPageLocation = function(page){
		    self.location =
		        "/consulting/list${pageMaker.makeQuery()}"
		     + "&sortType=" + $("#sortType option:selected").val()
		     + "&sortOrder=" + $("#sortOrder option:selected").val()
		     + "&searchType=" +$("#searchType option:selected").val()
		     + "&keyword=" + encodeURIComponent($('#keyword').val());
		  }
	
	  // 검색 버튼 이벤트
		$('.searchBtn').click(function(){
			searchLocation();
		});

	
	  // 삭제 이벤트
		$('#deleteBtn').click(function(){
			// 확인 alert
			var confirm_val = confirm('정말 삭제하시겠습니까?');
	
			if(confirm_val){
				var checkArr = new Array();
	
				$("input[class='endCheckBox']:checked").each(function(){
					checkArr.push($(this).attr("value"));
				});
	
				$.ajax({
					url : "/consulting/delete",
					type : "post",
					data : {chkbox : checkArr},
				    success : function(){
				    	searchPageLocation();
				    }
				});
			}
		});
	
	  // 테이블 자체 정렬 ( 진행중 )
		$('.sorting').click(function(){
			var consultings = "${consultings}";
			alert(consultings);
			consultings.each(function(index, item){
				alert("index : " + index + ", item : " + item);
			});
	
		});
	
		// 담당자 변경
		$('#assignBtn').click(function(){
			var adminId = $("#memberList option:selected").val();
			var checkArr = new Array();
	
			$("input[class='selectBox']:checked").each(function(){
				checkArr.push($(this).attr("value"));
			});
		  $.ajax({
			    url : "/consulting/updateAdmin",
			    method : "GET",
			    dataType : "json",
			    data : {chkbox:checkArr, "adminId":adminId},
			    success : function(data){
			      if(data == 1){
			        alert('success');
			        searchPageLocation();
			      }
			    },
			    error : function(){
			      alert('admin 수정 실패');
			    }
			  });
		});


		var makeConsultingArr = function(){
			var modifyArray = new Array();
			var modifyDTO;
			$(".tableRow").each(function(i){
				var rowData = new Array();
				
				var str = "";
				var tdArr = new Array();

				var tr = $(this);
				var td = tr.children();


				var pointSelect = $("#pointList option:selected").val();
				
				modifyDTO = 
				{
					"consultingNo" : td.eq(0).children().val(),
					"consultingIsCall" : td.eq(8).children().prop("checked"),
					"consultingIsEnd" : td.eq(12).children().prop("checked"),
					"consultingRemarks" : td.eq(11).children().val()
				};

				console.log("consultingModifyDTO log : " + modifyDTO);
				modifyArray.push(modifyDTO);

			});

			return modifyArray;
		}

		
		// 갱신
		  var checkUpdate = function(){
			    var checkType = $('#checkType option:selected').val();
			    var remarks = $('#remarks').val();
			    var consultingArr = new Array();
			    var checkArr = new Array();
			    var modifyNotJSONArray = makeConsultingArr();

			    console.log(modifyNotJSONArray);
				console.log(JSON.stringify(modifyNotJSONArray));

			    var modifyArray = JSON.stringify(modifyNotJSONArray);
			    
			    $.ajax({
			      url : "/consulting/update",
			      method : "post",
			      dataType : "json",
			      data : modifyArray,
			      contentType : "application/json; charset=UTF8",
			      success : function(data){
			        if(data == 1){
			          alert("success");
			          searchPageLocation();
			        }
			      },
			      error : function(){
			        alert('갱신 실패');
			      }
			    });
			  }
		  
			$('#modifyBtn').click(function(){
				checkUpdate();
			});
	
	
		  // 현재 지사들의 목록을 불러와서 옵션을 추가하는 이벤트
			var loadPoint =  function(){
			     $.ajax({
			       url : "/admin/listPoint", // HTTP요청을 보낼 URL 주소
			       method : "GET", // 요청 메소드
			       dataType : "json", // 서버에서 보내줄 데이터 타입
			       contentType : "application/json; charset=UTF8",
			       success : function(data){ // 성공 시 처리
			         var data_key = Object.keys(data);
			           for(var i = 0; i < data_key.length; i++){
				         var op = new Option();
				         op.value = data[data_key[i]].pointNo;
				         op.text = data[data_key[i]].pointName;
				         document.getElementById("pointList").add(op);
			         }
			       },
			       error : function(){
			         alert('point 불러오기 실패');
			       }
			     });
			}
		   loadPoint();
				
		   // 현재 지사의 회원 정보를 불러와서 옵션을 추가하는 이벤트
		   var listAdmin =  function(){
				  $("#memberList option").remove();
				  var pointSelect = $("#pointList option:selected").val();
				    $.ajax({
				      url : "/admin/listAdmin", // HTTP요청을 보낼 URL 주소
				      method : "GET", // 요청 메소드
				      data : {"pointNo": pointSelect},
				      dataType : "json", // 서버에서 보내줄 데이터 타입
				      success : function(data){ // 성공 시 처리
				        var data_key = Object.keys(data);
				        for(var i = 0; i < data_key.length; i++){
							var op = new Option();
							op.value = data[i].adminId;
							op.text = data[i].adminName + ":" + data[i].adminPosition;
							document.getElementById("memberList").add(op);
					    }
				      },
				      error : function(){
				        alert('admin 불러오기 실패');
				      }
				    });
			    }
		
		  $("#pointList").on('change',function(){
			  listAdmin();
			});
	
	});
</script>
</body>

</html>
