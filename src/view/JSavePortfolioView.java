package view;

import controller.Features;
import java.awt.BorderLayout;
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

    // North panel -> title
    this.titleLabel = new JLabel(title);

    JPanel northPanel = new JPanel();
    northPanel.add(this.titleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Whether to save the portfolio
    JPanel boxPanel = new JPanel();
    boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
    boxPanel.add(new JLabel("Would you like to save " + this.portfolioName + "?"));

    this.posButton = new JButton("YES");
    this.negButton = new JButton("NO");
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 8));
    buttonPanel.add(this.posButton);
    buttonPanel.add(this.negButton);
    boxPanel.add(buttonPanel);

    JPanel centerPanel = new JPanel();
    centerPanel.add(boxPanel);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Home button
    this.homeButton = new JButton("HOME");
    this.messageLabel = new JLabel("<Message comes here>");
    JPanel southPanel = new JPanel();
    southPanel.add(this.homeButton);
    southPanel.add(this.messageLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addActionListener(Features features) {
    this.negButton.addActionListener(event -> {
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
