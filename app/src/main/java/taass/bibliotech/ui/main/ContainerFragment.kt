package taass.bibliotech.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taass.bibliotech.R
import taass.bibliotech.model.Books
import com.google.gson.Gson

class ContainerFragment : Fragment() {

    private lateinit var adapter: ContainerAdapter

    private val labelFantasy = "Fantasy"
    private val labelClassic = "Classico"
    private val labelRomance = "Romanzo"
    private val labelPsycho = "Psicologia"
    private var allBooks: List<Books> = ArrayList()
    private val fantasyBooks: MutableList<Books> = ArrayList()
    private val romanceBooks: MutableList<Books> = ArrayList()
    private val classicBooks: MutableList<Books> = ArrayList()
    private val psychoBooks: MutableList<Books> = ArrayList()


    companion object {

        const val SECTION: String = "section"

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


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.container_frament, container, false)
        val pageSection = arguments?.getString(SECTION)

        val sharedPreferences =
            requireActivity().applicationContext.getSharedPreferences("data", Activity.MODE_PRIVATE)
        allBooks =
            Gson().fromJson(sharedPreferences.getString("catalog", ""), Array<Books>::class.java)
                .asList()

        if (pageSection != null) {
            loadBooks(view)
        }
        return view
    }

    private fun loadBooks(view: View) {

        //Suddivisioni per categoria
        for (book in allBooks) {
            val bookCategories = book.categories
            if (bookCategories.isEmpty())
                continue

            for (category in bookCategories) {
                if (category.name == labelFantasy)
                    fantasyBooks.add(book)
                if (category.name == labelClassic)
                    classicBooks.add(book)
                if (category.name == labelRomance)
                    romanceBooks.add(book)
                if (category.name == labelPsycho)
                    psychoBooks.add(book)
            }
        }

        //Section Fantasy
        val fantasyBooksView = view.findViewById<RecyclerView>(R.id.rv_fantasy)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        fantasyBooksView?.layoutManager = layoutManager
        adapter = ContainerAdapter(fantasyBooks)
        fantasyBooksView?.adapter = adapter

        //Section Romanzi
        val romanceBooksView = view.findViewById<RecyclerView>(R.id.rv_romance)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        romanceBooksView?.layoutManager = layoutManager
        adapter = ContainerAdapter(romanceBooks)
        romanceBooksView?.adapter = adapter

        // Section Classici
        val classicBooksView = view.findViewById<RecyclerView>(R.id.rv_classic)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        classicBooksView?.layoutManager = layoutManager
        adapter = ContainerAdapter(classicBooks)
        classicBooksView?.adapter = adapter

        // Section Psicologia
        val psychoBooksView = view.findViewById<RecyclerView>(R.id.rv_psycho)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        psychoBooksView?.layoutManager = layoutManager
        adapter = ContainerAdapter(psychoBooks)
        psychoBooksView?.adapter = adapter

    }
}