package edu.unisabana.dyas.tdd.registry;

import java.util.HashSet;
import java.util.Set;

public class Registry {
    
    // Set to store the IDs already registered.
    private Set<Integer> registeredIds = new HashSet<>();

    public RegisterResult registerVoter(Person p) {
        // Validate that the person is alive.
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        // Validate that the age is not negative.
        if (p.getAge() < 0) {
            return RegisterResult.INVALID_AGE;
        }
        // Validate that the person is 18 years old or older.
        if (p.getAge() < 18) {
            return RegisterResult.UNDERAGE;
        }
        // Validate that the ID has not been registered previously.
        if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }
        // Register the ID.
        registeredIds.add(p.getId());
        
        return RegisterResult.VALID;
    }
}
