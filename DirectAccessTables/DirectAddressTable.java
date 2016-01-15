/**
 * Rashedul Khan
 * 108921821
 * CSE 214 (5)
 * TA: Rathish Das
 */


import java.util.ArrayList;

public class DirectAddressTable<V extends Hashable> implements Dictionary<V>{
	private int key;
	private int size;
	private static final int CAPACITY = 26;
	ArrayList<V> list;
	
	public DirectAddressTable() {
		list = new ArrayList<V>(CAPACITY);
		size = 0;
		for (int i=0; i<CAPACITY; i++)
			list.add(null);
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void insert(V value) {
		key = value.hash();
		if (list.get(key) == null);	 //if its a new key increment size
			size++;
		list.set(key, value);
	}

	@Override
	public V delete(V value) {
		//	Try to retrieve V
		key = value.hash();
		V temp = list.get(key);
		if (temp != null) {
			list.set(key, null);	// delete
			size--;
			return temp;
		}
		else
			return null;
	}

	@Override
	public V find(int key) {
		return list.get(key);
	}
	
	public String toString() {
		String result = "";
		int j = 0;
		for (int i=0;i<CAPACITY && j < size; i++) {
			result += i + " -- " + list.get(i) + "\n";
			if (list.get(i) != null)
				j++;
		}
		return result;
	}

}
