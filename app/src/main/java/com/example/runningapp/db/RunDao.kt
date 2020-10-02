package com.example.runningapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query(value = "SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate():LiveData<List<Run>>

    @Query(value = "SELECT * FROM running_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis():LiveData<List<Run>>

    @Query(value = "SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned():LiveData<List<Run>>

    @Query(value = "SELECT * FROM running_table ORDER BY averageSpeedInKMPH DESC")
    fun getAllRunsSortedByAverageSpeed():LiveData<List<Run>>

    @Query(value = "SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance():LiveData<List<Run>>

    @Query(value = "SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis():LiveData<Long>

    @Query(value = "SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned():LiveData<Int>

    @Query(value = "SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance():LiveData<Int>

    @Query(value = "SELECT AVG(averageSpeedInKMPH) FROM running_table")
    fun getTotalAverageSpeed():LiveData<Float>

   /**
    * @Query("""
        SELECT * FROM running_table
        ORDER BY
        CASE WHEN :column = 'timestamp'  THEN timestamp END DESC,
        CASE WHEN :column = 'timeInMillis' THEN timeInMillis END DESC,
        CASE WHEN :column = 'calories' THEN caloriesBurned END DESC,
        CASE WHEN :column = 'speed'  THEN averageSpeedInKMPH END DESC,
        CASE WHEN :column = 'distance' THEN distanceInMeters END DESC
    """)
    fun sortBy(column : String) : LiveData<List<Run>>

   **/

}