package es.urjccode.mastercloudapps.adcs.draughts.models;

class Square {

    private Piece piece;

    Square() {
    }

    void put(Piece piece) {
        this.piece = piece;
    }

    Piece remove() {
        Piece tmpPiece = this.piece;
        this.piece = null;
        return tmpPiece;
    }

    Piece getPiece() {
        return this.piece;
    }

	boolean isEmpty() {
		return this.piece == null;
	}

	Color getColor() {
        if (piece == null){
            return null;
        }
		return this.piece.getColor();
	}

}