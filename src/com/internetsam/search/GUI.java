package com.internetsam.search;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.LimitExceededException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	JComboBox comboBox_type = new JComboBox();
	JComboBox comboBox_gamemode = new JComboBox();
	JComboBox comboBox_timeFrame = new JComboBox();
	JSpinner start = new JSpinner();
	JSpinner end = new JSpinner();
	JTextField linkField;
	JProgressBar progressBar = new JProgressBar();
	JTextArea txtrData = new JTextArea();
	Search search = new Search();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		
		txtrData.setWrapStyleWord(true);

		setBackground(new Color(255, 255, 255));
		setTitle("OCN Stat Search");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * START SEARCH
		 */
		JButton btnFindMe = new JButton("Find Me");
		btnFindMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
			         public void run() {
							search.searchStats(txtrData, username, start, end, comboBox_type, comboBox_gamemode, comboBox_timeFrame,linkField, progressBar);
			         }
				});
				t.start();
			}
		});
		btnFindMe.setBounds(12, 237, 117, 25);
		contentPane.add(btnFindMe);
		
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] {"Kills", "Deaths", "KD", "KK", "Cores", "Wool", "Monuments", "Play Time"}));
		comboBox_type.setBounds(12, 12, 117, 24);
		contentPane.add(comboBox_type);
		
		comboBox_gamemode.setModel(new DefaultComboBoxModel(new String[] {"All Gamemodes", "Project Ares", "Blitz", "GS"}));
		comboBox_gamemode.setBounds(144, 12, 144, 24);
		contentPane.add(comboBox_gamemode);
		
		comboBox_timeFrame.setModel(new DefaultComboBoxModel(new String[] {"24 Hours", "1 Week", "All Time"}));
		comboBox_timeFrame.setBounds(300, 12, 136, 24);
		contentPane.add(comboBox_timeFrame);
		
		/**
		 * EXIT THE PROGRAM
		 */
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnExit.setBounds(377, 237, 59, 25);
		contentPane.add(btnExit);
		
		username = new JTextField();
		username.setToolTipText("Put your username here");
		username.setBounds(12, 48, 144, 19);
		contentPane.add(username);
		username.setColumns(10);
		
		end.setToolTipText("Leave at 0 for infinte");
		end.setBounds(12, 177, 70, 20);
		end.setValue(250);
		contentPane.add(end);
		
		start.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		start.setBounds(12, 126, 70, 20);
		contentPane.add(start);
		
		JLabel lblStartPage = new JLabel("Start Page");
		lblStartPage.setBounds(12, 105, 117, 15);
		contentPane.add(lblStartPage);
		
		JLabel lblEndPage = new JLabel("End Page");
		lblEndPage.setBounds(12, 158, 117, 15);
		contentPane.add(lblEndPage);
		
		txtrData.setForeground(Color.RED);
		txtrData.setBackground(Color.DARK_GRAY);
		txtrData.setText("");
		txtrData.setBounds(168, 50, 268, 175);
		txtrData.setLineWrap(true);
		contentPane.add(txtrData);
		
		linkField = new JTextField();
		linkField.setColumns(10);
		linkField.setBounds(12, 79, 144, 19);
		linkField.setText("Link will go here");
		linkField.setToolTipText("Once we find your name a link to the page it's on will be put here");
		contentPane.add(linkField);
		
		progressBar.setBounds(12, 205, 150, 20);
		progressBar.setForeground(Color.red);
		progressBar.setToolTipText("Progress Bar");
		contentPane.add(progressBar);
		
	}
	
}
