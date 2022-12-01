package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the JPanel view for "Portfolio cost basis" operation on a flexible
 * portfolio.
 */
public class JCostBasisView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  // private JTextField dateField;
  private JDateChooser dateChooser;
  private JButton showButton;
  private JButton homeButton;
  private JLabel messageLabel;

  /**
   * Creates an instance of the JCostBasis view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JCostBasisView(String title) {
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

    // Center panel -> Date text field and show composition button
    // this.dateField = new JTextField(8);
    this.dateChooser = new JDateChooser(new Date());
    this.dateChooser.setDateFormatString("yyyy-MM-dd");
    this.showButton = new JButton("SHOW");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 8));
    centerPanel.add(new JLabel("Enter date (yyyy-mm-dd):"));
    centerPanel.add(this.dateChooser);
    centerPanel.add(this.showButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Result
    this.messageLabel = new JLabel("<Message comes here>"); // TODO
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

  @Override
  public void addActionListener(Features features) {
    this.showButton.addActionListener(evt -> {
      String status = features.getCostBasis(
          // dateField.getText()
          dateChooser.getDate().toString()
      );

      messageLabel.setText(status);
      // dateField.setText("");
      dateChooser.setDate(new Date());
    });

    this.homeButton.addActionListener(evt -> {
      this.clearInput();
      features.showHome();
    });
  }

  /**
   * Helper method to set the portfolio name.
   *
   * @param portfolioName the provided portfolio name
   */
  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  @Override
  public void clearInput() {
    this.messageLabel.setText("");
    // dateField.setText("");
    dateChooser.setDate(new Date());
  }
}
