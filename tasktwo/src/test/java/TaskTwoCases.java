import model.Author;
import model.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Task two.
 */
public class TaskTwoCases {

    private final List<Book> bookList = populate();
    private final Stream<Book> bookStream = bookList.stream();

    @Test
    public void testBookHasMoreThan200Pages() {
        Stream<Book> moreThan200PagesStream = this.bookStream.filter(book -> book.getNumberOfPages() >= 200);
        moreThan200PagesStream.forEach(System.out::println);
    }

    @Test
    public void testBookWithMaxNumberOfPages() {
        Optional<Book> max = this.bookStream.max(Comparator.comparingInt(Book::getNumberOfPages));
        System.out.println(max.get()); // could throw NPE
    }

    @Test
    public void testBookWithMinNumberOfPages() {
        Optional<Book> min = this.bookStream.min(Comparator.comparingInt(Book::getNumberOfPages));
        System.out.println(min.get()); // could throw NPE
    }

    @Test
    public void testBookWithSingleAuthor() {
        Stream<Book> bookStream = this.bookStream.filter(book -> book.getAuthors().size() == 1);
        bookStream.forEach(System.out::println);
    }

    @Test
    public void testSortBookByNumberOfPages() {
        Stream<Book> sorted = this.bookStream.sorted(Comparator.comparing(Book::getNumberOfPages));
        sorted.forEach(System.out::println);
    }

    @Test
    public void testGetListOfAllTitles() {
        Stream<String> titleStream = this.bookStream.map(Book::getTitle);
        titleStream.forEach(System.out::println);
    }

    @Test
    public void testDistinctAllOfAuthor() {
        List<Author> collect = this.bookStream.flatMap(book -> book.getAuthors().stream()).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testPeekFunction() {
        this.bookStream.peek(book -> System.out.println("This is debug (?): " + book));
    }

    @Test
    public void testParallel() {
        Stream<Book> bookStream = this.bookStream.parallel().filter(book -> book.getNumberOfPages() >= 200);
        bookStream.forEach(System.out::println);
    }

    @Test
    public void optionalUsage() {
        Optional<List<Author>> authors = this.bookStream.max(Comparator.comparingInt(Book::getNumberOfPages)).map(Book::getAuthors);
    }

    private static List<Book> populate(){
        Author author = new Author("Test");
        Author author1 = new Author("Test2");
        Author author2 = new Author("Test3");
        Author author3 = new Author("Test4");
        Author author4 = new Author("Test5");
        Book book = new Book("Title",Arrays.asList(author,author4),50);
        Book book1 = new Book("Title205",Arrays.asList(author1,author2),250);
        Book book2 = new Book("Bar",Arrays.asList(author3),200);
        Book book3 = new Book("Foo",Arrays.asList(author3),150);

        return Arrays.asList(book,book1,book2,book3);
    }

}
