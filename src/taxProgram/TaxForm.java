package taxProgram;


import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea; // for serial version UID
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TaxForm extends JFrame {

	private JPanel contentPane;
	JTextArea m_resultArea = new JTextArea(6, 30); // apparently we need this
    private static final long serialVersionUID = 1L; // or else we get a warning
    private JTextField textFieldInputGross;
    private JTextField textFieldInputDependents;
    private JTextField textFieldOutputGross;
    private JTextField textFieldOutputFed;
    private JTextField textFieldOutputSoc;
    private JTextField textFieldOutputMed;
    private JTextField textFieldOutputNet;
	
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
		
		JLabel lblNewLabel_4 = new JLabel("Gross Pay");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(242, 14, 86, 14);
		contentPane.add(lblNewLabel_4);		
		
		JLabel lblNewLabel_5 = new JLabel("Fed Tax");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(242, 36, 86, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Soc Sec Tax");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5_1.setBounds(242, 61, 86, 14);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Medicare Tax");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5_2.setBounds(242, 86, 86, 14);
		contentPane.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Net Pay");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5_3.setBounds(242, 118, 86, 14);
		contentPane.add(lblNewLabel_5_3);		
		
		// text fields (input)
		
		textFieldInputGross = new JTextField();
		textFieldInputGross.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldInputGross.setBounds(92, 8, 86, 20);
		contentPane.add(textFieldInputGross);
		textFieldInputGross.setColumns(10);
		textFieldInputGross.setText("0.00"); // default
		textFieldInputGross.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		textFieldInputGross.addFocusListener(new FocusAdapter() { // don't allow non-numeric input
			@Override
			public void focusLost(FocusEvent e) {
				JTextField textField = (JTextField) e.getSource();
                String textInput = textField.getText();
                Double testParse = 0.00;
	            try {
	            	testParse = Double.parseDouble(textInput);
	            } catch (NumberFormatException Exception) {
	            	textField.setText("0.00");
	            }
			}
		});		
		
		
		textFieldInputDependents = new JTextField();
		textFieldInputDependents.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldInputDependents.setBounds(92, 33, 86, 20);
		contentPane.add(textFieldInputDependents);
		textFieldInputDependents.setColumns(10);		
		textFieldInputDependents.setText("0"); // default
		textFieldInputDependents.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		textFieldInputDependents.addFocusListener(new FocusAdapter() { // don't allow non-integer input
			@Override
			public void focusLost(FocusEvent e) {
				JTextField textField = (JTextField) e.getSource();
                String textInput = textField.getText();
                Integer testParse = 0;
                Integer maxDependents = PaycheckCalculation.getMaxDependents();
	            try {
	            	testParse = Integer.parseInt(textInput);
	            	
	            	// if we make it here the parse succeeded
	            	if(testParse < 0 || testParse > maxDependents) {
	            		textField.setText("0");
	            	}
	            	
	            } catch (NumberFormatException Exception) {
	            	textField.setText("0");
	            }
			}
		});				
		
		// text fields (output)
		
		textFieldOutputGross = new JTextField();
		textFieldOutputGross.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldOutputGross.setEditable(false);
		textFieldOutputGross.setBounds(338, 8, 86, 20);
		contentPane.add(textFieldOutputGross);
		textFieldOutputGross.setColumns(10);		
		textFieldOutputGross.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		
		textFieldOutputFed = new JTextField();
		textFieldOutputFed.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldOutputFed.setEditable(false);
		textFieldOutputFed.setBounds(338, 33, 86, 20);
		contentPane.add(textFieldOutputFed);
		textFieldOutputFed.setColumns(10);
		textFieldOutputFed.setHorizontalAlignment(SwingConstants.RIGHT); // align right
				
		textFieldOutputSoc = new JTextField();
		textFieldOutputSoc.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldOutputSoc.setEditable(false);
		textFieldOutputSoc.setBounds(338, 58, 86, 20);
		contentPane.add(textFieldOutputSoc);
		textFieldOutputSoc.setColumns(10);
		textFieldOutputSoc.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		
		textFieldOutputMed = new JTextField();
		textFieldOutputMed.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldOutputMed.setEditable(false);
		textFieldOutputMed.setBounds(338, 84, 86, 20);
		contentPane.add(textFieldOutputMed);
		textFieldOutputMed.setColumns(10);
		textFieldOutputMed.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		
		textFieldOutputNet = new JTextField();
		textFieldOutputNet.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textFieldOutputNet.setEditable(false);
		textFieldOutputNet.setBounds(338, 115, 86, 20);
		contentPane.add(textFieldOutputNet);
		textFieldOutputNet.setColumns(10);		
		textFieldOutputNet.setHorizontalAlignment(SwingConstants.RIGHT); // align right
		
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
				
				//String  = textFieldInputGross.getText();
				
	            Boolean success = true; // whether there were any errors

	            /* init input variables */
	            
	            PaycheckCalculation calculation;
	            String name = ""; // not used
	            double grossPay = 0.0;
	            int dependents = 0;
	            PaycheckCalculation.maritalStatusOptions maritalStatus;
	            PaycheckCalculation.payPeriodOptions payPeriod;

	            /* results */
	            double[] resultData;

	            // parse form
	            try {
	            	grossPay = Double.parseDouble(textFieldInputGross.getText());
	            } catch (NumberFormatException Exception) {
	            	success = false;
	            }
	            try {
	            	dependents = Integer.parseInt(textFieldInputDependents.getText());
	            } catch (NumberFormatException Exception) {
	            	success = false;
	            }	            
	            
	            maritalStatus = PaycheckCalculation.maritalStatusOptions.valueOf(comboBoxInputMarital.getSelectedItem().toString());
	            payPeriod = PaycheckCalculation.payPeriodOptions.valueOf(comboBoxInputPeriod.getSelectedItem().toString());
	            
	           
	            if (success)
	            {
	                calculation = new PaycheckCalculation(name, grossPay, dependents, maritalStatus, payPeriod); // pass input to constructor
	                calculation.calculate(); // perform calculations
	                resultData = calculation.getResults(); // extract results for output

	                // display the results
	                DecimalFormat df = new DecimalFormat("0.00");
	                textFieldOutputGross.setText(df.format(resultData[0]));
	                textFieldOutputFed.setText(df.format(resultData[1]));
	                textFieldOutputSoc.setText(df.format(resultData[2]));
	                textFieldOutputMed.setText(df.format(resultData[3]));
	                textFieldOutputNet.setText(df.format(resultData[4]));
	                /*
	                textBoxResultGross.Text = resultData[0].ToString("0.00");
	                textBoxResultFederal.Text = resultData[1].ToString("0.00");
	                textBoxSocial.Text = resultData[2].ToString("0.00");
	                textBoxResultMedicare.Text = resultData[3].ToString("0.00");
	                textBoxResultNet.Text = resultData[4].ToString("0.00");
	                */

	            }	            

	            
				
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
		

		


		

		

		


		
		
	}
}
