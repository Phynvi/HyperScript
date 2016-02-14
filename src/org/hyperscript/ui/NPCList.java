package org.hyperscript.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hyperscript.entity.NPC;
import org.hyperscript.map.Map;

public class NPCList {
	
	private static JFrame frame;
	private static JTable table;

	private static void init() {
		frame = new JFrame();
		frame.setTitle(getTitle());
		frame.setSize(200, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		JButton updateButton = new JButton("Update");
		updateButton.setToolTipText("Updates the table below with new values.");
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] newValues = update();
				
			    DefaultTableModel model = new DefaultTableModel();
			    model.setColumnIdentifiers(getColumnNames());
				
				if ((newValues == null) || (newValues.length == 0)) {
					return;
				}
				
				for (int y = 0; y < newValues.length; y++) {
					String[] row = new String[newValues[0].length];
					for (int x = 0; x < newValues[0].length; x++) {
						row[x] = newValues[y][x];
					}
				    model.addRow(row);
				}
			    table.setModel(model);
			}
			
		});
		panel.add(updateButton, BorderLayout.NORTH);
		
		String columnNames[] = getColumnNames();

		String dataValues[][] = update();

		table = new JTable( dataValues, columnNames );

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER );
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				frame = null;
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.setVisible(true);
	}

	public static void show() {
		if (frame == null) {
			init();
		}
		else {
			frame.toFront();
		}
	}

	private static String[][] update() {
		NPC[] npcs = Map.getNPCs();
		
		String[][] data = new String[npcs.length][getColumnNames().length];
		
		for (int i = 0; i < npcs.length; i++) {
			data[i][0] = npcs[i].getName();
			data[i][1] = Integer.toString(npcs[i].getID());
			
			Point p = Map.convertToGlobalLocation(npcs[i].getLocation());
			data[i][2] = p.x + ", " + p.y;
			
			data[i][3] = Integer.toString(npcs[i].getSize());
			
			String actions = "";
			
			for (int a = 0; a < npcs[i].getActions().length; a++) {
				if ((npcs[i] != null) && (npcs[i].getActions()[a] != null)) {
					if (a != 0) {
						actions += ", ";
					}
					actions += npcs[i].getActions()[a];
				}
			}
			
			data[i][4] = actions;
			
			data[i][5] = Integer.toString(npcs[i].stepsTo());
		}
		
		return data;
	}
	
	private static String getTitle() {
		return "NPCs";
	}
	
	private static String[] getColumnNames() {
		return new String[]{"Name", "ID", "Location", "Size", "Actions", "Steps To"};
	}
	
}

