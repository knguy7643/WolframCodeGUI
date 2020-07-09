import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

public class UserInterface {
	
	private static Rule rule;
	private static Generation initialGen;
	private static BoundaryConditions bc;
	private static Automaton auto;

	public static void main(String[] args) {
		
		//Initialize GUI
		JFrame frame = new JFrame("Automaton Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(400, 400);
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
		
		JPanel bCPanel = new JPanel();
		JLabel bcType = new JLabel("Boundary Conditions: ");
		
		String[] bcTypes = {"Circular", "Fixed"};
		JComboBox bcOptions = new JComboBox<>(bcTypes);
		
		bCPanel.add(bcType);
		bCPanel.add(bcOptions);
		
		String[] fixedOptions = {"CellState OFF", "CellState ON"};
		JComboBox fixedOpts1 = new JComboBox<>(fixedOptions);
		JComboBox fixedOpts2 = new JComboBox<>(fixedOptions);
		 
		JPanel fbcL = new JPanel();
		JPanel fbcR = new JPanel();
		JLabel left = new JLabel("Left CellState: ");
		JLabel right = new JLabel("Right CellState: ");
		fbcL.add(left);
		fbcL.add(fixedOpts1);
		fbcR.add(right);
		fbcR.add(fixedOpts2);
		
		bcOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (bcOptions.getSelectedItem() == "Fixed") {
					frame.add(fbcL);
					frame.add(fbcR);
					frame.revalidate();
				}
				else {
					frame.remove(fbcL);
					frame.remove(fbcR);
					frame.revalidate();
				}
			}
		});
		
		JPanel symbolPanel = new JPanel();
		JLabel falseCharLabel = new JLabel("False/Off Charater: ");
		JLabel trueCharLabel = new JLabel("True/On Character: ");
		JTextField falseCharField = new JTextField(1);
		JTextField trueCharField = new JTextField(1);
		
		//Add components to JFrame.
		frame.add(ruleTypePanel);
		frame.add(rulePanel);
		frame.add(bCPanel);
		
		//Make JFrame visible.
		frame.setVisible(true);
		
	}
	
}
