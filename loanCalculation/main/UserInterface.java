//package main;
//
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.math.BigDecimal;
//import java.math.MathContext;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//public class UserInterface extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField textAmount;
//	private JTextField textYears;
//	private JTextField textInterest;
//
//	private BigDecimal amount;
//	private BigDecimal years;
//	private BigDecimal interest;
//	private JTextField textBullet;
//	private JTextField textAmoretizing;
//	private JTextField textAnnuity;
//	private JTextField textBulletRate;
//	private JTextField textAmoretizingRate;
//	private JTextField textAnnuityRate;
//	private Loan myCredit;
//	private XMLGenerator xmlGenerator;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserInterface frame = new UserInterface();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public UserInterface() {
//		setTitle("Check your loan");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 525, 381);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		JLabel lblAmount = new JLabel("Amount:");
//		lblAmount.setFont(new Font("Dialog", Font.BOLD, 12));
//		lblAmount.setBounds(22, 62, 60, 14);
//		contentPane.add(lblAmount);
//
//		JLabel lblCalculateThe = new JLabel("Find the best payment method for your loan!");
//		lblCalculateThe.setForeground(Color.BLACK);
//		lblCalculateThe.setFont(new Font("Arial", Font.BOLD, 14));
//		lblCalculateThe.setBounds(22, 26, 340, 14);
//		contentPane.add(lblCalculateThe);
//
//		textAmount = new JTextField();
//		textAmount.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int key = e.getKeyCode();
//				removeWrong(key);
//			}
//
//			void removeWrong(int key) {
//				if((key == 44) || (key >= 65 && key <= 90) || key == 46) {
//					textAmount.setBackground(new Color(240, 30, 30));
//					JOptionPane.showMessageDialog(null, "Please use the format 000");
//					String input = textAmount.getText();
//					if(input.length() < 2) {
//						textAmount.setText(input.substring(0, input.length()-1));
//					} else {
//						textAmount.setText(input.substring(0, input.length()));
//					}
//					textAmount.setBackground(Color.WHITE);
//				}
//			}
//		});
//
//		textAmount.setBounds(79, 59, 78, 20);
//		contentPane.add(textAmount);
//		textAmount.setColumns(10);
//
//		textYears = new JTextField();
//		textYears.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int key = e.getKeyCode();
//				removeWrong(key);
//			}
//
//			void removeWrong(int key) {
//				if((key == 44) || (key >= 65 && key <= 90) || key == 46) {
//					textYears.setBackground(new Color(240, 30, 30));
//					JOptionPane.showMessageDialog(null, "Please use the format 000");
//					String input = textYears.getText();
//
//					if(input.length() > 1) {
//						textYears.setText(input.substring(0, input.length()-1));
//					} else {
//						textYears.setText(input.substring(0, 1));
//					}
//
//					textYears.setBackground(Color.WHITE);
//				}
//			}
//		});
//		textYears.setColumns(10);
//		textYears.setBounds(79, 88, 78, 20);
//		contentPane.add(textYears);
//
//		JLabel lblYears = new JLabel("Years:");
//		lblYears.setBounds(22, 91, 60, 14);
//		contentPane.add(lblYears);
//
//		JLabel lblInterest = new JLabel("Interest:");
//		lblInterest.setBounds(22, 123, 60, 14);
//		contentPane.add(lblInterest);
//
//		textInterest = new JTextField();
//		textInterest.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				int key = e.getKeyCode();
//				removeWrong(key);
//			}
//
//			void removeWrong(int key) {
//				if((key == 44) || (key >= 65 && key <= 90)) {
//					textInterest.setBackground(new Color(240, 30, 30));
//					JOptionPane.showMessageDialog(null, "Please use the format 0.00");
//					String input = textInterest.getText();
//					textInterest.setText(input.substring(0, input.length()-1));
//					textInterest.setBackground(Color.WHITE);
//				}
//			}
//		});
//		textInterest.setBackground(Color.WHITE);
//		textInterest.setColumns(10);
//		textInterest.setBounds(79, 120, 78, 20);
//		contentPane.add(textInterest);
//
//		JLabel labelResultBullet = new JLabel("Bullet");
//		labelResultBullet.setEnabled(false);
//		labelResultBullet.setBounds(22, 227, 78, 16);
//		contentPane.add(labelResultBullet);
//
//		JLabel labelResultAmoretizing = new JLabel("Amoretizing");
//		labelResultAmoretizing.setEnabled(false);
//		labelResultAmoretizing.setBounds(22, 258, 78, 16);
//		contentPane.add(labelResultAmoretizing);
//
//		JLabel labelResultAnnuity = new JLabel("Annuity");
//		labelResultAnnuity.setEnabled(false);
//		labelResultAnnuity.setBounds(22, 290, 78, 16);
//		contentPane.add(labelResultAnnuity);
//
//		textBullet = new JTextField();
//		textBullet.setEditable(false);
//		textBullet.setColumns(10);
//		textBullet.setBounds(132, 227, 98, 20);
//		contentPane.add(textBullet);
//
//		textAmoretizing = new JTextField();
//		textAmoretizing.setEditable(false);
//		textAmoretizing.setColumns(10);
//		textAmoretizing.setBounds(132, 258, 98, 20);
//		contentPane.add(textAmoretizing);
//
//		textAnnuity = new JTextField();
//		textAnnuity.setEditable(false);
//		textAnnuity.setColumns(10);
//		textAnnuity.setBounds(132, 288, 98, 20);
//		contentPane.add(textAnnuity);
//
//		JLabel lblRepayment = new JLabel("Repayment");
//		lblRepayment.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblRepayment.setEnabled(false);
//		lblRepayment.setBackground(Color.WHITE);
//		lblRepayment.setForeground(Color.BLACK);
//		lblRepayment.setBounds(22, 190, 98, 16);
//		contentPane.add(lblRepayment);
//
//		JLabel lblTotalAmount = new JLabel("Total Amount");
//		lblTotalAmount.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblTotalAmount.setEnabled(false);
//		lblTotalAmount.setForeground(Color.BLACK);
//		lblTotalAmount.setBackground(Color.WHITE);
//		lblTotalAmount.setBounds(132, 190, 98, 16);
//		contentPane.add(lblTotalAmount);
//
//		JLabel lblMonthlyRate = new JLabel("Monthly Rate");
//		lblMonthlyRate.setForeground(Color.BLACK);
//		lblMonthlyRate.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblMonthlyRate.setEnabled(false);
//		lblMonthlyRate.setBackground(Color.WHITE);
//		lblMonthlyRate.setBounds(264, 190, 98, 16);
//		contentPane.add(lblMonthlyRate);
//
//		textBulletRate = new JTextField();
//		textBulletRate.setEditable(false);
//		textBulletRate.setColumns(10);
//		textBulletRate.setBounds(264, 227, 98, 20);
//		contentPane.add(textBulletRate);
//
//		textAmoretizingRate = new JTextField();
//		textAmoretizingRate.setEditable(false);
//		textAmoretizingRate.setColumns(10);
//		textAmoretizingRate.setBounds(264, 258, 98, 20);
//		contentPane.add(textAmoretizingRate);
//
//		textAnnuityRate = new JTextField();
//		textAnnuityRate.setEditable(false);
//		textAnnuityRate.setColumns(10);
//		textAnnuityRate.setBounds(264, 288, 98, 20);
//		contentPane.add(textAnnuityRate);
//
//		JButton btnCalculate = new JButton("Calculate ");
//		btnCalculate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				amount = new BigDecimal(textAmount.getText());
//				years = new BigDecimal(textYears.getText());
//				interest = new BigDecimal(textInterest.getText());
//
//				myCredit = new Loan(amount, years, interest);
//				BigDecimal resultBullet = myCredit.calculateBullet().get("bullet");
//				BigDecimal resultAmoretizing = myCredit.calculateAmortizing();
//				BigDecimal resultAnnuity = myCredit.calculateAnnuity();
//
//				displayInformation(resultBullet, resultAmoretizing, resultAnnuity);
//				enableFields();
//			}
//
//			void displayInformation(BigDecimal bullet, BigDecimal amoretizing, BigDecimal annuity) {
//				DecimalFormat df = new DecimalFormat("###,###.##");
//
//				textBullet.setText(df.format(bullet));
//				textAmoretizing.setText(df.format(amoretizing));
//				BigDecimal twelve = new BigDecimal("12");
//				textAmoretizingRate.setText("( " + df.format(amoretizing.divide(years, MathContext.DECIMAL128).divide(twelve, MathContext.DECIMAL128)) + " ) ");
//				textAnnuity.setText(df.format(annuity));
//				textAnnuityRate.setText(df.format(annuity.divide(years, MathContext.DECIMAL128).divide(twelve, MathContext.DECIMAL128)));
//
//				double[] loans = { bullet.doubleValue(), amoretizing.doubleValue(), annuity.doubleValue() };
//
//				highlightBestOf(loans);
//			}
//
//			void enableFields() {
//				textBullet.setEnabled(true);
//				textAmoretizing.setEnabled(true);
//				textAnnuity.setEnabled(true);
//				textBulletRate.setEnabled(true);
//				textAmoretizingRate.setEnabled(true);
//				textAnnuityRate.setEnabled(true);
//				labelResultAmoretizing.setEnabled(true);
//				labelResultAnnuity.setEnabled(true);
//				labelResultBullet.setEnabled(true);
//				lblRepayment.setEnabled(true);
//				lblTotalAmount.setEnabled(true);
//				lblMonthlyRate.setEnabled(true);
//			}
//
//			void highlightBestOf(double[] loans) {
//				double bullet = loans[0];
//				double amoretizing = loans[1];
//				double annuity = loans[2];
//
//				Arrays.sort(loans);
//				double bestLoan = loans[0];
//
//				if(bestLoan == bullet) {
//					textBullet.setForeground(Color.GREEN);
//					textBullet.setFont(new Font("Dialog", Font.BOLD, 12));
//				} else if(bestLoan == amoretizing) {
//					textAmoretizing.setForeground(Color.GREEN);
//					textAmoretizing.setFont(new Font("Dialog", Font.BOLD, 12));
//				} else if(bestLoan == annuity) {
//					textAnnuity.setForeground(Color.GREEN);
//					textAnnuity.setFont(new Font("Dialog", Font.BOLD, 12));
//				}
//			}
//		});
//		btnCalculate.setBounds(202, 72, 98, 52);
//		contentPane.add(btnCalculate);
//
//		JButton btnDownloadFileBullet = new JButton("XMLGenerator plan");
//		btnDownloadFileBullet.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) { myCredit.;
//			}
//		});
//		btnDownloadFileBullet.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		btnDownloadFileBullet.setBounds(385, 227, 114, 20);
//		contentPane.add(btnDownloadFileBullet);
//
//		JButton btnDownloadFileAmortizing = new JButton("XMLGenerator plan");
//		btnDownloadFileAmortizing.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				myCredit.generateAmortizingPlanForDownload();
//			}
//		});
//		btnDownloadFileAmortizing.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		btnDownloadFileAmortizing.setBounds(385, 258, 114, 20);
//		contentPane.add(btnDownloadFileAmortizing);
//
//		JButton btnDownloadFileAnnuity = new JButton("XMLGenerator plan");
//		btnDownloadFileAnnuity.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				myCredit.generateAnnuityPlanForDownload();
//			}
//		});
//		btnDownloadFileAnnuity.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		btnDownloadFileAnnuity.setBounds(385, 288, 114, 20);
//		contentPane.add(btnDownloadFileAnnuity);
//	}
//}
