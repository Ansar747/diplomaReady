package com.example.bottomnavigationbar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ShapeableImageView>(R.id.fruit)?.setOnClickListener {
            val intent = Intent(activity, LessonOfTheDay1::class.java)
            startActivity(intent)
        }
        view.findViewById<TextView>(R.id.tv1)?.setOnClickListener {
            val intent = Intent(activity, LessonOfTheDay1::class.java)
            startActivity(intent)
        }
        view.findViewById<TextView>(R.id.tv2)?.setOnClickListener {
            val intent = Intent(activity, LessonOfTheDay1::class.java)
            startActivity(intent)
        }
        view.findViewById<TextView>(R.id.tvtime)?.setOnClickListener {
            val intent = Intent(activity, LessonOfTheDay1::class.java)
            startActivity(intent)
        }

        view.findViewById<ShapeableImageView>(R.id.iv_nauryz)?.setOnClickListener {
            val intent = Intent(activity, CultureItem1::class.java)
            startActivity(intent)
        }
        view.findViewById<TextView>(R.id.tv_nauryz)?.setOnClickListener {
            val intent = Intent(activity, CultureItem1::class.java)
            startActivity(intent)
        }
        view.findViewById<LinearLayout>(R.id.grammarLayoutHome)?.setOnClickListener {
            val intent = Intent(activity, GrammarActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<LinearLayout>(R.id.dictionaryID)?.setOnClickListener {
            val intent = Intent(activity, DictionaryActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<LinearLayout>(R.id.interactive_testID)?.setOnClickListener {
            val intent = Intent(activity, InteractiveTestActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<LinearLayout>(R.id.historyLayout)?.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}