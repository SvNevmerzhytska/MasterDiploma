package edu.kpi.master.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateGraphDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nVehicles;
	private JTextField nVertexes;
	private JTextField nArcs;
	private JLabel lblNumberOfVehicles;
	private JLabel lblNumberOfArcs;
	private JLabel lblNumberOfVertexes;
	private JLabel statusGeneration;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{212, 212, 0};
		gbl_contentPanel.rowHeights = new int[] {50, 0, 23, 23, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
			JLabel lblStatus = new JLabel("Status:");
			GridBagConstraints gbc_lblStatus = new GridBagConstraints();
			gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
			gbc_lblStatus.gridx = 0;
			gbc_lblStatus.gridy = 5;
			contentPanel.add(lblStatus, gbc_lblStatus);
		}
		{
			statusGeneration = new JLabel("Not generated");
			statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/red_circle.png")));
			GridBagConstraints gbc_statusGeneration = new GridBagConstraints();
			gbc_statusGeneration.gridx = 1;
			gbc_statusGeneration.gridy = 5;
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
		statusGeneration.setText("Not generated");
		statusGeneration.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/red_circle.png")));
		//reset graph (for details)
		Generator.graph = new Graph();
	}

}
