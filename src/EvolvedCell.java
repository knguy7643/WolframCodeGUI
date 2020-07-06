
public class EvolvedCell extends Cell {

	/**
	 * This field stores the subrule number used to determine the CellState of this EvolvedCell.
	 */
	private int subRuleNumber;
	
	/**
	 * This method returns the subrule number of the EvolvedCell.
	 * @return The subrule number stored by the EvolvedCell.
	 */
	public int getSubruleNum() {
		return this.subRuleNumber;
	}
	
	/**
	 * This constructor creates an EvolvedCell using a specified CellState and subrule number/
	 * @param cellState The desired CellState for the EvolvedCell.
	 * @param subRuleNum The subrule number used to determine the CellState for the Evolved Cell.
	 */
	public EvolvedCell(CellState cellState, int subRuleNum) {
		super(cellState);
		this.subRuleNumber = subRuleNum;
	}
	
}
