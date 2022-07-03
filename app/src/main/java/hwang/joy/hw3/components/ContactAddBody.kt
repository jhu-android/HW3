package hwang.joy.hw3.components
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import hwang.joy.hw3.data.ContactWithAddresses
import hwang.joy.hw3.R
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ContactAddBody(
    scope: CoroutineScope,
    onContactAdd: suspend (ContactEntity) -> Unit,
) {
    Log.d("jhw!!", "ADD contact!")

    var firstName by remember {
        mutableStateOf(null)
    }

    Column {
        TextField(
            labelId = R.string.label_firstName,
            placeholderId = R.string.placeholder_firstName,
            value = firstName ?: "",
            onValueChange = {
                scope.launch {
                    onContactAdd(createNewContact().copy(firstName = it))
                }
            }
        )

    }
}

fun createNewContact(): ContactEntity {
    return ContactEntity(
        firstName = "",
        lastName = "",
        homePhone = "",
        workPhone = "",
        mobilePhone = "",
        emailAddress = "",
    )
}