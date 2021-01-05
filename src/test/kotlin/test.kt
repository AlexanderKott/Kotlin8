import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestNote {

    @Test
    fun testAdd(){
        val ns = NotesService()
            ns.add("title", "text")
            ns.getById(0) // Не выбрасываются исключения, заметка есть

    }

    @Test
    fun testCreateComment(){
        val ns = NotesService()
        val expected = "title123"

        //Создаем две заметки
        ns.add("title", "text") //id 0
        ns.add("title", "text") // id 1


        //Создаем комментарий к заметке id =1
        val comment = Comment(1,1,1,"new comment"
            , expected,null, null, null)

        ns.createComment(1,comment)

        //Проверяем что есть комментарий с заголовком title123
       val  actual = ns.getComments(1,0,1,1)[0].title
        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun testDeleteNote() {
        val ns = NotesService()

        //Создаем две заметки
        ns.add("title", "text") //id 0
        ns.add("title", "text") // id 1
        ns.add("title", "text") // id 2
        ns.delete(0)  //Удаляем первую

        //Смотрим что осталось две
        Assertions.assertEquals(ns.get().size, 2)

    }

    @Test
    fun testDeleteComment() {
        val ns = NotesService()
        val expected = "title123"

        //Создаем две заметки
        ns.add("title", "text") //id 0
        ns.add("title", "text") // id 1


        //Создаем комментарий к заметке id =1
        val comment = Comment(
            1, 1, 1, "new comment", expected, null, null, null
        )
        //Тестируем добавление комментариев
        ns.createComment(0, comment)
        ns.deleteComment(0,0)
        //Проверяем что кол-во комент помечен deleted = true
        Assertions.assertEquals(ns.getComments(0,0,100,1)[0].deleted,true)
    }


    @Test
    fun testEditNote() {
        val ns = NotesService()
        val expectedText = "text123"

        //Создаем
        ns.add("title", "text") //id 0

        ns.edit (0, expectedText)

        //Проверяем что текст замети изменился на ожидаемый
        val  actual  = ns.get()[0].text
        Assertions.assertEquals(actual, expectedText)

    }


    @Test
    fun testEditComment() {
        val ns = NotesService()
        val expectedtitle = "title123"
        val expectedText = "text123"

        //Создаем две заметки
        ns.add("title", "text") //id 0
        ns.add("title", "text") // id 1


        //Создаем комментарий к заметке id =1
        val comment = Comment(
            1, 1, 1, "title abc", "txt abc", null, null, null
        )
        //Тестируем добавление комментариев
        ns.createComment(0, comment)
        ns.editComment(0,0,expectedtitle,expectedText)

        //Проверяем что есть комментарий с заголовком title123
        val  actual = ns.getComments(0,0,1,1)[0].title
        Assertions.assertEquals(actual, expectedtitle)

        val  actual2 = ns.getComments(0,0,1,1)[0].message
        Assertions.assertEquals(actual2, expectedText)

    }

    @Test
    fun testRestoreEditComment() {
        val ns = NotesService()
        val expectedtitle = "title123"
        val expectedText = "text123"

        //Создаем две заметки
        ns.add("title", "text") //id 0
        ns.add("title", "text") // id 1


        //Создаем комментарий к заметке id =0
        val comment = Comment(
            1, 0, 1, "title abc", "txt abc", null, null, null
        )
        //Тестируем добавление комментария к заметке 0
        ns.createComment(0, comment)
        ns.deleteComment(0,0)
        ns.restoreComment(0,0)
        Assertions.assertEquals(ns.getComments(0,0,0,0).size, 1)

    }
}