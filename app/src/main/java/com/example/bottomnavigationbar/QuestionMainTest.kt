package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.bottomnavigationbar.databinding.ActivityQuestionMainTestBinding

class QuestionMainTest : AppCompatActivity() {

    private var _binding: ActivityQuestionMainTestBinding? = null
    private val binding get() = _binding!!

    private var currentQuestionIndex = 0

    private var answerSelected = false

    private val results = ArrayList<Boolean>()

    private var countDownTimer: CountDownTimer? = null
    private val totalTime = 10000

    private val questions = listOf(
        Question("Кто был первым президентом Республики Казахстан?", listOf("Нурсултан Назарбаев", "Ислам Каримов", "Сапарбай Раисов", "Касым-Жомарт Токаев"), 0),
        Question("В каком году Казахстан обрел независимость от Советского Союза?", listOf("1989", "1991", "1993", "1995"), 1),
        Question("Какое историческое событие произошло на территории Казахстана в 1723 году?", listOf("Великий шелковый путь", "Казахское ханство", "Актюбинская битва", "Годы великого бедствия"), 3),
        Question("Кто из этих деятелей был известным казахским поэтом и композитором XIX века?", listOf("Абылай хан", "Чокан Валиханов", "Абай Кунанбаев", "Мухтар Ауэзов"), 2),
        Question("Какое из этих озер является крупнейшим в Казахстане?", listOf("Балхаш", "Каспийское море", "Алаколь", "Иссык-Куль"), 1),
        Question("Кто из этих известных личностей является героем Казахского ханства?", listOf("Абылай хан","Кенесары Касымов","Тауке хан","Все вышеперечисленные"),3),
        Question("Какой город был столицей Казахстана до Астаны?", listOf("Алматы","Шымкент","Караганда","Усть-Каменогорск"),0),
        Question("Кто был последним ханом Казахского ханства?", listOf("Абылай хан","Тауке хан","Кенесары Касымов","Жангир хан"),2),
        Question("Какое событие положило начало Золотому Веку Казахского ханства?", listOf("Образование Советского Союза","Правление Абылая хана","Великий Шелковый Путь","Падение Джунгарского ханства"),1),
        Question("Кто является автором эпической поэмы \"Кобланды-батыр\"?", listOf("Абылай хан","Абай Кунанбаев","Махамбет Өтемісұлы","Народное творчество"),3)
    )

