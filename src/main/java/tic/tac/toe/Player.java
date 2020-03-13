package tic.tac.toe;

public interface Player {
    char getSymbol();
    boolean didMove();
    void makeAMove(Board board);
}