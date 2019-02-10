import java.io.File;
import java.io.FileReader;

import controller.ControllerImpl;
import controller.IController;
import view.IView;
import view.MainView;

public final class VODCA {

  /**
   * Da main. Get ready for some wacky business.
   * @param args Idk yet
   */
  public static void main(String[] args) {
    boolean good = true;
    //run that mf'in python script to get the stock valuation
    if (! (new File("python/Scripts/%s-analysis.json").isFile())) {
      good = false;
      try {
        String command = "VODCA/python/Scripts/analysis.py";
        Process p = Runtime.getRuntime().exec("python python/Scripts/analysis.py");
      } catch (Exception e) {
        e.printStackTrace();
      }

      //make sure the file has finished writing
      while (!(new File("python/Scripts/done.txt").isFile())) {

      }
    }

    //pass the finished valuation json to the controller
    try {
       File file = new File("python/Scripts/%s-analysis.json");
       IView view = new MainView();
       IController controller = new ControllerImpl(view, file, System.out);
       controller.externalWriter();
    } catch (Exception e) {
      e.printStackTrace();
    }

//    //clean up clean up everybody clean up
//    File done = new File ("\\VODCA\\python\\done.txt");
//    File valuation = new File ("\\VODCA\\python\\%s-analysis.json");
//    if (done.delete()) {
//      System.out.println("Done file successfully deleted.");
//    }
//    if (valuation.delete()) {
//      System.out.println("Valuation file successfully deleted.");
//    }
  }
}
