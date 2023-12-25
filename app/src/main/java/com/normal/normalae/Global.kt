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

    var cerbungs =arrayListOf(
            Cerbung(1, "Interstellar",
                "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                arrayListOf(
                    Paragraph("user1", 1,"After the crew becomes sick with food poisoning, a neurotic ex-fighter pilot must land a commercial airplane full of passengers safely."),
                ),"https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg",
                true),
            Cerbung(2, "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                arrayListOf(
                    Paragraph("user1", 2,"After the crew becomes sick with food poisoning, a neurotic ex-fighter pilot must land a commercial airplane full of passengers safely."),
                ),"https://live.staticflickr.com/737/32640476365_906f64ce29_b.jpg",
                true),
        Cerbung(3, "Airplane!",
            "After the crew becomes sick with food poisoning, a neurotic ex-fighter pilot must land a commercial airplane full of passengers safely.",
            arrayListOf(
                Paragraph("user1", 3,"After the crew becomes sick with food poisoning, a neurotic ex-fighter pilot must land a commercial airplane full of passengers safely."),
            ),
            "https://upload.wikimedia.org/wikipedia/en/2/21/Airplane%21_%281980_film%29.jpg",
            true),
        )

    var users = arrayListOf(
        User("user1", "user1"),
        User("user2", "user2"),
    )
}