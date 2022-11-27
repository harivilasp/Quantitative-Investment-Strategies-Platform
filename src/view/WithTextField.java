package view;

import java.util.List;

import controller.Features;


/**
 * This interface represents Views that with text field in them. It has  void addActionListener to
 * add a listener to button, String getInput() to get the content of text field that user typed,
 * void setHintMess(String message) that take a given a message, and show it on the view,
 * void clearField() that clear the text field, void resetFocus() to  reset focus.
 */
public interface WithTextField {

  /**
   * Add a listener to button.
   * @param listener the provided listener.
   */
  void addActionListener(Features features);

  /**
   * Get the content of text field that user typed.
   * @return the content of text field that user typed.
   */
  List<String> getInput();

  /**
   * Take a given a message, and show it on the view.
   * @param message A given string message.
   */
  void setHintMess(String message);

  /**
   * Clear the text field.
   */
  void clearField();

  /**
   * Reset focus.
   */
  void resetFocus();
}