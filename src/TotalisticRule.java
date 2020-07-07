
public class TotalisticRule extends Rule {

	/**
	 * This field the number of subrules for the TotalisticRule object.
	 */
	private int numSubRules = 6;
	
	/**
	 * This field stores the binary string of the rule number.
	 */
	private String binaryString;
	
	/**
	 * This field will store an array that dictates the corresponding CellState based on the inputted rule number.
	 */
	private CellState[] cellStates = new CellState[6];
	
	/**
	 * This constructor checks if the rule number is greater than or equal to 0 and less then or equal to 63.
	 * @param ruleNum The desired rule number.
	 * @throws InvalidRuleNumException This exception is thrown is the rule number is less than 0 or greater than 63.
	 */
	public TotalisticRule(int ruleNum) throws InvalidRuleNumException {
		super(ruleNum);
		
		if ((ruleNum < 0) || (ruleNum > 63)) {
			throw new InvalidRuleNumException();
		}
		
		this.binaryString = Integer.toBinaryString(ruleNum);
		this.binaryString = String.format("%06d", Integer.parseInt(binaryString));

		int counter = 0;
		
		for (int idxBS = 5; idxBS >= 0; idxBS--) {
			if (binaryString.charAt(idxBS) == '1') {
				cellStates[counter] = CellState.ON;
				counter++;
			}
			else {
				cellStates[counter] = CellState.OFF;
				counter++;
			}
		}
		
	}

	/**
	 * This method returns the number of subrules for the Totalistic Rule.
	 * @return The number of subrules. 
	 */
	@Override
	public int getNumSubrules() {
		return numSubRules;
	}

	/**
	 * This method returns the neighborhood for a cell of a specified index as an array.
	 * @param cellIdx The index of the main cell of interest in the generation.
	 * @param gen The generation from which the neighborhood will be determined.
	 * @param bc The boundary conditions that determine the neighsborhood.
	 * @return The neighborhood of cells as an array.
	 */
	@Override
	public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
		Cell[] nextGenCells = new Cell[5];
		
		nextGenCells[0] = bc.getNeighbor(cellIdx, -2, gen);
		nextGenCells[1] = bc.getNeighbor(cellIdx, -1, gen);
		nextGenCells[2] = bc.getNeighbor(cellIdx, 0, gen);
		nextGenCells[3] = bc.getNeighbor(cellIdx, 1, gen);
		nextGenCells[4] = bc.getNeighbor(cellIdx, 2, gen);
		
		return nextGenCells;
	}

	/**
	 * This method returns the string representation of a Totalistic Rule object.
	 * @return A string detailing the configurations and outcome of its rule number.
	 */
	@Override
	public String toString() {
		String str = "";
		
		str = str + "5 4 3 2 1 0" + "\n";

		for (int idx = 5; idx >= 0; idx--) {
			str = str + cellStates[idx].toString() + " ";
		}
		
		str = str.trim();
		
		return str;
	}

	/**
	 * This method returns an evolved cell based on the neighborhood provided.
	 * @param neighborhood The array of cells that will determine the CellState based on the configurations of the rule.
	 * @return An EvolvedCell with a CellState determined by the rule and its subrule number. 
	 */
	@Override
	public EvolvedCell evolve(Cell[] neighborhood) {
		int total = 0;
		
		for (int idx = 0; idx < neighborhood.length; idx++) {
			if (neighborhood[idx].getState().equals(CellState.ON)) {
				total = total + 1;
			}
		}
		
		EvolvedCell temp = new EvolvedCell(cellStates[total], total);
		
		return temp;
		
	}
	
	
	
}
