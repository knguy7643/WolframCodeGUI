
public interface BoundaryConditions {

	/**
	 * This method returns the neighbor of a Cell in any given generation with regards to its boundary conditions.
	 * @param cellIdx The index of the cell whose neighbor will be determined.
	 * @param offset The index of the neighbor cell relativbe to the cell at the specified index.
	 * @param gen The generation that contains the cells.
	 * @return The cell with the sum of the cell's index and offset with respect to the boundary conditions.
	 */
	Cell getNeighbor(int cellIdx, int offset, Generation gen);
}
