package com.trzewik.TicTacToe;

import com.trzewik.TicTacToe.displayer.IllegalInterruptedException;
import com.trzewik.TicTacToe.displayer.Language;
import com.trzewik.TicTacToe.displayer.UserInputProvider;
import com.trzewik.TicTacToe.settings.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Agnieszka Trzewik
 */
public class Players {

    private List<Player> players;
    private Player startingPlayer;
    private Player currentPlayer;
    private Language language;
    private UserInputProvider userInputProvider;

    private Players(UserInputProvider userInputProvider, Language language) throws IllegalInterruptedException {
        this.userInputProvider = userInputProvider;
        this.language = language;
        createPlayers();
    }

    static Players buildPlayers(UserInputProvider userInputProvider, Language language) throws IllegalInterruptedException {
        return new Players(userInputProvider, language);
    }

    public int numbersOfPlayers(){
        return players.size();
    }

    public Player getPlayer(int playerIndex){
        return players.get(playerIndex);
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    void changeCurrentPlayer() {
        currentPlayer = findOppositePlayerTo(currentPlayer);
    }

    void changeStartingPlayer() {
        setWhoStarts(findOppositePlayerTo(startingPlayer));
    }

    private Player findOppositePlayerTo(Player player) {
        return players.stream()
                .filter(oppositePlayer -> !oppositePlayer.equals(player))
                .findFirst()
                .get();
    }

    private void setWhoStarts(Player player) {
        startingPlayer = player;
    }

    private void createPlayers() throws IllegalInterruptedException {
        players = new ArrayList<>();
        createStartingPlayer();
        players.add(startingPlayer);
        players.add(new PlayerX(userInputProvider.collectString(language, "provide_name_for_sign", "X")));
    }

    private void createStartingPlayer() throws IllegalInterruptedException {
        startingPlayer = new PlayerO(userInputProvider.collectString(language, "provide_name_for_sign", "O"));
        currentPlayer = startingPlayer;
    }

}
