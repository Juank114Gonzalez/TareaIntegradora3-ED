package model;

import java.util.HashMap;
import java.util.List;

/**
 * Graph interface with the seen methods in class
 * 
 * @author Gabriel Esteban Restrepo Giraldo
 * @author Juan Camilo González Torres
 */
public interface GraphInterface<T> {

	/*
	 * ----------Graph implementation methods----------
	 */

	public boolean bfs(T s);

	public boolean dfs();

	public void dfsVisit(T u);

	public HashMap<T, Integer> dijkstra(T src);

	public void floydWarshall();

	public void kruskal();

	public void prim();

	public void addVertex(T newVertex);

	public void addEdge(T source, T destination, double weight, boolean bidirectional);
	
	public T findVertex(T vertex);

	public List<T> getListOfVertices();
	
	public List<T> getAdjacency(T source);

}
