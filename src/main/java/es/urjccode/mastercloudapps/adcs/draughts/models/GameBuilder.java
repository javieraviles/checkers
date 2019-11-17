package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBuilder {

	private List<String> strings;

	public GameBuilder() {
		this.strings = new ArrayList<String>();
	}

	public GameBuilder row(String string) {
		this.strings.add(string);
		return this;
	}

	public Game build() {
		final Map<Character, Piece> pieces = new HashMap<>();
		pieces.put('b', new Men(Color.WHITE));
		pieces.put('B', new King(Color.WHITE));
		pieces.put('n', new Men(Color.BLACK));
		pieces.put('N', new King(Color.BLACK));
		Board board = new Board();
		for (int i = 0; i < this.strings.size(); i++) {
			for (int j = 0; j < this.strings.get(i).length(); j++) {
				char character = this.strings.get(i).charAt(j);
				if(pieces.containsKey(character)) {
					board.put(new Coordinate(i, j), pieces.get(character));
				}
			}
		}
		return new Game(board);
	}

}