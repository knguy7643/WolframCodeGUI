
public abstract class Rule {

	/**
	 * This field stores the integer value of the rule number.
	 */
	private int ruleNum;
	
	/**
	 * This is a contructor that assigns a rule number for a rule object.
	 * @param ruleNum The number that will dictate the 
	 */
	protected Rule(int ruleNum) {
		this.ruleNum = ruleNum;
	}
	
	/** 
	 * This method returns the rule number for the rule object.
	 * @return The integer representation of the rule number.
	 */
	public int getRuleNum() {
		return this.ruleNum;
	}
	
	/**
	 * This method will evolve a generation by its rule object.
	 * @param gen The generation that will be evolved.
	 * @param bc The boundary conditions that determines a a cell's neighborhood or surrounding cells.
	 * @return The next generation in the automaton or the evolved generation of the specified generation.
	 */
	public Generation evolve(Generation gen, BoundaryConditions bc) {
		Cell[] nextGenCellInArray = new Cell[gen.size()];
		
		for (int idx = 0; idx < gen.size(); idx++) {
			nextGenCellInArray[idx] = this.evolve(this.getNeighborhood(idx, gen, bc));
		}
		
		Generation nextGen = new Generation(nextGenCellInArray);
		
		return nextGen;				
	}
	
	public abstract int getNumSubrules();
	public abstract Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc);
	public abstract String toString();
	public abstract EvolvedCell evolve(Cell[] neighborhood);
}
