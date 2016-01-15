//	CSE 214 HW # 4
//	By: Rashedul Khan (108921821)
//

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Quicksorter<E> implements Sorter<E>{
	private ArrayList<E> list;
	private Comparator<E> comp;
	
	public Quicksorter(Comparator<E> comp, ArrayList<E> list){
		this.comp = comp;
		this.list = list;
	}

	@Override
	public ArrayList<E> sort() {
		quickSort(list, 0, list.size()-1);
		return list;
	}
	
	private void quickSort(ArrayList<E> list, int left, int right) {
		int pivot = partition(list, left, right);
		if (left < pivot-1) 
			quickSort(list, left, pivot-1);
		if (pivot < right)
			quickSort(list, pivot, right);
	}
	
	private int partition(ArrayList<E> list, int left, int right) {
		int i = left;
		int j = right;
		int pivot = (left+right)/2;

		while (i<=j) {
			while (comp.compare(list.get(i), list.get(pivot)) < 0)
				i++;
			while (comp.compare(list.get(j), list.get(pivot)) > 0)
				j--;
			if (i<=j) {
				Collections.swap(list, i++, j--);
			}
		}
		
		return i;	// pivot
	}

	@Override
	public void setComparator(Comparator<E> comparator) {
		this.comp = comparator;
	}


}
