<%@ page session="false" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
	
<script type="text/javascript" src="//code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/spring-mvc-richeditor/resources/navereditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type='text/javascript'>
$(function(){
    //전역변수선언
    var editor_object = [];
    
    $('#smarteditor').val('Hello, Rich Editor !!!');
     
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "smarteditor", // textarea의 id
        sSkinURI: "/spring-mvc-richeditor/resources/navereditor/SmartEditor2Skin.html", 
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
    });

    //전송버튼 클릭이벤트
    $("#savebutton").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
         
        // 이부분에 에디터 validation 검증

        //폼 submit
        $("#frm").submit();
    })
})
</script>
</head>
<body>
<form action="/spring-mvc-richeditor/demo/write" method="post" id="frm">
<table style="width:800px;margin:0 auto">
	<tr>
		<th>
			<h3>Spring MVC Rich Editor Demo</h3>
		</th>
	</tr>
	<tr>
		<td>
			<input type="text" style="width:767px" name="title">
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" style="width:767px" name="writer">
		</td>
	</tr>
	<tr>
		<td>
			<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
		</td>
	</tr>
	<tr>
		<td>
			<input type="button" id="savebutton" value="서버전송" />
		</td>
	</tr>
</table>
</form>

</body>
</html>