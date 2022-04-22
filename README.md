# book-collection-mvc-demo
A demo project of Spring MVC using Thymeleaf, BootStrap, validation and persistence. 
```	
	• New Project > Spring Starter Project
		a. Artifact
		b. GroupID
Group id vs Artifact id for a project – User friendly Tech help (ufthelp.com)
	• Dependencies
		a. Spring Dev Tools
		b. Validation
		c. MariaDB
		d. Thymeleaf
		e. Spring Web
		f. JPA
		
	• Create file structure:
		Src/main/webapp/thymeleaf
		
	• Add index.html with:
		<!doctype html>
		<html lang="en">
		<head>
			<meta charset="UTF-8" />
			<title>Demo for MVC and Thymeleaf</title>
		</head>
		<body>
			<h1>Hello World</h1>
		</body>
		</html>
	
	• Create Package inside src/main/java called .controllers
		○ Create new Class:
		@Controller
		@RequestMapping
		Public String index() { return "index"}
		
	• Configure DB connections and thymeleaf file reference, Inside application.properties:
	
		spring.jpa.hibernate.ddl-auto=update
		
		spring.datasource.url=jdbc:mariadb://localhost:3306/books-library-db
		spring.datasource.username=root
		spring.datasource.password=rootpassword
		spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
		
		spring.thymeleaf.prefix=/thymeleaf/
		
	• Create new database in MariaDB matching datasource.url from above
		○ books-library-db
	• Create our model to structure our data and how Java interacts with a database.
		
		@Entity
		@Table(name="books")
		public class Book {
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		    @NotNull
		    @Size(min = 5, max = 200)
		    private String title;
		    @NotNull
		    @Size(min = 5, max = 200)
		    private String description;
		    @NotNull
		    @Size(min = 3, max = 40)
		    private String language;
		    @NotNull
		    @Min(100)
		    private Integer numberOfPages;
		    // This will not allow the createdAt column to be updated after creation
		    @Column(updatable=false)
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date createdAt;
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date updatedAt;
		    
		}
	• Create Getters and Setters for all fields.
	• Create Generic Constructor and Explicit constructor excluding Id, created_at, updated_at.
	• Create new class inside Controller name BooksApi
		@RestContoller
		@RequestMapping("api/books")
		Public String index() {return "Rest controller access";}
		
	• Create a repository interface to inherit from CrudRepository
	• Create new package .repositories > new interface BookRepository
		// ...
		@Repository
		public interface BookRepository extends CrudRepository<Book, Long>{
		    // this method retrieves all the books from the database
		    List<Book> findAll();
		    // this method finds books with descriptions containing the search string
		    List<Book> findByDescriptionContaining(String search);
		    // this method counts how many titles contain a certain string
		    Long countByTitleContaining(String search);
		    // this method deletes a book that starts with a specific title
		    Long deleteByTitleStartingWith(String search);
		}
		
	• Create a service package to implement Repository and interact with DB
	• Create new package .services > new class BookService
		@Service
		public class BookService {
		    // adding the book repository as a dependency
		    private final BookRepository bookRepository;
		    
		    public BookService(BookRepository bookRepository) {
		        this.bookRepository = bookRepository;
		    }
		    // returns all the books
		    public List<Book> allBooks() {
		        return bookRepository.findAll();
		    }
		    // creates a book
		    public Book createBook(Book b) {
		        return bookRepository.save(b);
		    }
		    // retrieves a book
		    public Book findBook(Long id) {
		        Optional<Book> optionalBook = bookRepository.findById(id);
		        if(optionalBook.isPresent()) {
		            return optionalBook.get();
		        } else {
		            return null;
		        }
		    }
		}

	• Populate some sample data
INSERT INTO books (created_at, description, LANGUAGE, NUMBER_of_pages, title)
VALUES (NOW(),"The Eternals, the ruling class of the Future, had the power of life...", "English", 256, "The End of Eternity"),
(NOW(), "A journey to the end of Mordar to save the world", "Spanish", 350, "The Hobbit"),
(NOW(), "The Eternals, the ruling class of the Future, had the power of life...", "English", 165, "The Fossil")

	• Modify our index.html to display books in our database
		Books-list.html
		
	• Adding bootstrap links
	• Creating a form
	
		
```		
![image](https://user-images.githubusercontent.com/64939708/164745133-590f9f49-7988-476d-89b0-3954a0cbe82c.png)
