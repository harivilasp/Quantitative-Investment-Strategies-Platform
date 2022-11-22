package view;

import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrameView extends JFrame implements IView {

  private final JLabel display;
  private final JButton inflexibleOpButton;
  private final JButton flexibleOpButton;
  private final JButton nextButton;
  private final JButton backButton;
  private final JButton exitButton;

  public JFrameView(JLabel display, JButton inflexibleOpButton, JButton flexibleOpButton,
      JButton nextButton, JButton backButton, JButton exitButton) throws HeadlessException {

    this.display = display;
    this.inflexibleOpButton = inflexibleOpButton;
    this.flexibleOpButton = flexibleOpButton;
    this.nextButton = nextButton;
    this.backButton = backButton;
    this.exitButton = exitButton;
  }

  @Override
  public void setEchoOutput(String s) {

  }

  @Override
  public String getInputString() {
    return null;
  }

  @Override
  public void clearInputString() {

  }

  @Override
  public void resetFocus() {

  }
}
