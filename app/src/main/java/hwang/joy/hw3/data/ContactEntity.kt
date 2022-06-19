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