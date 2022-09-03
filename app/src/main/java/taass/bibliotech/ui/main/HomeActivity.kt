package taass.bibliotech.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import taass.bibliotech.R
import taass.bibliotech.databinding.ActivityHomeBinding
import taass.bibliotech.ui.StudyActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupViewPager()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.study -> {
                    val intent = Intent(this, StudyActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun setupViewPager() {

        val adapter = HomeFragmentPagerAdapter(supportFragmentManager)
        val container = listOf("Shop")
        for (item in container) {
            val itemFrame: ContainerFragment = ContainerFragment.newInstance(item)
            adapter.addFragment(itemFrame, item)
        }


        binding?.viewpager!!.adapter = adapter
    }

}