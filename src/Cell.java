
public class Cell {
	
	/**
	 * This field stores the CellState of the Cell.
	 */
	private CellState cellstate;
	
	/**
	 * This default constructor creates a Cell with the OFF CellState.
	 */
	public Cell() {
		this.cellstate = CellState.OFF;
	}
	
	/**
	 * This constructor creates a cell with the desired CellState.
	 * @param cellState The CellState desired for the cell.
	 */
	public Cell(CellState cellState) {
		this.cellstate = cellState;
	}
	
	/**
	 * This method returns the CellState stored by the cell.
	 * @return The CellState of the cell.
	 */
	public CellState getState() {
		return this.cellstate;
	}
	
	/**
	 * This method returns the Cell's CellState in its string representation.
	 * @return The string representation of the Cell's CellState.
	 */
	public String toString() {
		return this.cellstate.toString();
	}
}
