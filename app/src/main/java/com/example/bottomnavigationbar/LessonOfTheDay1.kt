package com.example.bottomnavigationbar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.LinearProgressIndicator

class LessonOfTheDay1 : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var translateView: TextView
    private lateinit var button: Button
    private lateinit var progressBar: LinearProgressIndicator
    private lateinit var amountOfWordsTextView: TextView

    // Создаем массив элементов урока
    private val lessonItems = arrayOf(
        LessonItem(R.drawable.apple_photo, "alma", "яблоко"),
        LessonItem(R.drawable.cherry_photo, "shıe", "вишня"),
        LessonItem(R.drawable.grape_photo, "júzim", "виноград"),
        LessonItem(R.drawable.strawberry_photo, "qulpynaı", "клубника"),
        LessonItem(R.drawable.watermelon_photo, "qarbyz", "арбуз"),
        LessonItem(R.drawable.abrikos_photo, "órik", "абрикос"),
        LessonItem(R.drawable.peach_photo, "shabdaly", "персик "),
        LessonItem(R.drawable.pineapple_photo, "almurt", "груша"),
        LessonItem(R.drawable.raspberries_photo, "tańqýraı", "малина"),
        LessonItem(R.drawable.smorodina_photo, "qaraqat", "смородина"),
        LessonItem(R.drawable.pomegranate_photo, "anar", "гранат"),
        LessonItem(R.drawable.plums_photo, "qara órik", "чернослив"),
        LessonItem(R.drawable.blueberries_photo, "kókjıdek", "черника"),
        LessonItem(R.drawable.dynya_photo, "qaýyn", "дыня"),
        LessonItem(R.drawable.finiki_photo, "qurma", "финики"),
    )

    // Индекс текущего элемента урока
    private var currentItemIndex = 0

    private val totalWords = 15

    private var wordsLearned = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_of_the_day1)

        imageView = findViewById(R.id.rounded_image)
        textView = findViewById(R.id.text_of_image1)
        translateView = findViewById(R.id.translate_of_image1)
        button = findViewById(R.id.next_button)
        progressBar = findViewById(R.id.progress_bar_linear_lesson1)
        amountOfWordsTextView = findViewById(R.id.amount_of_words)

        // Установка начального элемента урока
        updateLessonItem()

        // Обработчик нажатия на кнопку
        button.setOnClickListener {
            // Загружаем анимацию для кнопки
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale_anim)

            // Устанавливаем продолжительность анимации (в миллисекундах)
            scaleAnimation.duration = 50

            // Запускаем анимацию для кнопки
            button.startAnimation(scaleAnimation)

            // Переходим к следующему элементу урока после завершения анимации кнопки
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    // Переход к следующему элементу урока
                    currentItemIndex = (currentItemIndex + 1) % lessonItems.size
                    updateLessonItem()
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }



        val imageView = findViewById<ImageView>(R.id.imageView2)
        imageView.setOnClickListener {
            finish() // Закрываем текущую активити, возвращаясь на предыдущую
        }

        val button = findViewById<Button>(R.id.quizz_button)
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale_anim)

        button.setOnClickListener {
            button.startAnimation(scaleAnimation)
        }

        val button2 = findViewById<Button>(R.id.flashcards_button)
        val scaleAnimation2 = AnimationUtils.loadAnimation(this, R.anim.button_scale_anim)

        button2.setOnClickListener {
            button2.startAnimation(scaleAnimation2)
        }


    }

    // Метод для обновления элемента урока (изображения и текста)
    @SuppressLint("SetTextI18n")
    private fun updateLessonItem() {
        val lessonItem = lessonItems[currentItemIndex]
        imageView.setImageResource(lessonItem.imageRes)
        textView.text = lessonItem.text
        translateView.text = lessonItem.translation

        // Увеличиваем счетчик слов
        wordsLearned++

        if (wordsLearned == totalWords) {
            // Если достигнут предел слов, устанавливаем текст кнопки "Заново"
            button.text = "Заново"
            // Сбрасываем счетчик слов
            wordsLearned = 0
        } else {
            // Устанавливаем текст кнопки "Следующий"
            button.text = "Следующий"
        }

        val progress = (wordsLearned.toFloat() / totalWords.toFloat()) * 100
        progressBar.progress = progress.toInt()

        amountOfWordsTextView.text = "$wordsLearned/$totalWords слов изучено"
    }







}

// Класс для представления элемента урока
data class LessonItem(val imageRes: Int, val text: String, val translation: String)
