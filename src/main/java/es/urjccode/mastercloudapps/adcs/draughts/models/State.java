package es.urjccode.mastercloudapps.adcs.draughts.models;

public class State {

    private StateValue stateValue;

	public State() {
		this.reset();
	}

	void next() {
        assert this.stateValue != StateValue.EXIT;
		this.stateValue = StateValue.values()[this.stateValue.ordinal() + 1];
	}

	void reset() {
		this.stateValue = StateValue.INITIAL;
	}

	StateValue getValueState() {
		return this.stateValue;
	}
}
