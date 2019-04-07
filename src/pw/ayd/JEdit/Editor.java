package pw.ayd.JEdit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Editor extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	JFrame frame;
	JScrollPane scroll;
	JMenuItem mntmSave;
	JMenuItem mntmSaveAs;
	File projectFile;
	Boolean projectOpened = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 500, 650);
		frame.setTitle("Untitled Document | JEdit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		URL iconURL = getClass().getResource("/pw/ayd/JEdit/JEditIcon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());

		textArea = new JTextArea();

		frame.getContentPane().add(textArea, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(this);
		mnFile.add(mntmOpen);

		JMenuItem mntmNew = new JMenuItem("New File");
		mntmNew.addActionListener(this);
		mnFile.add(mntmNew);

		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(this);
		mnFile.add(mntmSave);
		mntmSave.setEnabled(false);

		mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(this);
		mnFile.add(mntmSaveAs);

		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(this);
		mnFile.add(mntmPrint);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(this);
		mnEdit.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(this);
		mnEdit.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(this);
		mnEdit.add(mntmPaste);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();

		if (action.equals("Cut")) {
			textArea.cut();
		} else if (action.equals("Copy")) {
			textArea.copy();
		} else if (action.equals("New File")) {
			textArea.setText("");
			frame.setTitle("Untitled Document | JEdit");
			projectOpened = false;
		} else if (action.equals("Paste")) {
			textArea.paste();
		} else if (action.equals("About")) {
			JOptionPane.showMessageDialog(frame, "JEdit v0.0.1 \n A text editor made in Java by @Offence",
					"About JEdit", JOptionPane.INFORMATION_MESSAGE);
		} else if (action.equals("Open")) {
			// TODO

			JFileChooser openChooser = new JFileChooser("c:");
			int invokeOChooser = openChooser.showOpenDialog(null);

			if (invokeOChooser == JFileChooser.APPROVE_OPTION) {
				File openFile = new File(openChooser.getSelectedFile().getAbsolutePath());

				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(openFile));
					String st;
					String addToFile = "";
					while ((st = br.readLine()) != null)
						addToFile = addToFile + st + "\n";
					textArea.setText(addToFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frame.setTitle(openFile.getName() + " | JEdit");
				projectFile = openFile;
				projectOpened = true;
			}

		} else if (action.equals("Save As")) {

			String saveText = textArea.getText();

			JFileChooser saveChooser = new JFileChooser("c:");
			int invokeChooser = saveChooser.showSaveDialog(null);

			if (invokeChooser == JFileChooser.APPROVE_OPTION) {

				File saveFile = new File(saveChooser.getSelectedFile().getAbsolutePath());

				try {

					FileWriter fileWrite = new FileWriter(saveFile, false);
					BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);

					bufferedWrite.write(saveText);
					bufferedWrite.close();

				} catch (IOException e1) {

					e1.printStackTrace();

				}

				frame.setTitle(saveFile.getName() + " | JEdit");
				projectOpened = true;
				projectFile = saveFile;
			}

		} else if (action.equals("Save")) {

			String saveText = textArea.getText();

			try {

				FileWriter fileWrite = new FileWriter(projectFile, false);
				BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);

				bufferedWrite.write(saveText);
				bufferedWrite.close();

			} catch (IOException e1) {

				e1.printStackTrace();

			}

			frame.setTitle(projectFile.getName() + " | JEdit");
			projectOpened = true;

		} else if (action.equals("Print")) {
			try {
				textArea.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Unknown function", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		if (!projectOpened) {
			mntmSave.setEnabled(false);
		} else {
			mntmSave.setEnabled(true);
		}
	}

}
