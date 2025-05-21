package hu.heropractice.service;

import hu.heropractice.model.Weapon;
import hu.heropractice.repository.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public WeaponService(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    public Optional<Weapon> getWeaponById(int id) {
        return weaponRepository.findById(id);
    }
}
