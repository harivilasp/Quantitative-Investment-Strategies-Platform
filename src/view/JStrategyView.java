package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Features;

public class JStrategyView extends JPanel implements PanelView{

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField amountField;
  private JTextField intervalField;
  private JTextField startDateField;
  private JTextField endDateField;
  private JTextField commissionField;

  public JStrategyView(String title) {
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

    // Center panel -> Text input fields
    this.amountField = new JTextField(6);
    this.intervalField = new JTextField(4);
    this.startDateField = new JTextField(8);
    this.endDateField = new JTextField(8);
    this.commissionField = new JTextField(6);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
    centerPanel.add(new JLabel("Amount: "));
    centerPanel.add(this.amountField);
    centerPanel.add(new JLabel("Interval (in days): "));
    centerPanel.add(this.intervalField);
    centerPanel.add(new JLabel("Start date (yyyy-mm-dd): "));
    centerPanel.add(this.startDateField);
    centerPanel.add(new JLabel("End date (yyyy-mm-dd): "));
    centerPanel.add(this.endDateField);
    centerPanel.add(new JLabel("Commission: "));
    centerPanel.add(this.commissionField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Stock weightage addition
    // ComboBox panel
    JPanel boxPanel = new JPanel();
    boxPanel.setBorder(BorderFactory.createTitledBorder("Stock Weightage"));
    boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
    String[] options = new String[] {"GOOG", "AAPL", "NFLX", "MSFT"};
    JComboBox<String> comboBox = new JComboBox<>();
    for (String option: options) {
      comboBox.addItem(option);
    }
    boxPanel.add(comboBox);
    boxPanel.add(new JTextField(8));

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 16));
    southPanel.add(boxPanel);
    southPanel.add(new JButton("ADD"));
    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addActionListener(Features features) {

  }

  @Override
  public void clearInput() {

  }
}
