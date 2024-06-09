package net.fitriandfriends.egringotts.utility;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class VertexDistancePair<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<VertexDistancePair<T, N>> {

    // Instance variables
    private T vertex;
    private N distance;

    // Constructor
    public VertexDistancePair(T vertex, N distance) {

        this.vertex = vertex;
        this.distance = distance;

    }

    @Override
    public int compareTo(VertexDistancePair<T, N> otherVertexDistancePair) {

        return this.distance.compareTo(otherVertexDistancePair.distance);

    }

}