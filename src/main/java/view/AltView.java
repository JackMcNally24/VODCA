package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import model.Tuple;

public class AltView extends JFrame implements IView, ActionListener {
  private ArrayList<Tuple> stocks;
  private JPanel mainPanel;
  private JPanel textPanel;
  private JTextArea ticker;
  private JTextArea growthDiscount;
  private JTextArea discountRate;
  private JTextArea peValue;
  private JButton createVals;
  private HashMap<String, ArrayList<Double>> eight;
  private EightPanel eightPanel;
  private JButton switchButton;

  AltView() {
    super();
    setTitle("Custom Rates");
    mainPanel = new JPanel();
    mainPanel.setBackground(Color.BLACK);
    setSize(750,750);
    setBackground(Color.BLACK);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    textPanel = new JPanel();
    textPanel.setBackground(Color.BLACK);
    this.eight = new HashMap<>();

    ticker = new JTextArea(1,10);
    ticker.setBorder(BorderFactory.createTitledBorder("Ticker"));
    ticker.setBackground(Color.RED);
    textPanel.add(ticker);

    growthDiscount = new JTextArea(1,10);
    growthDiscount.setBorder(BorderFactory.createTitledBorder("Growth Discount"));
    growthDiscount.setBackground(Color.RED);
    textPanel.add(growthDiscount);


    discountRate = new JTextArea(1,10);
    discountRate.setBorder(BorderFactory.createTitledBorder("Discount Rate"));
    discountRate.setBackground(Color.RED);
    textPanel.add(discountRate);


    peValue = new JTextArea(1,10);
    peValue.setBorder(BorderFactory.createTitledBorder("PE Value"));
    peValue.setBackground(Color.RED);
    textPanel.add(peValue);

    createVals = new JButton("Calculate");
    createVals.setBackground(Color.GREEN);
    createVals.addActionListener(this);
    createVals.setActionCommand("calculate");
    textPanel.add(createVals);

    switchButton = new JButton("Main View");
    switchButton.setBackground(Color.GREEN);
    switchButton.addActionListener(this);
    switchButton.setActionCommand("switch");
    textPanel.add(switchButton);

    eightPanel = new EightPanel();

    mainPanel.add(textPanel);
    mainPanel.add(eightPanel);
    add(mainPanel);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setStocks(ArrayList<Tuple> stocks) {
    this.stocks = stocks;
  }

  @Override
  public void setEight(HashMap<String, ArrayList<Double>> eight) {
    this.eight = eight;
  }

  @Override
  public Appendable createAppendableView(Appendable ap) {
    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("calculate")) {
      try {
        eight.get(ticker.getText()).add(Double.parseDouble(growthDiscount.getText()));
        eight.get(ticker.getText()).add(Double.parseDouble(discountRate.getText()));
        eight.get(ticker.getText()).add(Double.parseDouble(peValue.getText()));
        eightPanel.setTicker(ticker.getText());
        eightPanel.setEight(this.eight);
      } catch (Exception ex) {
        System.out.println("Please enter all values");
      }
      ticker.setText("");
      growthDiscount.setText("");
      discountRate.setText("");
      peValue.setText("");
      refresh();
    }
    else if (e.getActionCommand().equals("switch")) {
      IView main = new MainView();
      main.setStocks(stocks);
      this.setVisible(false);
      main.makeVisible();
    }
  }
}
