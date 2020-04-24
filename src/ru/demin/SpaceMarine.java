package ru.demin;

import java.time.ZonedDateTime;

public class SpaceMarine implements Comparable<SpaceMarine> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private Integer height; //Поле может быть null
    private AstartesCategory category; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    //mine code here
    public SpaceMarine() {
        this.setId(this.hashCode() > 0
                ? this.hashCode()
                : (this.hashCode()) == 0
                ? 1
                : (this.hashCode() == Integer.MIN_VALUE
                ? Integer.MAX_VALUE
                : -this.hashCode()));
        this.setCreationDate(ZonedDateTime.now());
    }
    public SpaceMarine(String name, Coordinates coordinates, Long health, Integer height,
                AstartesCategory category, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.setId(this.hashCode() > 0
                ? this.hashCode()
                : (this.hashCode()) == 0
                ? 1
                : (this.hashCode() == Integer.MIN_VALUE
                ? Integer.MAX_VALUE
                : -this.hashCode()));
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setHealth(health);
        this.setHeight(height);
        this.setCategory(category);
        this.setMeleeWeapon(meleeWeapon);
        this.setChapter(chapter);
        this.setId(this.hashCode() > 0
                ? this.hashCode()
                : (this.hashCode()) == 0
                ? 1
                : (this.hashCode() == Integer.MIN_VALUE
                ? Integer.MAX_VALUE
                : -this.hashCode()));
        this.setCreationDate(ZonedDateTime.now());
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }
    public Long getHealth() {
        return this.health;
    }
    public Integer getHeight() {
        return this.height;
    }
    public AstartesCategory getCategory() {
        return this.category;
    }
    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }
    public Chapter getChapter() {
        return this.chapter;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Значение поля \"ID\" must be больше 0. ");
        }
        this.id = id;
    }
    public void setName(String name) throws NullPointerException, IllegalArgumentException {
        if (name == null) {
            throw new NullPointerException("Значение поля \"Name\" не может быть null. ");
        } else if (name.equals("")) {
            throw new IllegalArgumentException("Значение поля \"Name\" не может быть пустым. ");
        }
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) throws NullPointerException {
        if (coordinates == null) {
            throw new NullPointerException("Значение поля \"Coordinates\" не может быть null. ");
        }
        this.coordinates = coordinates;
    }
    public void setCreationDate(ZonedDateTime creationDate) throws NullPointerException {
        if (creationDate == null) {
            throw new NullPointerException("Значение поля \"Creation Date\" не может быть null. ");
        }
        this.creationDate = creationDate;
    }
    public void setHealth(Long health) {
        if (health == null) {
            throw new NullPointerException("Значение поля \"Health\" не может быть null. ");
        } else if (health.longValue() <= 0L) {
            throw new IllegalArgumentException("Значение поля \"Health\" должно быть больше 0. ");
        }
        this.health = health;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public void setCategory(AstartesCategory category) throws NullPointerException {
        if (category == null) {
            throw new NullPointerException("Значение поля \"Category\" не может быть null. ");
        }
        this.category = category;
    }
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) throws NullPointerException {
        if (meleeWeapon == null) {
            throw new NullPointerException("Значение поля \"Melle Weapon\" не может быть null. ");
        }
        this.meleeWeapon = meleeWeapon;
    }
    public void setChapter(Chapter chapter) {
        if (chapter == null) {
            throw new NullPointerException("Значение поля \"Chapter\" не может быть null. ");
        }
        this.chapter = chapter;
    }
    public void update() {
        this.setCreationDate(ZonedDateTime.now());
        this.setId(this.hashCode() > 0
                ? this.hashCode()
                : (this.hashCode()) == 0
                ? 1
                : (this.hashCode() == Integer.MIN_VALUE
                ? Integer.MAX_VALUE
                : -this.hashCode()));
    }
    public void checked() {
        if (id <= 0) {
            throw new IllegalArgumentException("Значение поля \"ID\" должно быть больше 0. ");
        } else if (name == null) {
            throw new NullPointerException("Значение поля \"Name\" не может быть null. ");
        } else if (name.equals("")) {
            throw new IllegalArgumentException("Значение поля \"Name\" не может быть пустым. ");
        } else if (coordinates == null) {
            throw new NullPointerException("Значение поля \"Coordinates\" не может быть null. ");
        } else if (health == null) {
            throw new NullPointerException("Значение поля \"Health\" не может быть null. ");
        } else if (health.longValue() <= 0L) {
            throw new IllegalArgumentException("Значение поля \"Health\" должно быть больше 0. ");
        } else if (category == null) {
            throw new NullPointerException("Значение поля \"Category\" не может быть null. ");
        } else if (meleeWeapon == null) {
            throw new NullPointerException("Значение поля \"Melle Weapon\" не может быть null. ");
        } else if (chapter == null) {
            throw new NullPointerException("Значение поля \"Chapter\" не может быть null. ");
        }
    }

    /*@Override
    public int hashCode() {
        return getName().hashCode() + 2 * getCoordinates().hashCode() + 3 * getHeight().hashCode() +
                + 4 * getHealth().hashCode() + 5 * getCategory().hashCode() + 6 * getCategory().hashCode() +
                + 7 * getMeleeWeapon().hashCode() + 8 * getChapter().hashCode();
    }*/

    @Override
    public String toString() {
        return "SpaceMarine: {id: " + getId() + ", name: " + getName() + ", " + getCoordinates().toString() +
                ", creationDate: " + getCreationDate() + ", health: " + getHealth() + ", height: " +
                getHeight() + ", category: " + getCategory() + ", meleeWeapon: " + getMeleeWeapon() +
                ", Chapter: " + getChapter() + "}";

    }

    @Override
    public int compareTo(SpaceMarine spaceMarine) {
        return this.getId() != spaceMarine.getId()
                ? getId() - spaceMarine.getId()
                : (!getName().equals(spaceMarine.getName())
                ? getName().compareTo(spaceMarine.getName())
                : (!getCoordinates().equals(spaceMarine.getCoordinates())
                ? getCoordinates().compareTo(spaceMarine.getCoordinates())
                : (!getCreationDate().equals(spaceMarine.getCreationDate())
                ? getCreationDate().compareTo(spaceMarine.getCreationDate())
                : (!getHealth().equals(spaceMarine.getHealth())
                ? getHealth().compareTo(spaceMarine.getHealth())
                : (!getHeight().equals(spaceMarine.getHeight())
                ? getHeight().compareTo(spaceMarine.getHeight())
                : (!getCategory().equals(spaceMarine.getCategory())
                ? getCategory().compareTo(spaceMarine.getCategory())
                : (!getMeleeWeapon().equals(spaceMarine.getMeleeWeapon())
                ? getMeleeWeapon().compareTo(spaceMarine.getMeleeWeapon())
                : getChapter().compareTo(spaceMarine.getChapter()))))))));
    }
}
