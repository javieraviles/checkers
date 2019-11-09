package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayView extends SubView {

	public PlayView() {
		super();
	}

	public void interact(PlayController playController) {
		String color = MessageView.COLORS[playController.getColor().ordinal()];
		Error error = null;
		GameView gameView = new GameView();
		do {
			String command = this.console.readString("Mueven las " + color + ": ");
			int origin = Integer.parseInt(command.substring(0, 2));
			int target = Integer.parseInt(command.substring(3, 5));
			error = playController.move(new Coordinate(origin / 10 - 1, origin % 10 - 1),
					new Coordinate(target / 10 - 1, target % 10 - 1));
			if (error != null) {
				console.writeln("Error!!!" + error.name());
				gameView.write(playController);
			}
		} while (error != null);
		if (playController.isBlocked()) {
			this.console.write(MessageView.MESSAGE_END_GAME.getMessage());
		}
	}

}