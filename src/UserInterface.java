import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class UserInterface {

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
		
		JComboBox eRuleNums = new JComboBox<>(ruleTypes);
		
		String[] tRule = new String[64];
		
		for (int idx = 0; idx < tRule.length; idx++) {
			tRule[idx] = idx;
		}
		
		//Add components to JFrame.
		frame.add(ruleTypePanel);
		frame.add(rulePanel);
		
		//Make JFrame visible.
		frame.setVisible(true);
		
	}
	
}
