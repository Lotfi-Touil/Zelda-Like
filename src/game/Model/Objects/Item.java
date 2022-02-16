package game.Model.Objects;

import game.Model.Types;

public class Item {

	private Types type;

	public Item(Types type) {
		this.type = type;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}
}
