package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * This class represents the JPanel view for "Create portfolio" operation.
 */
public class JCreatePortfolioView extends JPanel implements PanelView {

  private JButton homeButton;
  private JLabel titleLabel;
  private JTextField nameField;
  private JButton createButton;
  private JLabel messageLabel;

  /**
   * Creates an instance of the JCreatePortfolio view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JCreatePortfolioView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    this.titleLabel = new JLabel(title);

    JPanel northPanel = new JPanel();
    northPanel.add(this.titleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Name text field and create button
    this.nameField = new JTextField(12);
    this.createButton = new JButton("CREATE");
    this.homeButton = new JButton("HOME");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 8));
    centerPanel.add(new JLabel("Portfolio name:"));
    centerPanel.add(this.nameField);
    centerPanel.add(this.createButton);
    centerPanel.add(this.homeButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Result
    this.messageLabel = new JLabel("<Message comes here>"); // TODO

    JPanel southPanel = new JPanel();
    southPanel.add(this.messageLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  @Override
  public void addActionListener(Features features) {
    createButton.addActionListener(evt -> {
      String status = features.addFlexiblePortfolio(nameField.getText());
      messageLabel.setText(status);
      nameField.setText("");
    });

    homeButton.addActionListener(evt -> {
      this.clearInput();
      features.showHome();
    });
  }

  @Override
  public void clearInput() {
    this.nameField.setText("");
    this.messageLabel.setText("");
  }
}
