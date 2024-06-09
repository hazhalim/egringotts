package net.fitriandfriends.egringotts.utility;

public class Edge<T extends Comparable<T>, N extends Comparable <N>> {

	// Instance variables
	Vertex<T, N> toVertex;
	N weight;
	Edge<T, N> nextEdge;

	// Constructors
	public Edge() {

		toVertex = null;
		weight = null;
		nextEdge = null;

	}
	
	public Edge(Vertex<T, N> destination, N weighty, Edge<T, N> nextEdge) {

		this.toVertex = destination;
		this.weight = weight;
		this.nextEdge = this.nextEdge;

	}

}