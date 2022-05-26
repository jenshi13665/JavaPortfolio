/* This document defines the Hierarchy class.
 * Chloe Ingram
 * 09 May 2022
 * CMSC 350 -7380
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class Hierarchy<E> implements DFSActions<E>{
	private int indentation = 0;
	private List<String> outputs = new ArrayList<String>();
	
	@Override
	public void cycleDetected() {
		String last = outputs.get(outputs.size() - 1);
		last = last + " * ";
		outputs.set(outputs.size() - 1, last);
	}

	@Override
	public void processVertex(E element) {
		String vertexOut = " ".repeat(indentation);
		vertexOut = vertexOut + String.valueOf(element);
		outputs.add(vertexOut);
	}

	@Override
	public void descend() {
		indentation += 5;
	}

	@Override
	public void ascend() {
		indentation -= 5;
	}
	
	@Override
	public String toString() {
		for (String str : outputs) {
			if(str != " * ") {
				System.out.println(str);
			}
			else {
				System.out.print(str);
			}
		}
		return "Completed";
	}
}
