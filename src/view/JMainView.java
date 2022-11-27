package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;
import view.archive.IView;

public class JMainView extends JFrame implements IView {

  private JLabel display;
  private JButton inflexibleOpButton;
  private JButton flexibleOpButton;
  private JButton nextButton;
  private JButton backButton;
  private JButton exitButton;

  public JMainView() throws HeadlessException {
    setupJView();
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

  private void setupJView() {
    setSize(500, 500);
    setMinimumSize(new Dimension(500, 500));
    setLocation(200, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(new BorderLayout(16, 16));

    // North panel -> title
    JLabel titleLabel = new JLabel("FLEXIBLE PORTFOLIO");
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setVerticalAlignment(JLabel.CENTER);

    JPanel titleNPanel = new JPanel();
    titleNPanel.add(titleLabel);
    titleNPanel.setBackground(Color.BLUE);
    this.add(titleLabel, BorderLayout.NORTH);

    // West panel -> Set of radio buttons

    pack();
    setVisible(true);
  }
}
