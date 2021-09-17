package com.polotika.brokerage.pojo.models

sealed class Event {
    object Success : Event()
    object Failed : Event()
}