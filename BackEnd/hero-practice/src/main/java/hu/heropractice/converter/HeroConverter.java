package hu.heropractice.converter;

import hu.heropractice.dto.HeroReadDTO;
import hu.heropractice.model.Hero;

public class HeroConverter {

    public static HeroReadDTO toDto(Hero hero ) {
        return new HeroReadDTO(
                hero.getId(),
                hero.getName(),
                hero.getNationality(),
                hero.isCanFly(),
                hero.getWeapon().getType()
        );
    }
}
