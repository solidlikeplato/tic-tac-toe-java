package tic.tac.toe;

public class Messages {

  public static final char TIE_GAME = ' ';

  public Messages() {
  }

  public String greeting() {
    return "Welcome to The WOPR! Would you like to play a game?";
  }

  public String formattedBoard(Board board) {
    String firstRow = " " + board.getMarkAt(1) + " | " + board.getMarkAt(2) + " | "+ board.getMarkAt(3)+ " \n";
    String horizontalRule = "---|---|---\n";
    String secondRow = " " + board.getMarkAt(4) + " | " + board.getMarkAt(5) + " | "+ board.getMarkAt(6)+ " \n";
    String thirdRow = " " + board.getMarkAt(7) + " | " + board.getMarkAt(8) + " | "+ board.getMarkAt(9)+ " ";
    return firstRow + horizontalRule + secondRow + horizontalRule + thirdRow;
  }

  public String prompt(char currentPlayerSymbol) {
    return "1 2 3\n4 5 6\n7 8 9\nWhere would you like to put your " + currentPlayerSymbol + "?";
  }

  public String outcome(char winningSymbol) {
    if (winningSymbol == TIE_GAME) {
      return "There is no winner! The only winning move is not to play!";
    } else {
      return "Congratulations to " + winningSymbol + ", our winner!";
    }
  }
  public String informPlayerInvalidInput() {
    return "Invalid Input! please try again.";
  }
  public String promptForNumberOfPlayers() {
    return "Would you Like 1 or 2 Human Players?";
  }
}
