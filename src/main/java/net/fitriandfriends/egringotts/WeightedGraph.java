package net.fitriandfriends.egringotts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

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
   
   public int getIndex(T vertex) {

      Vertex<T, N> temp = head;

      int position = 0;

      while (temp != null) {

         if (temp.vertexInformation.compareTo(vertex) == 0)
            return position;

         temp = temp.nextVertex;
         position += 1;

      }

      return -1;

   }
   
   public List<T> getAllVertexObjects() {

      List<T> list = new ArrayList<>();
      Vertex<T, N> temp = head;

      while (temp != null) {

         list.add(temp.vertexInformation);

         temp = temp.nextVertex;

      }

      return list;

   }

   public List<Vertex<T, N>> getAllVertices() {

      List<Vertex<T,N>> list = new ArrayList<>();

      Vertex<T, N> temp = head;

      while (temp != null) {

         list.add(temp);

         temp = temp.nextVertex;

      }

      return list;

   }
   
   public T getVertex(int position) {

      if (position > size - 1 || position < 0)
         return null;

      Vertex<T, N> temp = head;

      for (int i = 0; i < position; i++)
         temp = temp.nextVertex;

      return temp.vertexInformation;

   }

   public T getVertex(T vertex) {

      if (!hasVertex(vertex))
         return null;

      Vertex<T, N> temp = head;

      for (int i = 0; i < size; i++) {

         if (temp.vertexInformation.compareTo(vertex) == 0)
            return temp.vertexInformation;

         temp = temp.nextVertex;

      }

      return null;

   }

   public boolean addEdge(T source, T destination, N weight) {

      if (head == null)
         return false;

      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;

      Vertex<T, N> sourceVertex = head;

      while (sourceVertex != null) {

         if (sourceVertex.vertexInformation.compareTo(source) == 0) {

            // Reached source vertex, look for destination now
            Vertex<T, N> destinationVertex = head;

            while (destinationVertex != null) {

               if (destinationVertex.vertexInformation.compareTo(destination) == 0) {

                  // Reached destination vertex, add edge here
                  Edge<T, N> currentEdge = sourceVertex.firstEdge;

                  sourceVertex.firstEdge = new Edge<>(destinationVertex, weight, currentEdge);
                  sourceVertex.outDegree++;
                  destinationVertex.inDegree++;

                  return true;

               }

               destinationVertex = destinationVertex.nextVertex;

            }

         }

         sourceVertex = sourceVertex.nextVertex;

      }

      return false;

   }
   
   public boolean hasEdge(T source, T destination) {

      if (head == null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;

      Vertex<T, N> sourceVertex = head;

      while (sourceVertex != null) {

         if (sourceVertex.vertexInformation.compareTo(source) == 0) {

            // Reached source vertex, look for destination now
            Edge<T, N> currentEdge = sourceVertex.firstEdge;

            while (currentEdge != null) {

               if (currentEdge.toVertex.vertexInformation.compareTo(destination) == 0)
               // destination vertex found 
                  return true;

               currentEdge = currentEdge.nextEdge;

            }

         }

         sourceVertex = sourceVertex.nextVertex;

      }

      return false;

   }
   
   public N getEdgeWeight(T source, T destination) {

      N notFound = null;

      if (head == null)
         return notFound;

      if (!hasVertex(source) || !hasVertex(destination)) 
         return notFound;

      Vertex<T, N> sourceVertex = head;

      while (sourceVertex != null) {

         if (sourceVertex.vertexInformation.compareTo(source) == 0) {

            // Reached source vertex, look for destination now 
            Edge<T, N> currentEdge = sourceVertex.firstEdge;

            while (currentEdge != null) {

               if (currentEdge.toVertex.vertexInformation.compareTo(destination) == 0)
               // destination vertex found 
                  return currentEdge.weight;

               currentEdge = currentEdge.nextEdge;

            }

         }

         sourceVertex = sourceVertex.nextVertex;

      }

      return notFound;

   }
   
   public List<T> getNeighbours(T vertex) {

      if (!hasVertex(vertex))
         return null;

      List<T> list = new ArrayList<>();

      Vertex<T, N> temp = head;

      while (temp != null) {

         if (temp.vertexInformation.compareTo(vertex) == 0) {

            // Reached vertex, look for destination now
            Edge<T, N> currentEdge = temp.firstEdge;

            while (currentEdge != null) {

               list.add(currentEdge.toVertex.vertexInformation);

               currentEdge = currentEdge.nextEdge;

            }

         }

         temp = temp.nextVertex;

      }

      return list;

   }
   
   public void printEdges() {

      Vertex<T, N> temp = head;

      while (temp != null) {

         System.out.print("# " + temp.vertexInformation + " : " );

         Edge<T, N> currentEdge = temp.firstEdge;

         while (currentEdge != null) {

            System.out.print("[" + temp.vertexInformation + ", " + currentEdge.toVertex.vertexInformation +"] ");

            currentEdge = currentEdge.nextEdge;

         }

         System.out.println();

         temp = temp.nextVertex;

      }

   }

   public void clear() {

      head = null;

   }

   // Implementation of Dijkstra's algorithm to find the shortest path between two vertices (currencies)
   public Map<T, N> shortestPath(T startVertex) {

      Map<T, N> distances = new HashMap<>();
      PriorityQueue<VertexDistancePair<T, N>> pq = new PriorityQueue<>();

      for (Vertex<T, N> vertex : getAllVertices()) {

         // Null here represents a distance of infinity
         distances.put(vertex.vertexInformation, null);

      }

      distances.put(startVertex, (N) (Double) 0.0);

      pq.add(new VertexDistancePair<>(startVertex, (N) (Double) 0.0));

      while (!pq.isEmpty()) {

         VertexDistancePair<T, N> current = pq.poll();

         T currentVertex = current.getVertex();
         N currentDistance = current.getDistance();

         for (T neighbor : getNeighbours(currentVertex)) {

            N edgeWeight = getEdgeWeight(currentVertex, neighbor);
            N newDist = (N) (Double) ((Double) currentDistance + (Double) edgeWeight);

            if (distances.get(neighbor) == null || (Double) newDist < (Double) distances.get(neighbor)) {

               distances.put(neighbor, newDist);
               pq.add(new VertexDistancePair<>(neighbor, newDist));

            }

         }

      }

      return distances;

   }

}