/** Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Draw' draw character (snake or frog) on game field. Implement 'Runnable'.
 *
 * Fields: pet - it is reference on 'Animal', which draw on game field.
 *          cells - it is reference on array of 'JPanel' (game field)
 *
 *  Abstract methods: draw() - draw character on game field
 *          inBlack() - paint character on black color
 *          grow() - allow pet to grow, if it is possible
 *
 * Methods: setDir(char ch) - change direction of pet, if it is possible
 *          getDir() - return direction
 *          getLoc() - return 'List<Vertex>', which contain coordinates of parts of pet
 *          getTail() - return 'Vertex' of tail
 *          setNewChar() - change current pet's coordinates on new coordinates(it depend from pet('Snake' or 'Toad'))
 * */


package practice.snake.draw;


import practice.snake.animals.*;
import java.util.List;
import javax.swing.JPanel;


public abstract class Draw implements Runnable{

    protected Animal pet;
    protected JPanel cells[][];

    public abstract void draw();

    public abstract void inBlack();

    public abstract void run();

    public abstract void grow();

    public void setDir(char ch){
        pet.setDir(ch);
    }

    public char getDir(){
        return pet.getDir();
    }

    public List<Vertex> getLoc(){
        return pet.getLoc();
    }

    public Vertex getTail(){
        return pet.getTail();
    }

    public void setNewChar(){
        pet.setNewChar();
    }
}