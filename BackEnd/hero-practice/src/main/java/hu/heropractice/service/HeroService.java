package hu.heropractice.service;

import hu.heropractice.model.Hero;
import hu.heropractice.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Optional<Hero> findById(int id) {
        return heroRepository.findById(id);
    }

    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }
}
