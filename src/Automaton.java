import java.util.ArrayList;
import java.util.List;

public class Automaton {
	
	/**
	 * This field stores the Rule object by which the automaton will evolve. 
	 */
	private Rule rule;
	
	/** 
	 * This field stores the generations within the automaton.
	 */
	private List<Generation> generations;
	
	/**
	 * This field stores the boundary conditions that will dictate how some cells within its generations will evovle.
	 */
	private BoundaryConditions bc;
	
	/**
	 * This field stores the number of times and automaton most current generation has been evolved. 
	 */
	private int stepNum;
	
	/**
	 * This constructor stores the rule, initial generation, and boundary conditions for the automaton. 
	 * @param rule The rule that will dictate the automaton's evolution.
	 * @param initialG The initial generation of the automaton.
	 * @param bc The boundary conditions that affect the neighborhood for automaton evolution.
	 */
	public Automaton(Rule rule, Generation initialG, BoundaryConditions bc) {
		this.rule = rule;
		
		this.bc = bc;
		
		generations = new ArrayList<>();
		
		generations.add(initialG);
		
		stepNum = 0;
	}
	
	/**
	 * This method returns the rule object used by the automaton to affect its evolution.
	 * @return The rule object that affects the automaton.
	 */
	public Rule getRule() {
		return this.rule;
	}
	
	/**
	 * This method returns the boundary conditions of the automaton.
	 * @return The automaton's boundary conditions.
	 */
	public BoundaryConditions getBC() {
		return this.bc;
	}
	
	/**
	 * This method will return the generation at a specified step number with in the automaton and will evolve the automaton if the automaton has not yet evovled far enough to reach the step number.
	 * @param stepNum The index of the desired generation within the automaton.
	 * @return The generation in the automaton at a specified step number.
	 * @throws InvalidStepNumException This exception is thrown if the step number is less than zero.
	 */
	public Generation getGeneration(int stepNum) throws InvalidStepNumException {
		if (stepNum < 0) {
			throw new InvalidStepNumException();
		}
		else if (stepNum >= generations.size()) {
			int diff = stepNum - generations.size() + 1;
			this.evolve(diff);
			return generations.get(stepNum);
		}
		else {
			return generations.get(stepNum);
		}
 	}
	
	/**
	 * This method takes the most recent generation in an automaton and evolves it by a specified number of times.
	 * @param numSteps The number of times the automaton should be evolved.
	 */
	public void evolve(int numSteps) {
		for (int idx = 0; idx < numSteps; idx++) {
			Generation copy = generations.get(stepNum);
			
			Generation copyFromString = new Generation(copy.toString());
			
			generations.add(rule.evolve(copyFromString, bc));
			
			stepNum++;
		}
	}
	
	/**
	 * This method returns the number of times the automaton has been evolved.
	 * @return The number of steps in a generation.
	 */ 
	public int getTotalSteps() {
		return stepNum;
	}
	
	/**
	 * This method returns a string of the automaton's most recent generation.
	 * @return A string representation of the most recent generation in the automaton.
	 */
	public String toString() {
		return generations.get(stepNum).toString();
	}
	
	public String getHistory() {
		String str = "";
		
		for (int idx = 0; idx < generations.size(); idx++) {
			if (idx == generations.size() - 1) {
				str = str + generations.get(idx).toString();
			}
			else {
				str = str + generations.get(idx).toString() + "\n";
			}
		}
		
		return str;
	}
}
