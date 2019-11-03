package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import java.util.HashMap;
import java.util.Map;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class Logic {
	
	private final State state;
	private Game game;
	private Map<StateValue, AcceptController> controllers;
	
	public Logic() {
		this.state = new State();
		this.game = new Game();
		this.controllers = new HashMap<>();
		this.controllers.put(StateValue.INITIAL, new StartController(this.game, this.state));
		this.controllers.put(StateValue.OPENED_GAME, new PlayController(this.game, this.state));
		this.controllers.put(StateValue.FINAL, new ResumeController(this.game, this.state));
		this.controllers.put(StateValue.EXIT, null);
	}

	public AcceptController getController() {
		return this.controllers.get(this.state.getStateValue());
	}
}
