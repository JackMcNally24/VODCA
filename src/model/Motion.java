package model;

import java.awt.Color;

import model.shape.Posn;

/**
 * A Motion is any object that describes how to Mutate some AShape.
 */
public class Motion {
  private int id;
  private Posn newPosition;
  private Posn size;
  private Color color;
  private Posn timeFrame;

  /**
   * Constructs a {@code Motion} object.
   *
   * @param id is an int corresponding to an id number of an AShape.
   * @param newPosition is where the AShape will be moved to.
   * @param timeFrame is the number of ticks it takes the AShape to do the Motion.
   * @param size is the new size that AShape will grow or shrink to.
   * @param color is the new Color that AShape will turn to.
   */
  public Motion(int id, Posn newPosition, Posn timeFrame, Posn size, Color color) {
    this.id = id;
    if (newPosition.getX() < 0 || newPosition.getY() < 0) {
      throw new IllegalArgumentException("Your position must be a positive number!");
    }
    else {
      this.newPosition = newPosition;
    }
    if (size.getX() <= 0 || size.getY() <= 0) {
      throw new IllegalArgumentException("Your size has to have positive dimensions!");
    }
    else {
      this.size = size;
    }
    if (timeFrame.getX() < 0 || timeFrame.getY() < 0) {
      throw new IllegalArgumentException("Your timeframe cannot contain negative numbers!");
    }
    else if (timeFrame.getY() <= timeFrame.getX()) {
      throw new IllegalArgumentException("Your end time must be greater than your start time!");
    }
    else {
      this.timeFrame = timeFrame;
    }
    try {
      this.color = color;
    }
    catch (Exception e) {
      throw new IllegalArgumentException("That is an invalid Color!");
    }
  }

  /**
   * A getter for the id number of the Motion.
   *
   * @return the id of the Motion
   */
  public int getId() {
    return id;
  }

  /**
   * A getter for the position the motion is attempting to move a shape to.
   *
   * @return the position the motion is moving to.
   */
  public Posn getNewPosition() {
    return newPosition;
  }

  /**
   * A getter for the time frame of the Motion.
   *
   * @return the timeFrame of the Motion
   */
  public Posn getTimeFrame() {
    return timeFrame;
  }

  /**
   * A getter for the size that the motion aims to change to for some shape.
   *
   * @return the size of the Motion
   */
  public Posn getSize() {
    return size;
  }

  /**
   * A getter for the color that the motion aims to change for some shape.
   *
   * @return the color of the Motion
   */
  public Color getColor() {
    return color;
  }
}
