package hu.heropractice.dto;

public class HeroUpdateDTO {
    private boolean canFly;
    private int weaponId;

    public HeroUpdateDTO() {
    }

    public HeroUpdateDTO(boolean canFly, int weaponId) {
        this.canFly = canFly;
        this.weaponId = weaponId;
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
