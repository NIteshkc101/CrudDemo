package com.example.crudusingroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.crudusingroom.db.ItemDao
import com.example.crudusingroom.db.ItemDatabase
import com.example.crudusingroom.modal.Items
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {


    val EDIT_TOOLS_EXTRA = "edit_tools_extra"
    private lateinit var  items: Items
    private var isUpdate = false
    private lateinit var database: ItemDatabase
    private lateinit var dao: ItemDao




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        database = ItemDatabase.getDatabase(applicationContext)
        dao = database.getItemsDao()



        if (intent.getParcelableExtra<Items>(EDIT_TOOLS_EXTRA) != null){
            btn_delete.visibility = View.VISIBLE
            isUpdate = true
            items = intent.getParcelableExtra(EDIT_TOOLS_EXTRA)!!
            tv_input_name.setText(items.item)
            tv_input_borrower.setText(items.friends)
            tv_input_qty.setText(items.qty)
            tv_input_borrower_qty.setText(items.get)

        }

        btn_save.setOnClickListener{
            val item = tv_input_name.text.toString()
            val friends = tv_input_borrower.text.toString()
            val qty = tv_input_qty.text.toString()
            val get = tv_input_borrower_qty.text.toString()

            if (item.isEmpty() && friends.isEmpty() && qty.isEmpty() && get.isEmpty()){
                Toast.makeText(applicationContext, "Note cannot be empty", Toast.LENGTH_SHORT).show()

            }
            else{
                if (isUpdate){
                    saveTools(Items(id = items.id, item = item, qty = qty , friends = friends , get = get ))
                }
                else{
                    saveTools(Items(item = item,qty = qty , friends = friends , get = get  ))
                }
            }

            finish()
        }

        btn_delete.setOnClickListener {
            deleteTools(items)
            finish()
        }
    }

    private fun saveTools(items: Items){
        if(dao.getById(items.id).isEmpty()){
            dao.insert(items)

        } else{
            dao.update(items)

        }

        Toast.makeText(applicationContext, "Items Saved", Toast.LENGTH_SHORT).show()


    }


    private fun deleteTools(items: Items){
        dao.delete(items)
        Toast.makeText(applicationContext, "Item Delete", Toast.LENGTH_SHORT).show()
    }

}