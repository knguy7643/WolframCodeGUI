import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

public class UserInterface {
	
	private static Rule rule;
	private static Generation initialGen;
	private static BoundaryConditions bc;
	private static Automaton auto;
	private static String filename;
	private static int stepNum;
	private static String information;
	
	//This method will print the needed information into the text document.
	public static void save(String filename, Automaton auto) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		writer.write(information + "\n\n");
		
		writer.write(auto.getHistory());
		
		writer.close();
		
	}

	public static void main(String[] args) {
		
		//Initialize GUI
		JFrame frame = new JFrame("Automaton Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(400, 600);
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(boxLayout);
		
		//Select Rule Type
		JPanel ruleTypePanel = new JPanel();
		JLabel ruleType = new JLabel("Rule Type: ");
		
		String[] ruleTypes = {"Elementary Rule", "Totalistic Rule"};
		JComboBox ruleList = new JComboBox<>(ruleTypes);

		ruleTypePanel.add(ruleType);
		ruleTypePanel.add(ruleList);
		
		//Select Rule Number
		JPanel rulePanel = new JPanel();
		JLabel ruleNumber = new JLabel("Rule Number: ");
		rulePanel.add(ruleNumber);
		
		String[] eRule = new String[256];
		
		for (int idx = 0; idx < eRule.length; idx++) {
			eRule[idx] = Integer.toString(idx);
		}
		
		JComboBox eRuleNums = new JComboBox<>(eRule);
		
		rulePanel.add(eRuleNums);
		
		String[] tRule = new String[64];
		
		for (int idx = 0; idx < tRule.length; idx++) {
			tRule[idx] = Integer.toString(idx);
		}
		
		JComboBox tRuleNums = new JComboBox<>(tRule);
		
		//This action listener changes the list of rule numbers based on the selected rule type.
		ruleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ruleList.getSelectedItem() == "Totalistic Rule") {
					rulePanel.remove(eRuleNums);
					rulePanel.add(tRuleNums);
					frame.revalidate();
				}
				else {
					rulePanel.remove(tRuleNums);
					rulePanel.add(eRuleNums);
					frame.revalidate();
				}
			}
		});
		
		//Select Boundary Conditions
		JPanel bCPanel = new JPanel();
		JLabel bcType = new JLabel("Boundary Conditions: ");
		
		String[] bcTypes = {"Circular", "Fixed"};
		JComboBox bcOptions = new JComboBox<>(bcTypes);
		
		bCPanel.add(bcType);
		bCPanel.add(bcOptions);
		
		String[] fixedOptions = {"CellState OFF", "CellState ON"};
		JComboBox fixedOpts1 = new JComboBox<>(fixedOptions);
		JComboBox fixedOpts2 = new JComboBox<>(fixedOptions);
		
		//Select CellStates for Left and Right ends (if Fixed is selected)
		JPanel fbcL = new JPanel();
		JPanel fbcR = new JPanel();
		JLabel left = new JLabel("Left CellState: ");
		JLabel right = new JLabel("Right CellState: ");
		fbcL.add(left);
		fbcL.add(fixedOpts1);
		fbcR.add(right);
		fbcR.add(fixedOpts2);

		//Input string of initial generation
		JPanel genPanel = new JPanel();
		JLabel genLabel = new JLabel("String of Initial Generation Characters: ");
		JTextField genString = new JTextField(20);
		
		genPanel.add(genLabel);
		genPanel.add(genString);
		
		//Submit Button once the user and inputed all parameters
		JButton submit = new JButton("Submit");
		
		
		//Dictate the number of times the automaton should evolve
		JPanel stepNumPanel = new JPanel();
		JLabel stepnumLabel = new JLabel("Number of Evolutions: ");
		JTextField stepNumField = new JTextField(4);
		
		stepNumPanel.add(stepnumLabel);
		stepNumPanel.add(stepNumField);
		
		//This action listeners will show the options to select CellState when the Fixed BC is selected
		bcOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (bcOptions.getSelectedItem() == "Fixed") {
					frame.add(fbcL);
					frame.add(fbcR);
					//frame.add(symbolPanel);	
					frame.add(genPanel);
					frame.add(stepNumPanel);
					frame.add(submit);
					frame.revalidate();
				}
				else {
					frame.remove(fbcL);
					frame.remove(fbcR);
					frame.revalidate();
				}
			}
		});
		
		//Add components to JFrame.
		frame.add(ruleTypePanel);
		frame.add(rulePanel);
		frame.add(bCPanel);	
		frame.add(genPanel);
		frame.add(stepNumPanel);
		frame.add(submit);
		
		//This action listeners will create a text document with the parameters selected by the user.
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//This portion will note the rule tyoe for the file name.
				if (ruleList.getSelectedItem() == "Totalistic Rule") {
					try {
						rule = new TotalisticRule(Integer.parseInt((String)tRuleNums.getSelectedItem()));
					} catch (InvalidRuleNumException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					filename = "TotalisitcRule-" + tRuleNums.getSelectedItem() + "-";
				}
				else {
					try {
						rule = new ElementaryRule(Integer.parseInt((String)eRuleNums.getSelectedItem()));
					} catch (InvalidRuleNumException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					filename = "ElementaryRule-" + eRuleNums.getSelectedItem() + "-";
				}
				
				//This will note the bondary conditions for the Fixed BC.
				if (bcOptions.getSelectedItem() == "Fixed") {
					if ((fixedOpts1.getSelectedItem() == "CellState OFF") && (fixedOpts2.getSelectedItem() == "CellState OFF")) {
						bc = new FixedBoundaryConditions(CellState.OFF, CellState.OFF);
					}
					else if ((fixedOpts1.getSelectedItem() == "CellState OFF") && (fixedOpts2.getSelectedItem() == "CellState ON")) {
						bc = new FixedBoundaryConditions(CellState.OFF, CellState.ON);
					}
					else if ((fixedOpts1.getSelectedItem() == "CellState ON") && (fixedOpts2.getSelectedItem() == "CellState OFF")) {
						bc = new FixedBoundaryConditions(CellState.ON, CellState.OFF);
					}
					else {
						bc = new FixedBoundaryConditions(CellState.ON, CellState.ON);
					}
					filename = filename + "FixedBoundaryConditions.txt";
				}
				else {
					bc = new CircularBoundaryConditions();
					filename = filename + "CircularBoundaryConditions.txt";
				}
				
				//Sotres the number of evolutions needed.
				stepNum = Integer.parseInt(stepNumField.getText());
				
				initialGen = new Generation(genString.getText().trim()); 
				
				auto = new Automaton(rule, initialGen, bc);
				
				auto.evolve(stepNum);
				
				information = "Initial Generation: " + initialGen.toString() + "\n\n";
				
				information = information + rule.toString();
				
				try {
					save(filename, auto);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Opens the txt file once it has been created.
				Desktop desktop = Desktop.getDesktop();
				File file = new File(filename);
				try {
					desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	
		//Make JFrame visible.
		frame.setVisible(true);
		
	}
	
}
