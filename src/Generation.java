
public class Generation {

	/**
	 * This field stores the cells of this generation in an array.
	 */
	private Cell[] cells;
	
	/**
	 * This constructor creates a generation from an array of CellStates.
	 * @param cellStates An array of CellStates.
	 */
	public Generation(CellState[] cellStates) {
		cells = new Cell[cellStates.length];
		
		for (int idx = 0; idx < cellStates.length; idx++) {
			cells[idx] = new Cell(cellStates[idx]);
		}
	}
	
	/**
	 * This constructor creates a generation from a string where each character within the string should
	 * correspond with a CellState, otherwise an IllegalArgumentException is thrown.
	 * @param states A string of chracters that correlates to a CellState.
	 */
	public Generation(String states) {
		cells = new Cell[states.length()];
		
		for (int idx = 0; idx < states.length(); idx++) {
			if (CellState.getState(states.charAt(idx)) == null) {
				throw new IllegalArgumentException();
			}
			else {
				this.cells[idx] = new Cell(CellState.getState(states.charAt(idx)));
			}
		}
	}
	
	/**
	 * This constructor creates a generation by copying an array of cells.
	 * @param cells An array of cells.
	 */
	public Generation(Cell[] cells) {
		this.cells = new Cell[cells.length];
			
		for (int idx = 0; idx < cells.length; idx++) {
			this.cells[idx] = cells[idx];
		}
	}
	
	/**
	 * This method returns the size of a generation.
	 * @return The size of a generation or the size of the array of cells.
	 */
	public int size() {
		return this.cells.length;
	}
	
	/**
	 * This method returns a cell at a desired index within a generation.
	 * @param idx The index of the location of the desired cell.
	 * @return The cell in the generation at the specified index.
	 */
	public Cell getCell(int idx) {
		return cells[idx];
	}
	
	/**
	 * This method returns a string of the generation by utilizing the characters of each cell's CellStates.
	 * @return A string representation of the generation.
	 */
	public String toString() {
		String str = "";
		
		for (int idx = 0; idx < cells.length; idx++) {
			str = str + cells[idx].toString() + " ";
		}
		
		return str.replaceAll("\\s+", "");
	}
}
