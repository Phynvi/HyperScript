package org.hyperscript.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.hyperscript.console.ConsoleProgram;
import org.hyperscript.console.builtinprograms.DumpProgram;
import org.hyperscript.console.builtinprograms.UsageProgram;

public class Console {
	
	private static final Dimension SIZE = new Dimension(400, 200);
	private static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	
	private static JFrame consoleFrame;
	private static JScrollPane scroll;
	private static JTextPane console;
	private static JTextField field;
	
	private static List<ConsoleProgram> programs = new ArrayList<ConsoleProgram>();

	public static void init() {
		
		registerBuiltinPrograms();
		
		consoleFrame = new JFrame();
		consoleFrame.setTitle("HyperScript Console");
		consoleFrame.setSize(SIZE);
		
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(FONT);
		
		scroll = new JScrollPane(console, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		consoleFrame.setLayout(new BorderLayout());
		consoleFrame.add(scroll, BorderLayout.CENTER);
		
		field = new JTextField();
		field.setFont(FONT);
		field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] commandInput = field.getText().split("\\s+");
					Console.log("[" + new Date().toString() + "]: " + field.getText(), Color.MAGENTA.darker());
					field.setText("");
					runCommand(commandInput);
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		consoleFrame.add(field, BorderLayout.SOUTH);
		
		consoleFrame.setVisible(true);
		
		Console.log("[Welcome to the HyperScript Console]", Color.BLUE.darker());
	}

	public static void log(String msg) {
		log(msg, Color.BLACK);
	}
	
	public static void error(String msg) {
		log("Error: " + msg, Color.RED);
	}
	
	public static void log(String msg, Color c) {
		if (consoleFrame == null) {
			init();
		}
		StyledDocument doc = console.getStyledDocument();

        Style style = console.addStyle("style", null);
		StyleConstants.setForeground(style, c);
		try {
			doc.insertString(doc.getLength(), (doc.getLength() == 0 ? "" : "\n") + msg, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StyleConstants.setForeground(style, Color.BLACK);
	}
	
	public static void show() {
		if (consoleFrame == null) {
			init();
		}
		consoleFrame.setVisible(true);
		consoleFrame.toFront();
	}
	
	
	public static void hide() {
		if (consoleFrame != null) {
			consoleFrame.setVisible(false);
		}
	}
	
	public static void runCommand(String[] input) {
		if (input.length > 0) {
			String commandName = input[0];
			
			ConsoleProgram cp = getProgram(commandName);
			
			if (cp != null) {
				String[] args = new String[input.length - 1];
				
				for (int i = 1; i < input.length; i++) {
					args[i-1] = input[i];
				}
				
				cp.run(args);
			}
			
		}
	}
	
	public static void registerProgram(ConsoleProgram cp) {
		programs.add(cp);
	}
	
	private static void registerBuiltinPrograms() {
		registerProgram(new DumpProgram());
		registerProgram(new UsageProgram());
	}

	public static ConsoleProgram getProgram(String commandName) {
		for (ConsoleProgram cp : programs) {
			if (cp.getCommand().equals(commandName)) {
				return cp;
			}
		}
		Console.error("Command '" + commandName + "' not found. Please make sure to register it via Console.registerProgram(ConsoleProgram cp) before using it.");
		return null;
	}
	
}
