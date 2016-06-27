/**Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * 'Animal' is abstract class, which contains common methods of all game characters.
 * Also contains game field and flag of 'game over'
 *
 * Abstract methods: getLoc() - return 'List<Vertex>', which contain coordinates of parts of 'Animal'
 *                   setNewChar() - change current 'Animal''s coordinates on new coordinates(it depend from pet('Snake' or 'Toad'))
 *                   grow() - allow pet to grow, if it is possible
 *                   getDir() - return direction
 *                   setDir(char ch) - change direction of pet, if it is possible
 *                   getTail() - return 'Vertex' of tail
 *                   tailExist() - return false if tail has coordinates low than (0, 0) else true
 *                   dead() - painting all field where exist 'Animal' by 0
 *                   walk() - describe behavior character ()
 * */


package practice.snake.animals;


import java.util.List;


public abstract class Animal{

    public static int [][] field;
    public static volatile boolean game_over = true;

    public abstract List<Vertex> getLoc();

    public abstract void setNewChar();

    public abstract void grow();

    public abstract char getDir();

    public abstract void setDir(char ch);

    public abstract Vertex getTail();

    public abstract boolean tailExist();

    public abstract void dead();

    public abstract void walk();
}