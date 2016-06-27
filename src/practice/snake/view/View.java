/**Author: Lebedev Alexander
 * Date of last update: 01.10.2015
 *
 * Class 'View' create game field and menu bar.
 * Menu bar consist of two buttons 'start' (start game) and 'end'(end game), label snake's head direction and score.
 * Game field consist of two dimensional array of 'JPanel', which paint in black.
 * Method control() describes react of game field on different event (in this case on click mouse buttons)
 *
 * Class 'Score' describe score's change when snake ate toad or snake ate itself. Implement 'Runnable'.
 * Method zero() change current score on zero
 * */


package practice.snake.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import practice.snake.animals.*;
import practice.snake.draw.*;


public class View extends JFrame
{
    JPanel cells[][];
    final JButton start;
    final JButton end;
    JLabel dir_lbl;

    final Draw drawSnake;
    final Draw drawFood[];

    public View(final int row, final int column, final  int length, final  int amount){
        super("Snake");
        Box menu = Box.createHorizontalBox();
        JPanel field = new JPanel();

        field.setLayout(new GridLayout(row, column, 1, 1));

        cells = new JPanel[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                cells[i][j] = new JPanel();
                field.add(cells[i][j]);
                cells[i][j].setBackground(Color.BLACK);
                control(cells[i][j]);
                cells[i][j].setVisible(true);
            }
        }

        Animal.field = new int [row][column];
        drawSnake = new DrawSnake(new Snake(length), cells);
        drawFood = new DrawToad[amount];

        for(int i = 0; i < amount; i++){
            drawFood[i] = new DrawToad(new Toad(), cells);
        }

        // -= MENU =-
        start = new JButton("start");
        end = new JButton("end");
        JLabel score_lbl = new JLabel("score : ");
        dir_lbl = new JLabel("direction: ");
        final Score score = new Score();

        end.setEnabled(false);
        start.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent event){
                                            start.setEnabled(false);
                                            end.setEnabled(true);

                                            drawSnake.inBlack();
                                            for(Draw food : drawFood)
                                            {
                                                food.inBlack();
                                            }

                                            dir_lbl.setText("direction: Right");
                                            drawSnake.setNewChar();
                                            for(Draw food: drawFood)
                                            {
                                                food.setNewChar();
                                            }

                                            score.zero();
                                            Animal.game_over = false;
                                        }
                                    });
        end.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                end.setEnabled(false);
                start.setEnabled(true);
                Animal.game_over = true;
            }
        });

        start.setVisible(true);
        end.setVisible(true);
        score_lbl.setVisible(true);
        score.setVisible(true);

        menu.add(start);
        menu.add(end);
        menu.add(Box.createHorizontalGlue());
        menu.add(dir_lbl);
        menu.add(Box.createHorizontalGlue());
        menu.add(score_lbl);
        menu.add(score);

        // -= SETTING =-
        menu.setVisible(true);
        field.setVisible(true);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(menu, BorderLayout.NORTH);
        add(field,BorderLayout.CENTER);
    }

    public void control(JPanel pan){
        pan.addMouseListener(new MouseListener(){
                                    public void mouseClicked(MouseEvent event){

                                        char dir = drawSnake.getDir();
                                        int size = drawSnake.getLoc().size();
                                        Vertex head = drawSnake.getLoc().get(0);
                                        Vertex neck = null;

                                        if(size > 1)
                                            neck = drawSnake.getLoc().get(1);

                                        if(event.getButton() == MouseEvent.BUTTON1){
                                            if(dir == 'U' && size > 1 && head.getX() - 1 != neck.getX()){
                                                drawSnake.setDir('L');
                                                dir_lbl.setText("direction: Left");
                                            }
                                            else if(dir == 'D' && size > 1 && head.getX() + 1 != neck.getX()){
                                                drawSnake.setDir('R');
                                                dir_lbl.setText("direction: Right");
                                            }
                                            else if(dir == 'L' && size > 1 && head.getY() + 1 != neck.getY()){
                                                drawSnake.setDir('D');
                                                dir_lbl.setText("direction: Down");
                                            }
                                            else if(dir == 'R' && size > 1 && head.getY() - 1 != neck.getY()){
                                                drawSnake.setDir('U');
                                                dir_lbl.setText("direction: Up");
                                            }
                                        }
                                        if(event.getButton() == MouseEvent.BUTTON3){
                                            if(dir == 'U' && size > 1 && head.getX() + 1 != neck.getX()){
                                                drawSnake.setDir('R');
                                                dir_lbl.setText("direction: Right");
                                            }
                                            else if(dir == 'D' && size > 1 && head.getX() - 1 != neck.getX()){
                                                drawSnake.setDir('L');
                                                dir_lbl.setText("direction: Left");
                                            }
                                            else if(dir == 'L' && size > 1 && head.getY() - 1 != neck.getY()){
                                                drawSnake.setDir('U');
                                                dir_lbl.setText("direction: Up");
                                            }
                                            else if(dir == 'R' && size > 1 && head.getY() + 1 != neck.getY()){
                                                drawSnake.setDir('D');
                                                dir_lbl.setText("direction: Down");
                                            }
                                        }
                                    }
                                    public void mouseEntered(MouseEvent event){}
                                    public void mouseExited(MouseEvent event){}
                                    public void mouseReleased(MouseEvent event){}
                                    public void mousePressed(MouseEvent event){}
                                }
        );
    }

    private class Score extends JLabel implements Runnable
    {
        Integer amount = 0;

        Score(){
            super("0");
            (new Thread(this)).start();
        }

        public void run(){
            while(true){
                while(Animal.game_over){}

                Vertex head = drawSnake.getLoc().get(0);

                for(Draw food: drawFood)
                {
                    if(head.getX() == food.getLoc().get(0).getX() && head.getY() == food.getLoc().get(0).getY()){

                        drawSnake.grow();

                        amount++;
                        setText(amount.toString());
                        food.setNewChar();
                    }
                }

                if(Animal.game_over)
                {
                    start.setEnabled(true);
                    end.setEnabled(false);
                }
            }
        }

        public void zero(){
            amount = 0;
            setText(amount.toString());
        }
    }
}