package hwang.joy.hw3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import hwang.joy.hw3.screens.ContactList
import hwang.joy.hw3.ui.theme.HW3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            BackHandler() {
                viewModel.pop()
            }
            HW3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Ui(scope, viewModel) { finish() }
                }
            }
        }
    }
}

@Composable
fun Ui (
    scope: CoroutineScope,
    viewModel: ContactViewModel,
    exit: () -> Unit,
) {
    val contacts by viewModel.contactsFlow.collectAsState(initial = emptyList())
    val addresses by viewModel.addressesFlow.collectAsState(initial = emptyList())

    when (val currentScreen = viewModel.screen) {
        null -> exit()
        is ContactListScreen -> ContactList(
            contacts = contacts,
            selection = currentScreen.selection, // currentScreen = currentScreen
            // onScreenSelect = viewModel:: selectListScreen,
            // scope = scope,
            // selectedIds = viewModel.selectedContactIds
//            onToggleSelect = viewModel::toggleSelectedContactId,
//            onDeleteSelections = viewModel::deleteSelectedContacts,
//            onClearSelections = viewModel::clearSelectedContactIds,
            modifier = Modifier,    // delete
            onReset = {
                scope.launch {
                    viewModel.resetDatabase()
                }
            }
        )
    }
}