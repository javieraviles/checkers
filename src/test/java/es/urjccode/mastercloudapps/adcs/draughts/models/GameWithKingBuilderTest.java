package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithKingBuilderTest {

    @Mock
    Piece piece;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewKing(){
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        Game game = new GameBuilder()
                .row("        ")
                .row("b       ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .build();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(Color.WHITE, game.getPiece(target).getColor());
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitAndEatingThenNewKing(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        Game game = new GameBuilder()
                .row("        ")
                .row("  n     ")
                .row(" b      ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .build();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(Color.WHITE, game.getPiece(target).getColor());
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewKing(){
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        Game game = new GameBuilder()
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("   n    ")
                .row("        ")
                .build();
        game.turn.change();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(Color.BLACK, game.getPiece(target).getColor());
    }
    
    @Test
    public void testGivenGameWhenBlackPawnAtLimitAndEatingThenNewKing(){
        Coordinate origin = new Coordinate(5,1);
        Coordinate target = new Coordinate(7,3);
        Game game = new GameBuilder()
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row(" n      ")
                .row("  b     ")
                .row("        ")
                .build();
        game.turn.change();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(Color.BLACK, game.getPiece(target).getColor());
    }
    
 
    @Test
    public void testGivenGameWhenKingMovingFurtherThenOkay(){
        Coordinate origin = new Coordinate(6,0);
        Coordinate target = new Coordinate(0,6);
        Game game = new GameBuilder()
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("B       ")
                .row("        ")
                .build();
        game.turn.change();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertEquals(Color.WHITE, game.getPiece(target).getColor());
    }
    
    @Test
    public void testGivenGameWhenKingMovingFurtherAndEatingOnePieceThenOkay(){
        Coordinate origin = new Coordinate(6,0);
        Coordinate target = new Coordinate(0,6);
        Coordinate eaten = new Coordinate(3,3);
        Game game = new GameBuilder()
                .row("        ")
                .row("        ")
                .row("        ")
                .row("   n    ")
                .row("        ")
                .row("        ")
                .row("B       ")
                .row("        ")
                .build();
        game.turn.change();
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertNull(game.getPiece(eaten));
        assertEquals(Color.WHITE, game.getPiece(target).getColor());
    }
    
    @Test(expected = AssertionError.class)
    public void testGivenGameWhenKingMovingFurtherAndEatingMoreThanOnePieceThenError(){
        Coordinate origin = new Coordinate(6,0);
        Coordinate target = new Coordinate(0,6);
        Game game = new GameBuilder()
                .row("        ")
                .row("        ")
                .row("        ")
                .row("   n    ")
                .row("  n     ")
                .row("        ")
                .row("B       ")
                .row("        ")
                .build();
        game.turn.change();
        game.move(origin, target);
    }
}
