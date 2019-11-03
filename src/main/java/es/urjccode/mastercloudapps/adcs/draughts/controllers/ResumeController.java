package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class ResumeController extends AcceptController {

	public ResumeController(Game game, State state) {
		super(game, state);
	}

	public void resume(boolean newGame) {
		if (newGame) {
			this.game.clear();
			this.state.reset();
		} else {
			this.nextState();
		}
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}
}