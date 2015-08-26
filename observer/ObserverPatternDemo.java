package day9.observer;

import day9.observer.ConcreteObserver;

/**
 * 
 * Benefits
 * 
 * It abstracts the coupling between the subject and the observer.
 * It provides support for broadcast-type communication.
 * 
 * Applicable Scenarios
 * 
 * A change to an object requires changing other objects, and the number of objects that need to be changed is unknown.
 * An object needs to notify other objects without making any assumptions about the identity of those objects.
 */
public class ObserverPatternDemo {

	public static void main(String[] args) {
		System.out.println("Observer Pattern Demo | Day 9 midgardabc.com");
		System.out.println();
		
		ConcreteSubject s = new ConcreteSubject();
		
		Observer o1 = new ConcreteObserver();
		Observer o2 = new ConcreteObserver();

		s.addObserver(o1);
		s.addObserver(o2);

		for (int i = 0; i < 10; i++) {
			s.doTheJob();
			System.out.println();
		}
		
		s.removeObserver(o2);

		for (int i = 0; i < 10; i++) {
			s.doTheJob();
		}
	}

}
