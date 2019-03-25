package hu.ait.todorecyclerviewdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.ait.todorecyclerviewdemo.R
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.touch.TodoTouchHelperCallback
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TodoTouchHelperCallback {


    var todoItems = mutableListOf<Todo>(
        Todo("21. 03. 2019.", false, "Todo1"),
        Todo("22. 03. 2019.", false, "Todo2"),
        Todo("22. 03. 2019.", false, "Todo3")
    )

    private val context: Context
    constructor(context: Context) : super() {
        this.context = context
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
        val todo  = todoItems.get(position)

        viewHolder.tvDate.text = todo.createDate
        viewHolder.cbDone.text = todo.todoText
        viewHolder.cbDone.isChecked = todo.done
    }


    fun addTodo(todo: Todo) {
        todoItems.add(0, todo)
        //notifyDataSetChanged()
        notifyItemInserted(0)
    }


    override fun onDismissed(position: Int) {
        todoItems.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
    }

}