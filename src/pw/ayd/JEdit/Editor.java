package pw.ayd.JEdit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;

public class Editor extends JFrame implements ActionListener {

	JTextArea textarea;
	JPanel contentPane;
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	//private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Editor() {
		
		setTitle("JEdit");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 650);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(this);
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(this);
		mnFile.add(mntmSave);
		
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(this);
		mnFile.add(mntmPrint);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCopy = new JMenuItem("Copy (Ctrl + C)");
		mntmCopy.addActionListener(this);
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmCut = new JMenuItem("Cut (Ctrl + X)");
		mntmCut.addActionListener(this);
		mnEdit.add(mntmCut);
		
		JMenuItem mntmPaste = new JMenuItem("Paste (Ctrl + V)");
		mntmPaste.addActionListener(this);
		mnEdit.add(mntmPaste);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}


	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if (s.equals("Copy (Ctrl + C)")) {
			textarea.copy();
			System.out.println("[ ! ] Copying");
		} else if (s.equals("Cut (Ctrl + X)")) {
			textarea.cut();
			System.out.println("[ ! ] Cutting");
		} else if (s.equals("Paste (Ctrl + V)")) {
			textarea.paste();
			System.out.println("[ ! ] Pasting");
		} else if (s.equals("Open")) {
						
		} else if (s.equals("Print")) {
			
		} else if (s.equals("Save")) {
			
		} else if (s.equals("About")) {
				JOptionPane.showMessageDialog(contentPane, "Eggs are not supposed to be green.");
		}
		
	}
	
	

}
