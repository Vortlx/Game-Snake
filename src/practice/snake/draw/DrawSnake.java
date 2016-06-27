/** Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Draw' draw character (snake or frog) on game field. Extends Draw. Implement 'Runnable'.
 *
 *  Fields: canGrow - if character can grow increments by 1
 *
 *  Methods: setNewChar() - change current pet's coordinates on new coordinates(it depend from pet('Snake' or 'Toad'))
 *           grow() - if canGrow more than 0, call grow() method of 'Animal'
 *           draw() - draw character on game field
 *           inBlack() - paint character on black color
 * */


package practice.snake.draw;


import practice.snake.animals.*;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ConcurrentModificationException;


public class DrawSnake extends Draw{

    private int canGrow = 0;

    public DrawSnake(Animal snake, JPanel cells[][]){
        pet = snake;
        this.cells = cells;

        (new Thread(this)).start();
    }

    public void setNewChar(){
        pet.setNewChar();
    }

    public void grow(){
        canGrow ++;
    }

    public void draw(){
        boolean headCol = true;
        for(Vertex ver: pet.getLoc()){
            if(headCol){
                cells[ver.getY()][ver.getX()].setBackground(Color.ORANGE);
                headCol = false;
            }
            else{
                if(ver.getX() >= 0 && ver.getY() >= 0)
                    cells[ver.getY()][ver.getX()].setBackground(Color.YELLOW);
            }
        }
        if(pet.tailExist())
            cells[pet.getTail().getY()][pet.getTail().getX()].setBackground(Color.BLACK);
    }

    public void inBlack(){
        for(Vertex ver: pet.getLoc()){
            cells[ver.getY()][ver.getX()].setBackground(Color.BLACK);
        }
        if(pet.tailExist()){
            cells[pet.getTail().getY()][pet.getTail().getX()].setBackground(Color.BLACK);
        }
    }

    public void run(){
        while(true){
            if(Animal.game_over)
                pet.dead();
            while(Animal.game_over){}

            try {
                pet.walk();
                if(canGrow > 0){
                    pet.grow();
                    canGrow --;
                }
            }catch(ConcurrentModificationException e){}
            draw();

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}
        }
    }
}