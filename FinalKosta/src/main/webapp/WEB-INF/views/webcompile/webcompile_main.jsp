<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">


<link href="https://fonts.googleapis.com/css?family=Architects+Daughter"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="/resources/dist/css/webcompile/style.css" media="screen" />
<!-- 알고리즘 캔버스 -->
<!-- <script type='text/javascript' src="/resources/dist/js/webcompile/algorithm.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/i18next/3.1.0/i18next.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/i18next-locize-backend/0.1.1/i18nextLocizeBackend.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-i18next/0.0.14/i18next-jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/i18next-browser-languagedetector/0.3.0/i18nextBrowserLanguageDetector.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<!-- 컴파일러 자동완성 라이브러리 -->
<script type='text/javascript'
	src="/resources/dist/js/webcompile/behave.js"></script>

<!-- Bootstrap -->
<link href="/resources/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
	/* function loadJQuery() {
	 var oScript = document.createElement("script");
	 oScript.type = "text/javascript";
	 oScript.charset = "utf-8";		  
	 oScript.src = "/resources/dist/js/webcompile/algorithm.js";	
	 document.getElementsByTagName("head")[0].appendChild(oScript);
	 } */
	$(document)
			.ready(
					function() {
						var canvas = document.getElementById('canvas');
						var context = canvas.getContext('2d');
						var x = 100;
						var y = 0;
						var h = 30;

						function overrect(mx, my) {
							if ((mx > this.x) && (mx <= (this.x + this.y))
									&& (my >= this.y)
									&& (my <= this.y + this.h)) {
								return true;
							} else {
								return false;
							}
						}

						function overoval(mx, my) {
							var x1 = 0;
							var y1 = 0;
							var x2 = (mx - this.x) / this.hor;
							var y2 = (my - this.y) / this.ver;

							if (distsq(x1, y1, x2, y2) <= (this.radsq)) {
								return true;
							} else {
								return false;
							}
						}

						function Figure(varName, figure, isOval, isCond) {
							this.varName = varName;
							this.figure = figure;
							this.isOval = isOval;
						}

						//Start/End 객체
						function Oval(x, y, r, hor, ver, c, m) {
							this.x = x;
							this.y = y;
							this.r = r;
							this.radsq = r * r;
							this.hor = hor;
							this.ver = ver;
							this.draw = drawOval;
							this.overcheck = overoval
							this.color = c;
							this.message = m;
						}

						//Operation 객체 - 사각형
						function Operation(x, y, w, h, m) {
							this.x = x;
							this.y = y;
							this.w = w;
							this.h = h;
							this.draw = drawOperation;
							this.overcheck = overrect;
							this.message = m;
						}

						//Subroutine 객체 - 사각형 안에 줄있는거
						function Subroutine(x, y, w, h, m) {
							this.x = x;
							this.y = y;
							this.w = w;
							this.h = h;
							this.draw = drawSubroutine;
							this.message = m;
						}

						//Condition 객체 - 마름모
						function Condition(x, y, w, h, m) {
							this.x = x;
							this.y = y;
							this.w = w;
							this.h = h;
							this.draw = drawCondition;
							this.message = m;
						}

						//InputOutput 객체 - 평행사변형
						function InputOutput(x, y, w, h, m) {
							this.x = x;
							this.y = y;
							this.w = w;
							this.h = h;
							this.draw = drawIO;
							this.message = m;
						}

						//Start/End 객체 그리는 함수
						function drawOval() {
							// z : 글자 넣을 때 도형의 왼쪽 여백
							var z = this.w / 5;

							// 글자 넣는 부분 - 미완성
							context.beginPath();
							context.font = 'italic 15px Calibri';
							context.strokeText(this.message,
									this.x + (z * 1.5), this.y + this.r);
							context.stroke();

							// 타원 그리는 부분
							context.save();
							context.translate(this.x, this.y);
							context.scale(this.hor, this.ver);
							context.fillStyle = this.color;
							context.beginPath();
							context.arc(0, 0, this.r, 0, 2 * Math.PI, true);
							context.closePath();
							context.fill();
							context.restore();
						}

						// Operation 객체 그리는 함수
						function drawOperation() {
							// z : 글자 넣을 때 도형의 왼쪽 여백
							// r : 글자 넣을 때 높이
							var z = this.w / 5;
							var r = this.h / 2;

							// 사각형 그리기
							context.beginPath();
							context.rect(this.x, this.y, this.w, this.h);
							context.stroke();

							// 글자 넣는 부분
							context.beginPath();
							context.font = 'italic 15px Calibri';
							context.strokeText(this.message,
									this.x + (z * 1.5), this.y + r);
							context.stroke();
						}

						// Subroutine 객체 그리는 함수
						function drawSubroutine() {
							// z : 글자 넣을 때 도형의 왼쪽 여백
							// r : 글자 넣을 때 높이
							var z = this.w / 5;
							var r = this.h / 2;

							// 사각형 그리는 부분
							context.beginPath();
							context.rect(this.x, this.y, this.w, this.h);
							context.stroke();

							// 사각형 내 오른쪽 줄
							context.beginPath();
							context.moveTo(this.x + z, this.y);
							context.lineTo(this.x + z, this.y + this.h);
							context.stroke();

							// 사각형 내 왼쪽 줄
							context.beginPath();
							context.moveTo(this.x + (this.w - z), this.y);
							context.lineTo(this.x + (this.w - z), this.y
									+ this.h);
							context.stroke();

							// 글자 넣기
							context.beginPath();
							context.font = 'italic 15px Calibri';
							context.strokeText(this.message,
									this.x + (z * 1.5), this.y + r);
							context.stroke();
						}

						// Condition객체 그리기 함수
						function drawCondition() {
							// z : 글자 넣을 때 도형의 왼쪽 여백
							// r : 글자 넣을 때 높이
							var z = this.w / 5;
							var r = this.h / 2;

							// 4개의 좌표 값을 이용해서 선분으로 연결 - 마름모
							context.moveTo(this.x + (this.w / 2), this.y);
							context.lineTo(this.x + this.w, this.y
									+ (this.h / 2));
							context.lineTo(this.x + (this.w / 2), this.y
									+ this.h);
							context.lineTo(this.x, this.y + (this.h / 2));
							context.closePath();
							context.stroke();

							// 글자 넣는 부분
							context.beginPath();
							context.font = 'italic 15px Calibri';
							context.strokeText(this.message,
									this.x + (z * 1.5), this.y + r);
							context.stroke();
						}

						// InputOutput 그리는 함수
						function drawIO() {
							// z : 글자 넣을 때 도형의 왼쪽 여백
							// r : 글자 넣을 때 높이 
							var z = this.w / 5;
							var r = this.h / 2;

							// 4개의 좌표를 선분으로 연결 - 평행사변형
							context.beginPath();
							context.moveTo(this.x + z, this.y);
							context.lineTo(this.x + this.w, this.y);
							context.lineTo(this.x + (this.w - z), this.y
									+ this.h);
							context.lineTo(this.x, this.y + this.h);
							context.closePath();
							context.stroke();

							// 글자 넣는 부분
							context.beginPath();
							context.font = 'italic 15px Calibri';
							context.strokeText(this.message,
									this.x + (z * 1.5), this.y + r);
							context.stroke();
						}

						// 순서 
						// codeStr : ';' 기준으로 자른 문자열 (Procedure 다음 나오는 ';' 까지 1덩어리)
						// stuff : 순서 없이 도형이 선언 될 때 도형 객체를 생성해서 넣은 배열
						function procedure(codeStr, stuff) {
							var startIndex = codeStr.indexOf("{") + 1;
							var lastIndex = codeStr.lastIndexOf("}");
							var procedureStr = codeStr.substring(startIndex,
									lastIndex);
							var procedure = procedureStr.split("->"); // '->'기준으로 문자열 잘라 넣은 배열 (도형 그리는 순서)
							var procedureArr = []; // 순서대로 도형 객체를 넣는 배열

							// 순서 배열(procedure)와 도형이 넣어진 배열(stuff)을 비교하여 순서 도형 객체(procedureArr)에 넣는다
							for (var i = 0; i < procedure.length; i++) {
								for (var j = 0; j < stuff.length; j++) {
									if (procedure[i] == stuff[j].varName) {
										// 순서 도형 객체에 넣을 때 도형의 y 값을 다시 세팅해주어 그릴 때 아래로 그릴 수 있도록
										var y = procedureArr.length * 70 + 30;
										stuff[j].figure.y = y;
										var x = 0;

										// 가운데 정렬을 위해 x값을 세팅해 주어야 하는데 (타원은 높이가 존재하지 않기 때문),
										// 타원와 다각형의 방식이 다르기 때문에 나누어 진행
										// isOval는 figuer 속성에 boolean형식으로 존재
										if (stuff[j].isOval) {
											var r = parseInt(stuff[j].figure.r);
											x = parseInt(canvas.width) * 0.5;
											stuff[j].figure.x = x;
										} else {
											var w = parseInt(stuff[j].figure.w);
											x = parseInt(canvas.width) * 0.5
													- w * 0.5;
											stuff[j].figure.x = x;
										}

										// 세팅이 끝난 도형은  도형 순서 배열에 넣어준다.
										procedureArr.push(stuff[j]);
										break;
									}
								}
							}
							;

							// 도형 순서 배열을 차례대로 draw해주어 그림을 그리는 함수 실행
							for (var i = 0; i < procedureArr.length; i++) {
								procedureArr[i].figure.draw();
							}

							// 연결선 그리는 함수 호출
							connection(procedureArr);
						}

						// 연결선 그리는 함수
						function connection(p) {
							var procedureArr = p;

							for (var i = 0; i < procedureArr.length - 1; i++) {
								var proX;
								var proY;
								var nextX;
								var nextY;

								// 타원과 다각형이 x,y 좌표가 위치가 다르기 때문에 따로 진행
								if (procedureArr[i].isOval
										|| procedureArr[i + 1].isOval) {
									var oval;
									var figure;
									if (procedureArr[i].isOval
											&& procedureArr[i + 1].isOVal) {
										alert("문법확인!");
										return;
									}

									// 타원와 다각형의 순서에 따라 연결선의 좌표가 다르다
									if (procedureArr[i].isOval) {
										oval = procedureArr[i].figure;
										figure = procedureArr[i + 1].figure;

										proX = oval.x;
										proY = oval.y + oval.r;

										nextX = figure.x + (figure.w / 2);
										nextY = figure.y;

									} else if (procedureArr[i + 1].isOval) {
										oval = procedureArr[i + 1].figure;
										figure = procedureArr[i].figure;

										proX = figure.x + (figure.w / 2);
										proY = figure.y + figure.h;

										nextX = oval.x;
										nextY = oval.y - oval.r;
									}

									context.beginPath();
									context.moveTo(proX, proY);
									context.lineTo(nextX, nextY);
									context.stroke();

								} else {
									var proFigure = procedureArr[i].figure;
									var nextFigure = procedureArr[i + 1].figure;

									proX = proFigure.x + (proFigure.w / 2);
									proY = proFigure.y + proFigure.h;

									nextX = nextFigure.x + (nextFigure.w / 2);
									nextY = nextFigure.y;

									context.beginPath();
									context.moveTo(proX, proY);
									context.lineTo(nextX, nextY);
									context.stroke();
								}

							}
						}

						// message길이에 마추어 도형의 너비 반환
						function makeWidth(message) {
							return message.length * 20;
						}

						// ""사이에 있는 문자열을 뽑아내어 반환
						function makeMessage(codeStr) {
							var startIndex = codeStr.indexOf("\"") + 1;
							var lastIndex = codeStr.lastIndexOf("\"");
							var message = codeStr.substring(startIndex,
									lastIndex);

							return message;
						}

						// textarea에 keydown이 될 때마다 함수를 실행 시켜 실시간인 것 처럼 보이게 해준다.
						$('#codeText')
								.on(
										"keydown",
										function() {
											/*
												codeText : textarea에서 값 가져와 저장
												codeStr :  codeText에서  ';' 기준으로 자른 배열
												keyword : codeStr에서 첫 번째 공백 앞의 문자열을 뽑아 내어 도형의 형태를 저장 (switch-case문 이용)
												stuff : 도형이 선언 될 때 객체를 생성하면서 변수명(varName),도형 객체, isOval의 유무를 figure객체에 담아 저장하는 배열
											 */
											var codeText = $('#codeText').val();
											var codeStr = codeText.split(';');
											var keyword = "";
											var stuff = [];

											context
													.clearRect(0, 0,
															canvas.width,
															canvas.height);

											for (var i = 0; i < codeStr.length - 1; i++) {
												keyword = codeStr[i].split(' ');

												switch (keyword[0].trim()) {
												case "Start":
												case "End":
													console.log("Oval");
													varName = keyword[1].trim();
													var message = makeMessage(codeStr[i]);
													stuff.push(new Figure(
															varName, new Oval(
																	x, y + h,
																	20, 2.0,
																	1.0,
																	"teal",
																	message),
															true));
													break;
												case "Operation":
													console.log("Operation");
													varName = keyword[1].trim();
													var message = makeMessage(codeStr[i]);
													w = makeWidth(message);
													stuff.push(new Figure(
															varName,
															new Operation(x, y,
																	w, h,
																	message),
															false));
													break;
												case "Condition":
													console.log("Condition");
													varName = keyword[1].trim();
													var message = makeMessage(codeStr[i]);
													w = makeWidth(message);
													stuff.push(new Figure(
															varName,
															new Condition(x, y,
																	w, h,
																	message),
															false));
													break;
												case "Subroutine":
													console.log("Subroutine");
													varName = keyword[1].trim();
													var message = makeMessage(codeStr[i]);
													w = makeWidth(message);
													stuff.push(new Figure(
															varName,
															new Subroutine(x,
																	y, w, h,
																	message),
															false));
													break;
												case "InputOutput":
													console.log("InputOutput");
													varName = keyword[1].trim();
													var message = makeMessage(codeStr[i]);
													w = makeWidth(message);
													stuff.push(new Figure(
															varName,
															new InputOutput(x,
																	y, w, h,
																	message),
															false));
													break;
												case "Procedure":
													console.log("Procedure");
													procedure(codeStr[i], stuff);
													break;
												default:
													break;
												}
												;

											}
											;
										});
					});

	$(function() {
		var editor = new Behave({

			textarea : document.getElementById('demo'),
			replaceTab : true,
			softTabs : true,
			tabSize : 4,
			autoOpen : true,
			overwrite : true,
			autoStrip : true,
			autoIndent : true
		});

		$("#complieFrom").submit(function(event) {
			event.preventDefault();

			$.ajax({
				url : "compile",
				type : "post",
				dataType : "text",
				data : $(this).serialize(),
				success : successHandler,
				error : function() {
					alert("실패");
				}
			});
		});

		function successHandler(data) {
			console.log(data);
			var str = data.replace(/^\s+/, "");
			$("#wc_result").empty();
			$("#wc_result").text(str);
		}

		$(document).ajaxStart(function() {
			$("#loading").fadeIn();
		}).ajaxStop(function() {
			$("#loading").fadeOut();
		});

	});
</script>

<title>Insert title here</title>
</head>
<body id="body">

	<div id="main">

		<div class="col-md-12" id="demo1">

		<div class="col-md-4">
			<form id="complieFrom">
				<textarea rows="30" cols="50" name="wc_code"
					onkeydown="useTab(this)" id="demo"></textarea>

				<div id="loading" style="width: 100%; text-align: center">
					<img alt="" src="/resources/dist/img/webcompile/compileLoading.gif"
						style="width: 100%; max-width: 150px; vertical-align: middle">
				</div>
				<br> <input type="submit" value="Compile"><br>

				<textarea rows="10" cols="50" type="disabled" id="wc_result"></textarea>
			</form>
		</div>
		<div id="algorithm">

			<textarea rows="30" cols="50" id="codeText"></textarea>
			<button class="btn">보기</button>
			<canvas id="canvas" width="400" height="700"
				style="border:3px solid black"></canvas>
		</div>
	</div>
	</div>
</body>
</html>