package com.dragome.examples.serverside;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class PersonCrudJPanel extends JPanel
{
	/**
	 * Create the panel.
	 */
	public PersonCrudJPanel()
	{
		setLayout(new BorderLayout(0, 0));

		JPanel panel= new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][]", "[]"));

		JButton btnNewButton= new JButton("Add");
		btnNewButton.setName("add-button");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		panel.add(btnNewButton, "cell 0 0");

		JButton btnNewButton_1= new JButton("Save");
		btnNewButton_1.setName("save-button");
		panel.add(btnNewButton_1, "cell 1 0");

		JScrollPane scrollPane= new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1= new JPanel();
		scrollPane.setViewportView(panel_1);

		panel_1.setLayout(new MigLayout("", "[600px,grow]", "[top][]"));

		JTextField givenNameTextField= new JTextField();
		givenNameTextField.setName("givenName");
		givenNameTextField.setColumns(15);

		JTextField lastNameTextField= new JTextField();
		lastNameTextField.setName("surname");
		lastNameTextField.setColumns(15);

		JPanel panel1= new JPanel();
		panel1.setName("row");
		panel1.setLayout(new MigLayout("", "[200px,fill][200px,fill][194.00px,fill][142.00,grow][trailing]", "[25px]"));
		panel1.add(givenNameTextField, "cell 0 0,alignx left,aligny top");
		panel1.add(lastNameTextField, "cell 1 0,alignx left,aligny top");

		panel_1.add(panel1, "growx,wrap,hidemode 2");

		JLabel jLabel= new JLabel();
		jLabel.setName("complete-name");
		jLabel.setText("label");
		panel1.add(jLabel, "cell 2 0,alignx left,aligny top");

		JButton btnButton= new JButton("delete");
		btnButton.setName("delete-button");
		panel1.add(btnButton, "cell 4 0,alignx left,aligny top");

	}
}
