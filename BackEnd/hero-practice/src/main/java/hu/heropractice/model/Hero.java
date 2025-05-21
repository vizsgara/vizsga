package hu.heropractice.model;


import jakarta.persistence.*;

@Entity
public class Hero {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nationality;
    private boolean canFly;


    @ManyToOne
    private Weapon weapon;

    public Hero() {
    }

    public Hero(int id, String name, String nationality, boolean canFly, Weapon weapon) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.canFly = canFly;
        this.weapon = weapon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
