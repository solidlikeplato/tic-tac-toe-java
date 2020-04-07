package tic.tac.toe;

public class Game {
  private InputOutput inputOutput;
  private Messages messages;
  private Board board;
  private Player player1;
  private Player player2;
  private Player currentPlayer;
  private Player winningPlayer = null;
  private Rules rules;

  public Game(Messages messages, InputOutput inputOutput, Board board, Player player1, Player player2) {
    this.inputOutput = inputOutput;
    this.messages = messages;
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
    rules = new Rules();
  }

  public Game(Messages messages, InputOutput inputOutput, Board board, Player player1, Player player2, Rules rules) {
    this.inputOutput = inputOutput;
    this.messages = messages;
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
    this.rules = rules;
    currentPlayer = player1;

  }

  public boolean isWinner() {
    return (rules.determineStatus(board, currentPlayer) == GameStatus.PLAYER_WINS);
  }

  private boolean isGameOver() {
    return rules.determineStatus(board, currentPlayer) != GameStatus.IN_PROGRESS;
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

    inputOutput.sendOutput(messages.formattedBoard(board));
    while (keepPlaying()) {
      if (!isGameOver()) {
        inputOutput.sendOutput(messages.prompt(currentPlayer.getSymbol()));
        takeATurn();
      }


      if (currentPlayer.didMove()) {
        changePlayer();
      }

      inputOutput.sendOutput("\n" + messages.formattedBoard(board));

    }

    if (isWinner()){
      winningPlayer = currentPlayer;
    }
    inputOutput.sendOutput(messages.outcome(winningPlayer == null ? Messages.TIE_GAME : winningPlayer.getSymbol()));
  }

  private boolean keepPlaying() {
    return rules.determineStatus(board, currentPlayer) == GameStatus.IN_PROGRESS;
  }
}
