package fr.epsi.louisdupont.projet.Game;

import android.content.Context;

import fr.epsi.louisdupont.projet.Bean.Car;
import fr.epsi.louisdupont.projet.Bean.Match;
import fr.epsi.louisdupont.projet.DAO.MatchDAO;
import fr.epsi.louisdupont.projet.Utils.CarColor;

/**
 * Created by Loulou on 14/06/2016.
 */
public class GameController {
    private int nbTour, scorePlayer, scoreComputer;
    Car computChoice;
    MatchDAO matchDAO;

    public GameController(Context context) {
        scoreComputer = 0;
        scorePlayer = 0;
        nbTour = 0;
        matchDAO = new MatchDAO(context);
    }

    public void newRandomCar() {
        int rand = (int) (Math.random() * 3);
        Car c = new Car();
        switch (rand) {
            case 0 : c.setCarColor(CarColor.Blue); break;
            case 1 : c.setCarColor(CarColor.Green); break;
            case 2 : c.setCarColor(CarColor.Red); break;
        }
        computChoice = c;
    }

    public void nextTour(Car player) {
        nbTour++;
        switch (computChoice.getCarColor()) {
            case Blue: switch (player.getCarColor()) {
                case Blue: break;
                case Green: scorePlayer++; break;
                case Red: scoreComputer++; break;
            } break;
            case Green: switch (player.getCarColor()) {
                case Blue: scoreComputer++; break;
                case Green: break;
                case Red: scorePlayer++; break;
            } break;
            case Red: switch (player.getCarColor()) {
                case Blue: scorePlayer++; break;
                case Green: scoreComputer++; break;
                case Red: break;
            } break;
        }

        Match m = new Match();
        m.setNamePlayer("Dupont Louis");
        m.setResComput(computChoice.getCarColor());
        m.setResPlayer(player.getCarColor());
        matchDAO.open();
        matchDAO.add(m);
        matchDAO.close();

    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public int getScorePlayer() {
        return scorePlayer;
    }

    public void setScorePlayer(int scorePlayer) {
        this.scorePlayer = scorePlayer;
    }

    public int getScoreComputer() {
        return scoreComputer;
    }

    public void setScoreComputer(int scoreComputer) {
        this.scoreComputer = scoreComputer;
    }

    public Car getComputChoice() {
        return computChoice;
    }

    public void setComputChoice(Car computChoice) {
        this.computChoice = computChoice;
    }
}
