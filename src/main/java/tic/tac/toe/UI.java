package tic.tac.toe;

public class UI {
  Board board;

  public UI(Board newBoard) {
    board = newBoard;
  }

  public UI() {
    board = new Board();
  }

  public String getGreeting() {
    return "Welcome to Tic Tac Toe";
  }

  public void addMark(int position) {
    board.addMark(position);
  }

  public String displayBoard() {
    String firstRow = " " + board.getMarkAt(1) + " | " + board.getMarkAt(2) + " | "+ board.getMarkAt(3)+ " \n";
    String horizontalRule = "---|---|---\n";
    String secondRow = " " + board.getMarkAt(4) + " | " + board.getMarkAt(5) + " | "+ board.getMarkAt(6)+ " \n";
    String thirdRow = " " + board.getMarkAt(7) + " | " + board.getMarkAt(8) + " | "+ board.getMarkAt(9)+ " ";
    return firstRow + horizontalRule + secondRow + horizontalRule + thirdRow;
  }

  public String prompt() {
    return "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your " + board.getCurrentPlayerSymbol() + "?";
  }
}
