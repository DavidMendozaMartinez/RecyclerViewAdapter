# RecyclerViewAdapter

[ ![Download](https://api.bintray.com/packages/davidmendozamartinez/maven/RecyclerViewAdapter/images/download.svg?version=1.0.0)](https://bintray.com/davidmendozamartinez/maven/RecyclerViewAdapter/1.0.0/link)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

RecyclerViewAdapter is an Android library that facilitates the use and configuration of RecyclerView. It also adds some basic functionality that does not include the default component which its predecessor `ListView` did.

## Introduction

### Goals
* Simplify the RecyclerView adapter configuration into a **single line**.
* Provide simple `setOnItemClickListener()` and `setOnItemLongClickListener()` methods to be invoked when an item in the RecyclerView is clicked.
* Add painless customizable **divisors** support for lists.

## Requirements
* **Data Binding Library**: For a greater simplification of the list items' configuration it is required to use the data binding library. This way, we avoid having to assign the corresponding value for each view's component in the adapter manually. You can find out how this library works in more detail [here](https://developer.android.com/topic/libraries/data-binding).

	To get started with data binding, download the library from the **Support Repository** in the Android SDK manager. For more information, see [Update the IDE and SDK Tools](https://developer.android.com/studio/intro/update.html).

	To configure your app to use data binding, add the dataBinding element to your **build.gradle** file in the **app module**, as shown in the following example:

~~~
    android {

        ....

        dataBinding {
            enabled = true
        }
    }
~~~

## Download
Add the following dependency to your **build.gradle** file in the **app module**: 

~~~
    dependencies {

        ....

        implementation 'com.github.DavidMendozaMartinez:RecyclerViewAdapter:1.0.0'
    }
~~~

## Basic usage

First we need to create the list's item resource layout with all its elements properly configured and a `variable` element set.

The variable's type will be the class model we are working with. In this case, we will use a simple Book class with three attributes (`title`, `author` and `year`) and we'll assign a name to identify it, for example `book`.

For each element of the view we'll assign the desired attribute's value of the variable that we have created. For example: **android:text="@{book.title}"**

* **R.layout.item_book:**
~~~xml

<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="book"
            type="com.davidmendozamartinez.recyclerviewadaptersample.Book" />
    </data>

    ....

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="17sp"
        android:textStyle="bold"
        android:text="@{book.title}" />

    ....

</layout>
~~~


Once we have the RecyclerView we proceed to assign our simple adapter. We will use the following constructor:

~~~java
RecyclerViewAdapter(ArrayList<T> list, int itemLayoutId, int variableId)
~~~

**\- list:** Item list to show.</br>
**\- itemLayoutId:** Id of the item resource layout.</br>
**\- variableId:** Id or Name of the item variable that we have assigned in the item resource layout.

* **MainActivty.java:** 
~~~java
....

mRecyclerView.setHasFixedSize(true);

mLayoutManager = new LinearLayoutManager(this);
mRecyclerView.setLayoutManager(mLayoutManager);

RecyclerViewAdapter<Book> mAdapter = new RecyclerViewAdapter<>(books, R.layout.item_book, BR.book);

mRecyclerView.setAdapter(mAdapter);

....
~~~

And this would be it! A list with divisors and with selectable background by default.

![](https://github.com/DavidMendozaMartinez/RecyclerViewAdapter/blob/master/static/basic_usage.gif?raw=true)

### **setOnItemClickListener()**

Once we have the adapter created we can call any of its available functions. For example, `setOnItemClickListener()`:

* **MainActivty.java:** 
~~~java
....

mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener<Book>() {
    @Override
    public void onItemClick(Book item) {
        Toast.makeText(MainActivity.this, "Clicked book: " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
});

....
~~~

The result would be the following:

![](https://github.com/DavidMendozaMartinez/RecyclerViewAdapter/blob/master/static/click_listener.gif?raw=true)


## Acknowledgments
Many thanks to [Javier Luna](https://github.com/JavierLuna) for helping me as always.


David