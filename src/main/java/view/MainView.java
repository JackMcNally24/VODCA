package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.*;

import model.Tuple;

public class MainView extends JFrame implements IView, ActionListener {
  private JPanel mainPanel;
  private StockListPanel stockListPanel;
  private JScrollPane scrollPane;
  private JPanel buttonPanel;
  private JButton switchButton;
  private JButton orderButton;
  private HashMap<String, ArrayList<Double>> eight;

  public MainView() {
    super();
    this.setTitle("Get me some VODCA bro!");
    this.setSize(750,750);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    stockListPanel = new StockListPanel();
    mainPanel.add(stockListPanel);
    scrollPane = new JScrollPane(stockListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //hey future jack, if the scroll pane isnt working, try adding a preferred size
    scrollPane.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(scrollPane);

    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.BLACK);

    switchButton = new JButton("Custom View");
    switchButton.setBackground(Color.GREEN);
    switchButton.addActionListener(this);
    switchButton.setActionCommand("switch");

    orderButton = new JButton("Switch Order");
    orderButton.setBackground(Color.GREEN);
    orderButton.addActionListener(this);
    orderButton.setActionCommand("order");

    buttonPanel.add(switchButton);
    buttonPanel.add(orderButton);

    mainPanel.add(buttonPanel);


    this.add(mainPanel);

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
    this.stockListPanel.setStocks(stocks);
  }

  @Override
  public void setEight(HashMap<String, ArrayList<Double>> eight) {
    this.eight = eight;
  }

  @Override
  public Appendable createAppendableView(Appendable ap) {
    try {
      return ap;
    }
    catch (Exception e) {
      throw new IllegalStateException();
    }
  }

  WindowListener exitListener = new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
      File done = new File ("C:\\Users\\Baxtop 2.0\\Documents\\VODCA\\python\\done.txt");
      File valuation = new File ("C:\\Users\\Baxtop 2.0\\Documents\\VODCA\\python\\valuation.json");
      if (done.delete()) {
        System.out.println("Done file successfully deleted.");
      }
      if (valuation.delete()) {
        System.out.println("Valuation file successfully deleted.");
      }
    }
  };

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("switch")) {
      IView alt = new AltView();
      alt.setStocks(stockListPanel.getStocks());
      alt.setEight(eight);
      this.setVisible(false);
      alt.makeVisible();
    }
    else if (e.getActionCommand().equals("order")) {
      ArrayList<Tuple> temp = stockListPanel.getStocks();
      Collections.reverse(temp);
      setStocks(temp);
      refresh();
    }
  }
}
