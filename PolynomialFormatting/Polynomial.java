/*The following class describes a Polynomial object.
 * 
 */

import java.util.Iterator;

import javax.swing.JOptionPane;

public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {
	
	//Polynomial attributes
	private Node head;
	private Node tail;
	
	//The following class defines a Polynomial term,
	//i.e. a coefficient and an exponent.
	static class Term {
		//Term attributes
		private double coefficient;
		private int exponent;
		
		//Term constructor
		public Term(double coefficient, int exponent) throws InvalidPolynomialSyntax {
			this.coefficient = coefficient;
			this.exponent = exponent;
		}
		
		//Term getter methods
		public double getCoefficient() {
			return this.coefficient;
		}
		
		public int getExponent() {
			return this.exponent;
		}
		
		//Term toString() method, used later in the
		//Polynomial.toString() method
		@Override
		public String toString() {
			StringBuilder strTerm = new StringBuilder();
			
			if (exponent == 0) {
				strTerm.append(coefficient);
			}
			else if (exponent == 1) {
				strTerm.append(coefficient + "x");
			}
			else {
				strTerm.append(coefficient + "x^" + exponent);
			}
			return strTerm.toString();
		}
	}
	
	//The following class defines a node of
	//a singly linked list description of Polynomial.
	static class Node{
		
		//Node attributes
		private Term data;
		private Node next;
		
		//Node constructor
		public Node(Term data) throws InvalidPolynomialSyntax {
			this.data = data;
		}
		
		//Node getter methods
		public Term getCurrentTerm() {
			return this.data;
		}
		
		public Node getNext() {
			return this.next;
		}
	}
	
	//Polynomial constructor
	//Here, the constructor receives a string, splits it based on whitespace
	//then iterates over the resulting list, so that every 2 indices, it will
	//parse a coefficient and an integer into a term, then adding that term as a Node.
	public Polynomial (String strPolynomial) throws InvalidPolynomialSyntax {
        String[] splitPoly = strPolynomial.split("\\s+");
        for(int i = 0; i < splitPoly.length; i+=2) {
        	double coef = Double.parseDouble(splitPoly[i]);
            int exp = Integer.parseInt(splitPoly[i+1]);
            Term newTerm = new Term(coef, exp);
            addNode(newTerm);
        } 
    }
	
	//getter methods
	public Node getHead() {
		return this.head;
	}
	
	public Node getTail() {
		return this.tail;
	}
	
	//method to add a node to the singly linked list/Polynomial
	public void addNode(Term data) throws InvalidPolynomialSyntax {
		Node newNode = null;
		try {
			newNode = new Node(data);
		} catch (InvalidPolynomialSyntax e) {
			e.getMessage();
		}
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		if(this.head.data.exponent < this.tail.data.exponent) {
			throw new InvalidPolynomialSyntax("A polynomial is not in descending order. Please check and try again.");
		}
		
	}
	
	//method to create a Term iterator
	@Override
	public Iterator<Term> iterator() {
		return new PolyIterator();
	}
	
	//method to define a Term iterator
	public class PolyIterator implements Iterator<Term> {
		private Node current;
		
		@Override
		public boolean hasNext() {
			if (current == null) {
				return head != null;
			}
			else {
				current = current.getNext();
				if (current == null) {
					return current == null;
				}
			}
			return current.data != null;
		}
		
		@Override
		public Term next() {
			if (current == null) {
				current = head;
			}
			else {
				current = current.getNext();
			}
			return current.getCurrentTerm();
		}
	}
	
	//method to describe comparison of two Polynomials,
	//to determine if one is larger than the other, using
	//iterators.
	@Override
	public int compareTo(Polynomial compPoly) {
		Iterator<Term> iter1 = this.iterator();
		Iterator<Term> iter2 = compPoly.iterator();
		
		while (iter1 != null && iter2 != null) {
			Term term1 = iter1.next();
			Term term2 = iter2.next();
			if (term1.exponent > term2.exponent) {
				return term1.exponent - term2.exponent;
			}
			if (term1.exponent < term2.exponent) {
				return term1.exponent - term2.exponent;
			}
			if (term1.exponent == term2.exponent) {
				if ((term1.coefficient > term2.coefficient) || (term1.coefficient < term2.coefficient)) {
					return (int)term1.coefficient - (int)term2.coefficient;
				}
				else {
					continue;
				}
			}
		}
		return 0;
	}
	
	//method to convert a Polynomial object to a human-readable
	//Polynomial string
	@Override
	public String toString() {
		StringBuilder strPolynomial = new StringBuilder();
		if (head.data.coefficient < 0) {
			strPolynomial.append("-").append(head.data.toString());
		}
		else {
			strPolynomial.append(head.data.toString());
		}
		for(Node iter = head.next; iter != null; iter = iter.next) {
			if (iter.data.coefficient > 0) {
				strPolynomial.append("+").append(iter.data.toString());
			}
			else {
				strPolynomial.append("-").append(iter.data.toString());
			}
		}
		return strPolynomial.toString();
	}

}
