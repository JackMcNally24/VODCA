package model;

import model.shape.IShape;

/**
 * This interface represents the operations offered by the animation model. One object of the model
 * represents one full animation.
 */
public interface AnimatorModel {

  /**
   * This method adds an IShape to the list of shapes that are relevant to the animation.
   *
   * @param shape is the shape that is added to the current list of shapes in this animation.
   */
  void addShape(IShape shape);

  /**
   * This method adds an Motion to the list of motions that are applied to the animation.
   *
   * @param motion is the motion that is added to the current list of motions in this animation.
   */
  void addMotion(Motion motion);

  /**
   * This method loops through each motion in the animator's list of motions and applies that motion
   * to the correct IShape in the animator's list of IShapes.
   */
  void applyMotions();

  /**
   * The getter for the build string.
   * @return The build string, which represents the data being changed by each motion.
   */
  String getBuild();
}
