package SudokuSolver;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

public class SudokoSolver implements ActionListener{
    JFrame f= new JFrame("Panel Example"); 
    JPanel Board = new JPanel(new GridLayout(9,9));
    JTextField[][] board=new JTextField[9][9];
    JPanel panel=new JPanel();  
    JButton Reset=new JButton("Reset");   
    JButton b2=new JButton("Solve");  

    SudokoSolver(){
        for(int i= 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                board[i][j] = new JTextField("");
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                Font font = new Font("Arial", Font.PLAIN, 20);
                board[i][j].setFont(font);
                board[i][j].setText("   ");
                if(i<3){
                    if(j<3){
                      board[i][j].setBackground(Color.yellow);  
                    }
                    else if(j<6){
                        board[i][j].setBackground(Color.red);
                    }
                    else{
                        board[i][j].setBackground(Color.green);
                    }
                }
                else if(i<6){
                    if(j<3){
                        board[i][j].setBackground(Color.blue);
                    }
                    else if(j<6){
                        board[i][j].setBackground(Color.orange);
                    }
                    else{
                        board[i][j].setBackground(Color.cyan);
                    }
                }
                else{
                    if(j<3){
                        board[i][j].setBackground(Color.magenta);
                    }
                    else if(j<6){
                        board[i][j].setBackground(Color.pink);
                    }
                    else{
                        board[i][j].setBackground(Color.lightGray);
                    }
                }
                board[i][j].setOpaque(true);
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                Board.add(board[i][j]);
            }
        }
        //board[0][0].setText("95");
        Reset.setBounds(50,100,80,30);    
        Reset.setBackground(Color.yellow);   
        b2.setBounds(100,100,80,30);    
        b2.setBackground(Color.green);   
        panel.add(Reset); 
        panel.add(b2);
        GridBagLayout layout = new GridBagLayout();
        f.setLayout(layout);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=2;
        Board.setSize(1000,1000);
        f.add(Board,gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        f.add(panel,gbc);
        f.setSize(500,500);  
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Reset.addActionListener(this);
        b2.addActionListener(this);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        int[][] a=new int[9][9];
        
        print(a);
        SudokoSolver myFrame=new SudokoSolver();
        //myFrame.init();
        //myFrame.actionPerformed1();
        
    }
    public static void print(int[][] grid){
        System.out.println("Sudoko");
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(grid[i][j]+"\t");
            }
            System.out.println();
        }
        
    }
    
    public boolean isSafe(int row, int col, int num){
        
        // Row has the unique (row-clash)
        for(int d=0;d<board.length;d++){
            // Check if the number we are trying to
            // place is already present in
            // that row, return false;
            if(Integer.valueOf(board[row][d].getText())==num){
                return false;
            }
        }
 
        // Column has the unique numbers (column-clash)
        for(int r =0;r<board.length;r++){
            // Check if the number
            // we are trying to
            // place is already present in
            // that column, return false;
            if (Integer.valueOf(board[r][col].getText())==num){
                return false;
            }
        }
 
        // Corresponding square has
        // unique number (box-clash)
        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;
 
        for(int r = boxRowStart;r<boxRowStart+sqrt;r++){
            for(int d=boxColStart;d<boxColStart+sqrt;d++){
                if(Integer.valueOf(board[r][d].getText())==num){
                    return false;
                }
            }
        }
        // if there is no clash, it's safe
        return true;
    }
    public boolean validate_sudoko(){
        HashSet<String> a=new HashSet<String>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(a.contains(board[i][j].getText())){
                    return false;
                }
                else{
                    if(!board[i][j].getText().equals("0")){
                        a.add(board[i][j].getText());
                    }
                }
            }
            a.clear();
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(a.contains(board[j][i].getText())){
                    return false;
                }
                else{
                    if(!board[j][i].getText().equals("0")){
                        a.add(board[j][i].getText());
                    }
                }
            }
            a.clear();
        }
        int c=0;
        int d=0;
        boolean flag=true;
        while(flag){
            for(int i=0;i<c+3;i++){
                for(int j=0;j<d+3;j++){
                    if(a.contains(board[i][j].getText())){
                        return false;
                    }
                    else{
                        if(!board[i][j].getText().equals("0")){
                            a.add(board[i][j].getText());
                        }
                    }
                }
            } 
            a.clear();
            d+=3;
            if(d==9){
                c+=3;
                d=0;
            }
            if(c==9 && d==0){
                flag=false;
            }
        }
        return true;
    }
    public boolean solveSudoko(){
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for(int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                if(Integer.valueOf(board[i][j].getText()) == 0){
                    row = i;
                    col = j;
 
                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty){
                break;
            }
        }
 
        // No empty space left
        if(isEmpty){
            return true;
        }
 
        // Else for each-row backtrack
        for(int num=1;num<=board.length;num++){
            if(isSafe(row, col, num)){
                board[row][col].setText(""+num);
                if (solveSudoko()){
                    // print(board, n);
                    return true;
                }
                else{
                    // replace it
                    board[row][col].setText("0");
                }
            }
        }
        return false;
    }
 
    public void print_Sudoko(){
        int N=board.length;
        // We got the answer, just print it
        for(int r=0;r<N;r++){
            for(int d=0;d<N;d++){
                System.out.print(board[r][d].getText());
                System.out.print(" ");
            }
            System.out.print("\n");
 
            if ((r + 1) % (int)Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }
    
    
    
    public void actionPerformed1() {
        for(int i= 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                board[i][j].setText("");
            }
        }
    }
    public int actionPerformed2() {
        int count=0;
        for(int i= 0;i<9;i++){
            for(int j=0;j<9;j++){
                //System.out.println(board[i][j].getText()+" "+board[i][j].getText().equals(""));
                if(board[i][j].getText().equals("")){
                    board[i][j].setText("0");
                    count+=1;
                }
                //System.out.println(board[i][j].getText()+" "+board[i][j].getText().equals(""));
            }
        }
        return count;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == Reset) {
            //Sudoko_Solver myFrame=new Sudoko_Solver();
            //myFrame.init();
            actionPerformed1();
        }
        if (evt.getSource() == b2) {
            //Sudoko_Solver myFrame=new Sudoko_Solver();
            //myFrame.init();
            if(actionPerformed2()<81){
                if(validate_sudoko()){
                    if(solveSudoko()){
                        //print_Sudoko()
                        print_Sudoko();
                    }
                    else{
                        actionPerformed1();
                        System.out.println("Sudoko solution does not exist");
                    }
                }
                else{
                    actionPerformed1();
                    System.out.println("Sudoko solution does not exist");
                }
            }
            else{
                System.out.println("You need to enter some values");
                actionPerformed1();
            }
        }
    }
    
}
