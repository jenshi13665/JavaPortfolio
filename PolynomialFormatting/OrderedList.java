/*The following class describes two checkSorted methods using comparable and comparator.
 * 
 */
import java.util.*;

public final class OrderedList<T extends Comparable<T>> {

	public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
		
        return checkSorted(list, (left, right) -> left.compareTo(right));
    }
	
	public static <T> boolean checkSorted(List<T> list, Comparator<? super T> c) {
		
		Iterator<T> iter = list.iterator();
		while (iter.hasNext()) {
			T previous = iter.next();
			if (iter.next() == null) {
				return true;
			}
			T current = iter.next();
			if (c.compare(current, previous) > 0) {
				return false;
			}
		}	
		return true;
	}

}
