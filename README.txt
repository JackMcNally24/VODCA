How our program works: A Short Story
Our interface for the Animator Model as a whole is AnimatorModel, which contains the methods
needed to add shapes to our list of shapes in the mode, and add motions to the list of motions we 
wish to do. It also applies the motions to the corresponding shapes.
The 3 variables in our implementation of the interface are shapes, the list of all of the shapes,
motions, the list of all of the motions we want to apply, and build, which is the outputted data
about all of the motions.
The purpose of the implementation is to apply the motions to the shapes.

The interface for Shapes is IShapes, which contains the getter and setter methods for the different
variables in a Shape. It is implemented by the abstract class AShape, which contains the variables
id, position, size, and color. The id represents the id number of the shape, which should always
be unique for each new shape (non enforced invariant). The position is a Posn which holds the
x and y coordinates of where the shape will be placed. The size is a Posn which holds the width
and height of the shape, and color is simply the color of the shape. AShape allows us to implement
other shapes in the future, but for now, the only shapes we have implemented are Oval and Rectangle.

A Posn contains 2 float values. We chose to use float values here instead of ints because when
dealing with small motions over long periods of time, it is necessary to have decimal values for
the intermediate positions.

The Motion class represents the motions that we wish to impose on our shapes. The variables
newPosition, size, and color all are basically the same thing as in AShape, but they represent
what the motion wants to change from the original shape. The variable id has to correspond to the
same id in an AShape, in order for the Motion to know what shape to act on.

When applying the motion in the class AnimatorModelImpl with the method applyMotions(), the method
finds how much the position, size, and color should change each tick, and then does it for the
specified amount of ticks in each motion. If a motion does not call to an existing shape an
exception will be thrown, and a shape does not necessarily have to have a corresponding Motion.

AnimatorModelImplTest is our test class.

I hope we did this right,
Thanks love ya,
Jack and Jack