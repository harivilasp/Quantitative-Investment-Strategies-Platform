package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents the JPanel view for "Load portfolio" operation.
 */
public class JLoadPortfolioView extends JPanel implements PanelView {

  private JButton homeButton;
  private JButton chooseFileButton;
  private JLabel messageLabel;

  /**
   * Creates an instance of the JLoadPortfolio view to map out all the view components.
   *
   * @param title the title of the view panel
   */
  public JLoadPortfolioView(String title) {
    this.setPreferredSize(new Dimension(500, 500));
    this.setLayout(new BorderLayout(8, 16));

    // North panel -> Title
    JLabel titleLabel = new JLabel(title);

    JPanel northPanel = new JPanel();
    northPanel.add(titleLabel);
    this.add(northPanel, BorderLayout.NORTH);

    // Center panel -> Choose file button
    this.chooseFileButton = new JButton("Choose portfolio from filepath");

    JPanel centerPanel = new JPanel();
    centerPanel.add(this.chooseFileButton);
    this.add(centerPanel, BorderLayout.CENTER);

    // South panel -> Message to show whether portfolio loaded was valid or not.
    this.messageLabel = new JLabel("");
    this.homeButton = new JButton("HOME");

    // Alignments
    this.messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    // BoxLayout for the message and home button
    JPanel southBox = new JPanel();
    southBox.setLayout(new BoxLayout(southBox, BoxLayout.PAGE_AXIS));
    southBox.add(this.messageLabel);
    southBox.add(new JLabel(" "));
    southBox.add(new JLabel(" "));
    southBox.add(this.homeButton);

    JPanel southPanel = new JPanel();
    southPanel.add(southBox);
    this.add(southPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  @Override
  public void addActionListener(Features features) {
    chooseFileButton.addActionListener(evt -> {
      // Open file chooser
      JFileChooser fileChooser = new JFileChooser();
      // Apply file extension filters
      FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
              "Text files",
              "txt"
      );

      fileChooser.setFileFilter(fileFilter);

      // Retrieve response code from the file chooser
      int responseCode = fileChooser.showOpenDialog(JLoadPortfolioView.this);
      // Response code is valid
      if (responseCode == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String responseStatus = features.loadFlexiblePortfolio(selectedFile.getAbsolutePath());
        this.messageLabel.setText(responseStatus);
      }
    });

    homeButton.addActionListener(evt -> {
      features.showHome();
    });
  }

  @Override
  public void clearInput() {
    this.messageLabel.setText("");
  }
}
