package games.suvse.functions;

import games.suvse.game.Basic;

public class Walking extends Basic {

	/*
	 * Method to walk Fileexplanation: ; Diffrent Paragraphs / Options % For new
	 * Point ID is only used to make it easier to know the points ID( + Additional
	 * description will not be shown)/Quicktravle true or false/Encounter
	 * ID/Pointdescription/1.Path description/1.PointID;NewFile/1.Walk
	 * description/2.Path description/2.PointID/2.Walk description% ';' used for
	 * separate lines
	 * 
	 * @param return the new Point
	 */
	public String[] walk(int currentPosition, String currentMap) {
		clear();
		String[] returnPosition = new String[2];
		String currentPoint[] = getStringFromMap(currentMap).split("%")[currentPosition].split("/");
		doSimpleDialog(currentPoint[3].split(";"));
		output("0. Open Inventory");
		for (int i = 4; i < currentPoint.length; i += 3) {
			if (!currentPoint[i].replaceAll(" ", "").equals("")) {
				output(Integer.toString(i / 3) + ". " + currentPoint[i]);
			}
		}
		output("where do you go?");
		int newPos = getInt();
		;
		while (newPos < 0 | newPos > (currentPoint.length - 4) / 3) {
			output("Please enter a number between 0 and " + Integer.toString((currentPoint.length - 4) / 3));
			newPos = getInt();
		}
		if (newPos == 0) {
			output("You opened your Inventory \nit is empty");
			waitForInput();
			returnPosition[0] = Integer.toString(currentPosition);
			returnPosition[1] = currentMap;
			return returnPosition;
		} else {
			doSimpleDialog(currentPoint[newPos * 3 + 3].split(";"));
			waitForInput();
			String[] newPoint = currentPoint[newPos * 3 + 2].split(";");
			returnPosition[0] = newPoint[0].replaceAll(" ", "");
			if (newPoint.length == 2) {
				returnPosition[1] = newPoint[1].replaceAll(" ", "");
			} else {
				returnPosition[1] = currentMap;
			}
			return returnPosition;
		}
	}
}