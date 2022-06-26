package hwang.joy.hw3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactDisplayBody(
    scope: CoroutineScope,
    contact: ContactWithAddresses?,
    // editing functions
) {
    // should have a scaffold etc...
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(text="contact display screen")
        Text(text = "${contact?.contact?.firstName} ${contact?.contact?.lastName}", style = MaterialTheme.typography.h4)
        contact?.addresses?.forEach {
            Text(text = "${it.type}: ${it.street}")
        }

    }
}