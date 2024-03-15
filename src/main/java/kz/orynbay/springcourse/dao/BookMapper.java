package kz.orynbay.springcourse.dao;

import kz.orynbay.springcourse.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setYear(resultSet.getInt("year"));
        if(resultSet.getObject("person_id") != null){
            book.setPersonId(resultSet.getInt("person_id"));
        }
        return book;
    }
}
