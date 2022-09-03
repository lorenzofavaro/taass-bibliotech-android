package taass.bibliotech.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import taass.bibliotech.R
import taass.bibliotech.model.Books
import taass.bibliotech.model.StudyHall
import taass.bibliotech.ui.main.HomeActivity
import com.google.gson.Gson


@SuppressLint("CustomSplashScreen")
class SplashActivity : Activity() {

    private lateinit var mRequestQueue: RequestQueue
    private val apiGetBooks = "http://10.0.2.2:8080/catalog-service/catalog/allCatalog"
    private val apiGetStudyHalls = "http://10.0.2.2:8080/studyhalls-service/studyhalls/all"

    private var booksReady = false
    private var studyHallsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mRequestQueue = Volley.newRequestQueue(this)
        pauseToLaunch()
    }

    private fun pauseToLaunch() {
        Handler().postDelayed({
            downloadData()
        }, 0)
    }

    private fun downloadData() {
        val requestBooks = StringRequest(Request.Method.GET, apiGetBooks,
            { response ->
                val list: List<Books> =
                    Gson().fromJson(response.toString(), Array<Books>::class.java).asList()
                Log.d("TAG", list.toString())
                val sharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("catalog", response.toString())
                editor.apply()
                booksReady = true
                checkReady()
            },
            { error -> error.printStackTrace() })

        val requestStudyHalls = StringRequest(Request.Method.GET, apiGetStudyHalls,
            { response ->
                val list: List<StudyHall> =
                    Gson().fromJson(response.toString(), Array<StudyHall>::class.java).asList()
                Log.d("TAG", list.toString())
                val sharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("studyhalls", response.toString())
                editor.apply()
                studyHallsReady = true
                checkReady()
            },
            { error -> error.printStackTrace() })

        mRequestQueue.add(requestBooks)
        mRequestQueue.add(requestStudyHalls)
    }


    private fun showMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkReady() {
        if (booksReady && studyHallsReady) showMainActivity()
    }

}