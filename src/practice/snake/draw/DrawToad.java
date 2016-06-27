/** Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'Draw' draw character (snake or frog) on game field. Extends Draw. Implement 'Runnable'.
 *
 *  Methods: grow() - doing nothing, because it not necessary
 *          draw() - draw character on game field
 *          inBlack() - paint character on black color
 * */


package practice.snake.draw;


import practice.snake.animals.*;
import javax.swing.JPanel;
import java.awt.*;


public class DrawToad extends Draw{

    public DrawToad(Animal toad, JPanel cells[][]){
        pet = toad;
        this.cells = cells;

        (new Thread(this)).start();
    }

    public void grow(){}

    public void draw(){
        cells[pet.getLoc().get(0).getY()][pet.getLoc().get(0).getX()].setBackground(Color.GREEN);
        if (pet.tailExist() && Animal.field[pet.getTail().getY()][pet.getTail().getX()] == 0)
            cells[pet.getTail().getY()][pet.getTail().getX()].setBackground(Color.BLACK);
    }

    public void inBlack(){
        cells[pet.getLoc().get(0).getY()][pet.getLoc().get(0).getX()].setBackground(Color.BLACK);
    }

    public void run(){
        while(true){
            if(Animal.game_over)
                pet.dead();
            while(Animal.game_over){}

            pet.walk();
            draw();

            try{
                Thread.sleep(200);
            }catch(InterruptedException e){}
        }
    }
}