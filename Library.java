package day4;

//IDEA:
//		1) Dlya realisacii bustrogo poiska po naimenovaniyu, avtoru i kategorii ispolzuem arrays so strukturoy kak u indexa - po alfavitu
//		2) V array searchCategory keshiruem knigi kotorie vivodila na pe4at. Pered vuvodom proveryaem v searchCategory vuvodilas li ranee. 
//		tolko dlya poiska po kategoriyam.
//
public class Library {
	
	//1 uroven - stroki (index = index alphabet), 2 uroven - array of book
	private Book[][] arrSortName;
	//1 uroven - stroki (index = index alphabet), 2 uroven - array of book
	private Book[][] arrSortAutor;
	//1 uroven - stroki (index = index enum Category), 2 uroven - array of book
	private Book[][] arrSortCategory;
	
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
	
	public Book[] searchCategory;
	private Category categorySearch;
	String typeSearch;
	
	/* Method: Library()
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		null
	 * Use:
	 * 		Konstruktor klassa. Inicializiruet massivi i zapolnyaet knigami
	 */	
	public Library(){
		
		arrSortName = new Book[alphabet.length()][10];
		
		arrSortAutor = new Book[alphabet.length()][10];
		
		arrSortCategory = new Book[5][100];
		
		createBook();
		
	}
	
	/* Method: createBook()
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		void
	 * Use:
	 * 		Sozdaet knigi po umol4aniyu i dobavlaet ih biblioteku
	 */	
	private void createBook(){
		
		Book book = new Book();
		book.setName("Java 8. Karmanniy spravo4nik");
		book.setAutor("Robert Liguiri");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Java 8. Polnoe rukovodstvo");
		book.setAutor("Gerbert Shildt");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Java 8. Rukovodstvo dla na4inayus4ih");
		book.setAutor("Gerbert Shildt");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Java EE 7. Osnovu");
		book.setAutor("Arun Gupta");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Java SE 8. Vvodniy kurs");
		book.setAutor("Key S. Hoffman");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("JavaServer Faces");
		book.setAutor("Devid M. Geri");
		book.setCategory(Category.Computers);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Uzhin doma");
		book.setAutor("Ferranom Adria");
		book.setCategory(Category.Food);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Garri Potter");
		book.setAutor("Dzhoan Rouling");
		book.setCategory(Category.Fantasy);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Solyaris");
		book.setAutor("Stanislav Lem");
		book.setCategory(Category.Fiction);
		addToLibrary(book);
		
		book = new Book();
		book.setName("Princ i Nis4iy");
		book.setAutor("Mark Tven");
		book.setCategory(Category.History);
		addToLibrary(book);
		
	}
	
	/* Method: addToLibrary(Book book)
	 * Parameters:
	 * 		Book book
	 * Return value:
	 * 		void
	 * Use:
	 * 		Dobavlaet zapisi v arrays sortirovki
	 */	
	private void addToLibrary(Book book){
		
		int idxName	= alphabet.indexOf(book.getName().charAt(0));
		addToStorage(arrSortName[idxName], book);
		
		int idxAutor = alphabet.indexOf(book.getAutor().charAt(0));
		addToStorage(arrSortAutor[idxAutor], book);
		
		int idxCategory = book.getCategory().getId() - 1;
		addToStorage(arrSortCategory[idxCategory], book);
		
	}
	
	/* Method: addToStorage(Book[] arrBook,Book book)
	 * Parameters:
	 * 		Book[] arrBook
	 * 		Book book
	 * Return value:
	 * 		void
	 * Use:
	 * 		Dobavlaet Book v konec massiva arrBook
	 */	
	private void addToStorage(Book[] arrBook,Book book){
		
		int idx = 0;
		for (Book bookStorage : arrBook) {
			
			if (bookStorage == null) {
				arrBook[idx] = book;
				return;
			}
			
			idx++;
		}
		 
	}
	
