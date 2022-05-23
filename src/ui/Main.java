package ui;

import generics.Graph;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<String> graph1 = new Graph<String>("1");

		System.out.println("Ejemplo");


		// Creamos las aristas
		
		graph1.newEdge("2", "1", 4);
		graph1.newEdge("2", "3", 3);
		graph1.newEdge("3", "4", 2);
		graph1.newEdge("1", "3", -2);
		graph1.newEdge("4", "2", -1);

		if(graph1.BFS("1")) {
			System.out.println("Conexo");
		}else {
			System.out.println("No conexo");
		}
		
		Graph<String> graph2 = new Graph<String>("1");

		
		
		System.out.println("Ejercicio 1");

		// Creamos las aristas
		graph2.newEdge("1", "2", 5);
		graph2.newEdge("1", "3", 9);
		graph2.newEdge("2", "3", 1);
		graph2.newEdge("3", "4", 2);
		graph2.newEdge("4", "2", 3);

		if(graph1.DFS()) {
			System.out.println("Conexo");
		}else {
			System.out.println("No conexo");
		}
		Graph<String> graph3 = new Graph<String>("A");
		

		System.out.println("Ejercicio 2");

		// Creamos las aristas
		graph3.newEdge("E", "A", 7);
		graph3.newEdge("A", "D", 60);
		graph3.newEdge("A", "C", 12);
		graph3.newEdge("C", "D", 32);
		graph3.newEdge("B", "A", 10);
		graph3.newEdge("C", "B", 20);

		graph3.floydWarshall();

	}

}
