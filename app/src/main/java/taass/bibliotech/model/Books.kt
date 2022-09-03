package taass.bibliotech.model

data class Books(
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val picture: String,
    val stock: Long,
    val categories: List<Category>
)



