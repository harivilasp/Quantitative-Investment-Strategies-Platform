package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

/**
 * This class represents the main view.
 */
public class JHome extends JPanel implements PanelView{
  private JButton createPortfolio;
  private JButton checkCost;
  private JButton checkValue;
  private JButton buySellStock;
  private JButton savePortfolioToFile;
  private JButton readPortfolioFromFile;
  private JButton applyStrategy;
  private JButton compositionAtDate;
  private JButton quit;

  public JHome() {
    this.setPreferredSize(new Dimension(450, 500));
    setVisible(true);

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
    checkCost.addActionListener(evt ->{
      features.showCostBasis();
    });
    checkValue.addActionListener(evt ->{
      features.showPortfolioValue();
    });
    savePortfolioToFile.addActionListener(evt ->{
      features.showSavePortfolio();
    });
    readPortfolioFromFile.addActionListener(evt ->{
      features.showReadPortfolio();
    });
    applyStrategy.addActionListener(evt ->{
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
