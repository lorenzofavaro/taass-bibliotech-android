package taass.bibliotech.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taass.bibliotech.R
import taass.bibliotech.model.StudyHall
import taass.bibliotech.ui.main.HomeActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*


class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.books -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.study


        val sharedPreferences =
            applicationContext.getSharedPreferences("data", Activity.MODE_PRIVATE)
        val studyHalls = Gson().fromJson(
            sharedPreferences.getString("studyhalls", ""),
            Array<StudyHall>::class.java
        ).asList()

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = StudyHallsAdapter(studyHalls)
        recyclerview.adapter = adapter
    }
}