    private val totalQuestions = questions.size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityQuestionMainTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstQuestion = questions[currentQuestionIndex]
        setupQuestionView(firstQuestion)

        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            endTestAndNavigateToMainActivity()
        }
    }

    private fun setupQuestionView(question: Question) {
        binding.textView.text = question.questionText

        binding.tvVariantValue1.text = question.answerOptions[0]
        binding.tvVariantValue2.text = question.answerOptions[1]
        binding.tvVariantValue3.text = question.answerOptions[2]
        binding.tvVariantValue4.text = question.answerOptions[3]

        startCountdownTimer()

        setAnswerOptionClickListeners(question.answerOptions, question.correctAnswerIndex)

        binding.btnSkip.setOnClickListener {
            setupNextQuestion()
        }
        binding.btnContinue.setOnClickListener {
            setupNextQuestion()
        }
    }

    private fun setAnswerOptionClickListeners(answerOptions: List<String>, correctAnswerIndex: Int) {
        if (!answerSelected) {
            binding.layoutAnswer1.setOnClickListener {
                handleAnswerClick(0, correctAnswerIndex)
            }

            binding.layoutAnswer2.setOnClickListener {
                handleAnswerClick(1, correctAnswerIndex)
            }

            binding.layoutAnswer3.setOnClickListener {
                handleAnswerClick(2, correctAnswerIndex)
            }

            binding.layoutAnswer4.setOnClickListener {
                handleAnswerClick(3, correctAnswerIndex)
            }
        }
    }

    private fun handleAnswerClick(selectedIndex: Int, correctAnswerIndex: Int) {
        answerSelected = true
        disableAnswerOptions()

        val isCorrect = selectedIndex == correctAnswerIndex
        results.add(isCorrect)

        val selectedLayout = binding.layoutAnswerViews.getChildAt(selectedIndex) as LinearLayout

        val index = selectedIndex + 1

        val numberTextViewId = resources.getIdentifier("tvVariantNumber$index", "id", packageName)
        val valueTextViewId = resources.getIdentifier("tvVariantValue$index", "id", packageName)

        if (selectedIndex == correctAnswerIndex) {
            markAnswerCorrect(selectedLayout, numberTextViewId, valueTextViewId)
        } else {
            markAnswerWrong(selectedLayout, numberTextViewId, valueTextViewId)
        }
    }

    private fun startCountdownTimer() {
        countDownTimer = object : CountDownTimer(totalTime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 100).toInt()
                binding.progressBarTimer.progress = progress
            }

            override fun onFinish() {
                handleTimeout()
            }
        }.start()
    }

    private fun handleTimeout() {
        setupNextQuestion()
    }

    private fun cancelCountdownTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun setupNextQuestion() {
        cancelCountdownTimer()
        resetAnswerStyles()
        answerSelected = false

        if (currentQuestionIndex < totalQuestions - 1) {
            currentQuestionIndex++
            val nextQuestion = questions[currentQuestionIndex]
            setupQuestionView(nextQuestion)

            if (countDownTimer == null) {
                startCountdownTimer()
            }
        } else {
            showTestResults()
        }
    }

    private fun disableAnswerOptions() {
        answerSelected = true

        binding.layoutAnswer1.setOnClickListener(null)
        binding.layoutAnswer2.setOnClickListener(null)
        binding.layoutAnswer3.setOnClickListener(null)
        binding.layoutAnswer4.setOnClickListener(null)
    }

    private fun resetAnswerStyles() {
        resetAnswerStyle(binding.layoutAnswer1, R.id.tvVariantNumber1, R.id.tvVariantValue1)
        resetAnswerStyle(binding.layoutAnswer2, R.id.tvVariantNumber2, R.id.tvVariantValue2)
        resetAnswerStyle(binding.layoutAnswer3, R.id.tvVariantNumber3, R.id.tvVariantValue3)
        resetAnswerStyle(binding.layoutAnswer4, R.id.tvVariantNumber4, R.id.tvVariantValue4)

        binding.layoutResult.isVisible = false
        binding.btnSkip.isVisible = true
    }

    private fun resetAnswerStyle(layout: LinearLayout, variantNumberId: Int, variantValueId: Int) {
        layout.background = ContextCompat.getDrawable(this, R.drawable.shaped_rounded_containers)

        val numberTextView = layout.findViewById<TextView>(variantNumberId)
        val valueTextView = layout.findViewById<TextView>(variantValueId)

        numberTextView.background = ContextCompat.getDrawable(this, R.drawable.shaped_rounded_variants)
        numberTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
        valueTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    private fun markAnswerWrong(layout: LinearLayout, variantNumberId: Int, variantValueId: Int) {
        layout.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_wrong)

        val index = binding.layoutAnswerViews.indexOfChild(layout) + 1

        val numberTextViewId = resources.getIdentifier("tvVariantNumber$index", "id", packageName)
        val valueTextViewId = resources.getIdentifier("tvVariantValue$index", "id", packageName)

        val numberTextView = layout.findViewById<TextView>(numberTextViewId)
        val valueTextView = layout.findViewById<TextView>(valueTextViewId)

        numberTextView.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_wrong)
        numberTextView.setTextColor(ContextCompat.getColor(this, R.color.white))
        valueTextView.setTextColor(ContextCompat.getColor(this, R.color.redWrongColor))

        binding.btnSkip.isVisible = false

        binding.layoutResult.setBackgroundColor(
            ContextCompat.getColor(
                this@QuestionMainTest,
                R.color.redWrongColor
            )
        )

        binding.ivResultIcon.setImageDrawable(
            ContextCompat.getDrawable(
                this@QuestionMainTest,
                R.drawable.ic_wrong
            )
        )

        binding.tvResultMessage.text = resources.getString(R.string.title_wrong)

        binding.btnContinue.setTextColor(
            ContextCompat.getColor(
                this@QuestionMainTest,
                R.color.redWrongColor
            )
        )

        binding.layoutResult.isVisible = true
    }

    private fun markAnswerCorrect(layout: LinearLayout, variantNumberId: Int, variantValueId: Int) {
        layout.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_correct)

        val index = binding.layoutAnswerViews.indexOfChild(layout) + 1

        val numberTextViewId = resources.getIdentifier("tvVariantNumber$index", "id", packageName)
        val valueTextViewId = resources.getIdentifier("tvVariantValue$index", "id", packageName)

        val numberTextView = layout.findViewById<TextView>(numberTextViewId)
        val valueTextView = layout.findViewById<TextView>(valueTextViewId)

        numberTextView.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_correct)
        numberTextView.setTextColor(ContextCompat.getColor(this, R.color.white))
        valueTextView.setTextColor(ContextCompat.getColor(this, R.color.greenCorrectColor))

        binding.btnSkip.isVisible = false

        binding.layoutResult.setBackgroundColor(
            ContextCompat.getColor(
                this@QuestionMainTest,
                R.color.greenCorrectColor
            )
        )

        binding.ivResultIcon.setImageDrawable(
            ContextCompat.getDrawable(
                this@QuestionMainTest,
                R.drawable.ic_correct
            )
        )

        binding.tvResultMessage.text = resources.getString(R.string.title_correct)

        binding.btnContinue.setTextColor(
            ContextCompat.getColor(
                this@QuestionMainTest,
                R.color.greenCorrectColor
            )
        )

        binding.layoutResult.isVisible = true
    }

    private fun showTestResults() {
        cancelCountdownTimer()

        val intent = Intent(this, TestResultsActivity::class.java)
        intent.putExtra("results", results.toBooleanArray())
        startActivity(intent)
    }

    private fun endTestAndNavigateToMainActivity() {
        cancelCountdownTimer()
        val intent = Intent(this, InteractiveTestActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        endTestAndNavigateToMainActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
