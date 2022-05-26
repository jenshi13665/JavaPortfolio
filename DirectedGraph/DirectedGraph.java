/* This document defines the DFSActions interface as well as the DirectedGraph class.
 * Chloe Ingram
 * 09 May 2022
 * CMSC 350 -7380
 * 
 */

import java.util.ArrayList;

interface DFSActions<E> {
	void cycleDetected();
	void processVertex(E element);
	void descend();
	void ascend();
}
public class DirectedGraph<E>{
	
	//attributes
	private Vertex<E> head; //findAddVertex, findUnreachable, 
	private Vertex<E> tail; //findAddVertex TODO:Add to findUnreachable?
	private enum Marks {UNDISCOVERED, DISCOVERED, FINISHED}; //depthFirstSearch, findUnreachable
	
	//Vertex class
	static class Vertex<E> {
		//attributes
		E element;
		Edge<E> firstEdge; //addEdge
		Edge<E> lastEdge; //addEdge
		Vertex<E> nextVertex; //findAddVertex, findVertex, findUnreachable, DFS
		Marks mark = Marks.UNDISCOVERED;
		
		//constructor
		Vertex(E element) {
			this.element = element;
		}
	}
	
	//Edge class
	static class Edge<E> {
		//attributes
		Vertex<E> vertex; 
		Edge<E> nextEdge; //addEdge
		
		//constructor
		Edge(Vertex<E> vertex){
			this.vertex = vertex; //set but not used
		}
	}

	//method to add an edge between two vertices
	public void addEdge(E fromVertex, E toVertex) {
		Vertex<E> from = findAddVertex(fromVertex);
		Vertex<E> to = findAddVertex(toVertex);
		Edge<E> newEdge = new Edge<E>(to);
		if (from.lastEdge != null) {
			from.lastEdge.nextEdge = newEdge;
		}
		else {
			from.firstEdge = newEdge;
		}
		from.lastEdge = newEdge;
	}
	
	//method to search for a vertex by its element name
	private Vertex<E> findVertex(Vertex<E> current, E element) { //used by findAddVertex
		if (current == null) {
			return null;
		}
		if (current.element.equals(element)) {
			return current;
		}
		return findVertex(current.nextVertex, element); 
	}
	
	//method to find/add a vertex to a linked list of vertices
	public Vertex<E> findAddVertex(E element) { //used by addEdge
		Vertex<E> vertexCheck = findVertex(this.head, element);
		if (vertexCheck != null) {
			return vertexCheck;
		}
		//runs if findVertex returns null
		Vertex<E> newVertex = new Vertex<E>(element);
		if (this.tail != null) {
			this.tail.nextVertex = newVertex;
		}
		else {
			this.head = newVertex;
		}
		this.tail = newVertex;
		return newVertex;
	}
	
	//method to perform a depth-first search
	private void depthFirstSearch(DFSActions<E> action, Vertex<E> v) { //used by findUnreachable
		if(v == null) {
			return;
		}
		if(v.mark == Marks.DISCOVERED) {
			action.cycleDetected(); 
			return;
		}
		action.processVertex(v.element); 
		v.mark = Marks.DISCOVERED;
		action.descend();
		Edge<E> edge = v.firstEdge;
		while(edge != null) {
			depthFirstSearch(action, edge.vertex);
			edge = edge.nextEdge;
		}
		action.ascend(); 
		v.mark = Marks.FINISHED;
	}
	
	//method to determine if any vertices were "undiscovered"/"unreachable"
	public void findUnreachable(DFSActions<E> action) { //called from main class only
		Vertex<E> vertex = this.head;
		while(vertex.nextVertex != null) {
			vertex.mark = Marks.UNDISCOVERED;
			vertex = vertex.nextVertex;
		}
		vertex = this.head;
		depthFirstSearch(action, vertex);
		ArrayList<Vertex<E>> undiscovered = new ArrayList<Vertex<E>> ();
		vertex = this.head;
		while(vertex != null) {
			if (vertex.mark != Marks.FINISHED) {
				undiscovered.add(vertex);
			}
			vertex = vertex.nextVertex;
		}
		System.out.println();
		System.out.print("Unreachable vertices: ");
		undiscovered.forEach(name ->{
            System.out.print(name.element + " ");
        });
		System.out.println();
	}
	

}
