package de.telran.pizzaProject.service;

import de.telran.pizzaProject.entity.Cafe;
import de.telran.pizzaProject.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;


    @Autowired  //inject Repository
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    //find all cafes
    public List<Cafe> getAllCafes() {
        return StreamSupport.stream(cafeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void saveCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    public Cafe getCafeById(String id) {

        return cafeRepository.findById(id).orElse(null);
    }

    //
    public void deleteCafeById(String id) {

        cafeRepository.deleteById(id);
    }


}
