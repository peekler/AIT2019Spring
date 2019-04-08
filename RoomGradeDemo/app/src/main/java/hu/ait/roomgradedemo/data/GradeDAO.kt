package hu.ait.roomgradedemo.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface GradeDAO {
    @Query("SELECT * FROM grade")
    fun getAllGrades() : List<Grade>

    @Query("""SELECT * FROM grade WHERE grade="B"""")
    fun getBGrades(): List<Grade>

    @Query("SELECT * FROM grade WHERE grade = :grade")
    fun getSpecificGrades(grade: String): List<Grade>

    @Insert
    fun saveGrade(vararg grade: Grade)

    @Delete
    fun deleteGrade(vararg grade: Grade)
}