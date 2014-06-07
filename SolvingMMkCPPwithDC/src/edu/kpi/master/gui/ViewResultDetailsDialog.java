package edu.kpi.master.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.kpi.master.algorithm.FeasibleSolution;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.gui.helper.Utils;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ViewResultDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCompTime;
	private JTextField tfMaxCost;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewResultDetailsDialog dialog = new ViewResultDetailsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewResultDetailsDialog() {
		setTitle("Result Feasible Solution");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[] {46, 222, 0};
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
			gbl_panel.rowHeights = new int[] {23, 23, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Computation time:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				tfCompTime = new JTextField();
				tfCompTime.setEditable(false);
				GridBagConstraints gbc_tfCompTime = new GridBagConstraints();
				gbc_tfCompTime.fill = GridBagConstraints.BOTH;
				gbc_tfCompTime.insets = new Insets(0, 0, 5, 0);
				gbc_tfCompTime.gridx = 1;
				gbc_tfCompTime.gridy = 0;
				panel.add(tfCompTime, gbc_tfCompTime);
				tfCompTime.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Maximum route cost:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				tfMaxCost = new JTextField();
				tfMaxCost.setEditable(false);
				GridBagConstraints gbc_ftMaxCost = new GridBagConstraints();
				gbc_ftMaxCost.fill = GridBagConstraints.BOTH;
				gbc_ftMaxCost.gridx = 1;
				gbc_ftMaxCost.gridy = 1;
				panel.add(tfMaxCost, gbc_ftMaxCost);
				tfMaxCost.setColumns(10);
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
						{null, null},
					},
					new String[] {
						"Route", "Cost"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Long.class
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
				cancelButton.setIcon(new ImageIcon(ViewResultDetailsDialog.class.getResource("/images/back.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Utils.hideResultDetailsDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void updateData(){
		
		tfCompTime.setText(Long.toString(FeasibleSolution.computationTime));
		tfMaxCost.setText(Long.toString(FeasibleSolution.maxCost));
		//fill table
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {
				"Rout", "Cost"
			});
		for(Path path : FeasibleSolution.graph.getPathes()) {
			Object[] rowData = new Object[2];
			rowData[0] = path.getArcChain();
			rowData[1] = path.getCost();
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
		
	}

}
