package tic.tac.toe;

public class UI {
  Game game;

  public UI(Game newGame) {
    game = newGame;
  }

  public String getGreeting() {
    return "Welcome to Tic Tac Toe";
  }

  public void addMark(int position) {
    game.addMark(position);
  }

  public String displayGame() {
    String firstRow = " " + game.getMarkAt(1) + " | " + game.getMarkAt(2) + " | "+ game.getMarkAt(3)+ " \n";
    String horizontalRule = "---|---|---\n";
    String secondRow = " " + game.getMarkAt(4) + " | " + game.getMarkAt(5) + " | "+ game.getMarkAt(6)+ " \n";
    String thirdRow = " " + game.getMarkAt(7) + " | " + game.getMarkAt(8) + " | "+ game.getMarkAt(9)+ " ";
    return firstRow + horizontalRule + secondRow + horizontalRule + thirdRow;
  }

  public String prompt() {
    return "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your X?";
  }
}