package model;

import java.awt.Color;
import java.util.ArrayList;

import model.shape.IShape;
import model.shape.Posn;

/**
 * This class represents a the model for an animation.
 * The List of IShapes is used to represent any shapes that can be seen in the view.
 * The List of Motions is used to represent any actions that can be applied to the shapes.
 * The String build is used as a textual representation of all the motions that have been carried
 * out by the animator.
 */
public class AnimatorModelImpl implements AnimatorModel {
  private ArrayList<IShape> shapes;
  private ArrayList<Motion> motions;
  private String build;

  /**
   * Constructs a {@code AnimatorModelImpl} object.
   */
  public AnimatorModelImpl() {
    this.shapes = new ArrayList<IShape>();
    this.motions = new ArrayList<Motion>();
    this.build = "";
  }

  /**
   * Constructs a {@code AnimatorModelImpl} object.
   *
   * @param shapes is the list of shapes that the animator starts out with.
   * @param motions is the list of motions that the animator starts out with.
   */
  public AnimatorModelImpl(ArrayList<IShape> shapes, ArrayList<Motion> motions) {
    this.shapes = shapes;
    this.motions = motions;
    this.build = "";
  }

  private void verifyMotions() throws IllegalArgumentException {
    for (Motion motion : motions) {
      boolean continuous = false;
      if (motion.getTimeFrame().getX() != 0) {
        for (Motion m : this.motions) {
          if (motion.getTimeFrame().getX() == m.getTimeFrame().getY()) {
            continuous = true;
          }
        }
        if (!continuous) {
          throw new IllegalArgumentException("There cannot be gaps in your timeline!");
        }
      }
    }
  }

  @Override
  public void addShape(IShape shape) {
    this.shapes.add(shape);
  }

  @Override
  public void addMotion(Motion motion) {
    boolean continuous = false;
    if (motion.getTimeFrame().getX() != 0) {
      for (Motion m : this.motions) {
        if (motion.getTimeFrame().getX() == m.getTimeFrame().getY()) {
          continuous = true;
        }
      }
      if (!continuous) {
        throw new IllegalArgumentException("There cannot be gaps in your timeline!");
      }
    }
    this.motions.add(motion);
  }

  @Override
  public void applyMotions() {
    //verifies that all of the motions have no gaps
    verifyMotions();
    for (Motion motion : motions) {
      boolean exists = false;
      for (IShape shape : shapes) {
        if (shape.getId() == motion.getId()) {
          exists = true;
          this.applyMotion(motion, shape);
        }
      }
      if (!exists) {
        throw new IllegalArgumentException("Your motion describes a shape that does not exist!");
      }
    }
  }

  private void applyMotion(Motion motion, IShape shape) {
    this.build = build + "shape id: " + shape.getId() + '\n';
    this.build = build + "motion shape id: " + shape.getId() + " " +
            Math.round(motion.getTimeFrame().getX()) + " "
            + Math.round(shape.getPosition().getX()) + " " + Math.round(shape.getPosition().getY())
            + " " + Math.round(shape.getSize().getX()) + " " +
            Math.round(shape.getSize().getY()) + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen() + " " + shape.getColor().getBlue() + "   ";
    float ticks = motion.getTimeFrame().getY() - motion.getTimeFrame().getX();
    float mX = (motion.getNewPosition().getX() - shape.getPosition().getX()) / ticks;
    float mY = (motion.getNewPosition().getY() - shape.getPosition().getY()) / ticks;
    float mRed = (motion.getColor().getRed() - shape.getColor().getRed()) / ticks;
    float mGreen = (motion.getColor().getGreen() - shape.getColor().getGreen()) / ticks;
    float mBlue = (motion.getColor().getBlue() - shape.getColor().getBlue()) / ticks;
    float mWidth = (motion.getSize().getX() - shape.getSize().getX()) / ticks;
    float mHeight = (motion.getSize().getY() - shape.getSize().getY()) / ticks;
    float lastRed = shape.getColor().getRed();
    float lastGreen = shape.getColor().getGreen();
    float lastBlue = shape.getColor().getBlue();
    for (int i = 0; i < motion.getTimeFrame().getY() - motion.getTimeFrame().getX(); i++) {
      float newXPos = shape.getPosition().getX() + (mX);
      if (newXPos < 0) {
        newXPos = 0;
      }
      float newYPos = shape.getPosition().getY() + (mY);
      if (newYPos < 0) {
        newYPos = 0;
      }
      Posn newPos = new Posn(newXPos, newYPos);

      float newWidth = shape.getSize().getX() + (mWidth);
      if (newWidth < 0) {
        newWidth = 0;
      }

      float newHeight = shape.getSize().getY() + (mHeight);
      if (newHeight < 0) {
        newHeight = 0;
      }
      Posn newSize = new Posn(newWidth, newHeight);

      float newRed = (lastRed + mRed);
      if (newRed < 0) {
        newRed = 0;
      }
      else if (newRed > 255.0) {
        newRed = 255;
      }

      float newGreen = (lastGreen + mGreen);
      if (newGreen < 0) {
        newGreen = 0;
      }
      else if (newGreen > 255.0) {
        newGreen = 255;
      }
      float newBlue = (lastBlue + mBlue);
      if (newBlue < 0) {
        newBlue = 0;
      }
      else if (newBlue > 255.0) {
        newBlue = 255;
      }
      lastRed = newRed;
      lastGreen = newGreen;
      lastBlue = newBlue;
      Color newColor = new Color(Math.round(newRed), Math.round(newGreen), Math.round(newBlue));
      shape.setPosition(newPos);
      shape.setSize(newSize);
      shape.setColor(newColor);
    }
    this.build = build + Math.round(motion.getTimeFrame().getY()) + " "
            + Math.round(shape.getPosition().getX()) + " " + Math.round(shape.getPosition().getY())
            + " " + Math.round(shape.getSize().getX()) + " " + Math.round(shape.getSize().getY())
            + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen() + " " + shape.getColor().getBlue() + '\n';
  }

  @Override
  public String getBuild() {
    return this.build;
  }
}
