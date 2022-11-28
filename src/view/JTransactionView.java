package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

public class JTransactionView extends JPanel implements PanelView {

  private JButton homeButton;
  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField nameField;
  private JTextField quantityField;
  private JTextField tranDateField;
  private JTextField commField;
  private JButton buyButton;
  private JButton sellButton;
  private JLabel messageLabel;

  public JTransactionView(String title) {
    setSize(500, 500);
    setLocation(200, 100);
    setLayout(new BorderLayout(8, 8));

    // North panel -> Title
    this.titleLabel = new JLabel(title);
    this.portfolioLabel = new JLabel("<Portfolio Name>"); // TODO
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    northPanel.add(this.titleLabel);
    northPanel.add(this.portfolioLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // West panel
    this.nameField = new JTextField(8);
    this.quantityField = new JTextField(6);
    this.tranDateField = new JTextField(10);
    this.commField = new JTextField(6);

    // Default values
    this.quantityField.setText("1");
    this.commField.setText("0");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
    centerPanel.add(new JLabel("Name: "));
    centerPanel.add(this.nameField);
    centerPanel.add(new JLabel("Quantity: "));
    centerPanel.add(this.quantityField);
    centerPanel.add(new JLabel("Transaction date (yyyy-mm-dd): "));
    centerPanel.add(this.tranDateField);
    centerPanel.add(new JLabel("Commission: "));
    centerPanel.add(this.commField);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Buy/Sell buttons
    this.buyButton = new JButton("BUY");
    this.sellButton = new JButton("SELL");
    this.homeButton = new JButton("HOME");
    this.messageLabel = new JLabel();

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 64, 16));
    southPanel.add(this.buyButton);
    southPanel.add(this.sellButton);
    southPanel.add(this.homeButton);
    southPanel.add(this.messageLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  public void setPortfolioName(String portfolioName) {
    portfolioLabel.setText(portfolioName);
  }

  public void setEchoOutput(String s) {

  }

  public String getInput() {
    return null;
  }

  @Override
  public void clearInput() {
    this.messageLabel.setText("");
    this.nameField.setText("");
    this.quantityField.setText("");
    this.tranDateField.setText("");
    this.commField.setText("");

  }

  @Override
  public void addActionListener(Features features) {
    buyButton.addActionListener(evt -> {
      try{
        String status = features.buyStock(nameField.getText(),
            Integer.parseInt(quantityField.getText()),
            tranDateField.getText(), Double.parseDouble(commField.getText()));
        messageLabel.setText(status);
        nameField.setText("");
        tranDateField.setText("");
    }catch (Exception e){
      messageLabel.setText(e.getMessage());
    }
    });
    sellButton.addActionListener(evt -> {
      try{
        String status = features.sellStock(nameField.getText(),
                Integer.parseInt(quantityField.getText()),
                tranDateField.getText(), Double.parseDouble(commField.getText()));
        messageLabel.setText(status);
        nameField.setText("");
        tranDateField.setText("");
      }catch (Exception e){
        messageLabel.setText(e.getMessage());
      }
    });
    homeButton.addActionListener(evt -> {
      this.clearInput();
      features.showHome();
    });
  }
}
