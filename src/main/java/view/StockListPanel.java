package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.Tuple;

public class StockListPanel extends JPanel {
  private ArrayList<Tuple> stocks;
  private JScrollPane scrollPane;

  public StockListPanel() {
    super();
    this.setBackground(Color.BLACK);
    this.stocks = new ArrayList<>();
  }

  public void setStocks(ArrayList<Tuple> stocks) {
    this.stocks = stocks;
  }

  public ArrayList<Tuple> getStocks() {
    return stocks;
  }

  @Override
  protected void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    super.paintComponent(g);
    for(int i = 0; i < stocks.size(); i++) {
      String ticker = stocks.get(i).getTicker();
      String valuation = Double.toString(stocks.get(i).getValuation()) + "%";
      g.setColor(Color.WHITE);
      g.drawString(ticker, 10, 10 + (i * 20));
      if (stocks.get(i).getValuation() >= 0) {
        g.setColor(Color.GREEN);
      }
      else {
        g.setColor(Color.RED);
      }
      g.drawString(valuation, 10 + 50, 10 + (i*20));
    }
  }
}
