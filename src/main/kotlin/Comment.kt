class Comment(

    var ownerId: Int,
    var postId: Int,
    var fromGroup: Int,
    var message: String,
    var title: String,
    var replyToComment: Int?,
    var stickerId: Int?,
    var guid: String?,
    var deleted : Boolean = false


)
