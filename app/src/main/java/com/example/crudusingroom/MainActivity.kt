package com.example.crudusingroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudusingroom.db.ItemDatabase
import com.example.crudusingroom.modal.Items
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getToolsData()
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

    }
    private fun getToolsData(){

        val database = ItemDatabase.getDatabase(applicationContext)
        val dao = database.getItemsDao()

        val listItems = arrayListOf<Items>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text.visibility = View.GONE
        }
        else {
            text.visibility = View.VISIBLE
        }

    }
    private fun setupRecyclerView(listItems : ArrayList<Items>){
        recycler_view_main.apply {
            adapter = ItemsAdapter (listItems, object : ItemsAdapter.ItemsListener{
                override fun OnItemClicked(items: Items) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra(EditActivity().EDIT_TOOLS_EXTRA, items)
                    startActivity(intent)

                }
            })

            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getToolsData()
    }

}