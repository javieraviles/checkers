package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class StartController extends AcceptController {

	public StartController(Game game, State state) {
		super(game, state);
	}

	public void play() {
		this.state.next();
	}

	public String getStringBoardGame() {
		return this.game.toString();
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}
