package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * The View that logs the keys that you press.
 */
public class KeyLogger extends JFrame implements KeyListener, ActionListener {
  private String fileName;
  private Appendable ap;
  private String keysLog;
  private Timer t;
  private double time;
  private JPanel mainPanel;
  private JPanel timerPanel;
  private String timeString;
  private JLabel timerLabel;
  private JPanel keyPanel;
  private JLabel keyPressedLabel;
  private String keyPressedString;
  private JPanel buttonPanel;
  private JButton startButton;
  private JButton stopButton;
  private JButton saveAndCloseButton;

  public KeyLogger(String fileName, Appendable ap) {
    super();
    this.fileName = fileName;
    this.ap = ap;
    this.keysLog = "";
    this.setTitle("KeyLogger for " + fileName);
    this.setSize(500,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    time = 0;
    timeString = Double.toString(time) + "s";
    keyPressedString = "Last key pressed: ";
    t = new Timer(100, timerListener);
    //set up mainPanel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    //set up timerPanel
    timerPanel = new JPanel();
    timerLabel = new JLabel(timeString);
    timerPanel.add(timerLabel);

    keyPanel = new JPanel();
    keyPressedLabel = new JLabel(keyPressedString);
    keyPanel.add(keyPressedLabel);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    //start button
    startButton = new JButton("Start Logger");
    startButton.setActionCommand("start");
    startButton.addActionListener(this);
    buttonPanel.add(startButton);

    //stop button
    stopButton = new JButton("Stop Logger");
    stopButton.setActionCommand("stop");
    stopButton.addActionListener(this);
    buttonPanel.add(stopButton);

    //save and close button
    saveAndCloseButton = new JButton("Save and Close");
    saveAndCloseButton.setActionCommand("save");
    saveAndCloseButton.addActionListener(this);
    buttonPanel.add(saveAndCloseButton);

    mainPanel.add(timerPanel);
    mainPanel.add(keyPanel);
    mainPanel.add(buttonPanel);

    this.add(mainPanel);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    String typed = e.paramString();
    if (typed.equals(" ")) {
      typed = "space";
    }
    keysLog = keysLog + typed + " ";
    keyPressedString = "Last key pressed: " + typed;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("start")) {
      t.start();
    }
    else if (e.getActionCommand().equals("stop")) {
      t.stop();
    }
    else if (e.getActionCommand().equals("save")) {
      try {
        ap = ap.append(keysLog);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  private ActionListener timerListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      time = time + .1;
      timeString = Double.toString(time) + "s";
    }
  };
}
