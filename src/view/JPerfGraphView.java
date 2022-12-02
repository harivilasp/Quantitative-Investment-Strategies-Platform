package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the JPanel view for "Performance graph" operation.
 */
public class JPerfGraphView extends JPanel implements PanelView {

  private JLabel portfolioLabel;
  private JDateChooser startDateChooser;
  private JDateChooser endDateChooser;
  private JButton showButton;
  private JButton homeButton;

  /**
   * Creates an instance of the JPerfGraph view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JPerfGraphView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    JLabel titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>");

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Text input fields
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_WEEK, -1);

    this.startDateChooser = new JDateChooser();
    this.startDateChooser.setDateFormatString("yyyy-MM-dd");
    this.startDateChooser.getDateEditor().setEnabled(false);
    this.endDateChooser = new JDateChooser();
    this.endDateChooser.setDateFormatString("yyyy-MM-dd");
    this.endDateChooser.getDateEditor().setEnabled(false);
    this.showButton = new JButton("SHOW");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Start date (yyyy-mm-dd): "));
    centerPanel.add(this.startDateChooser);
    centerPanel.add(new JLabel("End date (yyyy-mm-dd): "));
    centerPanel.add(this.endDateChooser);
    centerPanel.add(this.showButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Home button
    this.homeButton = new JButton("HOME");
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
    southPanel.add(this.homeButton);
    southPanel.add(new JLabel("      "));
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  @Override
  public void addActionListener(Features features) {
    SimpleDateFormat sdf = new SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    );

    showButton.addActionListener(event -> {
      try {
        Map<String, Integer> map = features.getPerformance(
            sdf.format(startDateChooser.getDateEditor().getDate()),
            sdf.format(endDateChooser.getDateEditor().getDate())
        );

        if (map.size() == 1) {
          System.out.println("Invalid Arguments");
        }
        // clearInput();
        PerformanceGraph.drawGraph(map);
      } catch (Exception e) {
        e.getMessage();
      }
    });

    homeButton.addActionListener(event -> {
      clearInput();
      features.showHome();
    });
  }

  @Override
  public void clearInput() {
    this.startDateChooser.setDate(null);
    this.endDateChooser.setDate(null);
  }
}
