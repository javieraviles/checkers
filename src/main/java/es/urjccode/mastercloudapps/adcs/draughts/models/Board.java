package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Board {

	private static final int DIMENSION = 8;

	private Square[][] squares;

	Board() {
		this.squares = new Square[this.getDimension()][this.getDimension()];
		for (int i = 0; i < this.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				this.squares[i][j] = new Square();
			}
		}
	}

	private Square getSquare(Coordinate coordinate) {
		assert coordinate != null && coordinate.isValid();
		return this.squares[coordinate.getRow()][coordinate.getColumn()];
	}

	void put(Coordinate coordinate, Piece piece) {
		assert piece != null;
		this.getSquare(coordinate).put(piece);
	}

	Piece remove(Coordinate coordinate) {
		assert this.getPiece(coordinate) != null;
		return this.getSquare(coordinate).remove();
	}

	void move(Coordinate origin, Coordinate target) {
		this.put(target, this.remove(origin));
	}

	Piece getPiece(Coordinate coordinate) {
		return this.getSquare(coordinate).getPiece();
	}

	boolean isEmpty(Coordinate coordinate) {
		return this.getSquare(coordinate).isEmpty();
	}

	Color getColor(Coordinate coordinate) {
		return this.getSquare(coordinate).getColor();
	}

	List<Piece> getPieces(Color color) {
		List<Piece> pieces = new ArrayList<Piece>();
		for (int i = 0; i < this.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				pieces.add(this.getSquare(new Coordinate(i, j)).getPiece());
			}
		}
		return pieces;
	}

	int getDimension() {
		return Board.DIMENSION;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.toStringHorizontalNumbers());
		for (int i = 0; i < this.getDimension(); i++) {
			builder.append(this.toStringHorizontalPiecesWithNumbers(i));
		}
		builder.append(this.toStringHorizontalNumbers());
		return builder.toString();
	}

	private String toStringHorizontalNumbers() {
		StringBuilder builder = new StringBuilder(" ");
		for (int j = 0; j < Board.DIMENSION; j++) {
			builder.append(j);
		}
		return builder.append("\n").toString();
	}

	private String toStringHorizontalPiecesWithNumbers(int row) {
		StringBuilder builder = new StringBuilder(row);
		for (int j = 0; j < this.getDimension(); j++) {
			Piece piece = this.getPiece(new Coordinate(row, j));
			if (piece == null) {
				builder.append(" ");
			} else {
				final String[] letters = { "b", "n" };
				builder.append(letters[piece.getColor().ordinal()]);
			}
		}
		return builder.append(row + "\n").toString();
	}

}