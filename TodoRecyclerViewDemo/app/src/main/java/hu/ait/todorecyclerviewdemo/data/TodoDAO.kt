package hu.ait.todorecyclerviewdemo.data

import android.arch.persistence.room.*


@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insertTodo(todo: Todo): Long

    @Insert
    fun insertTodos(vararg todo: Todo): List<Long>

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)
}