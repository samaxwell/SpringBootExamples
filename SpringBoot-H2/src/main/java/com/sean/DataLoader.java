package com.sean;

import com.sean.models.PersonEntity;
import com.sean.repositories.PersonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {

    PersonRepository personRepository;


    @PostConstruct
    public void loadData() {
        List.of(
         new PersonEntity("Sean", "Maxwell"),
         new PersonEntity("David", "Hinkley"),
         new PersonEntity("Sasha", "Owens"))
                .forEach(p -> personRepository.save(p));
    }
}
