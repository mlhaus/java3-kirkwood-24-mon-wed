package edu.kirkwood.demos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalCount {
    public static void main(String[] args) {
        Map<Person, List<Animal>> owners_and_their_pets = new HashMap<>();

        Person marc = new Person("Marc");
        List<Animal> marcs_pets = new ArrayList<>() {{
            add(new Cat("Velcro"));
        }};
        owners_and_their_pets.put(marc, marcs_pets);

        Person krystal = new Person("Krystal");
        List<Animal> krystal_pets = new ArrayList<>() {{
            add(new Cat("Todd"));
            add(new Cat("Margo"));
            add(new Dog("Gus"));
        }};
        owners_and_their_pets.put(krystal, krystal_pets);

        Person bob = new Person("Bob");
        List<Animal> bobs_pets = new ArrayList<>();
        owners_and_their_pets.put(bob, bobs_pets);

        Person amy = new Person("Amy");
        List<Animal> amys_pets = new ArrayList<>();
        amys_pets.add(new Cat("Zipper"));
        amys_pets.add(new Cat("Waffles"));
        amys_pets.add(new Cat("Sprout"));
        owners_and_their_pets.put(amy, amys_pets);

        owners_and_their_pets.forEach((person, animals) -> {
            System.out.print(person.getFirstName());
            if(!animals.isEmpty()) {
                System.out.print("'s ");
            } else {
                System.out.println(" has no pets.");
                return;
            }
            System.out.print(animals.size() > 1 ? "pets: " : "pet: ");
            System.out.println(animals.stream().map(Object::toString).collect(Collectors.joining(", ")));
        });

        System.out.println();

        Map<String, Integer> counter = new HashMap<>();
        for (Map.Entry<Person, List<Animal>> entry : owners_and_their_pets.entrySet()) {
            List<Animal> animals = entry.getValue();
            for (Animal animal : animals) {
                String animalType = animal.getClass().getSimpleName();
                if(counter.containsKey(animalType)) {
                    counter.put(animalType, counter.get(animalType) + 1);
                } else {
                    counter.put(animalType, 1);
                }
            }
        }

        for (String key : counter.keySet()) {
            Integer count = counter.get(key);
            if(count.equals(1)) {
                System.out.println("There is " + count + " " + key);
            } else {
                System.out.println("There are " + count + " " + key + "s");
            }
        }

        System.out.println();

        // Key is num of pets a person has, Value is count
        Map<Integer, Integer> counter2 = new HashMap<>();

        owners_and_their_pets.forEach((k, v) -> {
            Person person  = k;
            List<Animal> list = v;
            int numAnimals = list.size();
            if(!counter2.containsKey(numAnimals)) {
                counter2.put(numAnimals, 1);
            } else {
                counter2.put(numAnimals, counter2.get(numAnimals) + 1);
            }
        });

        counter2.forEach((numAnimals, numPeople) -> {
            System.out.printf("%s %s %s %s\n",
                    numPeople,
                    numPeople == 1 ? "person has" : "people have",
                    numAnimals,
                    numAnimals == 1 ? "pet" : "pets"
                    );
        });


    }
}

abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Person {
    private String firstName;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return firstName;
    }
}