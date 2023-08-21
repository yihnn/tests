<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장소 추가</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/postspot/resources/css/write.css"/>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div>
	<form action="" name="myForm" method="post">
	<div class="table_Div">
		<div class="select" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
			<div class="place">
				<select class="form-select" name="place">
				  <option selected>지역 선택</option>
				  <option value="1">One</option>
				  <option value="2">Two</option>
				  <option value="3">Three</option>
				</select>
			</div>
			
			<div class="city">
				<select class="form-select" name="city">
				  <option selected>장소 타입 선택</option>
				  <option value="korean">한식</option>
				  <option value="western">양식</option>
				  <option value="japanese">일식</option>
				  <option value="chinese">중식</option>
				  <option value="cafe">카페</option>
				  <option value="bakery">베이커리</option>
				  <option value="lodge">숙박</option>
				  <option value="place">명소</option>
				  <option value="propshop">소품점</option>
				  <option value="drinks">주류</option>
				</select>
			</div>
		</div>
		
		<div class="form-floating mb-3" style="width: 100%;">
		  <input type="text" class="form-control" id="floatingInput" placeholder="상호명" name="spotName">
		  <label for="floatingInput">상호명</label>
		</div>
		
		<div class="form-floating mb-3" style="width: 100%;">
		  <input type="text" class="form-control" id="floatingInput" placeholder="주소" name="address">
		  <label for="floatingInput">주소</label>
		</div>
		
		
		<div class="form-floating mb-3" style="width: 100%;">
		  <textarea class="form-control" placeholder="추천 내용" id="floatingTextarea" 
		   style="height: 250px; resize: none;" name="content" maxlength="2000"></textarea>
		  <label for="floatingTextarea">추천 내용</label>
		</div>
		
		<div class="choice_button">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="width: 70px;">
			  추가
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">장소 추가</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        저장 하시겠습니까?
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary" onclick="document.forms['myForm'].submit()">저장</button>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<button type="button" class="btn btn-secondary" style="width: 70px;" onclick="location.href='mainHome'">
			취소</button>
			
		</div>
	</div>
	
	
	</form>
</div>


</body>
</html>