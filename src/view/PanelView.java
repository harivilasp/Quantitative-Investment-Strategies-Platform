package view;

import controller.Features;

/**
 * This interface represents all the operations applicable to all the components connected to a
 * single JPanel and the actions needed to manage those components.
 */
public interface PanelView {

  /**
   * Helps to collate action listeners attached to JPanel components and use the provided features
   * to complete those actions.
   *
   * @param features the features provided to the view
   */
  void addActionListener(Features features);

  /**
   * Clears the text field. Note that a more general "setInputString" would work for this purpose
   * but would be incorrect. This is because the text field is not set programmatically in general
   * but input by the user.
   */
  void clearInput();
}
