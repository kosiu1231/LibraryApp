package com.example.libraryapp;

import android.app.Application;

import com.example.libraryapp.BookDatabase;
import com.example.libraryapp.BookDao;
import com.example.libraryapp.Book;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {
    private final BookDao bookDao;
    private final LiveData<List<Book>> books;

    public BookRepository(Application application) {
        BookDatabase database = BookDatabase.getDatabase(application);
        bookDao = database.bookDao();
        books = bookDao.findAll();
    }

    public LiveData<List<Book>> findAllBooks() {
        return books;
    }

    public void insert(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> bookDao.insert(book));
    }

    public void update(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> bookDao.update(book));
    }

    public void delete(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> bookDao.delete(book));
    }
}
