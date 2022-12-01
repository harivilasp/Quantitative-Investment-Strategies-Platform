package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.Utils;

/**
 * This class represents the JPanel view for "Portfolio buy/sell stock" operation on flexible
 * portfolios.
 */
public class JTransactionView extends JPanel implements PanelView {

  private JButton homeButton;
  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JComboBox<String> nameComboBox;
  private JTextField quantityField;
  private JDateChooser dateChooser;
  private JTextField commField;
  private JButton buyButton;
  private JButton sellButton;
  private JLabel messageLabel;

  /**
   * Creates an instance of the JTransaction view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JTransactionView(String title) {
    setSize(500, 500);
    setLocation(200, 100);
    setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>"); // TODO
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> User input
    // ComboBox for Stock names
    String[] options = new String[Utils.VALID_STOCKS.size()];
    options = Utils.VALID_STOCKS.toArray(options);
    Arrays.sort(options);

    this.nameComboBox = new JComboBox<>();
    this.nameComboBox.addItem("--none--");
    for (String option : options) {
      this.nameComboBox.addItem(option);
    }

    // Other fields
    this.quantityField = new JTextField(6);
    this.dateChooser = new JDateChooser(new Date());
    this.commField = new JTextField(6);

    // Default values and formats
    this.quantityField.setText("1");
    this.dateChooser.setDateFormatString("yyyy-MM-dd");
    this.commField.setText("0.0");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 0));
    centerPanel.add(new JLabel("Name: "));
    centerPanel.add(this.nameComboBox);
    centerPanel.add(new JLabel("Quantity: "));
    centerPanel.add(this.quantityField);
    centerPanel.add(new JLabel("Transaction date (yyyy-mm-dd): "));
    centerPanel.add(this.dateChooser);
    centerPanel.add(new JLabel("Commission: "));
    centerPanel.add(this.commField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Buy/Sell buttons
    this.buyButton = new JButton("BUY");
    this.sellButton = new JButton("SELL");
    this.homeButton = new JButton("HOME");
    this.messageLabel = new JLabel("<Message comes here>");

    // Set alignment
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // BoxLayout for south panel
    JPanel southBox = new JPanel();
    southBox.setLayout(new BoxLayout(southBox, BoxLayout.PAGE_AXIS));

    // Flow layout for buy and sell buttons
    JPanel buttonFlow = new JPanel();
    buttonFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 64, 16));
    buttonFlow.add(this.buyButton);
    buttonFlow.add(this.sellButton);

    // Arrange in BoxLayout
    southBox.add(this.messageLabel);
    southBox.add(new JLabel(" "));
    southBox.add(new JLabel(" "));
    southBox.add(buttonFlow);
    southBox.add(new JLabel(" "));
    southBox.add(new JLabel(" "));
    southBox.add(new JLabel(" "));
    southBox.add(this.homeButton);

    JPanel southPanel = new JPanel();
    southPanel.add(southBox);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  public void setEchoOutput(String s) {
    // TODO: Complete implementation
  }

  public String getInput() {
    return null;
  }

  @Override
  public void clearInput() {
    this.messageLabel.setText("");
    this.nameComboBox.setSelectedItem("--none--");
    this.quantityField.setText("");
    this.dateChooser.setDate(new Date());
    this.commField.setText("");
  }

  @Override
  public void addActionListener(Features features) {
    buyButton.addActionListener(evt -> {
      try {
        String status = features.buyStock(
            (String) this.nameComboBox.getSelectedItem(),
            Integer.parseInt(quantityField.getText()),
            dateChooser.getDate().toString(),
            Double.parseDouble(commField.getText())
        );

        messageLabel.setText(status);
        this.nameComboBox.setSelectedItem("--none--");
        dateChooser.setDate(new Date());
      } catch (Exception e) {
        messageLabel.setText(e.getMessage());
      }
    });

    sellButton.addActionListener(evt -> {
      try {
        String status = features.sellStock(
            (String) this.nameComboBox.getSelectedItem(),
            Integer.parseInt(quantityField.getText()),
            dateChooser.getDate().toString(),
            Double.parseDouble(commField.getText())
        );

        messageLabel.setText(status);
        this.nameComboBox.setSelectedItem("--none--");
        dateChooser.setDate(new Date());
      } catch (Exception e) {
        messageLabel.setText(e.getMessage());
      }
    });

    homeButton.addActionListener(evt -> {
      this.clearInput();
      features.showHome();
    });
  }
}
