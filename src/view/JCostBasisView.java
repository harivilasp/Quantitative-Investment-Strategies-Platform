package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * This class represents the JPanel view for "Portfolio cost basis" operation on a flexible
 * portfolio.
 */
public class JCostBasisView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField dateField;
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
    this.portfolioLabel = new JLabel("<Portfolio Name>");

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Date text field and show composition button
    this.dateField = new JTextField(8);
    this.showButton = new JButton("SHOW");
    this.homeButton = new JButton("HOME");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 8));
    centerPanel.add(new JLabel("Date:"));
    centerPanel.add(this.dateField);
    centerPanel.add(this.showButton);
    centerPanel.add(this.homeButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Result
    this.messageLabel = new JLabel("<Message comes here>");

    JPanel southPanel = new JPanel();
    southPanel.add(this.messageLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addActionListener(Features features) {
    this.showButton.addActionListener(evt -> {
      String status = features.getCostBasis(dateField.getText());
      messageLabel.setText(status);
      dateField.setText("");
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
    this.dateField.setText("");
  }
}
