
public class FixedBoundaryConditions implements BoundaryConditions {

	/**
	 * This field stores the cell that acts as the left boundary.
	 */
	private Cell leftCell;
	
	/**
	 * This field stores the cell that acts as the right boundary.
	 */
	private Cell rightCell;
	
	/**
	 * This constructor assigns the left and right cellstates to the left and right cell boundaries.
	 * @param left The cellstate for the left cell boundary.
	 * @param right The cellstate for the right cell boundary.
	 */
	public FixedBoundaryConditions(CellState left, CellState right) {
		this.leftCell = new Cell(left);
		this.rightCell = new Cell(right);
	}
	
	/**
	 * This method returns the CellState of the left cell boundary.
	 * @return The CellState of the left cell boundary.
	 */
	public CellState getLeftState() {
		return leftCell.getState();
	}
	
	/**
	 * This method returns the CellState of the right cell boundary.
	 * @return The CellState of the right cell boundary.
	 */
	public CellState getRightState() {
		return rightCell.getState();
	}
	
	/**
	 * This method returns the neighbor of a desired cell in a given generation with respect to the fixed boundary cells.
	 * @param cellIdx The index of the main cell of interest.
	 * @param offset The index of the neighbor relative to the cell.
	 * @param gen The entire generation from which the main cell exist and neighbor cell will be determined.
	 * @return The cell in the generation with the index of the cellIdx plus offset.
	 */
	@Override
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {
		int sum = cellIdx + offset;
		
		if (sum < 0) {
			return leftCell;
		}
		else if (sum >= gen.size()) {
			return rightCell;
		}
		else {
			return gen.getCell(sum);
		}
	}

}
