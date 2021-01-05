class NotesService {

    private val items = mutableListOf<Note>()
    private var index : Int = 0
    /**
     *  Создает новую заметку у текущего пользователя.
     */
    fun add(title: String, text: String) {
        items.add(Note(index,1,1,title, text))
        index ++
    }



    /**Добавляет новый комментарий к заметке.
     *
     */
    fun createComment (noteId: Int,comment: Comment)
    {  items[noteId].comment.add(comment)
    }

    /**
     * Удаляет заметку текущего пользователя.
     */
    fun delete (noteId: Int){
        items.removeAt(noteId)
    }

    /**
     * Удаляет комментарий к заметке.
     */
    fun deleteComment (noteId: Int, commentID: Int) {
        items[noteId].comment[commentID].deleted = true
    }

    /**
     *Редактирует указанный комментарий у заметки.
     */
    fun editComment(noteId: Int,commentID: Int, title :String,text :String ) {
        items[noteId].comment[commentID].message = text
        items[noteId].comment[commentID].title = title
    }



    /**
     *  Редактирует заметку текущего пользователя.
     */
    fun edit (noteId: Int,  text: String){
        items[noteId].text = text
    }

    /**
     * Возвращает список заметок, созданных пользователем.
     */
    fun get (): MutableList<Note> {
        return items
    }

    /**
     *  Возвращает заметку по её id.
     */
    fun getById(noteId : Int): Note{
          return items[noteId]
    }

    /**
     * Возвращает список комментариев к заметке.
     */
    fun getComments(noteId: Int,  offset: Int, count : Int, sort  : Int): MutableList<Comment> {
        val itemsToReturn = mutableListOf<Comment>()

        for (n in offset..items[noteId].comment.size-1){
            itemsToReturn.add(items[noteId].comment[n] )
        }
        return itemsToReturn
    }



    /**
     * Восстанавливает удалённый комментарий.
     */
    fun restoreComment(noteId: Int, comment_id:Int){
        items[noteId].comment[comment_id].deleted = false
    }

}