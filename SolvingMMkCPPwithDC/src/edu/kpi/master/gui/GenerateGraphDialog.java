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
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GenerateGraphDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nVehicles;
	private JTextField nVertexes;
	private JTextField nArcs;
	private JLabel lblNumberOfVehicles;
	private JLabel lblNumberOfArcs;

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
			nVehicles.setText("1");
			GridBagConstraints gbc_nVehicles = new GridBagConstraints();
			gbc_nVehicles.fill = GridBagConstraints.BOTH;
			gbc_nVehicles.insets = new Insets(0, 0, 5, 0);
			gbc_nVehicles.gridx = 1;
			gbc_nVehicles.gridy = 1;
			contentPanel.add(nVehicles, gbc_nVehicles);
			nVehicles.setColumns(10);
		}
		lblNumberOfVehicles.setLabelFor(nVehicles);
		{
			JLabel lblNumberOfVertexes = new JLabel("Number of vertexes:");
			lblNumberOfVertexes.setLabelFor(lblNumberOfVertexes);
			GridBagConstraints gbc_lblNumberOfVertexes = new GridBagConstraints();
			gbc_lblNumberOfVertexes.fill = GridBagConstraints.BOTH;
			gbc_lblNumberOfVertexes.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumberOfVertexes.gridx = 0;
			gbc_lblNumberOfVertexes.gridy = 2;
			contentPanel.add(lblNumberOfVertexes, gbc_lblNumberOfVertexes);
		}
		{
			nVertexes = new JTextField();
			nVertexes.setText("2");
			GridBagConstraints gbc_nVertexes = new GridBagConstraints();
			gbc_nVertexes.fill = GridBagConstraints.BOTH;
			gbc_nVertexes.insets = new Insets(0, 0, 5, 0);
			gbc_nVertexes.gridx = 1;
			gbc_nVertexes.gridy = 2;
			contentPanel.add(nVertexes, gbc_nVertexes);
			nVertexes.setColumns(10);
		}
		lblNumberOfArcs.setLabelFor(nArcs);
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
			JLabel statusGeneration = new JLabel("Not generated");
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
				okButton.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/arrow_right.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnDetails = new JButton("Details");
				btnDetails.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/images/info.png")));
				buttonPane.add(btnDetails);
			}
			{
				JButton cancelButton = new JButton("Back");
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
					mntmSave.setIcon(new ImageIcon(GenerateGraphDialog.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
					mnFile.add(mntmSave);
				}
			}
		}
	}

}
