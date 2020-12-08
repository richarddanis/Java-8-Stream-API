package model;

import java.util.List;

public class Author {
    private String name;
    private List<Book> bookList;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
