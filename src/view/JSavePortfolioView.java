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

/**
 * This class represents the JPanel view for "Portfolio save" operation on flexible portfolios.
 */
public class JSavePortfolioView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel messageLabel;
  private JButton posButton;
  private JButton negButton;
  private JButton homeButton;
  private String portfolioName;

  /**
   * Creates an instance of the JSavePortfolio view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JSavePortfolioView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));
    portfolioName = new String();
    // North panel -> title
    this.titleLabel = new JLabel(title);

    JPanel northPanel = new JPanel();
    northPanel.add(this.titleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Whether to save the portfolio
    JLabel headerLabel = new JLabel("Would you like to save " + this.portfolioName + "?");
    headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel centerBox = new JPanel();
    centerBox.setLayout(new BoxLayout(centerBox, BoxLayout.PAGE_AXIS));
    centerBox.add(headerLabel);

    // Positive and Negative buttons
    this.posButton = new JButton("YES");
    this.negButton = new JButton("NO");

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.add(this.posButton);
    buttonPanel.add(this.negButton);
    centerBox.add(buttonPanel);

    JPanel centerPanel = new JPanel();
    centerPanel.add(centerBox);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Home button
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
    this.negButton.addActionListener(event -> {
      features.showHome();
    });
    this.homeButton.addActionListener(event -> {
      features.showHome();
    });
    this.posButton.addActionListener(evt -> {
      String status = features.save();
      this.messageLabel.setText(status);
    });
  }

  public void setPortfolioName(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  @Override
  public void clearInput() {
    this.messageLabel.setText("");
  }
}
