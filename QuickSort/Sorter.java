//	CSE 214 HW # 4
//	By: Rashedul Khan (108921821)
//

import java.util.ArrayList;
import java.util.Comparator;

public interface Sorter<E> {
	public ArrayList<E> sort();
	public void setComparator(Comparator<E> comparator);

}
