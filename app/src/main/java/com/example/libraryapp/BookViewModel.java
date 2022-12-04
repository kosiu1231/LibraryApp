package com.example.libraryapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.libraryapp.BookRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private final BookRepository bookRepository;
    private final LiveData<List<Book>> books;

    public BookViewModel(@NotNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
        books = bookRepository.findAllBooks();
    }

    public LiveData<List<Book>> findAll() {
        return books;
    }

    public void insert(Book book) {
        bookRepository.insert(book);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }
}