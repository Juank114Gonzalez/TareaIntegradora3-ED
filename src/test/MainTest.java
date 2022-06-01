package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.ListGraph;
import model.MatrixGraph;

class MainTest {

	private ListGraph<String> graph;

	void setupStage1l() {
		graph = new ListGraph<>();
		graph.addVertex("A");
	}

	void setupStage2l() {
		graph.addVertex("B");
		graph.addVertex("C");
	}

	void setupStage3l() {
		graph.addEdge("A", "B", 1, false);

	}

	void setupStage4l() {
		graph.addEdge("A", "D", 3, false);
	}

	void setupStage5l() {
		graph = new ListGraph<String>();
		graph.addEdge("2", "1", 4, false);
		graph.addEdge("2", "3", 3, false);
		graph.addEdge("3", "4", 2, false);
		graph.addEdge("1", "3", -2, false);
		graph.addEdge("4", "2", -1, false);
	}

	boolean setupStage6l() {
		return graph.bfs("1");
	}

	boolean setupStage7l() {
		return graph.dfs();
	}

	String setupStage8l() {
		return graph.findVertex("1");
	}

	@Test
	void initGraphL() {
		setupStage1l();
		assertEquals(graph.getListOfVertices().get(0), "A");

		setupStage2l();
		assertEquals(graph.getListOfVertices().get(0), "A");
		assertEquals(graph.getListOfVertices().get(1), "B");
		assertEquals(graph.getListOfVertices().get(2), "C");

		setupStage3l();
		assertEquals(graph.getListOfVertices().get(0), "A");
		assertEquals(graph.getListOfVertices().get(1), "B");
		assertEquals(graph.getListOfVertices().get(2), "C");

		assertEquals(graph.getVertices().get("A").get(0).getVertex(), "B");
		assertEquals(graph.getVertices().get("A").get(0).getWeight(), 1);

		setupStage4l();
		assertEquals(graph.getListOfVertices().get(0), "A");
		assertEquals(graph.getListOfVertices().get(1), "B");
		assertEquals(graph.getListOfVertices().get(2), "C");
		assertEquals(graph.getListOfVertices().get(3), "D");

		assertTrue(graph.getVertices().get("A").get(0).getWeight() == 1
				&& graph.getVertices().get("A").get(0).getVertex() == "B");

		assertTrue(graph.getVertices().get("A").get(1).getWeight() == 3
				&& graph.getVertices().get("A").get(1).getVertex() == "D");
	}

	@Test
	void initGraphL2() {

		setupStage5l();
		assertEquals(graph.getListOfVertices().get(0), "1");
		assertEquals(graph.getListOfVertices().get(1), "2");
		assertEquals(graph.getListOfVertices().get(2), "3");
		assertEquals(graph.getListOfVertices().get(3), "4");

		assertTrue(graph.getVertices().get("2").get(0).getWeight() == 4
				&& graph.getVertices().get("2").get(0).getVertex() == "1");

		assertTrue(graph.getVertices().get("2").get(1).getWeight() == 3
				&& graph.getVertices().get("2").get(1).getVertex() == "3");

		assertTrue(graph.getVertices().get("3").get(0).getWeight() == 2
				&& graph.getVertices().get("3").get(0).getVertex() == "4");

		assertTrue(graph.getVertices().get("1").get(0).getWeight() == -2
				&& graph.getVertices().get("1").get(0).getVertex() == "3");

		assertTrue(graph.getVertices().get("4").get(0).getWeight() == -1
				&& graph.getVertices().get("4").get(0).getVertex() == "2");

		assertTrue(setupStage6l());

		assertTrue(setupStage7l());

		assertEquals(setupStage8l(), "1");
	}
	
	
	
	private MatrixGraph<String> graph2;

	void setupStage1m() {
		graph2 = new MatrixGraph<>(4);
		graph2.addVertex("A");
	}

	void setupStage2m() {
		graph2.addVertex("B");
		graph2.addVertex("C");
	}

	void setupStage3m() {
		graph2.addEdge("A", "B", 1, false);

	}

	void setupStage4m() {
		graph2.addEdge("A", "D", 3, false);
	}

	void setupStage5m() {
		graph2 = new MatrixGraph<String>(4);
		graph2.addEdge("2", "1", 4, false);
		graph2.addEdge("2", "3", 3, false);
		graph2.addEdge("3", "4", 2, false);
		graph2.addEdge("1", "3", -2, false);
		graph2.addEdge("4", "2", -1, false);
	}

	boolean setupStage6m() {
		return graph2.bfs("1");
	}

	boolean setupStage7m() {
		return graph2.dfs();
	}

	String setupStage8m() {
		return graph2.findVertex("1");
	}

	@Test
	void initGraphM() {
		setupStage1m();
		assertEquals(graph2.getListOfVertices().get(0), "A");

		setupStage2m();
		assertEquals(graph2.getListOfVertices().get(0), "A");
		assertEquals(graph2.getListOfVertices().get(1), "B");
		assertEquals(graph2.getListOfVertices().get(2), "C");

		setupStage3m();
		assertEquals(graph2.getListOfVertices().get(0), "A");
		assertEquals(graph2.getListOfVertices().get(1), "B");
		assertEquals(graph2.getListOfVertices().get(2), "C");

		assertTrue(graph2.hasEdge("A", "B", 1));

		setupStage4m();
		assertEquals(graph2.getListOfVertices().get(0), "A");
		assertEquals(graph2.getListOfVertices().get(1), "B");
		assertEquals(graph2.getListOfVertices().get(2), "C");
		assertEquals(graph2.getListOfVertices().get(3), "D");

		assertTrue(graph2.hasEdge("A", "B", 1));

		assertTrue(graph2.hasEdge("A", "D", 3));
	}

	@Test
	void initGraphM2() {

		setupStage5m();
		assertEquals(graph2.getListOfVertices().get(0), "1");
		assertEquals(graph2.getListOfVertices().get(1), "2");
		assertEquals(graph2.getListOfVertices().get(2), "3");
		assertEquals(graph2.getListOfVertices().get(3), "4");

		assertTrue(graph2.hasEdge("2", "1", 4));

		assertTrue(graph2.hasEdge("2", "3", 3));

		assertTrue(graph2.hasEdge("3", "4", 2));

		assertTrue(graph2.hasEdge("1", "2",-2));

		assertTrue(graph2.hasEdge("4", "2",-1));

		assertTrue(setupStage6m());

		assertTrue(setupStage7m());

		assertEquals(setupStage8m(), "1");
	}

}
