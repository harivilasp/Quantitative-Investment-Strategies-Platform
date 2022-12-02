package view;

import java.util.Map;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This class represents a frame that displays the performance metrics of the stocks in a portfolio
 * between certain dates.
 */
public class PerformanceGraph extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * Creates an instance of the performance graph frame.
   *
   * @param title the title of the frame
   * @param map   the hash map of values
   * @param scale the scale of the graph
   */
  public PerformanceGraph(String title, Map<String, Integer> map, String scale) {
    super(title);
    // Create dataset  
    DefaultCategoryDataset dataset = createDataset(map, scale);
    // Create chart  
    JFreeChart chart = ChartFactory.createLineChart(
        "Portfolio Performance", // Chart title
        "Date", // X-Axis Label
        "Relative Performance", // Y-Axis Label
        dataset
    );

    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  /**
   * This method draws a graph on the frame using the value set of dates and their respective
   * performance.
   *
   * @param map the map of graph values
   */
  public static void drawGraph(Map<String, Integer> map) {
    SwingUtilities.invokeLater(() -> {
      String scale = new String();
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        if (entry.getKey().contains("Scale")) {
          scale = "Base " + entry.getKey() + " Unit Scale: " + entry.getValue();
          map.remove(entry.getKey());
          break;
        }
      }

      PerformanceGraph performanceGraph = new PerformanceGraph("Line Chart", map, scale);
      performanceGraph.setAlwaysOnTop(true);
      performanceGraph.pack();
      performanceGraph.setSize(850, 600);
      performanceGraph.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      performanceGraph.setVisible(true);
    });
  }

  /* Helper method to create a dataset */
  private DefaultCategoryDataset createDataset(Map<String, Integer> map, String scale) {
    String series1 = scale;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      dataset.addValue(entry.getValue(), series1, entry.getKey());
    }

    return dataset;
  }
}
