<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FrameDown</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">
</head>
<body>
	<input type="hidden" id="lgnUserId" name="lgnUserId" value="hidden" />
	<table width=100%>
		<tr height=900px>
			<td style="vertical-align:top;width:20%;border-right:ridge">
				<iframe id="left" name="left" style="height:900px;width:100%" src="FrameLeft"></iframe>
			</td>
			<td style="vertical-align:top;width:80%">
				<iframe id="body" name="body" style="height:900px;width:100%;" src="Login"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>