# Flappy Dragon

The goal of the project is to build a scrolling 2D game called “Flappy Dragon” for the popular 
Android operating system. 

<img src="https://raw.githubusercontent.com/nadershamma/flappy-dragon/master/misc/Screenshot_2018-06-05-12-56-09.png"
alt="Demo Screenshot 1" />

<img src="https://raw.githubusercontent.com/nadershamma/flappy-dragon/master/misc/Screenshot_2018-06-05-12-57-02.png"
alt="Demo Screenshot 2" />

## Outline
The project goal was to create a side scrolling 2D mobile game built with JAVA using the libgdx 
game engine. The objective of the game is to manoeuvre a dragon along the y-axis, avoiding the 
incoming monsters and gaining points by collecting coins. Inspiration for the dragon’s mechanics 
was taken from the popular 2013 mobile game “Flappy Bird”

The game will include animated sprites that scroll through the screen, uses the mobile device’s 
touch sensors, vibration feature and sound capability to create an enjoyable gaming experience for 
the user. In addition, it will use Android’s shared preferences feature to store the user’s high 
score.

There are a number of complexities that the program needed to handle including:
 
- Rendering 2D graphics using images and sprites.
- Animating the graphics as they scroll across the screen
- Ensuring that the game viewport fits correctly on screen
- Making the main game character respond to the user’s input via touch
- Detect and manage collision detection when the main character touches another game object
- Playing sound in response to the character’s activity
- Keeping track of the user’s score
- Vibrating when the character touches an enemy character or the ground and therefore dies.

The game classes as a whole follow a simple logic:

- The main game class initialises the programme
    - The AssetBuilder class loads all the internal assets such as textures, sprites and sounds, ready to be used by the control and view classes.
    - The main view class, controlling the screen is called upon to set up the view port.
- The main screen class sets up the game width and height, instantiates the main game controller and graphics rendering controller, adds the graphics controller to the game controller and initialises the input controller. The Screen class will control the game loop that cycles through the classes as the game is played.
- The game controller instantiates the main character object, sets up the motion controller, determines the game state, score, game boundary and checks for collision between the main character and the other game objects.
- The motion controller instantiates all game world objects and characters that scroll across the screen, looping through them and updating them as the game progresses.
- The graphics controller will use the textures loaded by the asset builder to draw the game objects on screen and animate them where necessary using the libgdx library.
- Each one of the game objects, characters and background, will be defined via their own class, that will determine their main behaviour, such as their position on screen, dimensions and movement.

