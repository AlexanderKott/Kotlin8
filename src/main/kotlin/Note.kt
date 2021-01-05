data class Note(
    var id: Int, var albomId: Int, var userId: Int,
    var title: String, var text: String,
    var comment: MutableList<Comment> = mutableListOf())