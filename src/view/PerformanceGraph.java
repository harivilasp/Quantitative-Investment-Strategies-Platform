package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

import javax.swing.*;

public class PerformanceGraph extends JFrame {

  private static final long serialVersionUID = 1L;

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

  private DefaultCategoryDataset createDataset(Map<String, Integer> map, String scale) {
    String series1 = scale;

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      dataset.addValue(entry.getValue(), series1, entry.getKey());
    }

    return dataset;
  }

  public static void DrawGraph(Map<String, Integer> map) {
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
}
