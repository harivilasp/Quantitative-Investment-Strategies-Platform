package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8));
    centerPanel.add(new JLabel("Amount: "));
    centerPanel.add(this.amountField);

    this.add(centerPanel, BorderLayout.CENTER);
  }

  @Override
  public void addActionListener(Features features) {

  }

  @Override
  public void clearInput() {

  }
}
