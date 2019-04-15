package hu.ait.todorecyclerviewdemo

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import hu.ait.todorecyclerviewdemo.adapter.TodoAdapter
import hu.ait.todorecyclerviewdemo.data.AppDatabase
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.new_todo_dialog.view.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import java.util.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {


    companion object {
        val KEY_ITEM_TO_EDIT = "KEY_ITEM_TO_EDIT"
    }

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

        if (!wasOpenedEarlier()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.fab)
                .setPrimaryText("New TODO")
                .setSecondaryText("Click here to create new todo items")
                .show()
        }

        saveFirstOpenInfo()

        initRecyclerViewFromDB()
    }

    fun saveFirstOpenInfo() {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPref.edit()
        editor.putBoolean("KEY_WAS_OPEN", true)
        editor.apply()
    }

    fun wasOpenedEarlier() : Boolean {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        return sharedPref.getBoolean("KEY_WAS_OPEN", false)
    }



    private fun initRecyclerViewFromDB() {
        Thread {
            var todoList = AppDatabase.getInstance(this@ScrollingActivity).todoDao().getAllTodos()

            runOnUiThread {
                // Update UI

                todoAdapter = TodoAdapter(this, todoList)

                recyclerTodo.layoutManager = LinearLayoutManager(this)

                //recyclerTodo.layoutManager = GridLayoutManager(this, 2)
                //recyclerTodo.layoutManager = StaggeredGridLayoutManager(2,
                //    StaggeredGridLayoutManager.VERTICAL)

                recyclerTodo.adapter = todoAdapter

                val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                recyclerTodo.addItemDecoration(itemDecoration)

                val callback = TodoReyclerTouchCallback(todoAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerTodo)
            }

        }.start()
    }

    private fun showAddTodoDialog() {
        TodoDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")
    }

    var editIndex: Int = -1

    public fun showEditTodoDialog(todoToEdit: Todo, idx: Int) {
        editIndex = idx
        val editItemDialog = TodoDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_ITEM_TO_EDIT, todoToEdit)
        editItemDialog.arguments = bundle

        editItemDialog.show(supportFragmentManager,
            "EDITITEMDIALOG")
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



    override fun todoCreated(item: Todo) {
        Thread {
            var newId = AppDatabase.getInstance(this).todoDao().insertTodo(item)

            item.todoId = newId

            runOnUiThread {
                todoAdapter.addTodo(item)
            }
        }.start()
    }

    override fun todoUpdated(item: Todo) {
        Thread {
            AppDatabase.getInstance(this).todoDao().updateTodo(item)

            runOnUiThread {
                todoAdapter.updateTodo(item, editIndex)
            }
        }.start()
    }
}
