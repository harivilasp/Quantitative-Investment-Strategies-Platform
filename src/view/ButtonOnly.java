package view;

import controller.Features;

/**
 * This interface of Views that don't have textfield in the view. It contains
 * addActionLister(ActionListener listener) and resetFocus() methods.
 */
public interface ButtonOnly {

  /**
   * Add the provided listener.
   * @param listener provided listener.
   */
  void addActionListener(Features features);

  /**
   * Reset the focus on the appropriate part of the view.
   */
  void resetFocus();
}
