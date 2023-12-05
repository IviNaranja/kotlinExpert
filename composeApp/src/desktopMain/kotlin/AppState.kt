import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

object AppState {
    //val state: MutableState<UiState> = mutableStateOf(UiState())
    // Para ahorrar el .value y ya no iria el update para simplificar el get y el set
    var state: UiState by mutableStateOf(UiState())

    fun loadNotes(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            state = UiState(loading = true)
            state = UiState(getNotes(), false)
        }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false
    )

    private fun <T> MutableState<T>.update(produceValue: (T) -> T) {
        // La funcion produceValue devuelve un valor T para poder asignarlo a this.value
        // Recibe un valor para poder llamarlo como it y hacer un copy
        this.value = produceValue(value)
    }
}