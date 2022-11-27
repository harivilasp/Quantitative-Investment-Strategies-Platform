package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JTransactionView extends JPanel implements IView {

  private JLabel mTitleLabel;
  private JLabel mPortfolioLabel;
  private JTextField mNameField;
  private JTextField mQuantityField;
  private JTextField mTDateField;
  private JTextField mCommField;
  private JButton mBuyButton;
  private JButton mSellButton;
  private JLabel mResultLabel;

  public JTransactionView(String title) {
    setSize(500, 500);
    setLocation(200, 100);
    setLayout(new BorderLayout(8, 8));

    // North panel -> Title
    this.mTitleLabel = new JLabel(title);
    this.mPortfolioLabel = new JLabel("<Portfolio Name>"); // TODO
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    northPanel.add(this.mTitleLabel);
    northPanel.add(this.mPortfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // West panel
    this.mNameField = new JTextField(8);
    this.mQuantityField = new JTextField(4);
    this.mTDateField = new JTextField(6);
    this.mCommField = new JTextField(4);

    // Default values
    this.mQuantityField.setText("1");
    this.mCommField.setText("0");

    this.mBuyButton = new JButton("BUY");
    this.mSellButton = new JButton("SELL");

    JPanel westPanel = new JPanel();
    westPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
    westPanel.add(new JLabel("Name: "));
    westPanel.add(this.mNameField);
    westPanel.add(new JLabel("Quantity: "));
    westPanel.add(this.mQuantityField);
    westPanel.add(new JLabel("Transaction date: "));
    westPanel.add(this.mTDateField);
    westPanel.add(new JLabel("Commission: "));
    westPanel.add(this.mCommField);

    this.add(westPanel, BorderLayout.CENTER);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 64, 16));
    southPanel.add(this.mBuyButton);
    southPanel.add(this.mSellButton);

    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void setEchoOutput(String s) {

  }

  @Override
  public String getInput() {
    return null;
  }

  @Override
  public void clearInput() {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void addFeatures(Features features) {

  }
}
