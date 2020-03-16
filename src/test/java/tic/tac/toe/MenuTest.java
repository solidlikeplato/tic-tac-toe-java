package tic.tac.toe;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {
    private InputOutput inputOutput;
    private Menu menu;
    private Messages messages = new Messages();
    private Game computerGame = mock(Game.class);
    private Game twoPlayerGame = mock(Game.class);
    private Menu testMenu;

    @Before
    public void setUp() {
        inputOutput = mock(ConsoleInputOutput.class);
        menu = new Menu(inputOutput);
        testMenu  = spy( new Menu(inputOutput));
    }
    @Test
    public void menuDisplaysGreeting() {
        when(inputOutput.getInput()).thenReturn(2);
        menu.createGame();
        verify(inputOutput).sendOutput(messages.greeting());
    }

    @Test
    public void menuAsksOnceForPlayersWith2() {
        when(inputOutput.getInput()).thenReturn(2);
        menu.createGame();
        verify(inputOutput, times(1)).sendOutput(messages.promptForNumberOfPlayers());
    }

    @Test
    public void menuAsksOnceForPlayersWith1() {
        when(inputOutput.getInput()).thenReturn(1);
        menu.createGame();
        verify(inputOutput, times(1)).sendOutput(messages.promptForNumberOfPlayers());
    }

    @Test
    public void menuRepeatsAskingForPlayersWith3() {
        when(inputOutput.getInput()).thenReturn(3).thenReturn(2);
        menu.createGame();
        verify(inputOutput, times(2)).sendOutput(messages.promptForNumberOfPlayers());
    }

    @Test
    public void menuMakesComputerGameWhenTold1Player() {
        when(inputOutput.getInput()).thenReturn(1);
        doReturn(computerGame).when(testMenu)
                .makeGame(any( Messages.class ), any( InputOutput.class ),
                        any( Board.class ), any( HumanPlayer.class ),
                        any( DumbComputer.class ));
        assertEquals(testMenu.createGame(), computerGame);
    }

    @Test
    public void menuMakes2PlayerGameWhenTold2Player() {
        when(inputOutput.getInput()).thenReturn(2);
        doReturn(twoPlayerGame).when(testMenu)
                .makeGame(any( Messages.class ), any( InputOutput.class ),
                        any( Board.class ), any( HumanPlayer.class ),
                        any( HumanPlayer.class ));
        assertEquals(testMenu.createGame(), twoPlayerGame);
    }
}
