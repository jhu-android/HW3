// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class AddressEntity(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var type: String,
    var street: String,
    var city: String,
    var state: String,
    var zip: String,
    var contactId: String,
)