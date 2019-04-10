package hu.ait.todorecyclerviewdemo.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) var todoId : Long?,
    @ColumnInfo(name = "createDate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "todoText") var todoText: String
) : Serializable


