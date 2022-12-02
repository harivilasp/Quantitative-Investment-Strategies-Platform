package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.Utils;

/**
 * This class represents the JPanel view for "Portfolio strategy creation" operation on flexible
 * portfolios.
 */
public class JStrategyView extends JPanel implements PanelView {

  private JLabel portfolioLabel;
  private JTextField amountField;
  private JTextField intervalField;
  private JDateChooser startDateChooser;
  private JDateChooser endDateChooser;
  private JTextField commissionField;
  private JButton addButton;
  private JButton homeButton;
  private JLabel messageLabel;
  private JButton submitButton;
  private JComboBox<String> comboBox;
  private JTextField weightageField;

  private final Map<String, Double> weightsMap;

  /**
   * Creates an instance of the JStrategy view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JStrategyView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    this.weightsMap = new HashMap<>();

    // North panel -> Title
    JLabel titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("No portfolio Selected");

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Text input fields
    this.amountField = new JTextField("0.0", 6);
    this.intervalField = new JTextField("0", 4);
    this.startDateChooser = new JDateChooser();
    this.startDateChooser.setDateFormatString("yyyy-MM-dd");
    this.endDateChooser = new JDateChooser();
    this.endDateChooser.setDateFormatString("yyyy-MM-dd");
    this.commissionField = new JTextField("0.0", 6);

    // Disable start date editor
    this.startDateChooser.getDateEditor().setEnabled(false);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Amount (in USD): "));
    centerPanel.add(this.amountField);
    centerPanel.add(new JLabel("Interval (in days): "));
    centerPanel.add(this.intervalField);
    centerPanel.add(new JLabel("Start date (yyyy-mm-dd): "));
    centerPanel.add(this.startDateChooser);
    centerPanel.add(new JLabel("End date (yyyy-mm-dd): "));
    centerPanel.add(this.endDateChooser);
    centerPanel.add(new JLabel("Commission (in USD): "));
    centerPanel.add(this.commissionField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Stock weightage addition
    // ComboBox panel
    JPanel boxPanel = new JPanel();
    boxPanel.setBorder(BorderFactory.createTitledBorder("Stock Weightage (in %)"));
    boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));

    String[] options = new String[Utils.VALID_STOCKS.size()];
    options = Utils.VALID_STOCKS.toArray(options);
    Arrays.sort(options);

    this.comboBox = new JComboBox<>();
    this.weightageField = new JTextField("0.0", 8);
    this.comboBox.addItem("--none--");
    for (String option : options) {
      this.comboBox.addItem(option);
    }
    boxPanel.add(this.comboBox);
    boxPanel.add(this.weightageField);

    this.addButton = new JButton("ADD");
    JPanel sectionPanel = new JPanel();
    sectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 16));
    sectionPanel.add(boxPanel);
    sectionPanel.add(this.addButton);

    this.messageLabel = new JLabel("");
    this.messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.submitButton = new JButton("SUBMIT");
    this.submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.homeButton = new JButton("HOME");
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
    southPanel.add(sectionPanel);
    southPanel.add(this.messageLabel);
    southPanel.add(new JLabel("      "));
    southPanel.add(this.submitButton);
    southPanel.add(new JLabel("      "));
    southPanel.add(new JLabel("      "));
    southPanel.add(new JLabel("      "));
    southPanel.add(this.homeButton);

    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addActionListener(Features features) {
    SimpleDateFormat sdf = new SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    );

    addButton.addActionListener(event -> {
      // Reset the message
      this.messageLabel.setText("");
      // Get the selected stock name
      String stockName = (String) this.comboBox.getSelectedItem();
      // Parse stock name
      if (stockName.equals("--none--")) {
        this.messageLabel.setText("ERROR: Please select a stock!");
      }
      // Get the weightage quantity
      double weightage;

      // Parse weightage text
      try {
        weightage = Double.parseDouble(this.weightageField.getText());
        // Invalid weightage
        if (weightage < 0 || weightage > 100) {
          throw new NumberFormatException();
        }

        weightsMap.put(stockName, weightsMap.getOrDefault(stockName, 0.0) + weightage);
        messageLabel.setText("Added");
      } catch (NumberFormatException nfe) {
        this.messageLabel.setText("ERROR: Invalid weightage!");
      }
    });

    submitButton.addActionListener(event -> {
      // Verify end date
      String endDate = "2200-12-01";  // default
      if (endDateChooser.getDateEditor().getDate() != null) {
        endDate = sdf.format(endDateChooser.getDateEditor().getDate());
      }

      try {
        messageLabel.setText("Adding");
        String status = features.addStrategy(
            Double.parseDouble(this.amountField.getText()),
            Integer.parseInt(intervalField.getText()),
            sdf.format(startDateChooser.getDateEditor().getDate()),
            endDate,
            Double.parseDouble(commissionField.getText()),
            weightsMap
        );

        messageLabel.setText(status);
      } catch (Exception e) {
        messageLabel.setText(e.getMessage());
      }
    });

    homeButton.addActionListener(event -> {
      clearInput();
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
    this.comboBox.setSelectedItem("--none--");
    this.weightageField.setText("0.0");
    this.amountField.setText("0.0");
    this.intervalField.setText("1");
    this.startDateChooser.setDate(null);
    this.endDateChooser.setDate(null);
    this.commissionField.setText("0.0");
  }
}
