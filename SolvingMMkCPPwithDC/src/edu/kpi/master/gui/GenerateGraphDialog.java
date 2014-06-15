package edu.kpi.master.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.kpi.master.algorithm.Generator;
import edu.kpi.master.datatypes.Graph;
import edu.kpi.master.gui.helper.FileChooserHelper;
import edu.kpi.master.gui.helper.Utils;
import edu.kpi.master.io.Writer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateGraphDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nVehicles;
	private JTextField nVertexes;
	private JTextField nArcs;
	private JLabel lblNumberOfVehicles;
	private JLabel lblNumberOfArcs;
	private JLabel lblNumberOfVertexes;
	private JLabel statusGeneration;
	private JTextField tfDedlineReserve;
	private JTextField tfSolutions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenerateGraphDialog dialog = new GenerateGraphDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenerateGraphDialog() {
		setTitle("Generate graph");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{212, 212, 0};
		gbl_contentPanel.rowHeights = new int[] {30, 0, 23, 23, 23, 0, 23, 23, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNumberOfVehicles = new JLabel("Number of vehicles:");
			GridBagConstraints gbc_lblNumberOfVehicles = new GridBagConstraints();
			gbc_lblNumberOfVehicles.fill = GridBagConstraints.BOTH;
			gbc_lblNumberOfVehicles.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfVehicles.gridx = 0;
			gbc_lblNumberOfVehicles.gridy = 1;
			contentPanel.add(lblNumberOfVehicles, gbc_lblNumberOfVehicles);
		}
		{
			nVehicles = new JTextField();
			lblNumberOfVehicles.setLabelFor(nVehicles);
			nVehicles.setText("1");
			GridBagConstraints gbc_nVehicles = new GridBagConstraints();
			gbc_nVehicles.fill = GridBagConstraints.BOTH;
			gbc_nVehicles.insets = new Insets(0, 0, 5, 0);
			gbc_nVehicles.gridx = 1;
			gbc_nVehicles.gridy = 1;
			contentPanel.add(nVehicles, gbc_nVehicles);
			nVehicles.setColumns(10);
		}
		{
			lblNumberOfVertexes = new JLabel("Number of vertexes:");
			GridBagConstraints gbc_lblNumberOfVertexes = new GridBagConstraints();
			gbc_lblNumberOfVertexes.fill = GridBagConstraints.BOTH;
			gbc_lblNumberOfVertexes.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfVertexes.gridx = 0;
			gbc_lblNumberOfVertexes.gridy = 2;
			contentPanel.add(lblNumberOfVertexes, gbc_lblNumberOfVertexes);
		}
		{
			nVertexes = new JTextField();
			lblNumberOfVertexes.setLabelFor(nVertexes);
			nVertexes.setText("2");
			GridBagConstraints gbc_nVertexes = new GridBagConstraints();
			gbc_nVertexes.fill = GridBagConstraints.BOTH;
			gbc_nVertexes.insets = new Insets(0, 0, 5, 0);
			gbc_nVertexes.gridx = 1;
			gbc_nVertexes.gridy = 2;
			contentPanel.add(nVertexes, gbc_nVertexes);
			nVertexes.setColumns(10);
		}
		{
			lblNumberOfArcs = new JLabel("Number of arcs:");
			GridBagConstraints gbc_lblNumberOfArcs = new GridBagConstraints();
			gbc_lblNumberOfArcs.fill = GridBagConstraints.BOTH;
			gbc_lblNumberOfArcs.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfArcs.gridx = 0;
			gbc_lblNumberOfArcs.gridy = 3;
			contentPanel.add(lblNumberOfArcs, gbc_lblNumberOfArcs);
		}
		{
			nArcs = new JTextField();
			lblNumberOfArcs.setLabelFor(nArcs);
			nArcs.setText("2");
			GridBagConstraints gbc_nArcs = new GridBagConstraints();
			gbc_nArcs.insets = new Insets(0, 0, 5, 0);
			gbc_nArcs.fill = GridBagConstraints.BOTH;
			gbc_nArcs.gridx = 1;
			gbc_nArcs.gridy = 3;
			contentPanel.add(nArcs, gbc_nArcs);
			nArcs.setColumns(10);
		}
		{
			JLabel lblDedlineReserve = new JLabel("Dedline reserve:");
			GridBagConstraints gbc_lblDedlineReserve = new GridBagConstraints();
			gbc_lblDedlineReserve.anchor = GridBagConstraints.EAST;
			gbc_lblDedlineReserve.insets = new Insets(0, 0, 5, 5);
			gbc_lblDedlineReserve.gridx = 0;
			gbc_lblDedlineReserve.gridy = 5;
			contentPanel.add(lblDedlineReserve, gbc_lblDedlineReserve);
		}
		{
			tfDedlineReserve = new JTextField();
			tfDedlineReserve.setText("10");
			GridBagConstraints gbc_tfDedlineReserve = new GridBagConstraints();
			gbc_tfDedlineReserve.insets = new Insets(0, 0, 5, 0);
			gbc_tfDedlineReserve.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfDedlineReserve.gridx = 1;
			gbc_tfDedlineReserve.gridy = 5;
			contentPanel.add(tfDedlineReserve, gbc_tfDedlineReserve);
			tfDedlineReserve.setColumns(10);
		}
		{
			JLabel lblNumberOfSolutions = new JLabel("Number of solutions to generate:");
			GridBagConstraints gbc_lblNumberOfSolutions = new GridBagConstraints();
			gbc_lblNumberOfSolutions.anchor = GridBagConstraints.EAST;
			gbc_lblNumberOfSolutions.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfSolutions.gridx = 0;
			gbc_lblNumberOfSolutions.gridy = 7;
			contentPanel.add(lblNumberOfSolutions, gbc_lblNumberOfSolutions);
		}
		{
			tfSolutions = new JTextField();
			tfSolutions.setText("1");
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 7;
			contentPanel.add(tfSolutions, gbc_textField);
			tfSolutions.setColumns(10);
		}
		{
			JLabel lblStatus = new JLabel("Status:");
			GridBagConstraints gbc_lblStatus = new GridBagConstraints();
			gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
			gbc_lblStatus.gridx = 0;
			gbc_lblStatus.gridy = 8;
			contentPanel.add(lblStatus, gbc_lblStatus);
		}
		{
			statusGeneration = new JLabel("Not generated");
			statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/red_circle.png")));
			GridBagConstraints gbc_statusGeneration = new GridBagConstraints();
			gbc_statusGeneration.gridx = 1;
			gbc_statusGeneration.gridy = 8;
			contentPanel.add(statusGeneration, gbc_statusGeneration);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Generate");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						generate();
					}
				});
				okButton.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/arrow_right.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnDetails = new JButton("Details");
				btnDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Utils.showGeneratedDataDialog();
						Utils.viewGeneratedDataDetails.updateData();
					}
				});
				{
					JButton btnGenerateAll = new JButton("Generate all");
					btnGenerateAll.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSS");
								for(int i = 0; i < Integer.parseInt(tfSolutions.getText()); i++) {
									//generate data
									generate();
									//save generated data
									String fileName = "test_p" + Generator.nVehicles + "v" + Generator.nVertexes + "a" + Generator.nArcs + "d" + Generator.DEADLINE_RESERVE;
									Date date = new Date();
									File file;
									file = new File(System.getProperty("user.dir") + "/resources/input/" + fileName + "_" + dateFormat.format(date) + ".xml");
									Writer.writeDataToFile(file, false);
								}
							}
							catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Enter valid numbers into text fields.", "Warning", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					btnGenerateAll.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/green-right-double-arrows.png")));
					buttonPane.add(btnGenerateAll);
				}
				btnDetails.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/info.png")));
				buttonPane.add(btnDetails);
			}
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Utils.hideGenerateGraphDialog();
					}
				});
				cancelButton.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/back.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				{
					JMenuItem mntmSave = new JMenuItem("Save");
					mntmSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							FileChooserHelper.saveFile(contentPanel);
						}
					});
					mntmSave.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
					mnFile.add(mntmSave);
				}
			}
		}
	}
	
	public void generate() {
		try {
			Generator.nVehicles = Integer.parseInt(nVehicles.getText());
			Generator.nVertexes = Integer.parseInt(nVertexes.getText());
			Generator.nArcs = Integer.parseInt(nArcs.getText());
			Generator.DEADLINE_RESERVE = Integer.parseInt(tfDedlineReserve.getText());
			if(Generator.generateData()) {
				statusGeneration.setText("Generated");
				statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/green_circle.png")));
			} else {
				statusGeneration.setText("Not generated");
				statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/red_circle.png")));
			}
		}
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Enter valid numbers into text fields.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void reset() {
		nVehicles.setText("1");
		nVertexes.setText("2");
		nArcs.setText("2");
		tfDedlineReserve.setText("10");
		tfSolutions.setText("1");
		statusGeneration.setText("Not generated");
		statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/red_circle.png")));
		//reset graph (for details)
		Generator.graph = new Graph();
	}

}
