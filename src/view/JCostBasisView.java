package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the JPanel view for "Portfolio cost basis" operation on a flexible
 * portfolio.
 */
public class JCostBasisView extends JPanel implements PanelView {

  private JLabel portfolioLabel;
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
    JLabel titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>");

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Date text field and show composition button
    this.dateChooser = new JDateChooser(new Date(), "yyyy-MM-dd");
    this.dateChooser.getDateEditor().setEnabled(false);
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
    SimpleDateFormat sdf = new SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    );

    this.showButton.addActionListener(evt -> {
      String status = features.getCostBasis(
          sdf.format(dateChooser.getDateEditor().getDate())
      );

      messageLabel.setText(status);
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
    this.dateChooser.setDate(new Date());
  }
}
