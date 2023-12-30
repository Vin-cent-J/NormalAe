package com.normal.normalae

object Global {
    //var cerbung = 0

    var user: User? = null
    val genreList= arrayOf(
        Genre(1,"Action"),
        Genre(2, "Comedy"),
        Genre(3, "Sci-Fi"),
        Genre(4, "Drama"),
        Genre(5, "Horror"),
        Genre(6, "History")
    )

    var users = arrayListOf(
        User("user1", "user1", "a"),
        User("user2", "user2", "a"),
    )
}