package hu.heropractice.dto;

public class HeroCreateDTO {
    private String name;
    private String nationality;
    private boolean canFly;
    private int weaponId;

    public HeroCreateDTO() {
    }

    public HeroCreateDTO(String name, String nationality, boolean canFly, int weaponId) {
        this.name = name;
        this.nationality = nationality;
        this.canFly = canFly;
        this.weaponId = weaponId;
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

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }
}
