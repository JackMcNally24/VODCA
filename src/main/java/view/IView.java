package view;


import java.util.ArrayList;
import java.util.HashMap;

import model.Tuple;

public interface IView {

  /**
   * Makes the view visible, and is normally called after the view is constructed.
   */
  void makeVisible();

  /**
   * Refreshes the view.
   */
  void refresh();

  /**
   * Provides the view with stocks to be drawn.
   */
  void setStocks(ArrayList<Tuple> stocks);

  void setEight(HashMap<String, ArrayList<Double>> eight);

  /**
   * Creates an appendable that might be useful in the future lol but prob not.
   */
  Appendable createAppendableView(Appendable ap);
}
