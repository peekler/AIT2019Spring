package hu.ait.todorecyclerviewdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.ait.todorecyclerviewdemo.R
import hu.ait.todorecyclerviewdemo.ScrollingActivity
import hu.ait.todorecyclerviewdemo.data.AppDatabase
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.touch.TodoTouchHelperCallback
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TodoTouchHelperCallback {


    var todoItems = mutableListOf<Todo>()

    private val context: Context


    constructor(context: Context, listTodos: List<Todo>) : super() {
        this.context = context
        todoItems.addAll(listTodos)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val todoRowView = LayoutInflater.from(context).inflate(
            R.layout.todo_row, viewGroup, false
        )
        return ViewHolder(todoRowView)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todo  = todoItems.get(viewHolder.adapterPosition)

        viewHolder.tvDate.text = todo.createDate
        viewHolder.cbDone.text = todo.todoText
        viewHolder.cbDone.isChecked = todo.done

        viewHolder.btnDelete.setOnClickListener {
            deleteTodo(viewHolder.adapterPosition)
        }

        viewHolder.cbDone.setOnClickListener {
            todo.done = viewHolder.cbDone.isChecked
            updateTodo(todo)
        }

        viewHolder.btnEdit.setOnClickListener {
            (context as ScrollingActivity).showEditTodoDialog(todo,
                viewHolder.adapterPosition)
        }
    }

    fun updateTodo(todo: Todo) {
        Thread{
            AppDatabase.getInstance(context).todoDao().updateTodo(todo)


        }.start()
    }

    fun updateTodo(todo: Todo, editIndex: Int) {
        todoItems.set(editIndex, todo)
        notifyItemChanged(editIndex)
    }


    fun addTodo(todo: Todo) {
        todoItems.add(0, todo)
        //notifyDataSetChanged()
        notifyItemInserted(0)
    }

    fun deleteTodo(deletePosition: Int) {
        Thread{
            AppDatabase.getInstance(context).todoDao().deleteTodo(todoItems.get(deletePosition))

            (context as ScrollingActivity).runOnUiThread {
                todoItems.removeAt(deletePosition)
                notifyItemRemoved(deletePosition)
            }
        }.start()
    }

    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
        var btnDelete = itemView.btnDelete
        var btnEdit = itemView.btnEdit
    }

}