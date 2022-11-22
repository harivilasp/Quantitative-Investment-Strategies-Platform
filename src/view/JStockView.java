package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JStockView extends JFrame implements IView {

  private JLabel mTitleLabel;
  private JTextField mNameField;
  private JTextField mQuantityField;
  private JTextField mTDateField;
  private JTextField mCommField;
  private JButton mActionButton;
  private JLabel mResultLabel;

  public JStockView(String title) {
    setSize(500, 500);
    setLocation(200, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(8, 8));

    // North panel -> Title
    this.mTitleLabel = new JLabel(title);
    this.mTitleLabel.setHorizontalAlignment(JLabel.LEFT);
    this.mTitleLabel.setVerticalAlignment(JLabel.CENTER);
    JPanel northPanel = new JPanel();
    northPanel.add(this.mTitleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // West panel
    this.mNameField = new JTextField(8);
    this.mQuantityField = new JTextField(4);
    this.mTDateField = new JTextField(6);
    this.mCommField = new JTextField(4);

    this.mActionButton = new JButton(title);

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

    westPanel.add(new JLabel("    "));
    westPanel.add(this.mActionButton);

    this.add(westPanel, BorderLayout.WEST);

    pack();
    setVisible(true);
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
}
