/**Author: Lebedev Alexander
 * Date of last update: 24.09.2015
 *
 * 'Game' is main-class which reading input data from 'input.txt' in array 'var[4]'.
 * Where: var[0] - amount rows of game field;
 *       var[1] - amount columns of game field;
 *       var[2] - beginning length of snake
 *       var[3] - amount toads.
 * Check input data on correct.
 * Run game through 'new View(var[0], var[1], var[2], var[3])'.
 *
 * File 'input.txt' contain in same directory where file 'Game.java'
 * */


package practice.snake.game;


import com.sun.org.apache.bcel.internal.util.ClassPath;
import practice.snake.view.*;
import java.io.*;


public class Game {

    public static void main(String[] args) throws IOException{

        InputStream cin = Game.class.getResourceAsStream("input.txt");

        int var[] = new int[4];
        int idx = 0;
        int in = cin.read();

        while(in > -1){
            int value = in - '0';

            while(value >= 0 && value <= 9){
                var[idx] = var[idx] * 10 + value;
                in = cin.read();
                value = in - '0';

                if(value < 0 || value > 9)
                    idx++;
            }

            in = cin.read();
        }

        if(var[0] > 100 || var[1] > 100){
            System.out.println("Field is too HUGE");
            System.exit(0);
        }
        if(var[2] >= var[0] || var[2] >= var[1]){
            System.out.println("Snake is too HUGE");
            System.exit(0);
        }
        if(var[3] == 0)
        {
            System.out.println("Very few Toads");
            System.exit(0);
        }
        if(var[3] >= 100) {
            System.out.println("Toads is too much");
        }

        View newGame = new View(var[0], var[1], var[2], var[3]);
    }
}