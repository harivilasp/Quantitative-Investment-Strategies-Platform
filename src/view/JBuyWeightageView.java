package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
 * This class represents the JPanel view for "Buying stocks with weightage" operation on a flexible
 * portfolio.
 */
public class JBuyWeightageView extends JPanel implements PanelView {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField amountField;
  //  private JTextField dateField;
  private JDateChooser dateChooser;
  private JTextField commissionField;
  private JButton addButton;
  private JButton homeButton;
  private JLabel messageLabel;
  private JButton submitButton;
  private JComboBox<String> comboBox;
  private JTextField weightageField;

  private final Map<String, Double> weightsMap;

  /**
   * Creates an instance of the JBuyWeightage view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JBuyWeightageView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    this.weightsMap = new HashMap<>();

    // North panel -> Title
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("No Portfolio Selected");

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Text input fields
    this.amountField = new JTextField("0.0", 6);
    this.dateChooser = new JDateChooser(new Date(), "yyyy-MM-dd");
//    this.dateField = new JTextField(8);
    this.commissionField = new JTextField("0.0", 6);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Amount: "));
    centerPanel.add(this.amountField);
    centerPanel.add(new JLabel("Date (yyyy-mm-dd): "));
    centerPanel.add(this.dateChooser);
    centerPanel.add(new JLabel("Commission: "));
    centerPanel.add(this.commissionField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Stock weightage addition
    // ComboBox panel
    JPanel boxPanel = new JPanel();
    boxPanel.setBorder(BorderFactory.createTitledBorder("Stock Weightage"));
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

    this.messageLabel = new JLabel("<Message comes here>");
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
      } catch (NumberFormatException nfe) {
        this.messageLabel.setText("ERROR: Invalid weightage!");
      }
    });
    submitButton.addActionListener(event -> {
      try {
        String status = features.buyStocksWithWeights(
            Double.parseDouble(this.amountField.getText()),
            dateChooser.getDate().toString(),
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

  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  @Override
  public void clearInput() {
    this.comboBox.setSelectedItem("--none--");
    this.weightageField.setText("");
    this.amountField.setText("");
    this.dateChooser.setDate(new Date());
    this.commissionField.setText("");
  }
}
