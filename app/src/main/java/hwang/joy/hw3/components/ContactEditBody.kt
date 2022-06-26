package hwang.joy.hw3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactEditBody(
    scope: CoroutineScope,
    contact: ContactWithAddresses?,
) {
    Column {
        Text(text = "Editing ${contact?.contact?.firstName} ${contact?.contact?.lastName}")
    }
}