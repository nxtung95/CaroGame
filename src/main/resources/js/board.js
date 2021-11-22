var board = {
	writeBoard: function() {
		var st = '';
		st = '<table id="board-table"><tbody>';
		for(var i=0; i < currGame.SO_HANG; i++) {
			st += '<tr>';
			for(var j=0; j < currGame.SO_COT; j++) {
				st += '<td class="square" id="s' + String('00' + i).slice(-2) + String('00' + j).slice(-2) + '" onclick="board.sqClick(' + i + ',' + j + ')"></td>';
			};
			st += '</tr>';
		}
		st += '</tbody></table>';
		document.getElementById('board').innerHTML = st;
	},
	sqUpdate: function(i, j, type) {
		data = {
			"hang" : i,
			"cot" : j,
			"type" : type
		};
		$.ajax({
			url: "/kiemTraHople",
			contentType: "application/json",
			dataType: "json",
			type: "POST",
			data: JSON.stringify(data),
			success: function (result) {
				console.log(result);
				if (result.ketQua == true) {
					const OHtml='<img src="../img/o.png">';
					const XHtml='<img src="../img/x.png">';
					if (currGame.sq[i][j] == "O") {
						document.getElementById('s'+ String('00' + i).slice(-2) + String('00' + j).slice(-2) + '').innerHTML = OHtml;
					} else if (currGame.sq[i][j] == "X") {
						document.getElementById('s' + String('00' + i).slice(-2) + String('00' + j).slice(-2) + '').innerHTML = XHtml;
					} else {
						document.getElementById('s' + String('00' + i).slice(-2) + String('00' + j).slice(-2) + '').innerHTML = '';
					}
					$.ajax({
						url: "/kiemTraThang",
						contentType: "application/json",
						dataType: "json",
						type: "POST",
						data: JSON.stringify(data),
						success: function (result) {
							console.log(result);
							if (result.ketQua == true) {
								window.alert(result.moTa);
							}
						},
						error: function (jqXhr, textStatus, errorMessage) {
							console.log("Error: ", errorMessage);
						}
					});
				} else {
				window.alert(result.moTa);
			}
		},
		error: function (jqXhr, textStatus, errorMessage) {
			console.log("Error: ", errorMessage);
		}
	});
},

	/* when click a currGame.square */
	sqClick: function(row, col) {
		currGame.xMove(row, col);
	},

	khoiTaoBanCo: function () {
		const data = {
			"soHang": 20,
			"soCot": 20
		};
		$.ajax({
			url: "/khoiTao",
			contentType: "application/json",
			dataType: "json",
			type: "POST",
			data: JSON.stringify(data),
			success: function (result) {
				console.log(result);
				if (result.ketQua == true) {
					location.href = "/khoiTao";
				} else {
					window.alert("Game Caro đang có lỗi xảy ra...");
				}
			},
			error: function (jqXhr, textStatus, errorMessage) {
				console.log("Error: ", errorMessage);
			}
		});
	},

	activeBtnQuanCo: function (e) {
		const type = e.getAttribute("data-type");
		currGame.Turn = type;
		if (type == "X") {
			const btnCoO = document.getElementById("coO");
			btnCoO.className = btnCoO.className.replace(" active", "");
		} else {
			const btnCoX = document.getElementById("coX");
			btnCoX.className = btnCoX.className.replace(" active", "");
		}
		e.className = e.className.replace(" active", "");
		e.className += " active";
	}
};