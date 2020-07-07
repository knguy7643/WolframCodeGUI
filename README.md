# WolframCodeGUI

----------------------------

## Description

Every cell consists of a cellstate that can be either ON/OFF or T/F. A group of cells form a generation. This generation can be evolved by a set of rules to form an Elementary Cellular Automaton(ECA). An ECA have the following characteristics:

1. Each cell can only exist in two states.
2. The cells are arranged in a line to form a generation.
3. The rule determines the cell's next state based on its state and the state of its two closest neighbors.

The Boundary Conditions used for this ECA will be either fixed or circular. For fixed boundary conditions, there will be two pre-established states for the left and right most ends of the generation. The neighbors for the first cell would be the second cell and one of the pre-established states. The neighbors for the last cell would be the penultimate cell and the other pre-established states. For circular boundary conditions, it treats the generation as a circular object by considering the first and last cells to be neighbors, thus the neight for the first cell would be the second cell and the last cell. 

The rules used to determine the evolution of the cells will either be an elementary rule or a totalistic rule. For the elemntary rule, a cell evolution is determine by its neighborhood, the staes of itself and its two nearest neighbors. Since there are only 2 possible cellstates, there are a total of 8 different neightborhoods. This results in 256 (2^8) possible rules. When the rule number is converted into a binary number, it results in a set of 0's and 1's that dictate the rules. Taking 0 to be OFF and 1 to be ON, we can see that for Rule 110, it has a binary number of 01101110. This translate to the following:

|111|110|101|100|011|010|001|000|

| 0 | 1 | 1 | 0 | 1 | 1 | 1 | 0 |

Now each of these configurations are number 7 to 0 from left to right to serve as subrule numbers. These number will play a role in help us calculate the Hamming Distance. Now unlike the elementary, the totalistic rule is dependent not on the cell states of the neighborhood, but rather solely on the number of ON cells in the neighborhood. 

----------------------------
## Checklist

- [x] Create all needed classes.
- [ ] Add method signatures for Automaton class.
- [x] Add method signatures for Generation class.
- [x] Add method signatures for Cell class.
- [x] Add method signatures for EvolvedCell class.
- [x] Add method signatures for CellState enum.
- [x] Add method signatures for Rule(abstract) class.
- [ ] Add method signatures for ElementaryRule class.
- [ ] Add method signatures for TotalisticRule class.
- [ ] Add method signatures for InvalidRuleNumException class.
- [ ] Add method signatures for InvalidStepNumException class.
- [x] Add method signatures for BoundaryConditions class.
- [ ] Add method signatures for FixedBoundaryConditions class.
- [ ] Add method signatures for CircularBoundaryConditions class.
- [ ] Add method signatures for AutomatonMeasurements class.
