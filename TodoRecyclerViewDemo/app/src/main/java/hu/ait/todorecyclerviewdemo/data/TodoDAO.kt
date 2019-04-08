package hu.ait.todorecyclerviewdemo.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insertTodo(vararg todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)
}