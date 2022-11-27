package view;

import controller.Features;

public interface PanelView{
  void addActionListener(Features features);
  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInput();
}
