/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (5)
 * TA: Rathish Das
 */


public class Alphabet implements Hashable {
	private char letter;
	
	public Alphabet(char letter) {
		this.letter = letter;
	}
	
	public int hash() {
		/**
	     * Converts an instance of a hashable data type to a non-negative integer.
	     * It takes the alphabet as an input in the constructor.
	     * The ASCII value of the first letter is 97 and the second letter is 98.
	     * For each of the following alphabet the ASCII value is incremented by 1.
	     * Therefore, in order to hash this value to a minimum size ArrayList<V>
	     * each letter is converted into it's ASCII value and subtracted by 97
	     * to offset the hash table.
	     * @return the integer value.
	     */
		return (letter - 97);
	}
	
	public String toString() {
		return ""+letter;
	}

}
