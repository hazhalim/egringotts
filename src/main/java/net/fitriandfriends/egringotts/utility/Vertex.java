package net.fitriandfriends.egringotts.utility;

public class Vertex<T extends Comparable<T>, N extends Comparable<N>> {

   // Instance variables
   T vertexInformation;
   int inDegree;
   int outDegree;
   Vertex<T, N> nextVertex;
   Edge<T, N> firstEdge;

   // Constructors
   public Vertex() {

      this.vertexInformation = null;
      this.inDegree = 0;
      this.outDegree = 0;
      this.nextVertex = null;
      this.firstEdge = null;

   }
	
   public Vertex(T vertexInformation, Vertex<T, N> nextVertex) {

      this.vertexInformation = vertexInformation;
      this.inDegree = 0;
      this.outDegree = 0;
      this.nextVertex = nextVertex;
      this.firstEdge = null;

   }

}