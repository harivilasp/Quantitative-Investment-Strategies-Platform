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
 * This class represents the JPanel view for "Portfolio value at date" operation on flexible
 * portfolios.
 */
public class JValueAtDateView extends JPanel implements PanelView {

  private JButton homeButton;
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
    JLabel titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>");

    JPanel northPanel = new JPanel();
    northPanel.add(titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Enter date and submit
    this.dateChooser = new JDateChooser();
    this.dateChooser.setDateFormatString("yyyy-MM-dd");
    this.dateChooser.getDateEditor().setEnabled(false);
    this.actionButton = new JButton("Get Value");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    centerPanel.add(new JLabel("Enter date (yyyy-mm-dd): "));
    centerPanel.add(this.dateChooser);
    centerPanel.add(new JLabel("    "));
    centerPanel.add(this.actionButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Show result
    this.messageLabel = new JLabel("");
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
    SimpleDateFormat sdf = new SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    );

    this.actionButton.addActionListener(event -> {
      String response = features.getValue(
          sdf.format(dateChooser.getDateEditor().getDate())
      );

      messageLabel.setText(String.valueOf(response));
      dateChooser.setDate(new Date());
    });

    this.homeButton.addActionListener(event -> {
      this.clearInput();
      features.showHome();
    });
  }

  @Override
  public void clearInput() {
    this.dateChooser.setDate(null);
    this.messageLabel.setText("");
  }
}
