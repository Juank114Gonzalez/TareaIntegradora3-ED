package test;

/**
 * This stage simulate
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import generics.Graph;
import generics.Vertex;

class MainTest {

	private Graph<String> graph1;

	private Graph<String> graph2;

	void setupStage1() {
		graph1 = new Graph<>("A");
	}

	void setupStage2() {
		graph1.addVertex("B");
		graph1.addVertex("C");
	}

	void setupStage3() {
		graph1.newEdge("A", "B", 1);

	}

	void setupStage4() {
		graph1.newEdge("A", "D", 3);
	}

	void setupStage5() {
		graph2 = new Graph<String>("1");
		graph2.newEdge("2", "1", 4);
		graph2.newEdge("2", "3", 3);
		graph2.newEdge("3", "4", 2);
		graph2.newEdge("1", "3", -2);
		graph2.newEdge("4", "2", -1);
	}
	
	boolean setupStage6() {
		return graph2.BFS("1");
	}
	
	boolean setupStage7() {
		return graph2.DFS();
	}
	
	Vertex<String> setupStage8() {
		return graph2.search("1");
	}

	@Test
	void initGraph1() {
		setupStage1();
		assertEquals(graph1.getVertices().get(0).getValue(), "A");
		setupStage2();
		assertEquals(graph1.getVertices().get(0).getValue(), "A");
		assertEquals(graph1.getVertices().get(1).getValue(), "B");
		assertEquals(graph1.getVertices().get(2).getValue(), "C");
		setupStage3();
		assertEquals(graph1.getVertices().get(0).getValue(), "A");
		assertEquals(graph1.getVertices().get(1).getValue(), "B");
		assertEquals(graph1.getVertices().get(2).getValue(), "C");
		assertEquals(graph1.getEdges().get(0).getInputVertex().getValue(), "A");
		assertEquals(graph1.getEdges().get(0).getOutputVertex().getValue(), "B");
		setupStage4();
		assertEquals(graph1.getVertices().get(0).getValue(), "A");
		assertEquals(graph1.getVertices().get(1).getValue(), "B");
		assertEquals(graph1.getVertices().get(2).getValue(), "C");
		assertEquals(graph1.getVertices().get(3).getValue(), "D");
		assertEquals(graph1.getEdges().get(0).getInputVertex().getValue(), "A");
		assertEquals(graph1.getEdges().get(0).getOutputVertex().getValue(), "B");
		assertEquals(graph1.getEdges().get(0).getWeight(), 1);
		assertEquals(graph1.getEdges().get(1).getInputVertex().getValue(), "A");
		assertEquals(graph1.getEdges().get(1).getOutputVertex().getValue(), "D");
		assertEquals(graph1.getEdges().get(1).getWeight(), 3);
	}
	
	

	@Test
	void initGraph2() {
		
		setupStage5();
		assertEquals(graph2.getVertices().get(0).getValue(), "1");
		assertEquals(graph2.getVertices().get(1).getValue(), "2");
		assertEquals(graph2.getVertices().get(2).getValue(), "3");
		assertEquals(graph2.getVertices().get(3).getValue(), "4");
		assertEquals(graph2.getEdges().get(0).getInputVertex().getValue(), "2");
		assertEquals(graph2.getEdges().get(0).getOutputVertex().getValue(), "1");
		assertEquals(graph2.getEdges().get(0).getWeight(), 4);
		assertEquals(graph2.getEdges().get(1).getInputVertex().getValue(), "2");
		assertEquals(graph2.getEdges().get(1).getOutputVertex().getValue(), "3");
		assertEquals(graph2.getEdges().get(1).getWeight(), 3);
		assertEquals(graph2.getEdges().get(2).getInputVertex().getValue(), "3");
		assertEquals(graph2.getEdges().get(2).getOutputVertex().getValue(), "4");
		assertEquals(graph2.getEdges().get(2).getWeight(), 2);
		assertEquals(graph2.getEdges().get(3).getInputVertex().getValue(), "1");
		assertEquals(graph2.getEdges().get(3).getOutputVertex().getValue(), "3");
		assertEquals(graph2.getEdges().get(3).getWeight(), -2);
		assertEquals(graph2.getEdges().get(4).getInputVertex().getValue(), "4");
		assertEquals(graph2.getEdges().get(4).getOutputVertex().getValue(), "2");
		assertEquals(graph2.getEdges().get(4).getWeight(), -1);
		//assertTrue(setupStage6());
		//No sirven por ahora
		//assertTrue(setupStage7());
		assertEquals(setupStage8().getValue(), "1");
	}

}
