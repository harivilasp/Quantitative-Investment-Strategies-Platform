package view;

import controller.Features;

public interface IView {

  /**
   * Set the label that is showing what the model stores.
   *
   * @param s
   */
  void setEchoOutput(String s);

  /**
   * Retrieves the user input from a Text field.
   *
   * @return the user input string
   */
  String getInput();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInput();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached to
   * it, so that keyboard events will still flow through.
   */
  void resetFocus();

  void addFeatures(Features features);
}
