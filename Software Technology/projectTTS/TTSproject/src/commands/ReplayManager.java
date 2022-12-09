package commands;

import java.util.ArrayList;

public class ReplayManager {
	ArrayList<ActionListener> commandsList ;
	public ReplayManager(){
		commandsList = new ArrayList<ActionListener>();
	}
	public void addReplayAction(ActionListener a) {
		commandsList.add(a);
	}
	public void replay() {
		
	}
}
