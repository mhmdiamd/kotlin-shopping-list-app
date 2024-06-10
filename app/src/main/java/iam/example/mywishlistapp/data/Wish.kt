package iam.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name="wish-title")
    val title: String = "",
    @ColumnInfo(name="wish-desc")
    val description : String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "Google watch 2", description = "An android watch"),
        Wish(title = "Oculus quest 2", description = "An VR headset for playing games"),
        Wish(title = "A Sci-fi, Book", description = "A Science friction book from any best seller"),
        Wish(title = "Bean Bag", description = "A Comfy bean bag to substitute for a chair"),
    )
}