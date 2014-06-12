package edu.kpi.master.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;

import edu.kpi.master.algorithm.Generator;
import edu.kpi.master.datatypes.Arc;
import edu.kpi.master.datatypes.Path;
import edu.kpi.master.gui.helper.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewGeneratedDataDetailsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblGeneratedArcs;
	private JTabbedPane tabbedPane;
	private JTable tblGeneratedRoutes;
	private JTextField maxCost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewGeneratedDataDetailsDialog dialog = new ViewGeneratedDataDetailsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewGeneratedDataDetailsDialog() {
		setTitle("Generated Data");
		setBounds(100, 100, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			{
				JScrollPane arcs = new JScrollPane();
				tabbedPane.addTab("Arcs", null, arcs, null);
				{
					tblGeneratedArcs = new JTable();
					tblGeneratedArcs.setModel(new DefaultTableModel(
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
					arcs.setRowHeaderView(tblGeneratedArcs);
					arcs.setViewportView(tblGeneratedArcs);
				}
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Routes", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{234, 234, 0};
		gbl_panel.rowHeights = new int[] {23, 109, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblMaxCost = new JLabel("Max cost:");
		GridBagConstraints gbc_lblMaxCost = new GridBagConstraints();
		gbc_lblMaxCost.fill = GridBagConstraints.BOTH;
		gbc_lblMaxCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxCost.gridx = 0;
		gbc_lblMaxCost.gridy = 0;
		panel.add(lblMaxCost, gbc_lblMaxCost);
		{
			maxCost = new JTextField();
			maxCost.setEditable(false);
			GridBagConstraints gbc_maxCost = new GridBagConstraints();
			gbc_maxCost.fill = GridBagConstraints.BOTH;
			gbc_maxCost.insets = new Insets(0, 0, 5, 0);
			gbc_maxCost.gridx = 1;
			gbc_maxCost.gridy = 0;
			panel.add(maxCost, gbc_maxCost);
			maxCost.setColumns(10);
		}
		{
			JScrollPane routes = new JScrollPane();
			GridBagConstraints gbc_routes = new GridBagConstraints();
			gbc_routes.gridwidth = 2;
			gbc_routes.fill = GridBagConstraints.BOTH;
			gbc_routes.insets = new Insets(0, 0, 0, 5);
			gbc_routes.gridx = 0;
			gbc_routes.gridy = 1;
			panel.add(routes, gbc_routes);
			
			tblGeneratedRoutes = new JTable();
			tblGeneratedRoutes.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"Rout", "Cost"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Long.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tblGeneratedRoutes.getColumnModel().getColumn(0).setPreferredWidth(100);
			tblGeneratedRoutes.getColumnModel().getColumn(0).setMinWidth(50);
			routes.setViewportView(tblGeneratedRoutes);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Utils.hideGeneratedDataDialog();
					}
				});
				cancelButton.setIcon(new ImageIcon(ViewGeneratedDataDetailsDialog.class.getResource("/images/back.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void updateData(){
		//fill table with arcs
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {
				"Arc", "Service Cost", "Transit Cost"
			});
		for(Arc arc : Generator.graph.getArcs()) {
			Object[] rowData = new Object[3];
			rowData[0] = arc.getName();
			rowData[1] = arc.getServiceCost();
			rowData[2] = arc.getTransitCost();
			dtm.addRow(rowData);
		}
		tblGeneratedArcs.setModel(dtm);
		//max cost
		maxCost.setText(Long.toString(Generator.getMaxCost()));
		//fill table with arcs
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {
				"Rout", "Cost"
			});
		for(Path path : Generator.graph.getPathes()) {
			Object[] rowData = new Object[3];
			rowData[0] = path.getArcChain();
			rowData[1] = path.getCost();
			dtm.addRow(rowData);
		}
		tblGeneratedRoutes.setModel(dtm);
	}
}
