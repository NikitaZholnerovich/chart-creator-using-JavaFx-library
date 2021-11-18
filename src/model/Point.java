package model;

public class Point {
    private Double X;
    private Double Y;

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    public Point(Double X, Double Y) {
        this.X = X;
        this.Y = Y;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }
}
