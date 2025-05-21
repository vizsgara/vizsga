package hu.heropractice.controller;
import hu.heropractice.dto.HeroCreateDTO;
import hu.heropractice.dto.HeroReadDTO;
import hu.heropractice.dto.HeroUpdateDTO;
import hu.heropractice.converter.HeroConverter;
import hu.heropractice.model.Hero;
import hu.heropractice.model.Weapon;
import hu.heropractice.service.HeroService;
import hu.heropractice.service.WeaponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;
    private final WeaponService weaponService;

    public HeroController(HeroService heroService, WeaponService weaponService) {
        this.heroService = heroService;
        this.weaponService = weaponService;
    }

    // 1. Hős lekérdezése ID alapján
    @GetMapping("/{id}")
    public ResponseEntity<HeroReadDTO> getHeroById(@PathVariable int id) {
        Optional<Hero> optionalHero = heroService.findById(id);
        if (optionalHero.isPresent()) {
            HeroReadDTO dto = HeroConverter.toDto(optionalHero.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // 2. Hős létrehozása
    @PostMapping
    public ResponseEntity<Void> createHero(@RequestBody HeroCreateDTO dto) {
        Optional<Weapon> optionalWeapon = weaponService.getWeaponById(dto.getWeaponId());
        if (optionalWeapon.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Hero hero = new Hero(0, dto.getName(), dto.getNationality(), dto.isCanFly(), optionalWeapon.get());
        heroService.save(hero);
        return ResponseEntity.ok().build();
    }

    // 3. Hős módosítása
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHero(@PathVariable int id, @RequestBody HeroUpdateDTO dto) {
        Optional<Hero> optionalHero = heroService.findById(id);
        Optional<Weapon> optionalWeapon = weaponService.getWeaponById(dto.getWeaponId());

        if (optionalHero.isEmpty() || optionalWeapon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Hero hero = optionalHero.get();
        hero.setCanFly(dto.isCanFly());
        hero.setWeapon(optionalWeapon.get());
        heroService.save(hero);

        return ResponseEntity.ok().build();
    }
}