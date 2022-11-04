package com.hivian.lydia_test.core.database

import androidx.room.*
import com.hivian.lydia_test.core.models.dto.Name
import com.hivian.lydia_test.core.models.dto.RandomUserDTO

@Dao
interface RandomUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg randomUserEntity: RandomUserDTO)

    @Update
    suspend fun update(vararg randomUserEntity: RandomUserDTO)

    @Delete
    suspend fun delete(vararg randomUserEntity: RandomUserDTO)

    @Query("DELETE FROM random_user_entity")
    suspend fun deleteAll()

    @Query("SELECT * FROM random_user_entity")
    suspend fun getAllRandomUsers() : List<RandomUserDTO>

    @Query("SELECT * FROM random_user_entity WHERE localId = :id")
    suspend fun getRandomUserById(id : Int) : RandomUserDTO

    @Query("SELECT * FROM random_user_entity WHERE name = :name LIMIT 1")
    suspend fun getRandomUserByServerId(name: Name): RandomUserDTO?

    @Transaction
    suspend fun upsert(randomUserDTO: RandomUserDTO) {
        val isRandomUser = getRandomUserByServerId(randomUserDTO.name)
        isRandomUser?.let {
            update(randomUserDTO.apply {
                localId = isRandomUser.localId
            })
        } ?: run {
            insert(randomUserDTO)
        }
    }

    @Transaction
    suspend fun upsert(randomUsersList: List<RandomUserDTO>) {
        randomUsersList.forEach { upsert(it) }
    }

}
