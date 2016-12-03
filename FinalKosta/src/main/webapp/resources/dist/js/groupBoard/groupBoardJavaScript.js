var point = "";

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drag(ev) {
		ev.dataTransfer.setData("text", ev.target.id);
		var a = ev.target.id;
		point = $("#" + a).parents('div').parents('div').attr('id');

	}

	function drop(ev) {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text"); /* 선택한 태그 id값 가져오기 */
		ev.target.appendChild(document.getElementById(data));
		var parentId = $("#" + data).parents('div').parents('div').attr('id'); /* 최상위 div의 id값 가져오기 */
		var trash = $("#" + data).parents('div').attr('id'); /* 휴지통 div의 id값 가져오기 */
		var selectNum = data.replace(/[a-z]/gi, ''); /* update될 타겟의 groupboardid를 추출하기 위한 변수 */
		if (parentId != "ToDo" && parentId != "ING" && parentId != "END"
				&& parentId != "Issue" && trash != "deletes") {
			var firstparent = $("#" + data).parents('div').attr('id');
			var getDiv = document.getElementById(data); /* 드래그한 태그의 값을 전부 가져온다. */
			var parentsDiv = document.getElementById(firstparent); /* 상위 부모에 대한 값을 가져온다. */
			parentsDiv.removeChild(getDiv); /* ToDo,ING,END이외에 곳에 드래그되면 먼저  */

			var firstChild = $("#" + point).children().attr('id'); /* ToDo,ING,END의 판에 대한 첫번째 자식div의 id값을 가져온다. */
			var beforeparentsdiv = document.getElementById(firstChild); /* 첫번째 자식div의 값을 가져온다. */

			beforeparentsdiv.append(getDiv);
		} else {
			if (trash == "deletes") { /* 휴지통으로 들어왔을 때 실행 될 기능 */
				var getDiv = document.getElementById(data);
				var parentsDiv = document.getElementById(trash);
				parentsDiv.removeChild(getDiv);
				$.ajax({
					url : "deleteData",
					data : "groupBoardId=" + selectNum,
					dataType : 'json',
					success : function(data) {

						var html = ""; /* 수정되는 scrumCount를 변경시켜주기 위해 */

						$.each(data, function(index, entry) { /* 테이블 화면에 html태그를 붙여준다. */
							html += '<td>';
							html += entry;
							html += '</td>';
						});

						var scrumTable = document.getElementById('scrumTable');
						scrumTable.innerHTML = html;

						alert("제거성공");
					}
				});
			} else {
				/* 해당하는 todo, ing, end 위치에 드랍했을 때 DB에 gBoardCategori값을 ajax를 통해 해당하는 위치에 대한 값으로 변경해준다. */
				$.ajax({
					type : "post",
					url : "updateData",
					data : {
						groupBoardId : selectNum,
						gBoardCategori : parentId
					},
					dataType : 'json',
					success : function(data) {
						var html = ""; /* 수정되는 scrumCount를 변경시켜주기 위해 */

						$.each(data, function(index, entry) { /* 테이블 화면에 html태그를 붙여준다. */
							html += '<td>';
							html += entry;
							html += '</td>';
						});

						var scrumTable = document.getElementById('scrumTable');
						scrumTable.innerHTML = html;

						var scrumTable = document.getElementById('scrumTable');
						scrumTable.innerHTML = html;

						alert("업데이트성공");
					}
				});
			}
		}
	}