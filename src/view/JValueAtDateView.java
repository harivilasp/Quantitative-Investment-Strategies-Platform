package view;

import com.toedter.calendar.JDateChooser;
import java.awt.*;

import java.util.Date;
import javax.swing.*;

import controller.Features;

/**
 * This class represents the JPanel view for "Portfolio value at date" operation on flexible
 * portfolios.
 */
public class JValueAtDateView extends JPanel implements PanelView {

  private JButton homeButton;
  private JLabel titleLabel;
  // private JTextField dateField;
  private JDateChooser dateChooser;
  private JButton actionButton;
  private JLabel messageLabel;
  private JLabel portfolioLabel;

  /**
   * Creates an instance of the JValueAtDate view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JValueAtDateView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>");

    JPanel northPanel = new JPanel();
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Enter date and submit
    // this.dateField = new JTextField(10);
    this.dateChooser = new JDateChooser(new Date());
    this.dateChooser.setDateFormatString("yyyy-MM-dd");
    this.actionButton = new JButton("Get Value");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    centerPanel.add(new JLabel("Enter date (yyyy-mm-dd): "));
    centerPanel.add(this.dateChooser);
    centerPanel.add(new JLabel("    "));
    centerPanel.add(this.actionButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Show result
    this.messageLabel = new JLabel("Show result");
    this.homeButton = new JButton("HOME");

    // Alignments
    this.messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    // BoxLayout for the message and home button
    JPanel southBox = new JPanel();
    southBox.setLayout(new BoxLayout(southBox, BoxLayout.PAGE_AXIS));
    southBox.add(this.messageLabel);
    southBox.add(new JLabel(" "));
    southBox.add(new JLabel(" "));
    southBox.add(this.homeButton);

    JPanel southPanel = new JPanel();
    southPanel.add(southBox);
    this.add(southPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  public void setPortfolioName(String portfolioName) {
    this.portfolioLabel.setText(portfolioName);
  }

  @Override
  public void addActionListener(Features features) {
    this.actionButton.addActionListener(event -> {
      String response = features.getValue(
          // dateField.getText()
          dateChooser.getDate().toString()
      );

      messageLabel.setText(String.valueOf(response));
      // dateField.setText("");
      dateChooser.setDate(new Date());
    });

    this.homeButton.addActionListener(event -> {
      this.clearInput();
      features.showHome();
    });
  }

  @Override
  public void clearInput() {
    this.dateChooser.setDate(new Date());
    this.messageLabel.setText("");
  }
}
