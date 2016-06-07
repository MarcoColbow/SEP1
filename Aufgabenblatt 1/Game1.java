public class Game1 {
	public StringBuffer board;
	private final int NOMOVE = -1;

	public Game1(String s) {board = new StringBuffer(s);}

	public Game1(StringBuffer s, int position, char player) {
		board = new StringBuffer();
		board.append(s);
		board.setCharAt(position, player);
	}

	public int move(char player) {
		for (int move = 0; move < 9; move++) {
			if (isFreePosition(move)) {
				Game1 game = play(move, player);
				if (game.winner() == player) 
					return move;
			}
		}


		for (int move = 0; move < 9; move++) {
			if (isFreePosition(move)) 
				return move;
		}	
		return NOMOVE;
	}

	public Game1 play(int move, char player) {
		return new Game1(this.board, move, player);
	}

	public char winner() {
		for (int move = 0; move <= 6; move += 3) {
			if (!isFreePosition(move)
					&& board.charAt(move) == board.charAt(move + 1)
					&& board.charAt(move + 1) == board.charAt(move + 2))
				return board.charAt(move);
		}
		return '-';
	}

	private boolean isFreePosition(int i) {
		return board.charAt(i) == '-';
	}
}
