package model;

import java.util.HashMap;

public interface IModel {

  double fiveInputs(String name, double price, double growth, double avgthis, double avgnext, double dividends);

  double eightInputs(double price, double growth, double avgthis, double avgnext, double dividends, double growthdiscount,
                     double discountrate, double pevalue, double dividendest);

  HashMap<String, Double> getStocks();
}
