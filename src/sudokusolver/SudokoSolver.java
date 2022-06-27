package SudokuSolver;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import SudokuSolver.congratulation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
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
    JButton Solve=new JButton("Solve");  
    JButton Puzzle=new JButton("New Puzzle"); 
    JButton Hint=new JButton("Hint"); 
    JButton Check = new JButton("Result");
   
    int[][] dummyBoard=new int[9][9];

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
                        board[i][j].setBackground(Color.cyan);
                    }
                    else{
                        board[i][j].setBackground(Color.yellow);
                    }
                }
                else if(i<6){
                    if(j<3){
                        board[i][j].setBackground(Color.cyan);
                    }
                    else if(j<6){
                        board[i][j].setBackground(Color.yellow);
                    }
                    else{
                        board[i][j].setBackground(Color.cyan);
                    }
                }
                else{
                    if(j<3){
                        board[i][j].setBackground(Color.yellow);
                    }
                    else if(j<6){
                        board[i][j].setBackground(Color.cyan);
                    }
                    else{
                        board[i][j].setBackground(Color.yellow);
                    }
                }
                board[i][j].setOpaque(true);
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                Board.add(board[i][j]);
            }
        }
        Reset.setBackground(Color.cyan);    
        Solve.setBackground(Color.yellow); 
        Puzzle.setBackground(Color.cyan);
        Hint.setBackground(Color.yellow);
        Check.setBackground(Color.cyan);
        
        Solve.setBounds(100,100,80,30);    
        Solve.setBackground(Color.yellow);   
        panel.add(Reset); 
        panel.add(Solve);
        panel.add(Puzzle);
        panel.add(Hint);
        panel.add(Check);
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
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Reset.addActionListener(this);
        Solve.addActionListener(this);
        Puzzle.addActionListener(this);
        Hint.addActionListener(this);
        Check.addActionListener(this);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        int[][] a=new int[9][9];
        
        print(a);
        SudokoSolver myFrame=new SudokoSolver();
        
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
    
    // is result correct or not 
    public boolean isSafe(int row, int col, int num){
        for(int d=0;d<board.length;d++){
            if(Integer.valueOf(board[row][d].getText())==num){
                return false;
            }
        }
        for(int r =0;r<board.length;r++){
            if (Integer.valueOf(board[r][col].getText())==num){
                return false;
            }
        }
        
        
        int sqrt=(int)Math.sqrt(board.length);
        int boxRowStart=row-row%sqrt;
        int boxColStart=col-col%sqrt;
 
        for(int r = boxRowStart;r<boxRowStart+sqrt;r++){
            for(int d=boxColStart;d<boxColStart+sqrt;d++){
                if(Integer.valueOf(board[r][d].getText())==num){
                    return false;
                }
            }
        }
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
            for(int i=c;i<c+3;i++){
                for(int j=d;j<d+3;j++){
                    if(a.contains(board[i][j].getText())){
                        System.out.println(i+" "+j);
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
                    isEmpty = false;
                    break;
                }
            }
            if(!isEmpty){
                break;
            }
        }
        if(isEmpty){
            return true;
        }
        for(int num=1;num<=board.length;num++){
            if(isSafe(row, col, num)){
                board[row][col].setText(""+num);
                if (solveSudoko()){
                    return true;
                }
                else{
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
    public void newPuzzle(){
        actionPerformed3();
        solveSudoko1();
        int count=0;
        int a;
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(dummyBoard[i][j]+"\t");
                ArrayList<Integer> list1=new ArrayList<Integer>();
                list1.add(i);
                list1.add(j);
                list.add(list1);
            }
            System.out.println();
        }
        actionPerformed1();
        actionPerformed2();
        while(count<9){
            a=rand.nextInt(100)%list.size();
            board[list.get(a).get(0)][list.get(a).get(1)].setText(""+dummyBoard[list.get(a).get(0)][list.get(a).get(1)]);
            list.remove(a);
            count++;
        }
        actionPerformed4();
    }
    public void Hint(){
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        int a;
        Random rand = new Random();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j].getText().equals("")){
                    ArrayList<Integer> list1=new ArrayList<Integer>();
                    list1.add(i);
                    list1.add(j);
                    list.add(list1);
                    dummyBoard[i][j]=0;
                }
                else{
                    dummyBoard[i][j]=Integer.valueOf(board[i][j].getText());
                }
            }
        }
        if(solveSudoko1()){
            a=rand.nextInt(100)%list.size();
            board[list.get(a).get(0)][list.get(a).get(1)].setText(""+dummyBoard[list.get(a).get(0)][list.get(a).get(1)]);
        }
        else{
            actionPerformed1();
            System.out.println("Sudoko solution does not exist");
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
                if(board[i][j].getText().equals("")){
                    board[i][j].setText("0");
                    count+=1;
                }
            }
        }
        return count;
    }
     public int checkValidity() {
        int count=0;
        for(int i= 0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j].getText().equals("")){
//                    board[i][j].setText("");
                    count+=1;
                }
            }
        }
        return count;
    }
     
    public void actionPerformed3() {
        for(int i= 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                dummyBoard[i][j]=0;
            }
        }
    }
    public void actionPerformed4() {
        for(int i= 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j].getText().equals("0")){
                    board[i][j].setText("");
                }
            }
        }
    }
    
    public boolean solveSudoko1(){
        int row=-1;
        int col=-1;
        boolean isEmpty = true;
        for(int i=0;i<dummyBoard.length;i++){
            for(int j=0;j<dummyBoard.length;j++){
                if(dummyBoard[i][j]==0){
                    row=i;
                    col=j;
                    isEmpty=false;
                    break;
                }
            }
            if(!isEmpty){
                break;
            }
        }
        if(isEmpty){
            return true;
        }
        for(int num=1;num<=dummyBoard.length;num++){
            if(isSafe1(row, col, num)){
                dummyBoard[row][col]=num;
                if(solveSudoko1()){
                    return true;
                }
                else{
                    dummyBoard[row][col]=0;
                }
            }
        }
        return false;
    }
    
    public boolean isSafe1(int row, int col, int num){
        for(int d=0;d<dummyBoard.length;d++){
            if(dummyBoard[row][d]==num){
                return false;
            }
        }
        for(int r =0;r<dummyBoard.length;r++){
            if(dummyBoard[r][col]==num){
                return false;
            }
        }
        int sqrt=(int)Math.sqrt(dummyBoard.length);
        int boxRowStart=row-row%sqrt;
        int boxColStart=col-col%sqrt;
 
        for(int r = boxRowStart;r<boxRowStart+sqrt;r++){
            for(int d=boxColStart;d<boxColStart+sqrt;d++){
                if(dummyBoard[r][d]==num){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==Reset) {
            actionPerformed1();
        }
        if(evt.getSource()==Solve) {
            if(actionPerformed2()<81){
                if(validate_sudoko()){
                    if(solveSudoko()){
                        print_Sudoko();
                    }
                    else{
                        actionPerformed1();
//                        System.out.println("Sudoko solution does not exist");
                        JOptionPane.showMessageDialog(null,"Sudoko solution does not exist");
                    }
                }
                else{
                    actionPerformed1();
                    System.out.println("validate sudoko Sudoko solution does not exist");
                    JOptionPane.showMessageDialog(null,"Validate sudoko, sudoko solution does not exist");
                }
            }
            else{
//                System.out.println("You need to enter some values");
                JOptionPane.showMessageDialog(null,"You need to enter some values");
                actionPerformed1();
            }
        }
        if(evt.getSource()==Puzzle){
            newPuzzle();
        }
        if(evt.getSource()==Hint){
            if(checkValidity()>0){
                actionPerformed2();
              if(validate_sudoko()){
                  actionPerformed4();
                  Hint();
              }
              else{
                  JOptionPane.showMessageDialog(null,"Your grid currently violates sodoku rules. Solution is not possible");
                  JOptionPane.showMessageDialog(null,"Reseting the Grid");
                  actionPerformed1();
              }
            }
            else{
                JOptionPane.showMessageDialog(null,"Grid is already completed");
            }
          
            
        }
        if(evt.getSource()==Check){
            System.out.println("check");
            
            if(checkValidity()==0){
                if(validate_sudoko()){
                    try{
                        f.setVisible(false);
                    }
                    catch(Exception e){
                        System.out.print(e);
                    }
                    
                    new congratulation().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Your solution is Wrong");

                    int a = JOptionPane.showConfirmDialog(null,"Do you want to try again","select",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        actionPerformed1();
                    }
                    else{
                          try{
                            f.setVisible(false);
                        }
                        catch(Exception e){
                            System.out.print(e);
                        }
                        new HomePage().setVisible(true);
                    }
                }   
            }
            else{
                JOptionPane.showMessageDialog(null,"Try to complete the grid");
            }
            
        }
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }  
}