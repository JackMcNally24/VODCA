package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import util.StockBuilder;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ModelImpl {

    // constructor stuff
    private ArrayList<ArrayList<Double>> table;
    private ArrayList<Tuple> stocks;
    private HashMap<String, ArrayList<Double>> eight;

    public ModelImpl() {
      this.table = new ArrayList<ArrayList<Double>>();
      this.stocks = new ArrayList<>();
      this.eight = new HashMap<>();
    }

    public void fiveInputs(String name, double price, double growth, double avgthis, double avgnext, double dividends) {
      // these are the constants, computer inputted:
      double growthdiscount = 0.1;
      double discountrate = 0.15;
      double pevalue = price/avgthis;
      double dividendest = dividends * price;
      table.add(new ModelUtils().futureGrowth(growth, growthdiscount));
      table.add(new ModelUtils().earningsEst(table.get(0), avgthis, avgnext));
      table.add(new ModelUtils().dividend(dividends, growth));
      table.add(new ModelUtils().projPrice(table.get(1), pevalue));
      table.add(new ModelUtils().fiveYearHold(table.get(2), table.get(3).get(4), table.get(2).get(4)));
      table.add(new ModelUtils().tenYearHold(table.get(2), table.get(3).get(4), table.get(2).get(4)));
      double fiveyearnpv = new ModelUtils().npv(table.get(4), discountrate);
      double tenyearnpv = new ModelUtils().npv(table.get(5), discountrate);
      double npv =  (fiveyearnpv + tenyearnpv) /2;
      double answer =  ((npv - price) / npv) * 100;

      stocks.add(new Tuple(name, answer));
    }


    //Getter for the hashmap
    public ArrayList<Tuple> getStocks() {
      ArrayList<Tuple> sorted = this.stocks;
      Collections.sort(sorted);
      Collections.reverse(sorted);
      return sorted;
    }

    public void startEight(String ticker, double price, double growth, double curAvgEst, double nextAvgEst, double dividends) {
      ArrayList<Double> dubs = new ArrayList<>();
      dubs.add(price);
      dubs.add(growth);
      dubs.add(curAvgEst);
      dubs.add(nextAvgEst);
      dubs.add(dividends);
      eight.put(ticker, dubs);
    }

    public HashMap<String, ArrayList<Double>> getEight() {
      return eight;
    }

  }

