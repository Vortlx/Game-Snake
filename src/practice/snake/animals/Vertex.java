/**Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Vertex' describe common parts of which consist characters in game: snake, toad, etc.
 * Fields: x,y - coordinates on game field.
 *
 * Methods: getX() - return x coordinate of Vertex
 *          getY() - return y coordinate of Vertex
 *          setLoc() - change current coordinates on new coordinates
 *
 * Class 'Head' describe head of snake.
 * Where: dir - snake's walk direction ('R' - right;  'L' - left;    'U' - up;    'D' - down)
 *
 * Methods: getDir() - return direction of head
 *          setDir() - change current direction on new direction
 * */


package practice.snake.animals;


public class Vertex{

    private int x,y;

    public Vertex(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLoc(int x, int y){
        this.x = x;
        this.y = y;
    }
}


class Head extends Vertex{

    private char dir;

    public Head(char dir, int x, int y){
        super(x,y);
        this.dir = dir;
    }

    public char getDir() {
        return dir;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }
}