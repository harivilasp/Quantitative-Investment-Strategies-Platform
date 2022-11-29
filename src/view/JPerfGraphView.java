package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class represents the JPanel view for "Performance graph" operation.
 */
public class JPerfGraphView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField startDateField;
  private JTextField endDateField;
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
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>"); // TODO

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Text input fields
    this.startDateField = new JTextField(10);
    this.endDateField = new JTextField(10);
    this.showButton = new JButton("SHOW");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Start date (yyyy-mm-dd): "));
    centerPanel.add(this.startDateField);
    centerPanel.add(new JLabel("End date (yyyy-mm-dd): "));
    centerPanel.add(this.endDateField);
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

  @Override
  public void addActionListener(Features features) {
    // TODO: Complete implementation
  }

  @Override
  public void clearInput() {
    // TODO: Complete implementation
  }
}
