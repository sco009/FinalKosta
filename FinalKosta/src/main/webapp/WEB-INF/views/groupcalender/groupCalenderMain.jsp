<%@page import="cosmos.groupcalender.domain.CalenderVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Calendar cal = Calendar.getInstance();
	int year = request.getParameter("yy") == null
			? cal.get(Calendar.YEAR)
			: Integer.parseInt(request.getParameter("yy"));
	int month = request.getParameter("mm") == null
			? cal.get(Calendar.MONTH)
			: (Integer.parseInt(request.getParameter("mm")) - 1);

	// ���ۿ��� Ȯ��
	// - Calendar MONTH�� 0-11������
	cal.set(year, month, 1);
	int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

	// ����/������ ���
	// - MONTH ���� ǥ����� ����ϱ� ������ +1�� �� ���¿��� �����
	int prevYear = year;
	int prevMonth = (month + 1) - 1;
	int nextYear = year;
	int nextMonth = (month + 1) + 1;

	// 1���� ��� ������ 12���� ����
	if (prevMonth < 1) {
		prevYear--;
		prevMonth = 12;
	}

	// 12���� ��� ������ 1���� ����
	if (nextMonth > 12) {
		nextYear++;
		nextMonth = 1;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script type="text/javascript">
		function detail(yy,mm,dd){
			popupOpen(yy,mm,dd);
			/* loaction.href="/sboard/test/detail?yy="+yy+"&mm="+mm+"&dd="+dd; */
		};
		
		
		function popupOpen(yy,mm,dd){
			var popUrl = "/groupcalender/groupCalenderDetail?yy="+yy+"&mm="+mm+"&dd="+dd;	//�˾�â�� ��µ� ������ URL
			var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //�˾�â �ɼ�(optoin)
				window.open(popUrl,"",popOption);
			};
		</script>
<title>Insert title here</title>
<style type="text/css">
.week {
	background: #FFC19E
}

a.no-uline {
	text-decoration: none
}


</style>
</head>
<body>


	<table border="0" cellpadding="0" cellspacing="0" width="1000px">
		<tr>
			<td align="center" style="font-size: 3em; color: #FFC19E;"><a
				href="/groupcalender/movemain?yy=<%=prevYear%>&mm=<%=prevMonth%>"
				style="text-decoration: none" >��</a> <%=year%>�� <%=month + 1%>�� <a
				href="/groupcalender/movemain?yy=<%=nextYear%>&mm=<%=nextMonth%>"
				style="text-decoration: none">��</a></td>
		</tr>
		<tr>
			<td>

				<table border="1" bordercolor="#F29661" width="1500px">
					<tr align="center" height="50px" class="week">
						<td>��</td>
						<td>��</td>
						<td>ȭ</td>
						<td>��</td>
						<td>��</td>
						<td>��</td>
						<td>��</td>
					</tr>
					<tr align='center' height="100px" class="day">

						<%
							// ���ۿ��ϱ��� �̵�
							for (int i = 1; i < bgnWeek; i++)
								out.println("<td>&nbsp;</td>");

							// ù��~������������ ó��
							// - ��¥�� �Ϸ羿 �̵��Ͽ� ���� �ٲ𶧱��� �׸���
							while (cal.get(Calendar.MONTH) == month) {

								out.println("<td id='"
										+cal.get(Calendar.DATE)+"'><a onclick='detail("+year+","+(month+1)+","+cal.get(Calendar.DATE)+")' style='text-decoration:none' >" + cal.get(Calendar.DATE) + "</a></td>");

								// ������� ��� �����ٷ� ����
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
									out.println("</tr><tr align='center' height='100px'>");

								// ��¥ ������Ű�� ��
								cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) + 1);
							}

							// �������� ����ϱ��� ��ĭ���� ó��
							if(cal.get(Calendar.DAY_OF_WEEK) != 6){
							for (int i = cal.get(Calendar.DAY_OF_WEEK); i <= 7; i++)
								out.println("<td>&nbsp;</td>");
							}
						%>
					</tr>
				</table>

			</td>
		</tr>
	</table>

	<c:set var="yy" value='<%=year%>' />
	<c:set var="mm" value='<%=month + 1%>' />
	
	<c:forEach items="${list }" var="list">
		<c:if test="${list.yy == yy}">
			<c:if test="${list.mm==mm}">
				<script type="text/javascript">
					var a = $('#'+${list.dd}).text();
					if(a == ${list.dd}){
						document.getElementById(${list.dd}).style.backgroundColor = "#FFC19E";
						
					}
					
				</script>
			</c:if>
		</c:if>
		
	</c:forEach>
	
	
</body>
</html>