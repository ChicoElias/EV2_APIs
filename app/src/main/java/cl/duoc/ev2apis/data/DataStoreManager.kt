package cl.duoc.ev2apis.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

private val Context.userDataStore by preferencesDataStore("ev2_user")

object Keys {
    val TOKEN = stringPreferencesKey("token")
    val FAVORITES = stringPreferencesKey("favorites")
    val DARK = booleanPreferencesKey("dark")
    val NOTIFY = booleanPreferencesKey("notify")
    val AVATAR = stringPreferencesKey("avatar")
}

class DataStoreManager(private val context: Context) {
    private val ds get() = context.userDataStore

    fun token(): Flow<String?> = ds.data.map { it[Keys.TOKEN] }
    suspend fun setToken(token: String?) = ds.edit { it[Keys.TOKEN] = token ?: "" }

    fun favorites(): Flow<Set<String>> = ds.data.map {
        it[Keys.FAVORITES]?.split(",")?.filter { s -> s.isNotBlank() }?.toSet() ?: emptySet()
    }
    suspend fun toggleFavorite(id: String) = ds.edit { prefs ->
        val set = prefs[Keys.FAVORITES]?.split(",")?.toMutableSet() ?: mutableSetOf()
        if (set.contains(id)) set.remove(id) else set.add(id)
        prefs[Keys.FAVORITES] = set.joinToString(",")
    }

    fun dark(): Flow<Boolean> = ds.data.map { it[Keys.DARK] ?: true }
    suspend fun setDark(value: Boolean) = ds.edit { it[Keys.DARK] = value }

    fun notify(): Flow<Boolean> = ds.data.map { it[Keys.NOTIFY] ?: true }
    suspend fun setNotify(value: Boolean) = ds.edit { it[Keys.NOTIFY] = value }

    fun avatar(): Flow<String?> = ds.data.map { it[Keys.AVATAR] }
    suspend fun setAvatar(uri: String?) = ds.edit { it[Keys.AVATAR] = uri ?: "" }
}
