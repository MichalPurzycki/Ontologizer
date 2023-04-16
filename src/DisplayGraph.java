import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.List;

public class DisplayGraph {
    public static void main(String args[]) throws IOException {
        DisplayGraph.DisplayGraph(ReaderOBO.read("gene_test.txt"));
    }

    public static void DisplayGraph(List<ReadOBO> nodes) {
        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = new SingleGraph("GraphOBO"); // polimorfizm


        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.stylesheet",
                "edge { fill-color: green; }");

        for (ReadOBO node : nodes) {

            graph.addNode(node.id);

        }

        for (ReadOBO node : nodes) {

            for (String childID : node.is_a) {

                String edgeID = node.id + childID;
                graph.addEdge(edgeID, node.id, childID);
            }
        }

        graph.display();
    }
}

