package tech.userland.userland.database

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import tech.userland.userland.database.dao.*
import tech.userland.userland.database.entity.*

class DataRepository {

    private lateinit var sessionDao: SessionDao
    private lateinit var fileSystemDao: FilesystemDao

    private lateinit var allSessions: LiveData<List<SessionEntity>>
    private lateinit var allFilesystems: LiveData<List<FilesystemEntity>>

    fun DataRepository(application: Application) {
        val database = AppDatabase.getInstance(application)

        sessionDao = database.sessionDao()
        fileSystemDao = database.filesystemDao()

        allSessions = sessionDao.getAllSessions()
        allFilesystems = fileSystemDao.getAllFilesystems()
    }

    fun getAllSessions(): LiveData<List<SessionEntity>> {
        return allSessions
    }

    fun getAllFilesystems(): LiveData<List<FilesystemEntity>> {
        return allFilesystems
    }

    public fun insertSession(session: SessionEntity) {

    }

    private class insertAsyncTask: AsyncTask<SessionEntity, Void, Void>() {
        private asyncTaskDao:
    }
}