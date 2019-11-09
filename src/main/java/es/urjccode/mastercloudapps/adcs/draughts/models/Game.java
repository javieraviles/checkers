package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public class Game {

	private Board board;

	private Turn turn;

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
		if (coordinate.isBlack()) {
			if (coordinate.getRow() <= 2) {
				return new Piece(Color.BLACK);
			} else if (coordinate.getRow() >= 5) {
				return new Piece(Color.WHITE);
			}
		}
		return null;
	}

	public Error move(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;
		this.board.move(origin, target);
		this.turn.change();
		return null;
	}
	
	public Error isValidMove(Coordinate origin, Coordinate target){
		return this.board.isValidMovement(origin, target, this.turn.getColor());
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public List<Piece> getPieces(Color color) {
		return this.board.getPieces(color);
	}

}