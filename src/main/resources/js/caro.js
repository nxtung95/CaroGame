var SO_HANG = 20;
var SO_COT = 20;

var currGame = new game(SO_HANG, SO_COT);

board.writeBoard();


function game(noOfRow, noOfCol) {
	this.SO_HANG = noOfRow;
	this.SO_COT = noOfCol;
	this.Turn = "X";

	this.sq = new Array(); /* define an array storing XO position */
	for (var i = 0; i < this.SO_HANG; i++) {
		this.sq[i] = new Array();
		for (var j = 0; j < this.SO_COT; j++) {
			this.sq[i][j] = 0;
		}
	}

	this.xMove = function(i, j) {
		currGame.sq[i][j] = currGame.Turn;
		board.sqUpdate(i, j, currGame.Turn);
		const e = currGame.Turn == "X" ? document.getElementById("coX") : document.getElementById("coO");
		board.activeBtnQuanCo(e);
		if (currGame.Turn == "O") {
			currGame.Turn = "X";
		} else {
			currGame.Turn = "O";
		}
	};
}

