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