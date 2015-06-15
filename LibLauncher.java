package day4;

public class LibLauncher {
	
	public static void main(String[] args) {
		Library libr = new Library();

		System.out.println("Pervie 5 knig");
		Book[] books = libr.findByCategory(Category.Computers);		
		libr.printCategory(books);
		System.out.println("Sleduyus4ie 5 knig");
		books = libr.findByCategory(Category.Computers);		
		libr.printCategory(books);
		
		System.out.println("*****************" );
		System.out.println("Knigi po avtor: David" );		
		books =  libr.findBiAutor("David");
		libr.print(books);
		
		System.out.println("*****************" );
		System.out.println("Knigi po name: Java" );		
		books =  libr.findByName("Java");
		libr.print(books);
		
	}
	
	
	

}
