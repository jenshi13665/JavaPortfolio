/* This document defines the ParenthesizedList class.
 * Chloe Ingram
 * 09 May 2022
 * CMSC 350 -7380
 * 
 */

public class ParenthesizedList<E> implements DFSActions<E> {
	
	private StringBuilder parenthList = new StringBuilder();
	private String regex1 = "\\(\\)";
	private String regex2 = "\\(\\*\\)";

	@Override
	public void cycleDetected() {
		parenthList.append("*");
	}

	@Override
	public void processVertex(E element) {
		parenthList.append(element);
	}

	@Override
	public void descend() {
		parenthList.append("(");
	}

	@Override
	public void ascend() {
		parenthList.append(")");
	}
	
	@Override
	public String toString() {
		String pList = parenthList.toString();
		pList = pList.replaceAll(regex1, " ");
		pList = pList.replaceAll(regex2, " * ");
		return pList;
	}
}
