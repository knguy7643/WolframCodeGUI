
public class CircularBoundaryConditions implements BoundaryConditions {

	/**
	 * This is a no-argument constructor.
	 */
	public CircularBoundaryConditions() {
	}
	
	/**
	 * This method returns the neighbor of a Cell in generation with respect to the boundary conditions.
	 * @param cellIdx The index of the desired cell within its generation.
	 * @param offset The index of the neighbor relative to the cell.
	 * @param gen The generation containing the desired cell.
	 * @return The cell with th eindex of the cellIdx plus the offset.
	 */
	@Override
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {

		int sum = cellIdx + offset;
		
		if (sum < 0) {
			while (offset != 0) {
				if (cellIdx == 0) {
					cellIdx = gen.size() - 1;
				}
				else {
					cellIdx = cellIdx - 1;
				}
				offset = offset + 1;
			}
			return gen.getCell(cellIdx);
		}
		else if (sum >= gen.size()) {
			while (offset != 0) {
				cellIdx = cellIdx  + 1;
				if (cellIdx == gen.size()) {
					cellIdx = 0;
				}
				offset = offset - 1;
			}
			return gen.getCell(cellIdx);
		}
		else {
			return gen.getCell(sum);
		}
		
	}

}
