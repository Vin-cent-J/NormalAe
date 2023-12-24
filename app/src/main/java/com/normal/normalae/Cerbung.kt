package com.normal.normalae

data class Cerbung(val id: Number, val title: String, val description: String, val paragraph: String = description, val url: String, var public: Boolean){
    var continuation = arrayListOf<String>("Continuation of Cerbung")
}