	/* Method: Book [] findByName(String name)
	 * Parameters:
	 * 		String name - name of search
	 * Return value:
	 * 		Book []
	 * Use:
	 * 		Return array with book po naimenovaniyu
	 */	
	public Book [] findByName(String name){		
		
		if (name == null || name.isEmpty() == true || name.charAt(0) == ' ') {
			return null;
		}
		
		this.typeSearch = "name";
				
		int idx = alphabet.indexOf(name.charAt(0));

		return arrSortName[idx];		
		
	}
	
	/* Method: Book[] findBiAutor(String autor)
	 * Parameters:
	 * 		String autor - autor of search
	 * Return value:
	 * 		Book []
	 * Use:
	 * 		Return array with book po autor
	 */	
	public Book[] findBiAutor(String autor){
		
		if (autor == null || autor.isEmpty() == true || autor.charAt(0) == ' '){
			return null;
		}
		
		this.typeSearch = "autor";
		
		int idx = alphabet.indexOf(autor.charAt(0));
			
		return arrSortAutor[idx];
	}
	
	/* Method: Book[] findByCategory(Category category)
	 * Parameters:
	 * 		Category category - enum of category
	 * Return value:
	 * 		Book []
	 * Use:
	 * 		Return array with book po category
	 */	
	public Book[] findByCategory(Category category){
		
		if (category == null) {
			return null;
		}
		
		if (thisSearchAgain(category) == false) {
			this.searchCategory = new Book[0];
		}
		
		this.typeSearch = "category";
		this.categorySearch = category;
		
		int idx = category.getId() - 1;
		
		return arrSortCategory[idx];
	}
	
	/* Method: boolean thisSearchAgain(Category category)
	 * Parameters:
	 * 		Category category - enum of category 
	 * Return value:
	 * 		boolean
	 * Use:
	 * 		Sravnivaet tip i znachenie poiska s predidus4im
	 */	
	private boolean thisSearchAgain(Category category) {
		boolean result = true;
		if (this.typeSearch != "category" || category != this.categorySearch) {
			
			result = false;
			
		}
		
		return result;
	}	
	
	/* Method:boolean bookIsPrinting(Book book)
	 * Parameters:
	 * 		Book book - book dlya proverki v keshe 
	 * Return value:
	 * 		boolean
	 * Use:
	 * 		Proveryaet v searchCategory vivodilas li kniga na pe4at
	 */	
	public boolean bookIsPrinting(Book book) {
		boolean result = false;
		
		for (Book bookPrinted : searchCategory) {
			
			if (bookPrinted.equals(book)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/* Method: updateCash(Book book)
	 * Parameters:
	 * 		Book book - book dlya dobavleniya v  searchCategory (eto cash)
	 * Return value:
	 * 		void
	 * Use:
	 * 		Dobavlyaet v cash-massiv knigu kotoruyu vuveli na pe4at
	 */	
	public void updateCash(Book book) {
		
		int lengthOldArray = searchCategory.length;
		
		Book[] searchCategoryNew = new Book[lengthOldArray + 1];
		
		System.arraycopy(searchCategory, 0, searchCategoryNew, 0, lengthOldArray);
		
		searchCategoryNew[searchCategoryNew.length - 1] = book;
		
		searchCategory = searchCategoryNew;
		
	}
	
	/* Method: printCategory(Book[] books)
	 * Parameters:
	 * 		Book[] book - book array dlya vuvoda na pe4at
	 * Return value:
	 * 		void
	 * Use:
	 * 		Vuvodit na pe4at array of book. Proveryaet vivodilas li ranee book
	 */	
	public void printCategory(Book[] books){
		
		int idx = 0;
		for (Book book_ : books) {
			
			if (book_ == null || bookIsPrinting(book_) == true) {
				continue;
			}			
			
			System.out.println("Book: " + book_.getName());
			
			updateCash(book_);
						
			idx++;
			
			if (idx >= 5) {
				break;
			}
		}
		
	}
	
	public void print(Book[] books){
		
		for (Book book_ : books) {
			
			if (book_ == null) {
				continue;
			}	
						
			System.out.println("Book: " + book_.getName());
			
		}
		
	}

}
