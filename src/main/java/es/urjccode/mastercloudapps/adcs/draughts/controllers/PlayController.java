package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class PlayController extends Controller {

	public PlayController(Game game, State state) {
		super(game, state);
	}

	public Error move(Coordinate origin, Coordinate target) {
		Error error = this.game.isValidMove(origin, target);
		if (error == null) {
			this.game.move(origin, target);
			if (this.isBlocked()) {
				this.state.next();
			}
		}
		return error;
	}

	Piece getPiece(Coordinate coordinate) {
		return game.getPiece(coordinate);
	}

	public Color getColor() {
		return game.getColor();
	}

	public boolean isBlocked() {
		return game.isBlocked();
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}