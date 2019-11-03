package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public abstract class AcceptController extends Controller {

	public AcceptController(Game game, State state) {
		super(game, state);
	}

	public abstract void accept(ControllersVisitor controllersVisitor);
}