package util;

import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A helper to read stock data and construct a list from it.
 */
public class StockReader {

  /**
   * A factory for producing the list of stocks and their valuations

   * @param file The source of data for the stock list
   * @param builder A builder for helping to construct a new list
   * @param <Doc> The main model interface type describing stock lists
   */
  public static <Doc> Doc parseFile(File file, util.StockBuilder<Doc> builder) {
    Objects.requireNonNull(file, "Must have non-null readable source");
    Objects.requireNonNull(builder, "Must provide a non-null StockBuilder");
    JSONParser parser = new JSONParser();
    try {
      JSONArray a = (JSONArray) parser.parse(new FileReader(file));
      for (Object o : a) {
        try {
          JSONObject stock = (JSONObject) o;
          String stockString = stock.toString();
          //good ticker
          String ticker = (String) stock.get("ticker");
          //good price
          Double price = Double.parseDouble(((String) stock.get("Previous Close")).replaceAll(",", ""));
          //good dividends
          String divString = (String) stock.get("Forward Dividend & Yield");
          divString = divString.substring(0, divString.indexOf(" "));
          if (divString.equals("N/A")) {
            divString = "0";
          }
          Double dividends = Double.parseDouble(divString.replaceAll(",", ""));
          //good current average estimate
          String curAvg = (String) stock.get("Current Estimate");
          curAvg = curAvg.substring(curAvg.indexOf(" ") + 1);
          curAvg = curAvg.substring(curAvg.indexOf(" ") + 1);
          Double curAvgEst = Double.parseDouble(curAvg.substring(0, curAvg.indexOf(" ")).replaceAll(",", ""));
          //good next average estimate
          curAvg = curAvg.substring(curAvg.indexOf(" ") + 1);
          Double nextAvgEst = Double.parseDouble(curAvg.replaceAll(",", ""));

          //good growth
          int growIndex = stockString.indexOf("Next 5 Years (per annum)") + 25;
            String remainingStock = stockString.substring(growIndex);
            remainingStock = remainingStock.substring(0, remainingStock.indexOf("%"));
            Double growth = (Double.parseDouble(remainingStock.replaceAll(",", ""))) / 100;
            builder.addFiveInputs(ticker, price, growth, curAvgEst, nextAvgEst, dividends);
            builder.addEightInputs(ticker, price, growth, curAvgEst, nextAvgEst, dividends);
        } catch (Exception e) {

        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return builder.build();
  }

}
