/**Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Snake' describe snake: coordinates of head and body, walk algorithm.
 *
 * Methods: setNewChar() - change snake's size on the initial size,
 *                          head's direction on 'right', put snake on top left corner
 *          setDir(char ch) - change current direction on new direction
 *          getDir() - return direction
 *          getTail() - return reference on tail of snake (tail is coordinates last place where was snake)
 *          tailExist() - return false if tail has coordinates low than (0, 0) else true
 *          getLoc() - return 'List<Vertex>', which contain coordinates of parts of pet
 *          grow() - describe snake's behavior when snake ate toad
 *          dead() - painting all field where exist snake by 0
 *          walk() - describe walk behavior of snake (change head's coordinates on new according direction)
 *          bodyWalk() - change coordinates of body(coordinates of first element became last coordinates of head,
 *                                                  coordinates of second element became last coordinates of first element
 *                                                  and etc)
 * */


package practice.snake.animals;


import java.util.*;
import java.util.List;


public class Snake extends Animal{

    private Head head;
    private List<Vertex> body = new ArrayList<Vertex>();
    private Vertex tail = new Vertex(-1, -1);
    private int length;

    public Snake(int length) {
        this.length = length;
        head = new Head('R', length - 1, 0);
        field[0][length - 1] = 1;

        for (int i = length - 2; i >= 0; i--) {
            body.add(new Vertex(i, 0));
            field[0][i] = 2;
        }
    }

    public void setNewChar() {
        head.setLoc(length - 1, 0);
        head.setDir('R');
        field[0][length - 1] = 1;
        body.clear();

        for (int i = length - 2; i >= 0; i--) {
            body.add(new Vertex(i, 0));
            field[0][i] = 2;
        }
        tail.setLoc(-1, -1);
    }

    public void setDir(char ch) {
        head.setDir(ch);
    }

    public char getDir(){
        return head.getDir();
    }

    public Vertex getTail(){
        return tail;
    }

    public boolean tailExist(){
        if(tail.getX() < 0 && tail.getY() < 0)
            return false;
        return true;
    }

    public List<Vertex> getLoc(){
        List<Vertex> res = new ArrayList(body);
        res.add(0, head);

        return res;
    }

    public void grow(){
        body.add(new Vertex(tail.getX(), tail.getY()));
        tail.setLoc(-1, -1);
    }

    public void dead(){
        field[head.getY()][head.getX()] = 0;
        for(Vertex ver: body){
            field[ver.getY()][ver.getX()] = 0;
        }
        if(tailExist())
            field[tail.getY()][tail.getX()] = 0;
    }

    public void walk(){
        try{
            int headX = head.getX();
            int headY = head.getY();

            if(head.getDir() == 'U' && headY - 1 >= 0
                    && field[headY - 1][headX] != 2){
                head.setLoc(headX,headY - 1);
                bodyWalk(headX, headY);
            }
            else if(head.getDir() == 'D' && headY + 1 < field.length
                    && field[headY + 1][headX] != 2){
                head.setLoc(headX,headY + 1);
                bodyWalk(headX, headY);
            }
            else if(head.getDir() == 'L' && headX - 1 >= 0
                    && field[headY][headX - 1] != 2){
                head.setLoc(headX - 1,headY);
                bodyWalk(headX, headY);
            }
            else if(head.getDir() == 'R' && headX + 1 < field[0].length
                    && field[headY][headX + 1] != 2){
                head.setLoc(headX + 1,headY);
                bodyWalk(headX , headY);
            }
            else{
                game_over = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Snake is escaped");
        }
    }

    private void bodyWalk(int newX, int newY){
        field[head.getY()][head.getX()] = 1;

        for(Vertex ver: body){
            if(ver.getX() < 0 || ver.getY() < 0)
                continue;

            int tX = ver.getX();
            int tY = ver.getY();

            ver.setLoc(newX, newY);
            field[newY][newX] = 2;

            newX = tX;
            newY = tY;
        }

        tail.setLoc(newX, newY);
        if(tailExist())
            field[tail.getY()][tail.getX()] = 0;
    }
}