import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

data class Note(val title: String, val description: String, val type: Type) {
    enum class Type {
        TEXT, AUDIO
    }
}

suspend fun getNotes(): List<Note> = withContext(Dispatchers.IO) {
    // IO porque es una peticion al servidor
    delay(2000)
    (0..10).map {
        Note("Title $it", "Description $it", if (it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT)
    }

}