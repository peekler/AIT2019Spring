package hu.ait.todorecyclerviewdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import hu.ait.todorecyclerviewdemo.adapter.TodoAdapter
import hu.ait.todorecyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.*

class ScrollingActivity : AppCompatActivity() {

    lateinit var todoAdapter : TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null).show()

            showAddTodoDialog()
        }


        todoAdapter = TodoAdapter(this)
        recyclerTodo.layoutManager = LinearLayoutManager(this)
        recyclerTodo.adapter = todoAdapter
    }

    private fun showAddTodoDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Enter todo")
        val input = EditText(this)
        dialogBuilder.setView(input)
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, button -> dialog.dismiss()
        }
        dialogBuilder.setPositiveButton("Add") {
                dialog, button ->
            val todo = Todo(
                Date(System.currentTimeMillis()).toString(),
                false, input.text.toString())

            todoAdapter.addTodo(todo)
        }
        dialogBuilder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
