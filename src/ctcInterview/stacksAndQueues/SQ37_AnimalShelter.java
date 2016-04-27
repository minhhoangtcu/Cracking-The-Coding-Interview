package ctcInterview.stacksAndQueues;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/*
 * An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis. People must adopt either the "oldest" (based on arrival time) of all animal at the shelter, or they can slect whether they would perfer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog and dequeueCat. You may use the built-in LinkedList data structure.
 */
public class SQ37_AnimalShelter {
	
	public static void main(String[] args) {
		
		SQ37_AnimalShelter shelter = new SQ37_AnimalShelter();
		shelter.enqueue(shelter.new Cat("Miku"));
		shelter.enqueue(shelter.new Cat("Musty"));
		shelter.enqueue(shelter.new Dog("Husky"));
		shelter.enqueue(shelter.new Cat("Hell"));
		shelter.enqueue(shelter.new Dog("Poppy"));
		
		System.out.printf("Should be Miku: %s\n", shelter.dequeueAny());
		System.out.printf("Should be Husky: %s\n", shelter.dequeueDog());
		System.out.printf("Should be Musty: %s\n", shelter.dequeueAny());
		System.out.printf("Should be Hell: %s\n", shelter.dequeueCat());
		System.out.printf("Should be Poppy: %s\n", shelter.dequeueAny());
		
	}
	
	
	
	
	

	Queue<Dog> dogs;
	Queue<Cat> cats;
	int order;
	
	public SQ37_AnimalShelter() {
		order = 0;
		dogs = new LinkedList<>();
		cats = new LinkedList<>();
	}
	
	public void enqueue(Animal animal) {
		
		animal.setOrder(order);
		
		if (animal instanceof Dog)
			dogs.add((Dog) animal);
		else if (animal instanceof Cat)
			cats.add((Cat) animal);
		
		order++;
	}
	
	public Animal dequeueAny() {
		// In the case that either one is empty, we just need to return the other one.
		if (dogs.isEmpty())
			return dequeueCat();
		else if (cats.isEmpty())
			return dequeueDog();
		
		if (dogs.peek().isOlder(cats.peek()))
			return dequeueDog();
		else
			return dequeueCat();
	}
	
	public Cat dequeueCat() {
		if (cats.isEmpty())
			throw new EmptyStackException();
		return cats.remove();
	}
	
	public Dog dequeueDog() {
		if (dogs.isEmpty())
			throw new EmptyStackException();
		return dogs.remove();
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
	
	class Animal {

		// The smaller the order variable, the older it is.
		int order;
		String name;

		public Animal(String name) {
			this.name = name;
		}
		
		public void setOrder(int order) {
			this.order = order;
		}
		
		public boolean isOlder(Animal other) {
			if (order < other.order)
				return true;
			else
				return false;
		}
		
		public String toString() {
			return name;
		}

	}
}
