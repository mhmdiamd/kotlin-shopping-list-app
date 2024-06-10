package iam.example.mywishlistapp

import android.credentials.CredentialDescription
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import iam.example.mywishlistapp.data.Wish
import iam.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository : WishRepository = Graph.wishRepository
) : ViewModel() {
    var wishTitleState by mutableStateOf<String>("")
    var wishDescriptionState by mutableStateOf<String>("")

    fun onWishTitleChanged(title : String) {
        wishTitleState = title
    }

    fun onWishDescriptionChanged(description : String) {
        wishDescriptionState = description
    }

    //digunakan saat kita tidak bisa memasukkan nilai secara langsung,
    // melainkan hanya menampung terlebih dahulu yang nantinya bisa diisi null atau non-null.
    lateinit var getAllWishes : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish : Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish = wish)
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish)
        }
    }

    fun deleteAWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wish)
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun getAWishById(id : Long) : Flow<Wish> {
        return wishRepository.getAWishById(id)
    }
}