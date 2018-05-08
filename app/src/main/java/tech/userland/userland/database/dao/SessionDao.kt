package tech.userland.userland.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import tech.userland.userland.database.entity.SessionEntity


@Dao
interface SessionDao {
    @Query("select * from sessions")
    fun getAllSessions(): LiveData<List<SessionEntity>>

    @Query("select * from sessions where name = :name")
    fun findSessionByName(name: String): LiveData<SessionEntity>

    @Query("select * from sessions where sessionId = :id")
    fun findSessionById(id: Long): LiveData<SessionEntity>

    @Query("select * from sessions, filesystems " +
            "where sessions.filesystemId = filesystems.filesystemId " +
            "and filesystems.filesystemId = :id")
    fun findSessionsByFileSystemId(id: Long): LiveData<List<SessionEntity>>

    @Query("select * from sessions, filesystems " +
    "where sessions.filesystemId = filesystems.filesystemId " +
    "and filesystems.name = :name")
    fun findSessionsByFilesystemName(name: String): LiveData<List<SessionEntity>>

    @Insert(onConflict = REPLACE)
    fun insertSession(session: SessionEntity)

    @Update(onConflict = REPLACE)
    fun updateSession(session: SessionEntity)

    @Delete
    fun deleteSession(session: SessionEntity)
}