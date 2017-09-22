<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="res/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="res/favicon.ico" type="image/x-icon">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet" >
<style> 
body {
    <!--background-image: url("img_tree.gif"), url("img_flwr.gif");-->
    <!--background-color: #cccdcc;-->
    
    background-repeat: repeat;
    background-image: url(res/swirl_pattern.png);
}
input[type=text], select {
    width: 100%;
    padding: 12px 15px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

div {
    border-radius: 5px;
    background-color: #FFFFFF;
    padding: 20px;
    padding-left: 150px;
    padding-right: 150px;
    -webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
	-moz-box-sizing: border-box;    /* Firefox, other Gecko */
	box-sizing: border-box;         /* Opera/IE 8+ */
	max-width:700px;
}
</style>
<style type="text/css">
	#bike__icon{fill:#E54C3B; visibility: hidden}
	#bike__rect{fill:#E54C3B;}
	#bike__text{fill:#fff;font-size:20px;}
</style>

<title>Smack Reservation</title>
</head>
<body>
<img src = "res/SmackLogo.png"
height = "84" width = "280"
vspace = "10">
<div>
<table class ="table table-bordered">
    <tr>
        <th>Rack ID</th>
        <th>Slot 0</th>
        <th>Slot 1</th>
        <th>Slot 2</th>
        <th>Slot 3</th>
    </tr>
    <c:forEach items="${racksData}" var="racksData">
    <tr>
        <td><c:out value="${racksData.rackID}" /></td>
        <td><c:out value="${racksData.slot0}" /></td>
        <td><c:out value="${racksData.slot1}" /></td>
        <td><c:out value="${racksData.slot2}" /></td>
        <td><c:out value="${racksData.slot3}" /></td>
    </tr>
    </c:forEach>
</table>
<!--<c:out value="${racksData}" />-->
<form action="reserve" method="post">
            <p>
                <label for="id">Enter rack ID:</label>
                
                <input type="text" id="id" name="id" value="${fn:escapeXml(param.id)}">
                <span class="error" style="color:#fa4030">${messages.id}</span>
            </p>
            <p>
                <label for="slot">Enter slot number:</label>
                <input type="text" id="slot" name="slot" value="${fn:escapeXml(param.slot)}">
                <span class="error" style="color:#fa4030">${messages.slot}</span>
            </p>
            <p>
                <label for="pin">Enter PIN:</label>
                <input type="text" id="pin" name="pin" value="${fn:escapeXml(param.pin)}">
                <span class="error" style="color:#fa4030">${messages.pin}</span>
            </p>
            <p>
                <label for="duration">Enter duration for reservation:</label>
                <input type="text" id="duration" name="duration" value="${fn:escapeXml(param.duration)}">
                <span class="error" style="color:#fa4030">${messages.duration}</span>
            </p>
            <p><center>
            	<label style="text-align:right;">
            		<input type="submit" />
                		<svg version="1.1" id="favorite" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
	 						viewBox="0 0 151.8 75" style="enable-background:new 0 0 151.8 75;" xml:space="preserve" width="100px">
 							<metadata id="metadata13">image/svg+xml</metadata>

							<style type="text/css">
								#bike__icon{fill:#fa4030; visibility: hidden}
								#bike__rect{fill:#fa4030;}
								#bike__text{fill:#fff;font-size:20px;}
							</style>
  

 							<g id = "bike__icon">
  								<path fill="#ffffff" fill-rule="evenodd" stroke="#fa4030" stroke-width="2.5px" d="m-101.56634,275.21499c0,193.29966 -156.70034,350 -350,350c-193.29966,0 -350,-156.70034 -350,-350c0,-193.29966 156.70034,-350 350,-350c193.29966,0 350,156.70034 350,350l0,0z" id="OuterCircle"/>
  								<path fill="#fa4030" fill-rule="evenodd" d="m-787.50747,13.11189c-184.78586,0 -335.50003,150.71414 -335.50003,335.5c0,184.78586 150.71417,335.5 335.50003,335.5c184.78586,0 335.5,-150.71414 335.5,-335.5c0,-184.78586 -150.71414,-335.5 -335.5,-335.5z" id="BlueCircle"/>
  								<g stroke="null" id>
  									 <circle stroke="#fa4030" fill="none" stroke-width="40px" id="svg_5" cx="647.174952" cy="-1511.135294" r="161" transform="matrix(0.14396556233686614,0,0,0.128232944943123,-57.99244442135567,245.99748164730016) "/>
   									 <circle stroke="#fa4030" fill="none" stroke-width="40px" id="svg_6" cx="1222.174952" cy="-1514.135294" r="161" transform="matrix(0.14396556233686614,0,0,0.128232944943123,-57.99244442135567,245.99748164730016) "/>
   									 <path stroke="#fa4030" fill="#fa4030" fill-rule="evenodd" stroke-width="0.612193" d="m66.056809,1.756265c-19.084981,0.165705 -15.848344,-1.756264 -26.116299,27.636443c-8.364776,23.484758 -7.453661,20.923658 -7.497398,22.570985c-0.030655,1.279779 1.129172,2.294486 2.586627,2.381327c3.031003,-0.272968 1.756101,0.292005 12.264505,-28.774128c38.397359,36.079055 23.991621,30.228503 44.655688,30.961195c27.157997,-0.231967 29.365359,1.2592 29.687804,-2.286458c-0.037211,-1.203999 -0.669678,-1.921969 -19.308247,-38.869951l1.785738,-4.374133l6.138464,-0.099392l-0.223236,-4.572943l-22.879662,0c-2.021367,2.463199 0.818462,2.452164 11.049206,4.473525l-1.785738,4.473525l-45.647751,0.099418c3.075399,-9.565628 1.339296,-8.969146 15.178696,-8.94705l0.111603,-4.672361zm-16.609328,18.522353l44.667244,-0.07055l-12.909156,30.897627l-31.758088,-30.827077zm50.052657,1.834109l-12.67155,29.627863l27.719035,0.07055l-15.047485,-29.698413z" id="svg_7"/>
  								</g>
 								</g>
								<path id="bike__rect" d="M149.8,62.5H2c-1.1,0-2-0.9-2-2v-46c0-1.1,0.9-2,2-2h147.8c1.1,0,2,0.9,2,2v46
								C151.8,61.6,150.9,62.5,149.8,62.5z"/>
							<text id="bike__text" transform="matrix(1 0 0 1 40 43)">Submit</text>
				</svg>
				</label></center>
                <span class="error">${messages.success}</span>
            </p>
            
        </form>
        </div>
        <!--img src = "https://maps.googleapis.com/maps/api/staticmap?center=UT,Austin,TX&zoom=15&size=600x300&maptype=roadmap
&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318
&markers=color:red%7Clabel:C%7C40.718217,-73.998284
&key=AIzaSyDw5oNnVFH7Onksji47uGRz6dl_sX_9OE4">-->
    <script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/MorphSVGPlugin.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js'></script>

    <script src="js/index.js"></script>
</body>
</html>