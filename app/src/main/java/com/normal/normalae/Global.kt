package com.normal.normalae

object Global {
    //var cerbung = 0

    val genreList= arrayOf(
        Genre(1,"Action"),
        Genre(2, "Comedy"),
        Genre(3, "Sci-Fi"),
        Genre(4, "Drama"),
        Genre(5, "Horror"),
        Genre(6, "History")
    )

    val cerbungs =arrayOf(
            Cerbung("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg"),
            Cerbung("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.", "https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg"),
        )

    val users = arrayOf(
        User("user1", "user1"),
        User("user2", "user2"),
    )
}