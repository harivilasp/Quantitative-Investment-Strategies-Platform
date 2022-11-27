package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JCompositionView extends JPanel {

  private JLabel titleLabel;
  private JLabel portfolioLabel;
  private JTextField dateField;
  private JButton showButton;
  private JLabel resultLabel;

  public JCompositionView(String title) {
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

    // Center panel -> Date text field and show composition button
    this.dateField = new JTextField(8);
    this.showButton = new JButton("SHOW");

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 8));
    centerPanel.add(new JLabel("Date:"));
    centerPanel.add(this.dateField);
    centerPanel.add(this.showButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Result
    this.resultLabel = new JLabel("<Result comes here>"); // TODO

    JPanel southPanel = new JPanel();
    southPanel.add(this.resultLabel);
    this.add(southPanel, BorderLayout.SOUTH);
  }
}
