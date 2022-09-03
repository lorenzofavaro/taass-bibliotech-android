package taass.bibliotech.ui.main

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taass.bibliotech.R
import taass.bibliotech.model.Books
import com.google.gson.Gson

class ContainerFragment : Fragment() {

    private lateinit var adapter: ContainerAdapter

    private val LABEL_FANTASY = "Fantasy"
    private val LABEL_CLASSICO = "Classico"
    private val LABEL_ROMANZO = "Romanzo"
    private val LABEL_PSICOLOGIA = "Psicologia"

    private var allBooks: List<Books> = ArrayList()

    private val productsRecommended: MutableList<Books> = ArrayList()
    private val productsRomanzi: MutableList<Books> = ArrayList()
    private val productsClassici: MutableList<Books> = ArrayList()
    private val productsLingue: MutableList<Books> = ArrayList()


    companion object {

        val SECTION: String = "section"

        @JvmStatic
        fun newInstance(name: String): ContainerFragment {
            val args = Bundle()
            args.putString(SECTION, name)
            val fragment = ContainerFragment()
            fragment.arguments = args
            return fragment
        }
    }


    private lateinit var layoutManager: RecyclerView.LayoutManager


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.container_frament, container, false)
        val page_section = arguments?.getString(SECTION)

        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("data", Activity.MODE_PRIVATE)
        allBooks = Gson().fromJson(sharedPreferences.getString("catalog", ""), Array<Books>::class.java).asList()

        if (page_section != null) {
            loadProducts(view, page_section)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadProducts(view: View, page_section: String) {

        //Suddivisioni per categoria
        for (book in allBooks) {
            val bookCategories = book.categories
            if (bookCategories.isEmpty())
                continue

            for (category in bookCategories) {
                if (category.name == LABEL_FANTASY)
                    productsRecommended.add(book)
                if (category.name == LABEL_CLASSICO)
                    productsClassici.add(book)
                if (category.name == LABEL_ROMANZO)
                    productsRomanzi.add(book)
                if (category.name == LABEL_PSICOLOGIA)
                    productsLingue.add(book)
            }
        }

        val recyclerViewProducts = view.findViewById<RecyclerView>(R.id.rv_recommend)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProducts?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsRecommended)
        recyclerViewProducts?.adapter = adapter


        //Section Romanzi
        val vegRecyclerView = view.findViewById<RecyclerView>(R.id.rv_romanzi)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        vegRecyclerView?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsRomanzi)
        vegRecyclerView?.adapter = adapter

        // Section Classici
        val recyclerViewChinies = view.findViewById<RecyclerView>(R.id.rv_classici)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewChinies?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsClassici)
        recyclerViewChinies?.adapter = adapter

        // Section Lingue
        val recyclerViewThai = view.findViewById<RecyclerView>(R.id.rv_lingue)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewThai?.layoutManager = layoutManager
        adapter = ContainerAdapter(productsLingue)
        //adapter.replaceItems(products)
        recyclerViewThai?.adapter = adapter

    }
}