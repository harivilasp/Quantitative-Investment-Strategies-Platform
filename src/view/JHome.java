package view;

import controller.Features;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the home screen view.
 */
public class JHome extends JPanel implements PanelView {

  private JLabel portfolioLabel;
  private JButton createPortfolio;
  private JButton checkCost;
  private JButton checkValue;
  private JButton buySellStock;
  private JButton savePortfolioToFile;
  private JButton readPortfolioFromFile;
  private JButton applyStrategy;
  private JButton compositionAtDate;
  private JButton quit;

  /**
   * Creates an instance of the JHome view to map out all the view components.
   */
  public JHome() {
    this.setPreferredSize(new Dimension(450, 500));
    this.setVisible(true);
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.portfolioLabel = new JLabel("<Portfolio Name>"); // TODO
    this.portfolioLabel.setAlignmentX(Container.CENTER_ALIGNMENT);
    this.add(new JLabel("    "));
    this.add(portfolioLabel);
    this.add(new JLabel("        "));

    GridLayout layout = new GridLayout(8, 2);
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(layout);

    createPortfolio = new JButton("Create Portfolio");
    buySellStock = new JButton("Buy/Sell Stock");
    quit = new JButton("Quit");
    checkCost = new JButton("Check Cost Basis");
    checkValue = new JButton("Check Value");
    savePortfolioToFile = new JButton("Save Portfolio To File");
    readPortfolioFromFile = new JButton("Read Portfolio From File");
    applyStrategy = new JButton("Apply Strategy");
    compositionAtDate = new JButton("Composition at Date");

    createPortfolio.setActionCommand("createPortfolio");
    buySellStock.setActionCommand("buySellStockChooseAWay");
    checkCost.setActionCommand("checkCostBasis");
    checkValue.setActionCommand("checkValue");
    savePortfolioToFile.setActionCommand("savePortfolioToFile");
    readPortfolioFromFile.setActionCommand("readPortfolioFromFile");
    quit.setActionCommand("quit");
    applyStrategy.setActionCommand("apply strategy");
    compositionAtDate.setActionCommand("compositionAtDate");

    panel.add(createPortfolio);
    panel.add(buySellStock);
    panel.add(compositionAtDate);
    panel.add(checkCost);
    panel.add(checkValue);
    panel.add(savePortfolioToFile);
    panel.add(readPortfolioFromFile);
    panel.add(applyStrategy);
    panel.add(quit);

    this.add(panel);
  }

  @Override
  public void addActionListener(Features features) {
    createPortfolio.addActionListener(evt -> {
      features.showCreatePortfolio();
    });
    buySellStock.addActionListener(evt -> {
      features.showBuySellStock();
    });
    compositionAtDate.addActionListener(evt -> {
      features.showPortfolioComposition();
    });
    checkCost.addActionListener(evt -> {
      features.showCostBasis();
    });
    checkValue.addActionListener(evt -> {
      features.showPortfolioValue();
    });
    savePortfolioToFile.addActionListener(evt -> {
      features.showSavePortfolio();
    });
    readPortfolioFromFile.addActionListener(evt -> {
      features.showReadPortfolio();
    });
    applyStrategy.addActionListener(evt -> {
      features.showCreateStrategy();
    });
    quit.addActionListener(evt -> features.exitProgram());
  }

  @Override
  public void clearInput() {

  }

  /**
   * Reset the focus on the appropriate part of the view.
   */
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}
