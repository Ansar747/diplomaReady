package com.example.bottomnavigationbar

data class Question(
    val questionText: String,
    val answerOptions: List<String>,
    val correctAnswerIndex: Int,
    var userAnswerIndex: Int? = null
) {
    val isCorrect: Boolean
        get() = userAnswerIndex == correctAnswerIndex
}