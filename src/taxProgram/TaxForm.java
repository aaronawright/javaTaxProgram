package taxProgram;


import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea; // for serial version UID
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TaxForm extends JFrame {

	private JPanel contentPane;
	JTextArea m_resultArea = new JTextArea(6, 30); // apparently we need this
    private static final long serialVersionUID = 1L; // or else we get a warning
    private JTextField textFieldInputGross;
    private JTextField textFieldInputDependents;
    private JTextField textFieldOutputGross;
    private JTextField textFieldOutputFed;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxForm frame = new TaxForm();
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
	public TaxForm() {
		setTitle("Tax Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Form Fields */
		
		// labels
		JLabel lblNewLabel = new JLabel("Gross Pay");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 11, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dependents");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(10, 36, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Marital Status");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 61, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pay Period");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(10, 86, 72, 14);
		contentPane.add(lblNewLabel_3);
		
		// test fields (input)
		
		textFieldInputGross = new JTextField();
		textFieldInputGross.setBounds(92, 8, 86, 20);
		contentPane.add(textFieldInputGross);
		textFieldInputGross.setColumns(10);
		textFieldInputGross.setText("0.00"); // default
		
		textFieldInputDependents = new JTextField();
		textFieldInputDependents.setBounds(92, 33, 86, 20);
		contentPane.add(textFieldInputDependents);
		textFieldInputDependents.setColumns(10);		
		textFieldInputDependents.setText("0"); // default
		
		// combo boxes (input)
		JComboBox comboBoxInputMarital = new JComboBox();
		comboBoxInputMarital.setModel(new DefaultComboBoxModel(new String[] {"Single", "Married"}));
		comboBoxInputMarital.setSelectedIndex(0);
		comboBoxInputMarital.setBounds(92, 57, 86, 22);
		contentPane.add(comboBoxInputMarital);
		
		JComboBox comboBoxInputPeriod = new JComboBox();
		comboBoxInputPeriod.setModel(new DefaultComboBoxModel(new String[] {"Biweekly", "Monthly"}));
		comboBoxInputPeriod.setSelectedIndex(0);
		comboBoxInputPeriod.setBounds(92, 82, 86, 22);
		contentPane.add(comboBoxInputPeriod);		
		
		// Calculate Button
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when button clicked
				
				String test = textFieldInputGross.getText();
				
				System.out.print(test);
				
			}
		});
		btnCalculate.setBounds(89, 227, 89, 23);
		contentPane.add(btnCalculate);
		
		// Exit button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
				
			}
		});
		btnExit.setBounds(335, 227, 89, 23);
		contentPane.add(btnExit);		
		
		textFieldOutputGross = new JTextField();
		textFieldOutputGross.setBounds(338, 8, 86, 20);
		contentPane.add(textFieldOutputGross);
		textFieldOutputGross.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Gross Pay");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(242, 14, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldOutputFed = new JTextField();
		textFieldOutputFed.setBounds(338, 33, 86, 20);
		contentPane.add(textFieldOutputFed);
		textFieldOutputFed.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Fed Tax");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(242, 36, 86, 14);
		contentPane.add(lblNewLabel_5);
		

		


		
		
	}
}
