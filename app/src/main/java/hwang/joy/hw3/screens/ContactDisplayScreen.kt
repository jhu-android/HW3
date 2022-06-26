package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactDisplayScreen(
    scope: CoroutineScope,
    currentScreen: Screen,
    onListScreenSelect:(Screen) -> Unit,
    contact: ContactWithAddresses?,
) {
    // should have a scaffold etc...
   Column {
       Text(text="contact display screen")
       Text(text = "${contact?.contact?.firstName} ${contact?.contact?.lastName}", style = MaterialTheme.typography.h4)
       contact?.addresses?.forEach {
           Text(text = "${it.type}: ${it.street}")
       }
   }

}