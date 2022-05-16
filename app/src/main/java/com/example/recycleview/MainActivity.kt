package com.example.recycleview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    private val items = mutableListOf(
        NewsItem("Title 5", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 3", Color.GRAY),
        NewsItem("Title 7", "SubTitle 2", Color.CYAN),
        NewsItem("Title 8", "SubTitle 1", Color.DKGRAY),
        NewsItem("Title 5", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 3", Color.GRAY),
        NewsItem("Title 7", "SubTitle 2", Color.CYAN),
        NewsItem("Title 8", "SubTitle 1", Color.DKGRAY),
        NewsItem("Title 9", "SubTitle 2", Color.YELLOW),
        NewsItem("Title 8", "SubTitle 3", Color.LTGRAY),
        NewsItem("Title 7", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 5", Color.GRAY),
        NewsItem("Title 5", "SubTitle 6", Color.CYAN),
        NewsItem("Title 4", "SubTitle 7", Color.DKGRAY),
        NewsItem("Title 3", "SubTitle 8", Color.YELLOW),
        NewsItem("Title 2", "SubTitle 9", Color.LTGRAY),
        NewsItem("Title 5", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 3", Color.GRAY),
        NewsItem("Title 7", "SubTitle 2", Color.CYAN),
        NewsItem("Title 8", "SubTitle 1", Color.DKGRAY),
        NewsItem("Title 9", "SubTitle 2", Color.YELLOW),
        NewsItem("Title 8", "SubTitle 3", Color.LTGRAY),
        NewsItem("Title 7", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 5", Color.GRAY),
        NewsItem("Title 5", "SubTitle 6", Color.CYAN),
        NewsItem("Title 4", "SubTitle 7", Color.DKGRAY),
        NewsItem("Title 3", "SubTitle 8", Color.YELLOW),
        NewsItem("Title 2", "SubTitle 9", Color.LTGRAY),
        NewsItem("Title 5", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 3", Color.GRAY),
        NewsItem("Title 7", "SubTitle 2", Color.CYAN),
        NewsItem("Title 8", "SubTitle 1", Color.DKGRAY),
        NewsItem("Title 9", "SubTitle 2", Color.YELLOW),
        NewsItem("Title 8", "SubTitle 3", Color.LTGRAY),
        NewsItem("Title 7", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 5", Color.GRAY),
        NewsItem("Title 5", "SubTitle 6", Color.CYAN),
        NewsItem("Title 4", "SubTitle 7", Color.DKGRAY),
        NewsItem("Title 3", "SubTitle 8", Color.YELLOW),
        NewsItem("Title 2", "SubTitle 9", Color.LTGRAY),
        NewsItem("Title 5", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 3", Color.GRAY),
        NewsItem("Title 7", "SubTitle 2", Color.CYAN),
        NewsItem("Title 8", "SubTitle 1", Color.DKGRAY),
        NewsItem("Title 9", "SubTitle 2", Color.YELLOW),
        NewsItem("Title 8", "SubTitle 3", Color.LTGRAY),
        NewsItem("Title 7", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 5", Color.GRAY),
        NewsItem("Title 5", "SubTitle 6", Color.CYAN),
        NewsItem("Title 4", "SubTitle 7", Color.DKGRAY),
        NewsItem("Title 3", "SubTitle 8", Color.YELLOW),
        NewsItem("Title 2", "SubTitle 9", Color.LTGRAY),
        NewsItem("Title 9", "SubTitle 2", Color.YELLOW),
        NewsItem("Title 8", "SubTitle 3", Color.LTGRAY),
        NewsItem("Title 7", "SubTitle 4", Color.BLUE),
        NewsItem("Title 6", "SubTitle 5", Color.GRAY),
        NewsItem("Title 5", "SubTitle 6", Color.CYAN),
        NewsItem("Title 4", "SubTitle 7", Color.DKGRAY),
        NewsItem("Title 3", "SubTitle 8", Color.YELLOW),
        NewsItem("Title 2", "SubTitle 9", Color.LTGRAY),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val toolbar = findViewById<Toolbar>(R.id.t)

        initRecycler()
        initListener()
    }

    private fun initListener() {
        findViewById<Button>(R.id.addButton).setOnClickListener{
            items.add(2, NewsItem("Added manually", "Ohhh", Color.LTGRAY))
            recyclerView.adapter?.notifyItemInserted(3)
        }
        findViewById<Button>(R.id.deleteButton).setOnClickListener{
            items.removeAt(2)
            recyclerView.adapter?.notifyItemRemoved(3)
        }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = NewsItemAdapter(items, object: NewsItemAdapter.NewsClickListener {
            override fun onNewsClick(newsItem: NewsItem, position: Int) {
                Toast.makeText(this@MainActivity, "News click", Toast.LENGTH_SHORT).show()
            }

            override fun onFavoriteClick(newsItem: NewsItem, position: Int) {
                Toast.makeText(this@MainActivity, "Favorite click", Toast.LENGTH_SHORT).show()
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)?.let {
            divider.setDrawable(it)
        }
        recyclerView.addItemDecoration(divider)

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() >= items.size - 5) {
                    repeat(10) {
                        items.add(NewsItem("New title", "New subtitle", Color.DKGRAY))
                    }
                    recyclerView.adapter?.notifyItemRangeInserted(items.size + 1, 10)
                }
            }
        })

    }
}