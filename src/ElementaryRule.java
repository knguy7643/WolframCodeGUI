
public class ElementaryRule extends Rule {
	
	/**
	 * This field stores the rule number as a binary string.
	 */
	private String binaryString;
	
	/**
	 * This field stores the number of subrules for the elementary rule.
	 */
	private int numSubRules = 8;
	
	/**
	 * This field is an array that stores the rule's cellstates based on its subrule number. 
	 */
	private CellState[] nextState = new CellState[8];
	
	/**
	 * This constructor creates an Elementary Object that converts the rule number into its binary string and stores the rule's cellstates based on its subrule number.
	 * @param ruleNumber The desired rule number.
	 * @throws InvalidRuleNumException This exception is thrown if the rule number is less than 0 or greater than 255.
	 */
	public ElementaryRule(int ruleNumber) throws InvalidRuleNumException {
		super(ruleNumber);
		
		if ((ruleNumber < 0) || (ruleNumber > 255)) {
			throw new InvalidRuleNumException();
		}
		
		this.binaryString = Integer.toBinaryString(ruleNumber);
		
		this.binaryString = String.format("%08d", Integer.parseInt(binaryString));
		
		for (int idxOfString = 0; idxOfString < binaryString.length(); idxOfString++) {
			if (binaryString.charAt(idxOfString) == '0') {
				nextState[idxOfString] = CellState.OFF;
			}
			else {
				nextState[idxOfString] = CellState.ON;
			}
		}
		
	}

	/**
	 * This method returns the number of subrules for the Elementary Rule object.
	 * @return The number of subrules.
	 */
	@Override
	public int getNumSubrules() {
		return numSubRules;
	}

	/**
	 * This method returns an neighborhood of a desired cell and its neighbors as an array.
	 * @param cellIdx The index of the main cell for the neighborhood.
	 * @param gen The generation from which the neighborhood will be taken from.
	 * @param bc The boundary conditions that the cell must follow.
	 * @return An array of the neighborhood for a desired cell.
	 */
	@Override
	public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
		Cell[] neighborhood = new Cell[3];
		
		neighborhood[0] = bc.getNeighbor(cellIdx, -1, gen);
		neighborhood[1] = bc.getNeighbor(cellIdx, 0, gen);
		neighborhood[2] = bc.getNeighbor(cellIdx, 1, gen);
		
		return neighborhood;
	}

	/**
	 * This method returns information on the rule as a string.
	 * @return A string detailing the subrules and their corresponding cellstates for the rule object.
	 */
	@Override
	public String toString() {
		String str = "";
		
		String topLine = "000 00. 0.0 0.. .00 .0. ..0 ...";
		
		for (int idx = 0; idx < topLine.length(); idx++) {
			if (topLine.charAt(idx) == '0') {
				str = str + CellState.getChar(CellState.ON);
			}
			else if (topLine.charAt(idx) == '.') {
				str = str + CellState.getChar(CellState.OFF);
			}
			else {
				str = str + " ";
			}
		}
		
		str = str + "\n";
		
		str = str + " ";
		
		for (int idx = 0; idx < nextState.length; idx++) {
			if (idx == 7) {
				str = str + CellState.getChar(nextState[idx]) + " "; 
			}
			else {
				str = str + CellState.getChar(nextState[idx]) + "   ";
			}
		}
		
		return str;
	}

	/**
	 * This method takes a neighborhood of cells and determines the CellState for the main cell of the neighborhood.
	 * @param neighborhood An array of cell that will determine the next CellState.
	 * @return An EvolvedCell determined by the neighborhood.
	 */
	@Override
	public EvolvedCell evolve(Cell[] neighborhood) {
		
		EvolvedCell newCell = new EvolvedCell(CellState.OFF, 0);
		
		if (neighborhood[0].getState() == CellState.ON) {
			if (neighborhood[1].getState() == CellState.ON) {
				if (neighborhood[2].getState() == CellState.ON) { //ON ON ON
					newCell = new EvolvedCell(nextState[0], 7);
					//numSubRules = numSubRules + 7;
				}
				else { //ON ON OFF
					newCell = new EvolvedCell(nextState[1], 6);
					//numSubRules = numSubRules + 6;
				}
			}
			else { 
				if (neighborhood[2].getState() == CellState.ON) { //ON OFF ON
					newCell = new EvolvedCell(nextState[2], 5);
					//numSubRules = numSubRules + 5;
				}
				else { //ON OFF OFF
					newCell = new EvolvedCell(nextState[3], 4);
					//numSubRules = numSubRules + 4;
				}
			}
		}
		else { 
			if (neighborhood[1].getState() == CellState.ON) { 
				if (neighborhood[2].getState() == CellState.ON) { //OFF ON ON
					newCell = new EvolvedCell(nextState[4], 3);
					//numSubRules = numSubRules + 3;
				}
				else { //OFF ON OFF
					newCell = new EvolvedCell(nextState[5], 2);
					//numSubRules = numSubRules + 2;
				}
			}
			else { 
				if (neighborhood[2].getState() == CellState.ON) { //OFF OFF ON
					newCell = new EvolvedCell(nextState[6], 1);
					//numSubRules = numSubRules + 1;
				}
				else { //OFF OFF OFF
					newCell = new EvolvedCell(nextState[7], 0);
					//numSubRules = numSubRules + 0;
				}
			}
		}
		
		return newCell;
	}
}
