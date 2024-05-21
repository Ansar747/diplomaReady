package com.example.bottomnavigationbar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var imageAvatar: ImageView
    private lateinit var textNickname: TextView
    private lateinit var editNickname: EditText
    private lateinit var buttonEditNickname: ImageButton
    private lateinit var buttonSave: Button

    private var isEditing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        imageAvatar = view.findViewById(R.id.avatarID)
        textNickname = view.findViewById(R.id.textNickname)
        editNickname = view.findViewById(R.id.editNickname)
        buttonEditNickname = view.findViewById(R.id.buttonEditNickname)
        buttonSave = view.findViewById(R.id.buttonSave)

        // Загружаем текущее значение никнейма из SharedPreferences
        val sharedPref = requireContext().getSharedPreferences(
            "my_app_preferences", // Название файла SharedPreferences
            Context.MODE_PRIVATE // Режим доступа
        )
        val savedNickname = sharedPref.getString("user_nickname", "")
        textNickname.text = savedNickname // Устанавливаем текущее значение никнейма

        // Устанавливаем обработчик нажатия на кнопку "Изменить никнейм"
        buttonEditNickname.setOnClickListener {
            isEditing = !isEditing
            if (isEditing) {
                textNickname.visibility = View.GONE
                editNickname.visibility = View.VISIBLE
                editNickname.setText(textNickname.text)
                buttonEditNickname.setImageResource(R.drawable.baseline_edit_24) // Установите изображение отмены
                buttonSave.visibility = View.VISIBLE
            } else {
                textNickname.visibility = View.VISIBLE
                editNickname.visibility = View.GONE
                buttonEditNickname.setImageResource(R.drawable.baseline_edit_24) // Установите изображение для изменения
                buttonSave.visibility = View.GONE
            }

        }

        // Устанавливаем обработчик нажатия на кнопку "Сохранить"
        buttonSave.setOnClickListener {
            textNickname.text = editNickname.text
            // Сохраняем никнейм в приложении, например, с помощью SharedPreferences
            saveNickname(editNickname.text.toString())
            // Возвращаемся к режиму просмотра
            isEditing = false
            textNickname.visibility = View.VISIBLE
            editNickname.visibility = View.GONE
            buttonEditNickname.setImageResource(R.drawable.baseline_edit_24) // Установите изображение для изменения
            buttonSave.visibility = View.GONE
        }

        return view
    }


    // Метод для сохранения никнейма в приложении (используя SharedPreferences)
    private fun saveNickname(nickname: String) {
        // Получаем доступ к SharedPreferences для нашего приложения
        val sharedPref = requireContext().getSharedPreferences(
            "my_app_preferences", // Название файла SharedPreferences
            Context.MODE_PRIVATE // Режим доступа
        )

        // Получаем объект Editor для редактирования SharedPreferences
        val editor = sharedPref.edit()

        // Сохраняем никнейм в SharedPreferences
        editor.putString("user_nickname", nickname)

        // Применяем изменения
        editor.apply()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}