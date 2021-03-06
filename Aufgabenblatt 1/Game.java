public class Game {
	private StringBuffer board;

	private final int NOMOVE = -1;

	public Game(String s) {
		board = new StringBuffer(s);
	}

	public Game(StringBuffer s, int position, char player) {
		board = new StringBuffer();
		board.append(s);
		board.setCharAt(position, player);
	}

	public int move(char player) {
		int defaultMove = NOMOVE;
		for (int move = 0; move < 9; move++) {
			if (isFreePosition(move)) {
				if (defaultMove == NOMOVE) {
					defaultMove = move;
				}
				Game game = new Game(this.board, move, player);
				if (game.winner() == player) {
					defaultMove = move;
					break;
				}
			}
		}
		return defaultMove;
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
