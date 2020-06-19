package org.example.entity;

public class PlayerMove {

    private String x;
    private String y;
    private String side;
    private Boolean winner;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "PlayerMove{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}
