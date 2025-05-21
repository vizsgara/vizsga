package hu.heropractice.dto;

public class HeroReadDTO {
    private int id;
    private String name;
    private String nationality;
    private boolean canFly;
    private String weaponName;

    public HeroReadDTO() {
    }

    public HeroReadDTO(int id, String name, String nationality, boolean canFly, String weaponName) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.canFly = canFly;
        this.weaponName = weaponName;
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

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}
