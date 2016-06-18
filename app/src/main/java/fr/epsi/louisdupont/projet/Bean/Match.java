package fr.epsi.louisdupont.projet.Bean;

import fr.epsi.louisdupont.projet.Utils.CarColor;

/**
 * Created by Loulou on 14/06/2016.
 */
public class Match {
    private String namePlayer;
    private CarColor resPlayer, resComput;

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public CarColor getResPlayer() {
        return resPlayer;
    }

    public void setResPlayer(CarColor resPlayer) {
        this.resPlayer = resPlayer;
    }

    public CarColor getResComput() {
        return resComput;
    }

    public void setResComput(CarColor resComput) {
        this.resComput = resComput;
    }

}
