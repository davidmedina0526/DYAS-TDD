package edu.unisabana.dyas.tdd.registry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistryTest {

    private Registry registry;

    @Before
    public void setUp() {
        registry = new Registry();
    }
    
    @Test
    // Test case for a person who is dead.
    public void validateRegistryResultWithDeadPerson() {
        Person person = new Person();
        person.setAlive(false);
        person.setAge(25);     // Set a valid age so the dead condition is reached.
        person.setId(1001);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }
    
    @Test
    // Test case for a valid voter registration.
    public void validateRegistryResult() {
        Person person = new Person();
        person.setAlive(true);
        person.setAge(23);     // Valid age (>=18).
        person.setId(1002);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    
    @Test
    // Test case for a person who is underage.
    public void validateRegistryResultWithUnderagePerson() {
        Person person = new Person();
        person.setAlive(true);
        person.setAge(17);     // Underage.
        person.setId(1003);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }
    
    @Test
    // Test case for a person with an invalid age.
    public void validateRegistryResultWithInvalidAge() {
        Person person = new Person();
        person.setAlive(true);
        person.setAge(-1);     // Invalid age.
        person.setId(1004);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }
    
    @Test
    // Test case for a duplicated ID.
    public void validateRegistryResultWithDuplicatedId() {
        // Create two different Person objects with the same ID.
        Person person1 = new Person();
        person1.setAlive(true);
        person1.setAge(30);
        person1.setId(12345);
        
        Person person2 = new Person();
        person2.setAlive(true);
        person2.setAge(35);
        person2.setId(12345);
        
        // The first registration should be valid.
        RegisterResult firstRegister = registry.registerVoter(person1);
        Assert.assertEquals(RegisterResult.VALID, firstRegister);
        
        // The second registration with the same ID should be detected as duplicate.
        RegisterResult secondRegister = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, secondRegister);
    }
}
