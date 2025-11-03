package cl.duoc.ev2apis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.ev2apis.data.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SessionState(
    val token: String? = null,
    val favorites: Set<String> = emptySet(),
    val dark: Boolean = true,
    val notify: Boolean = true,
    val avatarUri: String? = null
)

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val store = DataStoreManager(app)

    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                store.token(), store.favorites(),
                store.dark(), store.notify(), store.avatar()
            ) { t, f, d, n, a -> SessionState(t, f, d, n, a) }
                .collect { _state.value = it }
        }
    }

    fun toggleFavorite(id: String) = viewModelScope.launch { store.toggleFavorite(id) }
    fun setDark(v: Boolean) = viewModelScope.launch { store.setDark(v) }
    fun setNotify(v: Boolean) = viewModelScope.launch { store.setNotify(v) }
    fun setAvatar(uri: String?) = viewModelScope.launch { store.setAvatar(uri) }
    fun setToken(token: String?) = viewModelScope.launch { store.setToken(token) }
}
