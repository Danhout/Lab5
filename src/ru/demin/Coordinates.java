package ru.demin;

public class Coordinates implements Comparable<Coordinates> {
    private long x; //Максимальное значение поля: 658
    private Long y; //Поле не может быть null

    public Coordinates() {}
    public Coordinates(long x, Long y) throws IllegalArgumentException, NullPointerException {
        this.setX(x);
        this.setY(y);
    }

    public long getX() {
        return x;
    }
    public Long getY() {
        return y;
    }
    public void setX(long x) throws IllegalArgumentException {
        if (x > 658L) {
            throw new IllegalArgumentException("Значение поля x не может быть больше 658. ");
        }
        this.x = x;
    }
    public void setY(Long y) throws NullPointerException {
        if (y == null) {
            throw new NullPointerException("Значение поля y не может быть null. ");
        }
        this.y = y;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null || !ob.getClass().equals(getClass()) || ob.hashCode() != hashCode()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) ob;
        return getX() == coordinates.getX() && getY().equals(coordinates.getY());
    }

    @Override
    public String toString() {
        return "Coordinates: {x: " + getX() + ", y: " + getY() + "}";
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        if (this.getX() != coordinates.getX()) {
            return this.getX() > coordinates.getX() ? 1 : -1;
        } else {
            return  this.getY().compareTo(coordinates.getY());
        }
    }
}
