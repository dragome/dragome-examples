package com.dragome.examples.serverside;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class TodoMVCJPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public TodoMVCJPanel()
	{
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[60.00,grow,leading][407.00,grow][60px,grow,trailing]", "[102.00][112.00,grow]"));
		
		JLabel lblTodos = new JLabel("todos");
		lblTodos.setForeground(new Color(204, 204, 204));
		lblTodos.setFont(new Font("Arial", Font.BOLD, 70));
		panel.add(lblTodos, "cell 1 0,alignx center");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 1,grow");
		panel_1.setLayout(new MigLayout("", "[371.00px,grow]", "[141.00,grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.LIGHT_GRAY));
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[right][0.00][grow][]", "[40:40:40,fill][12.00][40:40.00:40,fill][40.00:40.00:40.00,fill][270.00,grow][59.00px,fill]"));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		panel_2.add(chckbxNewCheckBox, "cell 0 0,grow");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 2 0 2 1,grow");
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		panel_2.add(separator, "cell 0 1 4 1,growx");
		
		JCheckBox checkBox = new JCheckBox("");
		panel_2.add(checkBox, "cell 0 2");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 2 2,growx");
		textField_1.setColumns(10);
		
		JButton button = new JButton("X");
		panel_2.add(button, "cell 3 2");
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, "cell 2 3 2 1,growx");
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "cell 0 5 4 1,grow");
		panel_3.setLayout(new MigLayout("", "[101.00][145.00,grow,center][fill]", "[grow][]"));
		
		JLabel lblItemsLeft = new JLabel("4 Items left");
		panel_3.add(lblItemsLeft, "cell 0 0");
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "cell 1 0,alignx center,growy");
		panel_4.setLayout(new MigLayout("", "[30px][30px][30px]", "[]"));
		
		JLabel lblAll = new JLabel("All");
		panel_4.add(lblAll, "cell 0 0");
		
		JLabel lblActive = new JLabel("Active");
		panel_4.add(lblActive, "cell 1 0");
		
		JLabel lblCompleted = new JLabel("Completed");
		panel_4.add(lblCompleted, "cell 2 0");
		
		JButton btnNewButton = new JButton("Clear completed (2)");
		panel_3.add(btnNewButton, "cell 2 0");

	}
}
