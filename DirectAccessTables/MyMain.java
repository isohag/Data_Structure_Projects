
public class MyMain {
	public static void main(String[] args) {
		DirectAddressTable<Alphabet> alphabetTable = new DirectAddressTable<>();
		
		alphabetTable.insert(new Alphabet('a'));
		alphabetTable.insert(new Alphabet('b'));
		alphabetTable.insert(new Alphabet('q'));
		System.out.println(alphabetTable.toString());
		//System.out.println(alphabetTable);
	}
}
