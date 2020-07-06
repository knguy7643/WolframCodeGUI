import java.util.HashMap;
import java.util.Map;

public enum CellState {

	/**
	 * The two possible constants for this enum.
	 */
	OFF, ON;
	
	/**
	 * This field stores the symbol that will correspond to each CellState.
	 */
	private char symbol;
	
	/**
	 * This map stores the character as a key for its CellState stored as a value.
	 */
	private static Map<Character, CellState> SYMBOL_TO_STATE = new HashMap<>(2);
	
	/**
	 * This map stores utilizes the CellState as a key to acquire its corresponding character stored as a value.
	 */
	private static Map<CellState, Character> STATE_TO_SYMBOL = new HashMap<>(2);
	
	static {
		SYMBOL_TO_STATE.put('.', CellState.OFF);
		SYMBOL_TO_STATE.put('O', CellState.ON);
		STATE_TO_SYMBOL.put(CellState.OFF, '.');
		STATE_TO_SYMBOL.put(CellState.ON, 'O');
	}
	
	/**
	 * This default constructor assigns a whitespace character for a no-argument CellState.
	 */
	private CellState() {
		this.symbol = ' ';
	}
	
	/**
	 * This constructor assigns a symbol to a CellState.
	 * @param symbol The deisred symbol character to be stored with the CellState.
	 */
	private CellState(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * This method returns the string representation of the character for its corresponding CellState.
	 * @return The string of the symbol for its corresponding CellState.
	 */
	@Override
	public String toString() {
		if (this == CellState.OFF) {
			return STATE_TO_SYMBOL.get(CellState.OFF).toString();
		}
		else {
			return STATE_TO_SYMBOL.get(CellState.ON).toString();
		}
	}
	
	/**
	 * This method returns the CellState based on its symbol character.
	 * @param symbol The symbol of the desired CellState.
	 * @return The CellState that corresponds to the symbol.
	 */
	public static CellState getState(char symbol) {
		return SYMBOL_TO_STATE.get(symbol);
	}
	
	/**
	 * This method returns the character that corresponds to the CellState.
	 * @param state The CellState that corresponds to the desired symbol character.
	 * @return The symbol that corresponds to the provided CellState.
	 */
	public static char getChar(CellState state) {
		return STATE_TO_SYMBOL.get(state);
	}
}
