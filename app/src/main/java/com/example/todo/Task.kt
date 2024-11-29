package com.example.todo

data class Task(
    val id: String, // Unique ID for each task
    val title: String,
    val description: String,
    val deadline: String, // Date or other format if needed
    var status: String // Could be "To Do", "In Progress", "Done"
)
