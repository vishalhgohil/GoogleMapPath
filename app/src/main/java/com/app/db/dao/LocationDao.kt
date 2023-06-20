package com.app.db.dao

import androidx.room.*
import com.app.db.entity.LocationDetailsRoom


@Dao
interface LocationDao {

    @Query("SELECT * FROM location_room")
    fun getAll(): List<LocationDetailsRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocationRoom(message: LocationDetailsRoom): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateChatRoom(message: LocationDetailsRoom)

    @Query("DELETE FROM location_room where location_id in (:listOfIds)")
    fun deleteLocationsWithLocalDbId(listOfIds: List<String>)

    //delete from timestamp where id in (:idList)
    /*@Query("DELETE FROM location_room where location_id in (:listOfRooms)")
    fun deleteChatRoom(listOfRooms: List<String>)*/

    @Query("DELETE FROM location_room WHERE (location_id = :locationID)")
    fun deleteMessage(locationID: String)

}