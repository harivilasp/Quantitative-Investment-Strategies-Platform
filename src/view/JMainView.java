package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.archive.IView;

/**
 * This class represents the Main screen frame view for displaying and working on a portfolio.
 */
public class JMainView extends JFrame implements IView {

  private JLabel display;
  private JButton inflexibleOpButton;
  private JButton flexibleOpButton;
  private JButton nextButton;
  private JButton backButton;
  private JButton exitButton;

  /**
   * Creates an instance of the JMain frame view to map out all the panel view components.
   *
   * @throws HeadlessException in case of frame instantiation errors
   */
  public JMainView() throws HeadlessException {
    setupJView();
  }

  @Override
  public void setEchoOutput(String s) {
    // TODO: Complete implementation
  }

  @Override
  public String getInput() {
    return null;
  }

  @Override
  public void clearInput() {
    // TODO: Complete implementation
  }

  @Override
  public void resetFocus() {
    // TODO: Complete implementation
  }

  @Override
  public void addFeatures(Features features) {
    // TODO: Complete implementation
  }

  /* Helper method to set up the frame view. */
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
