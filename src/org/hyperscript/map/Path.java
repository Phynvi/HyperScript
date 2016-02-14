package org.hyperscript.map;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Path {

	private List<Point> points;
	
	public Path() {
		this.points = new ArrayList<Point>();
	}
	
	public Path(Point[] points) {
		this.points = Arrays.asList(points);
	}
	
	public Path(List<Point> points) {
		this.points = points;
	}
	
	public void add(Point p) {
		this.points.add(p);
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	public void walk() {
		Map.walkPath(this);
	}
	
	public void clear() {
		this.points = new ArrayList<Point>();
	}
	
	public String dumpPathToString() {
		String s = "Path path = new Path();\n";
		
		for (Point p : points) {
			s += "path.add(new Point(" + p.x + ", " + p.y + "));\n";
		}
		
		return s;
	}
	
	public void dumpPath() {
		JFrame frame = new JFrame("Path Dump");
		frame.setSize(new Dimension(800, 600));
		
		JTextArea area = new JTextArea();
		area.setEditable(true);
		area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		area.setText(dumpPathToString());
		
		frame.add(area);
		frame.setVisible(true);
	}
	
}
