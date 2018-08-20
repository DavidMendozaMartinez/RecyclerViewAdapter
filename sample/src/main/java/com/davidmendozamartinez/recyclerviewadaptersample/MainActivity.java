package com.davidmendozamartinez.recyclerviewadaptersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.davidmendozamartinez.recyclerviewadapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = getBooks();

        RecyclerViewAdapter<Book> mAdapter = new RecyclerViewAdapter<>(books, R.layout.item_book, BR.book);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener<Book>() {
            @Override
            public void onItemClick(Book item) {
                Toast.makeText(MainActivity.this, "Clicked book: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Don't Make Me Think", "Steve Krug", 2000));
        books.add(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips", 2012));
        books.add(new Book("Essential Mobile Interaction Design", "Cameron Banga", 2014));
        books.add(new Book("Responsive Mobile Design", "Phil Dutson", 2014));
        books.add(new Book("Implementing Material Design for Developers", "Ian G. Clifton", 2015));
        books.add(new Book("Kotlin for Android Developers", "Antonio Leiva", 2016));
        books.add(new Book("Kotlin Programming: The Big Nerd Ranch Guide", "Josh Skeen & David Greenhalgh", 2018));
        books.add(new Book("Don't Make Me Think", "Steve Krug", 2000));
        books.add(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips", 2012));
        books.add(new Book("Essential Mobile Interaction Design", "Cameron Banga", 2014));
        books.add(new Book("Responsive Mobile Design", "Phil Dutson", 2014));
        books.add(new Book("Implementing Material Design for Developers", "Ian G. Clifton", 2015));
        books.add(new Book("Kotlin for Android Developers", "Antonio Leiva", 2016));
        books.add(new Book("Kotlin Programming: The Big Nerd Ranch Guide", "Josh Skeen & David Greenhalgh", 2018));

        return books;
    }
}
