package tic.tac.toe;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppTest {
  @Test
  public void appUsesGameInterface() {
    Game mockedGame = mock(Game.class);
    App app = new App(mockedGame);
    app.run();
    verify(mockedGame).getGreeting();
    verify(mockedGame).displayGame();
  }
  
}
