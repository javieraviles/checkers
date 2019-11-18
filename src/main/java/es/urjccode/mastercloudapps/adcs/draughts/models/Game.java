package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.Iterator;
import java.util.List;

public class Game {

	private Board board;
	Turn turn;

	public Game(Board board) {
		this.turn = new Turn();
		this.board = board;
	}

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		assert coordinate != null;
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= 2) {
				color = Color.BLACK;
			} else if (row >= 5) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Piece(color);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {		
		assert this.isCorrect(origin, target) == null : "Incorrect move "+this.isCorrect(origin, target);
		if (origin.diagonalDistance(target) >= 2) {
			List<Coordinate> diagonalList = origin.betweenDiagonalList(target);
			boolean alreadyRemovedPiece = false;
			for (Iterator<Coordinate> iterator = diagonalList.iterator(); iterator.hasNext();) {
				Coordinate coordinate = (Coordinate) iterator.next();
				if(this.getPiece(coordinate)!=null) {
					if(alreadyRemovedPiece) {
						throw new AssertionError();
					}
					alreadyRemovedPiece = true;
					this.board.remove(coordinate);
				}
			}
		}
		Color pieceColor = this.getColor(origin);
		this.board.move(origin, target);
		if (this.board.getPiece(target).isLimit(target)){
			this.board.remove(target);
			this.board.put(target, new King(pieceColor == Color.WHITE ? Color.WHITE : Color.BLACK));
		}
		this.turn.change();
	}

	public Error isCorrect(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		return this.board.getPiece(origin).isCorrect(origin, target, board);
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

}