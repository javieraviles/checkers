package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class Controller {

	Game game;
	State state;

	public Controller(Game game, State state) {
		this.game = game;
		this.state = state;
	}

	public void nextState() {
		this.state.next();
	}
}