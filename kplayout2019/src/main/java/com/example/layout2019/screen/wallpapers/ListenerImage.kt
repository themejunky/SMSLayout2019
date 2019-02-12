package com.example.layout2019.screen.wallpapers

import java.text.FieldPosition

interface ListenerImage {
     fun whenClickOnImage(urlString: String,isClicked:Boolean,position: Int)
}