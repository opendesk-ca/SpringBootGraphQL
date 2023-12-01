package com.books.gql.controller;

import com.books.gql.domain.book.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BooksController {

    @QueryMapping
    Book getBookById (@Argument String id){
        return Book.getById(id);
    }

    @QueryMapping
    List<Book> getAllBooks () {
        return Book.getAllBooks();
    }
}

/*
query  {
	getAllBooks {
	  id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
	}
}


query  {
	getBookById(id: "book-2"){
	  id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
	}
}
 */