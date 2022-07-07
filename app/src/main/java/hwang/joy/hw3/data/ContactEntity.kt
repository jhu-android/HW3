// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity
data class ContactEntity(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var firstName: String,
    var lastName: String,
    var homePhone: String,
    var workPhone: String,
    var mobilePhone: String,
    var emailAddress: String,
)

data class ContactWithAddresses(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "contactId",
        entity = AddressEntity::class
    )
    val addresses: List<AddressEntity>
)