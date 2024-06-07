package net.fitriandfriends.egringotts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Data
@Getter
@Setter
@ToString
public class WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {

   // Instance variables
   Vertex<T, N> head;
   int size;

   // Constructors
   public WeightedGraph() {

      head = null;
      size = 0;

   }

   // Accessor and mutator methods (custom implementations)
   public int getInDegree(T vertex) {

      if (hasVertex(vertex)) {

         Vertex<T, N> temp = head;

         while (temp != null) {

            if (temp.vertexInformation.compareTo(vertex) == 0)
               return temp.inDegree;

            temp = temp.nextVertex;

         }

      }

      return -1;

   }
         
   public int getOutDegree(T vertex) {

      if (hasVertex(vertex)) {

         Vertex<T, N> temp = head;

         while (temp != null) {

            if (temp.vertexInformation.compareTo(vertex) == 0)
               return temp.outDegree;

            temp = temp.nextVertex;

         }

      }

      return -1;

   }

   public boolean hasVertex(T vertex) {

      if (head == null)
         return false;

      Vertex<T, N> temp = head;

      while (temp != null) {

         if (temp.vertexInformation.compareTo(vertex) == 0)
            return true;

         temp = temp.nextVertex;

      }

      return false;

   }

   public boolean addVertex(T vertex) {

      if (!hasVertex(vertex)) {

         Vertex<T, N> temp = head;
         Vertex<T, N> newVertex = new Vertex<>(vertex, null);

         if (head == null) {

            head = newVertex;

         } else {

            Vertex<T, N> previous = head;

            while (temp != null) {

               previous = temp;
               temp = temp.nextVertex;

            }

            previous.nextVertex = newVertex;

         }

         size++;

         return true;

      } else {

         return false;

      }

   }
   
   public int getIndex(T v) {
      Vertex<T,N> temp = head;
      int pos=0;
      while (temp!=null)	{
         if ( temp.vertexInformation.compareTo( v ) == 0 )
            return pos;
         temp=temp.nextVertex;
         pos+=1;
      }
      return -1;
   }
   
   public ArrayList<T> getAllVertexObjects() {
      ArrayList<T> list = new ArrayList<>();
      Vertex<T,N> temp = head;
      while (temp!=null)	{
         list.add(temp.vertexInformation);
         temp=temp.nextVertex;
      }
      return list;
   }

   public ArrayList<Vertex<T,N>> getAllVertices() {
      ArrayList<Vertex<T,N>> list = new ArrayList<>();
      Vertex<T,N> temp = head;
      while (temp!=null)	{
         list.add(temp);
         temp=temp.nextVertex;
      }
      return list;
   }
   
   public T getVertex(int pos) {
      if (pos>size-1 || pos<0) 
         return null;
      Vertex<T,N> temp = head;
      for (int i=0; i<pos; i++)
         temp=temp.nextVertex;
      return temp.vertexInformation;
   }

   public boolean addEdge(T source, T destination, N w)   {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInformation.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,N> destinationVertex = head;
            while (destinationVertex!=null)	{
               if ( destinationVertex.vertexInformation.compareTo( destination ) == 0 )   {
                  // Reached destination vertex, add edge here
                  Edge<T,N> currentEdge = sourceVertex.firstEdge;
                  Edge<T,N> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                  sourceVertex.firstEdge=newEdge;
                  sourceVertex.outDegree++;
                  destinationVertex.inDegree++;
                  return true;
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
   
   public boolean hasEdge(T source, T destination) {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInformation.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now 
            Edge<T,N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInformation.compareTo(destination)==0)
               // destination vertex found 
                  return true;
               currentEdge=currentEdge.nextEdge;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
   
   public N getEdgeWeight(T source, T destination) {
      N notFound=null;
      if (head==null)
         return notFound;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return notFound;
      Vertex<T,N> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInformation.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now 
            Edge<T,N> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInformation.compareTo(destination)==0)
               // destination vertex found 
                  return currentEdge.weight;
               currentEdge=currentEdge.nextEdge;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return notFound;
   }
   
   public ArrayList<T> getNeighbours (T v)  {
      if (!hasVertex(v))
         return null;
      ArrayList<T> list = new ArrayList<T>();
      Vertex<T,N> temp = head;
      while (temp!=null)	{
         if ( temp.vertexInformation.compareTo( v ) == 0 )   {
            // Reached vertex, look for destination now
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
               list.add(currentEdge.toVertex.vertexInformation);
               currentEdge=currentEdge.nextEdge;
            }
         }
         temp=temp.nextVertex;
      }
      return list;   
   }
   
   public void printEdges()   {
      Vertex<T,N> temp=head;
      while (temp!=null) {
         System.out.print("# " + temp.vertexInformation + " : " );
         Edge<T,N> currentEdge = temp.firstEdge;
         while (currentEdge != null) {
            System.out.print("[" + temp.vertexInformation + "," + currentEdge.toVertex.vertexInformation +"] " );
            currentEdge=currentEdge.nextEdge;
         }
         System.out.println();
         temp=temp.nextVertex;
      }  
   }

   public void clear() {

      head = null;

   }

}

