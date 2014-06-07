package edu.kpi.master.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import edu.kpi.master.algorithm.PresetSolution;
import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.gui.helper.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewInputDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfVehicles;
	private JTextField tfVertexes;
	private JTextField tfArcs;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewInputDetailsDialog dialog = new ViewInputDetailsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewInputDetailsDialog() {
		setTitle("Input Data");
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[] {76, 245, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{212, 212, 0};
			gbl_panel.rowHeights = new int[] {23, 23, 23, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Vehicles:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				tfVehicles = new JTextField();
				tfVehicles.setEditable(false);
				GridBagConstraints gbc_tfVehicles = new GridBagConstraints();
				gbc_tfVehicles.fill = GridBagConstraints.BOTH;
				gbc_tfVehicles.insets = new Insets(0, 0, 5, 0);
				gbc_tfVehicles.gridx = 1;
				gbc_tfVehicles.gridy = 0;
				panel.add(tfVehicles, gbc_tfVehicles);
				tfVehicles.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Vertexes:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				tfVertexes = new JTextField();
				tfVertexes.setEditable(false);
				GridBagConstraints gbc_tfVertexes = new GridBagConstraints();
				gbc_tfVertexes.fill = GridBagConstraints.BOTH;
				gbc_tfVertexes.insets = new Insets(0, 0, 5, 0);
				gbc_tfVertexes.gridx = 1;
				gbc_tfVertexes.gridy = 1;
				panel.add(tfVertexes, gbc_tfVertexes);
				tfVertexes.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Arcs:");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 2;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				tfArcs = new JTextField();
				tfArcs.setEditable(false);
				GridBagConstraints gbc_tfArcs = new GridBagConstraints();
				gbc_tfArcs.fill = GridBagConstraints.BOTH;
				gbc_tfArcs.gridx = 1;
				gbc_tfArcs.gridy = 2;
				panel.add(tfArcs, gbc_tfArcs);
				tfArcs.setColumns(10);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
					},
					new String[] {
						"Arc", "Service Cost", "Transit Cost"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Utils.hideInputDetailsDialog();
					}
				});
				cancelButton.setIcon(new ImageIcon(ViewInputDetailsDialog.class.getResource("/images/back.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void updateData() {
		tfVehicles.setText(Integer.toString(PresetSolution.graph.getNVehicles()));
		tfVertexes.setText(Integer.toString(PresetSolution.graph.getVertexes().size()));
		tfArcs.setText(Integer.toString(PresetSolution.graph.getArcs().size()));
		//fill table
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {
				"Arc", "Service Cost", "Transit Cost"
			});
		for(Arc arc : PresetSolution.graph.getArcs()) {
			Object[] rowData = new Object[3];
			rowData[0] = arc.getName();
			rowData[1] = arc.getServiceCost();
			rowData[2] = arc.getTransitCost();
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}
}
