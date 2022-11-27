package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JGetAtDateView extends JPanel implements IView {

  private JLabel mTitleLabel;
  private JTextField mDateField;
  private JButton mActionButton;
  private JLabel mResultLabel;

  public JGetAtDateView(String title) {
    setSize(500, 500);
    setLocation(200, 100);
    setLayout(new BorderLayout(8, 8));

    // North panel -> Title
    this.mTitleLabel = new JLabel(title);
    this.mTitleLabel.setHorizontalAlignment(JLabel.LEFT);
    this.mTitleLabel.setVerticalAlignment(JLabel.CENTER);
    JPanel northPanel = new JPanel();
    northPanel.add(this.mTitleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // West panel -> Enter date and submit
    this.mDateField = new JTextField(6);
    this.mActionButton = new JButton("Get Value");
    JPanel westPanel = new JPanel();
    westPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
    westPanel.add(new JLabel("Enter date:"));
    westPanel.add(this.mDateField);
    westPanel.add(new JLabel("    "));
    westPanel.add(this.mActionButton);
    this.add(westPanel, BorderLayout.WEST);

    // South panel -> Show result
    this.mResultLabel = new JLabel("Show result");
    JPanel southPanel = new JPanel();
    southPanel.add(this.mResultLabel);
    this.add(southPanel, BorderLayout.SOUTH);

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

  @Override
  public void addFeatures(Features features) {
  }
}
