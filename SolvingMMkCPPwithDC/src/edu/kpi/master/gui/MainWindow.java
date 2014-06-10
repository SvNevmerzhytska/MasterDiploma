package edu.kpi.master.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import edu.kpi.master.algorithm.FeasibleSolution;
import edu.kpi.master.algorithm.PresetSolution;
import edu.kpi.master.generator.Generator;
import edu.kpi.master.gui.helper.FileChooserHelper;
import edu.kpi.master.gui.helper.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frmMmKcppWith;
	private JTextField nVehicles;
	private JTextField nVertexes;
	private JTextField nArcs;
	private JTextField computationTime;
	private JTextField maxCost;
	private JTextField presetMaxCost;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = Utils.window;
					window.frmMmKcppWith.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMmKcppWith = new JFrame();
		frmMmKcppWith.setTitle("MM k-CPP with DC");
		frmMmKcppWith.setBounds(100, 100, 450, 360);
		frmMmKcppWith.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMmKcppWith.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open file");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooserHelper.openFile(frmMmKcppWith);
				nVehicles.setText(Integer.toString(PresetSolution.graph.getPathes().size()));
				nVertexes.setText(Integer.toString(PresetSolution.graph.getVertexes().size()));
				nArcs.setText(Integer.toString(PresetSolution.graph.getArcs().size()));
				presetMaxCost.setText(Long.toString(PresetSolution.maxPathCost));
			}
		});
		mntmOpenFile.setIcon(new ImageIcon(MainWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmGenerateGraph = new JMenuItem("Generate graph");
		mntmGenerateGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.showGenerateGraphDialog();
				nVehicles.setText(Integer.toString(PresetSolution.graph.getNVehicles()));
				nVertexes.setText(Integer.toString(PresetSolution.graph.getVertexes().size()));
				nArcs.setText(Integer.toString(PresetSolution.graph.getArcs().size()));
				presetMaxCost.setText(Long.toString(PresetSolution.maxPathCost));
			}
		});
		mnFile.add(mntmGenerateGraph);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSaveResults = new JMenuItem("Save results");
		mntmSaveResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooserHelper.saveResultFile(frmMmKcppWith);
			}
		});
		mntmSaveResults.setIcon(new ImageIcon(MainWindow.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mnFile.add(mntmSaveResults);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMmKcppWith.setVisible(false);
				frmMmKcppWith.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutMmKcpp = new JMenuItem("About MM k-CPP with DC");
		mntmAboutMmKcpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmMmKcppWith, "Bla bla about programm.", "About MM k-CPP with DC", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelp.add(mntmAboutMmKcpp);
		
		JPanel Input = new JPanel();
		Input.setBorder(new TitledBorder(null, "Input parametes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMmKcppWith.getContentPane().add(Input, BorderLayout.NORTH);
		GridBagLayout gbl_Input = new GridBagLayout();
		gbl_Input.columnWidths = new int[]{125, 100, 0};
		gbl_Input.rowHeights = new int[]{23, 23, 23, 23, 0};
		gbl_Input.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_Input.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Input.setLayout(gbl_Input);
		
		JLabel label = new JLabel("Number of vehicles:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		Input.add(label, gbc_label);
		
		nVehicles = new JTextField();
		nVehicles.setText("0");
		nVehicles.setEditable(false);
		nVehicles.setColumns(10);
		GridBagConstraints gbc_nVehicles = new GridBagConstraints();
		gbc_nVehicles.fill = GridBagConstraints.BOTH;
		gbc_nVehicles.insets = new Insets(0, 0, 5, 0);
		gbc_nVehicles.gridx = 1;
		gbc_nVehicles.gridy = 0;
		Input.add(nVehicles, gbc_nVehicles);
		
		JLabel label_1 = new JLabel("Number of vertexes:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		Input.add(label_1, gbc_label_1);
		
		nVertexes = new JTextField();
		nVertexes.setText("0");
		nVertexes.setEditable(false);
		nVertexes.setColumns(10);
		GridBagConstraints gbc_nVertexes = new GridBagConstraints();
		gbc_nVertexes.fill = GridBagConstraints.BOTH;
		gbc_nVertexes.insets = new Insets(0, 0, 5, 0);
		gbc_nVertexes.gridx = 1;
		gbc_nVertexes.gridy = 1;
		Input.add(nVertexes, gbc_nVertexes);
		
		JLabel label_2 = new JLabel("Number of arcs:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		Input.add(label_2, gbc_label_2);
		
		nArcs = new JTextField();
		nArcs.setText("0");
		nArcs.setEditable(false);
		nArcs.setColumns(10);
		GridBagConstraints gbc_nArcs = new GridBagConstraints();
		gbc_nArcs.fill = GridBagConstraints.BOTH;
		gbc_nArcs.insets = new Insets(0, 0, 5, 0);
		gbc_nArcs.gridx = 1;
		gbc_nArcs.gridy = 2;
		Input.add(nArcs, gbc_nArcs);
		
		JButton button = new JButton("Details");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.showInputDetailsDialog();
				Utils.viewInputDataDetails.updateData();
			}
		});
		button.setIcon(new ImageIcon(MainWindow.class.getResource("/images/info.png")));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		Input.add(button, gbc_button);
		
		JPanel Run = new JPanel();
		Run.setBorder(new TitledBorder(null, "Find feasible solution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMmKcppWith.getContentPane().add(Run, BorderLayout.CENTER);
		GridBagLayout gbl_Run = new GridBagLayout();
		gbl_Run.columnWidths = new int[]{106, 71, 146, 0};
		gbl_Run.rowHeights = new int[]{25, 0};
		gbl_Run.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Run.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Run.setLayout(gbl_Run);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeasibleSolution.findFeasibleSolution();
				computationTime.setText(Long.toString(FeasibleSolution.computationTime));
				maxCost.setText(Long.toString(FeasibleSolution.maxCost));
			}
		});
		btnRun.setIcon(new ImageIcon(MainWindow.class.getResource("/images/arrow_right.png")));
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRun.anchor = GridBagConstraints.NORTH;
		gbc_btnRun.insets = new Insets(0, 0, 0, 5);
		gbc_btnRun.gridx = 0;
		gbc_btnRun.gridy = 0;
		Run.add(btnRun, gbc_btnRun);
		
		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.anchor = GridBagConstraints.WEST;
		gbc_progressBar.gridx = 2;
		gbc_progressBar.gridy = 0;
		Run.add(progressBar, gbc_progressBar);
		progressBar.setMaximum(4);
		
		JPanel Result = new JPanel();
		Result.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMmKcppWith.getContentPane().add(Result, BorderLayout.SOUTH);
		GridBagLayout gbl_Result = new GridBagLayout();
		gbl_Result.columnWidths = new int[] {125, 150, 0};
		gbl_Result.rowHeights = new int[]{23, 23, 23, 23, 0};
		gbl_Result.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_Result.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Result.setLayout(gbl_Result);
		
		JLabel lblComputationTime = new JLabel("Computation time:");
		GridBagConstraints gbc_lblComputationTime = new GridBagConstraints();
		gbc_lblComputationTime.fill = GridBagConstraints.BOTH;
		gbc_lblComputationTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblComputationTime.gridx = 0;
		gbc_lblComputationTime.gridy = 0;
		Result.add(lblComputationTime, gbc_lblComputationTime);
		
		computationTime = new JTextField();
		computationTime.setEditable(false);
		computationTime.setText("0");
		GridBagConstraints gbc_computationTime = new GridBagConstraints();
		gbc_computationTime.fill = GridBagConstraints.BOTH;
		gbc_computationTime.insets = new Insets(0, 0, 5, 0);
		gbc_computationTime.gridx = 1;
		gbc_computationTime.gridy = 0;
		Result.add(computationTime, gbc_computationTime);
		computationTime.setColumns(10);
		
		JLabel lblMaximumRouteCost = new JLabel("Maximum route cost:");
		GridBagConstraints gbc_lblMaximumRouteCost = new GridBagConstraints();
		gbc_lblMaximumRouteCost.fill = GridBagConstraints.BOTH;
		gbc_lblMaximumRouteCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaximumRouteCost.gridx = 0;
		gbc_lblMaximumRouteCost.gridy = 1;
		Result.add(lblMaximumRouteCost, gbc_lblMaximumRouteCost);
		
		maxCost = new JTextField();
		maxCost.setEditable(false);
		maxCost.setText("0");
		GridBagConstraints gbc_maxCost = new GridBagConstraints();
		gbc_maxCost.fill = GridBagConstraints.BOTH;
		gbc_maxCost.insets = new Insets(0, 0, 5, 0);
		gbc_maxCost.gridx = 1;
		gbc_maxCost.gridy = 1;
		Result.add(maxCost, gbc_maxCost);
		maxCost.setColumns(10);
		
		JLabel lblExpectedMaxCost = new JLabel("Expected max cost:");
		GridBagConstraints gbc_lblExpectedMaxCost = new GridBagConstraints();
		gbc_lblExpectedMaxCost.fill = GridBagConstraints.BOTH;
		gbc_lblExpectedMaxCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpectedMaxCost.gridx = 0;
		gbc_lblExpectedMaxCost.gridy = 2;
		Result.add(lblExpectedMaxCost, gbc_lblExpectedMaxCost);
		
		presetMaxCost = new JTextField();
		presetMaxCost.setEditable(false);
		presetMaxCost.setText("0");
		GridBagConstraints gbc_presetMaxCost = new GridBagConstraints();
		gbc_presetMaxCost.fill = GridBagConstraints.BOTH;
		gbc_presetMaxCost.insets = new Insets(0, 0, 5, 0);
		gbc_presetMaxCost.gridx = 1;
		gbc_presetMaxCost.gridy = 2;
		Result.add(presetMaxCost, gbc_presetMaxCost);
		presetMaxCost.setColumns(10);
		
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.showResultDetailsDialog();
				Utils.viewResultDetails.updateData();
			}
		});
		btnDetails.setIcon(new ImageIcon(MainWindow.class.getResource("/images/info.png")));
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.fill = GridBagConstraints.BOTH;
		gbc_btnDetails.gridx = 1;
		gbc_btnDetails.gridy = 3;
		Result.add(btnDetails, gbc_btnDetails);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
