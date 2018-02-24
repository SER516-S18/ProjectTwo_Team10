package client;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Author: Lei Zhang,
 * PlotDiagramPanel is a class to draw plot diagram in a panel
 * Each line of plot is in different color
 */
@SuppressWarnings("serial")
public class PlotDiagramPanel extends JPanel{
	private List<List<Integer>> values;
	private int max_score, min_score;
	private int width, height, border_gap, y_hatch_center;
	private static final int GRAPH_POINT_WIDTH = 8;
	private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
	private static final List<Color> LINE_COLOR_ARRAY = Arrays.asList(
				Color.green, Color.red, Color.blue, Color.white, Color.black
			);
	
	/**
	 * Constructor
	 * Initialize the max value and min value of the diagram
	 * Initialize the width and height of diagram as those of the panel
	 */
	public PlotDiagramPanel(){
		values = new ArrayList<List<Integer>>();
		max_score = 20;
		min_score = 0;
		y_hatch_center = (max_score-min_score)/2;
		width = this.getSize().width;
		height = this.getSize().height;
	}
	
	/**
	 * Constructor
	 * Initialize the data in the diagram as the input
	 * Initialize the max value and min value of the diagram
	 * Initialize the width and height of diagram as those of the panel
	 * @param values
	 */
	public PlotDiagramPanel(List<List<Integer>> values){
		this.values = values;
		if (values==null||this.values.size()==0||this.values.get(0).size()==0){
			max_score = 20;
			min_score = 0;
		}else{
			for (int i=0;i<values.size();i++){
				max_score = 
						Math.max(max_score, Collections.max(values.get(i)))+1;
				min_score = 
						Math.min(min_score, Collections.min(values.get(i)))-1;
			}
		}
		width = this.getSize().width;
		height = this.getSize().height;
		y_hatch_center = (max_score-min_score)/2;
	}
	
	/**
	 * Add data as a list of integers to the diagram
	 * Auto refresh the diagram after adding new data
	 * @param list
	 */
	public void addData(List<Integer> list){
		if (values == null||list.size()!=values.size()) {
			values = new ArrayList<List<Integer>>();
		}
		if (values.size()==0){
			for (int i=0;i<list.size();i++){
				List<Integer> temp = new ArrayList<Integer>();
		    	values.add(temp);
			}
		}
		for (int i=0;i<list.size();i++){
			values.get(i).add(list.get(i));
			max_score = Math.max(max_score, list.get(i))+1;
			min_score = Math.min(min_score, list.get(i))-1;
		}
		this.repaint();
	}

	/**
	 * Override the paintComponent method of JPanel
	 * Automatic call by repaint method
	 * Used to draw the plot, line and axis
	 * @param graph
	 */
	@Override
	protected void paintComponent(Graphics graph) {
	    super.paintComponent(graph);
	    if (values==null||this.values.size()==0 || this.values.get(0).size()==0) return;
	    Graphics2D g2 = (Graphics2D)graph;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    double xScale = ((double) getWidth() - 2 * border_gap) / (values.get(0).size() - 1);
	    double yScale = ((double) getHeight() - 2 * border_gap) / (max_score - min_score);

	    for (int j = 0; j<values.size();j++){

	      List<Point> graphPoints = new ArrayList<Point>();
	      for (int i = 0; i < values.get(j).size(); i++) {
	        int x1 = (int) (i * xScale + border_gap);
	      	int y1 = (int) ((max_score - values.get(j).get(i)) * yScale + border_gap);
	      	graphPoints.add(new Point(x1, y1));
	      }

	      // create x and y axes 
	      g2.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
	      g2.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);

	      // create hatch marks for y axis. 
	      for (int i = 0; i < y_hatch_center; i++) {
	         int x0 = border_gap;
	         int x1 = GRAPH_POINT_WIDTH + border_gap;
	         int y0 = getHeight() - (((i + 1) * (getHeight() - border_gap * 2)) / y_hatch_center + border_gap);
	         int y1 = y0;
	         g2.drawLine(x0, y0, x1, y1);
	      }

	      // and for x axis
	      for (int i = 0; i < values.size() - 1; i++) {
	         int x0 = (i + 1) * (getWidth() - border_gap * 2) / (values.size() - 1) + border_gap;
	         int x1 = x0;
	         int y0 = getHeight() - border_gap;
	         int y1 = y0 - GRAPH_POINT_WIDTH;
	         g2.drawLine(x0, y0, x1, y1);
	      }

	      Stroke oldStroke = g2.getStroke();
	      g2.setColor(LINE_COLOR_ARRAY.get(j));
	      g2.setStroke(GRAPH_STROKE);
	      for (int i = 0; i < graphPoints.size() - 1; i++) {
	         int x1 = graphPoints.get(i).x;
	         int y1 = graphPoints.get(i).y;
	         int x2 = graphPoints.get(i + 1).x;
	         int y2 = graphPoints.get(i + 1).y;
	         g2.drawLine(x1, y1, x2, y2);         
	      }

	      g2.setStroke(oldStroke);      
	      g2.setColor(GRAPH_POINT_COLOR);
	      for (int i = 0; i < graphPoints.size(); i++) {
	         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
	         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
	         int ovalW = GRAPH_POINT_WIDTH;
	         int ovalH = GRAPH_POINT_WIDTH;
	         g2.fillOval(x, y, ovalW, ovalH);
	      }
	    }
	}
}
