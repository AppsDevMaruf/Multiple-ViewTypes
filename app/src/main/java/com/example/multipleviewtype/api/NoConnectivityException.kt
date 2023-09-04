package com.example.multipleviewtype.api
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Internet Connection !" //TODO Language

}