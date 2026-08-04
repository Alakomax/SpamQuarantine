package com.antigravity.spamquarantine.data.db

import android.content.Context
import androidx.room.*
import com.antigravity.spamquarantine.data.model.QuarantineLogEntity
import com.antigravity.spamquarantine.data.model.RuleEntity
import com.antigravity.spamquarantine.util.PhoneUtils

@Dao
interface RuleDao {
    @Query("SELECT * FROM spam_rules WHERE isActive = 1 ORDER BY id DESC")
    suspend fun getActiveRules(): List<RuleEntity>

    @Query("SELECT * FROM spam_rules ORDER BY id DESC")
    suspend fun getAllRules(): List<RuleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(rule: RuleEntity): Long

    @Delete
    suspend fun deleteRule(rule: RuleEntity)

    @Query("SELECT COUNT(*) FROM spam_rules")
    suspend fun getRuleCount(): Int
}

@Dao
interface QuarantineDao {
    @Query("SELECT * FROM quarantine_logs ORDER BY timestamp DESC")
    suspend fun getAllQuarantineLogs(): List<QuarantineLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: QuarantineLogEntity): Long

    @Delete
    suspend fun deleteLog(log: QuarantineLogEntity)

    @Query("DELETE FROM quarantine_logs")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM quarantine_logs")
    suspend fun getBlockedCount(): Int
}

@Database(entities = [RuleEntity::class, QuarantineLogEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ruleDao(): RuleDao
    abstract fun quarantineDao(): QuarantineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "spam_quarantine_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
