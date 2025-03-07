package com.merfolkstation.grocery.tracker.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GroceryTrackerSpringApplication

fun main(args: Array<String>) {
	runApplication<GroceryTrackerSpringApplication>(*args)
}
