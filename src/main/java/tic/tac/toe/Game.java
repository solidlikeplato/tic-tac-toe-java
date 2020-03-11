package tic.tac.toe;

public class Game {
  private InputOutput inputOutput;
  private Messages messages;
  private Board board;
  private Player player1;
  private Player player2;
  private Player currentPlayer;
  private Player winningPlayer = null;

  public Game(Messages messages, InputOutput inputOutput, Board board, Player player1, Player player2) {
    this.inputOutput = inputOutput;
    this.messages = messages;
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
  }

  public boolean isWinner() {
    int[][] winningCombinations = {{1,4,7}, {2,5,8}, {3,6,9}, {1,2,3}, {4,5,6}, {7,8,9}, {1,5,9}, {3,5,7}};
    for (int[] column: winningCombinations) {
      char mark1 = board.getMarkAt(column[0]);
      char mark2 = board.getMarkAt(column[1]);
      char mark3 = board.getMarkAt(column[2]);
      if (mark1 == mark2 && mark2 == mark3 && !board.isCellEmpty(column[1])) {
        return true;
      }
    }
    return false;
  }

  private boolean isGameOver() {
    return board.isBoardFull() || isWinner();
  }

  private void changePlayer() {
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
  }

  public char getCurrentPlayerSymbol() {
    return currentPlayer.getSymbol();
  }

  public void takeATurn() {
    currentPlayer.makeAMove(board);
  }

  public void run() {
    boolean keepPlaying = true;

    inputOutput.sendOutput(messages.formattedBoard(board));
    while (keepPlaying) {
      if (!isGameOver()) {
        inputOutput.sendOutput(messages.prompt(currentPlayer.getSymbol()));
        takeATurn();
      }

      if (isWinner()){
        winningPlayer = currentPlayer;
        inputOutput.sendOutput("Winner is " + currentPlayer.getSymbol());
      }

      if (currentPlayer.didMove()) {
        changePlayer();
      }

      keepPlaying = !isGameOver();
      inputOutput.sendOutput("\n" + messages.formattedBoard(board));
      
    }

    inputOutput.sendOutput(messages.outcome(winningPlayer == null ? Messages.TIE_GAME : winningPlayer.getSymbol()));
  }
}
