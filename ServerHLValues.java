public class ServerHLValues {
	
	int highestVal;
	int lowestVal;

	public ServerHLValues() {
	}

	public int getHighestVal() {
		highestVal = Integer.parseInt(ServerGUI.textPane.getText());
		return highestVal;
	}

	public int getLowestVal() {
		lowestVal = Integer.parseInt(ServerGUI.textPane_1.getText());
		return lowestVal;
	}

}
