package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

public class JTransactionView extends JPanel implements PanelView {

  private JButton homeButton;
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

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    centerPanel.add(new JLabel("Name: "));
    centerPanel.add(this.mNameField);
    centerPanel.add(new JLabel("Quantity: "));
    centerPanel.add(this.mQuantityField);
    centerPanel.add(new JLabel("Transaction date: "));
    centerPanel.add(this.mTDateField);
    centerPanel.add(new JLabel("Commission: "));
    centerPanel.add(this.mCommField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Buy/Sell buttons
    this.mBuyButton = new JButton("BUY");
    this.mSellButton = new JButton("SELL");
    this.homeButton = new JButton("HOME");
    this.mResultLabel = new JLabel();

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 64, 16));
    southPanel.add(this.mBuyButton);
    southPanel.add(this.mSellButton);
    southPanel.add(this.homeButton);
    southPanel.add(this.mResultLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public void setEchoOutput(String s) {

  }

  public String getInput() {
    return null;
  }

  public void clearInput() {
    
  }

  public void resetFocus() {

  }
  @Override
  public void addActionListener(Features features) {
    mBuyButton.addActionListener(evt -> {
      String status = features.buyStock(mNameField.getText(),
              Integer.parseInt(mQuantityField.getText()),
              mTDateField.getText(),Double.parseDouble(mCommField.getText()));
      mResultLabel.setText(status);
    });
    mSellButton.addActionListener(evt -> {
      String status = features.sellStock(mNameField.getText(),
              Integer.parseInt(mQuantityField.getText()),
              mTDateField.getText(),Double.parseDouble(mCommField.getText()));
      mResultLabel.setText(status);
    });
    homeButton.addActionListener(evt -> {
      features.showHome();
    });
  }
}
