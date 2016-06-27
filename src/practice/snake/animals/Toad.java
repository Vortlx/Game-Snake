/**Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Toad' describe toads: coordinates, walk algorithm.
 *
 * Methods: getLoc() - return 'List<Vertex >' (contains x and y) of toad
 *          grow() - doing nothing, because it not necessary
 *          setDir(char ch) - doing nothing, because it not necessary
 *          getDir() - return character '?', because toad has not direction
 *          getTail() - return reference on tail of toad (tail is coordinates last place where was toad)
 *          tailExist() - return false if tail has coordinates low than (0, 0) else true
 *          setNewChar() - change current coordinates of Toad on random coordinates
 *          dead() - painting all field where exist toad by 0
 *          walk() - describe walk behavior of toads (random walk)
 * */


package practice.snake.animals;


import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class Toad extends Animal{

    private List<Vertex> loc;
    private Vertex tail;
    private Vertex newLoc;
    private Random rnd = new Random();

    public Toad(){
        int newX = Math.abs(rnd.nextInt() % field[0].length);
        int newY = Math.abs(rnd.nextInt() % field.length);
        loc = new ArrayList();
        newLoc = new Vertex(newX, newY);
        tail = new Vertex(-1, -1);

        loc.add(newLoc);
    }

    public List<Vertex> getLoc(){
        return loc;
    }

    public void grow(){}

    public void setDir(char ch){}

    public char getDir(){
        char ch = '?';
        return ch;
    }

    public Vertex getTail(){
        return tail;
    }

    public boolean tailExist(){
        if(tail.getX() < 0 && tail.getY() < 0)
            return false;
        return true;
    }

    public void setNewChar(){
        int newX = Math.abs(rnd.nextInt() % field[0].length);
        int newY = Math.abs(rnd.nextInt() % field.length);

        newLoc.setLoc(newX, newY);
        loc.set(0, newLoc);
    }

    public void dead(){
        field[loc.get(0).getY()][loc.get(0).getX()] = 0;
    }

    public void walk(){
        try{
            int tX = loc.get(0).getX();
            int tY = loc.get(0).getY();
            tail.setLoc(tX, tY);
            int dir;

            while(true){
                tail.setLoc(tX, tY);
                field[tail.getY()][tail.getX()] = 0;
                Animal.field[tY][tX] = 0;
                dir = Math.abs(rnd.nextInt() % 100);

                if(dir < 25 && tY - 1 >= 0 && field[tY - 1][tX] == 0){
                    newLoc.setLoc(tX, tY - 1);
                    loc.set(0, newLoc);
                    break;
                }
                else if(dir >= 25 && dir < 50 && tY + 1 < field.length && field[tY + 1][tX] == 0){
                    newLoc.setLoc(tX, tY + 1);
                    break;
                }
                else if(dir >= 50 && dir < 75 && tX - 1 >= 0 && field[tY][tX - 1] == 0){
                    newLoc.setLoc(tX - 1, tY);
                    loc.set(0, newLoc);
                    break;
                }
                else if(dir >= 75 && tX + 1 < field[0].length && field[tY][tX + 1] == 0){
                    newLoc.setLoc(tX + 1, tY);
                    loc.set(0, newLoc);
                    break;
                }
            }
            field[loc.get(0).getY()][loc.get(0).getX()] = 3;
            field[tail.getY()][tail.getX()] = 0;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Someone Toad is escaped");
        }
    }
}