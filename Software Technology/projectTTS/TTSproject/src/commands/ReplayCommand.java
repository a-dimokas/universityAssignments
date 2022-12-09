package commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class ReplayCommand implements ActionListener{
	//private ReplayManager replayManager;
	
	public ReplayCommand(ReplayManager rmgr) {
		//replayManager=rmgr;
	}
	
	public void actionPerformed(ActionEvent event) {
		//replayManager.replay();
		JOptionPane.showMessageDialog(null, "Not working!", "Under Construction", JOptionPane.ERROR_MESSAGE, null);
	}
}
