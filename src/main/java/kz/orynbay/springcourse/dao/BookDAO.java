package kz.orynbay.springcourse.dao;

import kz.orynbay.springcourse.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id = ?", new BookMapper(),
                        new Object[]{id})
                .stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(name,author,year) values(?,?,?)", book.getName(),book.getAuthor(),
                book.getYear());
    }
    public void update(Book updatedBook, int id){
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE book_id = ?", updatedBook.getName(),
                updatedBook.getAuthor(),updatedBook.getYear(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }
    public void setPersonId(int bookId,int personId){
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", personId, bookId);
    }
    public List<Book> getBooksByAuthor(int person_id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new BookMapper(),
                new Object[]{person_id});
    }
    public void freeFromPerson(int bookId){
        jdbcTemplate.update("UPDATE BOOK SET person_id = null WHERE book_id = ? ", bookId);
    }
}
