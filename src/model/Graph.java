package model;

public abstract class Graph<T> implements GraphInterface<T>{

	public abstract void addEdge(T source, T destination, double weigth, boolean b);

	public abstract T findVertex(T vertex);
	
	public abstract void addVertex(T newVertex);
	
}