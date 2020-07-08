
public class AutomatonMeasurements {

	/**
	 * This method compares two generation and determines the number of cells that differ in state.
	 * @param g1 The first generation of interest.
	 * @param g2 The second generation of interest.
	 * @return The number of cells the differ in states between the two generations.
	 */
	public static int hammingDistance(Generation g1, Generation g2) {
		int count = 0;
		
		if (g1.size() != g2.size()) {
			throw new IllegalArgumentException();
		}
		else {
			for (int idx = 0; idx < g1.size(); idx++) {
				if (g1.getCell(idx).getState() != g2.getCell(idx).getState()) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	/**
	 * This method compares the generations prior to and after the step number generation within the automaton.
	 * @param stepNum The index of the generation in the automaton that lies in between the two generations of interest.
	 * @param a The automaton containing the two generations of interest.
	 * @return The number of cells that differ in state between the two generations.
	 * @throws InvalidStepNumException This exception is thrown if the step number is less than zero.
	 */
	public static int hammingDistance(int stepNum, Automaton a) throws InvalidStepNumException {
		int count = 0;
		
		if (stepNum <= 0) {
			throw new InvalidStepNumException();
		}
		else {
			for (int idx = 0; idx < a.getGeneration(stepNum).size(); idx++) {
				if (a.getGeneration(stepNum - 1).getCell(idx).getState() != a.getGeneration(stepNum + 1).getCell(idx).getState()) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	/**
	 * This method returns the count of subrule for every step in the automaton.
	 * @param a The automaton of interest.
	 * @return A 2D array of the subrule counts for each generation in the automaton.
	 * @throws InvalidStepNumException This exception is thrown if the step number is less than zero.
	 */
	public static int[][] subruleCounts(Automaton a) throws InvalidStepNumException {
		int[][] temp = new int[a.getTotalSteps()][a.getRule().getNumSubrules()];
		
		for (int idx = 0; idx < a.getTotalSteps(); idx++) {
			int [] tempInternal = subruleCount(idx + 1, a);
			for (int idx2 = 0; idx2 < tempInternal.length; idx2++) {
				int idx3 = tempInternal.length - 1 - idx2;
				temp[idx][idx2] = tempInternal[idx3];
				idx3--;
			}
		}
		
		return temp;
	}
	
	/**
	 * THis method returns an array with hamming distance values between successive pairs of generations in an automaton.
	 * @param a The automaton of interest.
	 * @return An array of the hamming distance between sucessive generations of an array.
	 * @throws InvalidStepNumException This exception is thrown if the step number is less than zero.
	 */
	public static int[] hammingDistance(Automaton a) throws InvalidStepNumException {
		int[] hammingValues = new int[a.getTotalSteps()];
		
		for (int idx = 0; idx < a.getTotalSteps(); idx++) {
			Generation g1 = a.getGeneration(idx);
			Generation g2 = a.getGeneration(idx + 1);
			
			hammingValues[idx] = hammingDistance(g1, g2);
		}
		
		return hammingValues;
	}
	
	
	public static int[] subruleCount(int stepNum, Automaton a ) throws InvalidStepNumException {
		int[] subRuleCounts = new int[a.getRule().getNumSubrules()];
		
		int counter = 0;
		
		if (stepNum <= 0) {
			throw new InvalidStepNumException();
		}
		else {
			for (int idx = 0; idx < subRuleCounts.length; idx++) {
				subRuleCounts[idx] = 0;
			}
			if (a.getRule().getClass() == ElementaryRule.class) {
				Generation focus = a.getGeneration(stepNum);
				for (int idx = 0; idx < focus.size(); idx++) {
					Cell[] neighborhood = a.getRule().getNeighborhood(idx, focus, a.getBC());
					if (neighborhood[0].getState() == CellState.OFF) {
						if (neighborhood[1].getState() == CellState.OFF) {
							if (neighborhood[2].getState() == CellState.OFF) { 
								subRuleCounts[7] = subRuleCounts[7] + 1;
							}
							else {
								subRuleCounts[6] = subRuleCounts[6] + 1;
							}
						}
						else { 
							if (neighborhood[2].getState() == CellState.OFF) { 
								subRuleCounts[5] = subRuleCounts[5] + 1;
							}
							else {
								subRuleCounts[4] = subRuleCounts[4] + 1;
							}
						}
					}
					else { 
						if (neighborhood[1].getState() == CellState.OFF) { 
							if (neighborhood[2].getState() == CellState.OFF) { 
								subRuleCounts[3] = subRuleCounts[3] + 1;
							}
							else { 
								subRuleCounts[2] = subRuleCounts[2] + 1;
							}
						}
						else { 
							if (neighborhood[2].getState() == CellState.OFF) { 
								subRuleCounts[1] = subRuleCounts[1] + 1;
							}
							else { 
								subRuleCounts[0] = subRuleCounts[0] + 1;
							}
						}
					}
				}
			}
			else if (a.getRule().getClass() == TotalisticRule.class) {
				Generation focus = a.getGeneration(stepNum - 1);
				for (int idx = 0; idx < focus.size(); idx++) {
					Cell[] neighborhood = a.getRule().getNeighborhood(idx, focus, a.getBC());
					for (int idxN = 0; idxN < neighborhood.length; idxN++) {
						if (neighborhood[idxN].getState() == CellState.ON) {
							counter = counter + 1;
						}
					}
					subRuleCounts[counter] = subRuleCounts[counter] + 1; 
					counter = 0;
				}
				//Reverse order of array
				int[] temp = new int[subRuleCounts.length];
				
				for (int idx = 0; idx < subRuleCounts.length; idx++) {
					temp[idx] = subRuleCounts[subRuleCounts.length - 1 - idx];
				}
				
				subRuleCounts = temp;
			}
			
		}
		
		return subRuleCounts;
	}
}
