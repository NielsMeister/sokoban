package Sokoban.game.model;

import Sokoban.game.Board;
import Sokoban.game.controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @DisplayName("Move")
    @Nested
    class move {
        @Test()
        @DisplayName("not_found > Karte wurde nicht korrekt geladen")
        void notFound() {
            Controller controller = new Controller();
            Board board = new Board();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(board, 1, 0), "Karte wurde nicht korrekt geladen");

            assertEquals("Karte wurde nicht korrekt geladen", exception.getMessage());
        }

        @Test()
        @DisplayName("happy_path")
        void happyPath() throws Exception {
            Controller controller = new Controller();
            Board board = new Board();
            board.buildMap();
            controller.move(board, 1, 0);
            String excepted = "[[BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, DESTINATION, BARRIER, FLOOR, BARRIER, DESTINATION, BARRIER], [BARRIER, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, DESTINATION, FLOOR, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, FLOOR, FLOOR, CHEST, FLOOR, CHEST, FLOOR, BARRIER], [BARRIER, FLOOR, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, FLOOR, CHEST, CHEST, DESTINATION, BARRIER], [BARRIER, BARRIER, BARRIER, FLOOR, FLOOR, PLAYER, FLOOR, FLOOR, FLOOR, BARRIER], [BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER, BARRIER]]";
            assertEquals(excepted, Arrays.deepToString(board.getBoard()));
        }

        @Test()
        @DisplayName("out_of_bounds > Ung??ltige Bewegung im Spielfeld.")
        void outOfBounds() throws Exception{
            Controller controller = new Controller();
            Board board = new Board();
            board.buildMap();
            Exception exception = Assertions.assertThrows(Exception.class, () ->
                    controller.move(board, TestUtils.generateRandomNumber(), TestUtils.generateRandomNumber()), "Ung??ltige Bewegung im Spielfeld. Breite: " + board.getLongestLineInMap() + " H??he : " + board.getLines().size() + "Eingaben: Breite: " + board.getPlayerX() + " H??he: " + board.getPlayerY());

            assertEquals("Ung??ltige Bewegung im Spielfeld. Breite: " + board.getLongestLineInMap() + " H??he : " + board.getLines().size() + "Eingaben: Breite: " + board.getPlayerX() + " H??he: " + board.getPlayerY(), exception.getMessage());
        }
    }
}
