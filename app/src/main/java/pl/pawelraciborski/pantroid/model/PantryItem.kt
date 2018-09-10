package pl.pawelraciborski.pantroid.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Pawel Raciborski on 10.09.2018.
 */
@Entity
data class PantryItem(
        var name: String,
        var quantity: Int,
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null
)