package hwang.joy.hw3

import android.os.Bundle
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
import hwang.joy.hw3.components.emptyImmutableList
import hwang.joy.hw3.screens.*
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
    val contacts by viewModel.contactsFlow.collectAsState(initial = emptyImmutableList())
    
    when (viewModel.screen) {
        null -> exit()
        is ContactListScreen -> ContactListScreen(
            scope = scope,
            contacts = contacts,
            selectedIds = viewModel.selectedContactIds,
            onToggleSelect = viewModel::toggleSelectedContactId,
            onClearSelections = viewModel::clearSelectedContactIds,
            onDeleteSelections = viewModel::deleteSelectedContacts,
            onReset = {
                scope.launch {
                    viewModel.resetDatabase()
                }
            },
            onAddContact = {
                viewModel.unselectContact()
                viewModel.push(ContactEditScreen)
            },
            onClickAbout = {
                viewModel.push(AboutScreen)
            }
        ) {
            scope.launch {
                viewModel.select(it)
                viewModel.push(ContactDisplayScreen)
            }
        }
        is ContactDisplayScreen -> ContactDisplayScreen(
            scope = scope,
            contact = viewModel.contact,
            onEdit = {
                viewModel.push(ContactEditScreen)
            },
            onClickAbout = {
                viewModel.push(AboutScreen)
            },
        )
        is ContactEditScreen -> ContactEditScreen(
            scope = scope,
            contact = viewModel.contact,
            onClickAbout = {
                viewModel.push(AboutScreen)
            },
            onContactAdd = {
                scope.launch {
                    viewModel.insertContact(it)
                }
            },
            onContactChange = {
                scope.launch {
                    viewModel.updateContact(it)
                }
            },
            onAddressDelete = {
                scope.launch {
                    viewModel.deleteSelectedAddress(it)
                }
            },
            onAddAddressClick = {
                scope.launch {
                    viewModel.unselectAddress()
                    viewModel.push(AddressEditScreen)
                }
            },
            onAddAddressClickNewContact = {
                scope.launch {
                    viewModel.insertContact(it)
                    viewModel.unselectAddress()
                    viewModel.push(AddressEditScreen)
                }
            },
            onAddressEdit = {
                scope.launch {
                    viewModel.select(it)
                    viewModel.push(AddressEditScreen)
                }
            }
        )
        is AddressEditScreen -> AddressEditScreen(
            scope = scope,
            address = viewModel.address,
            contactId = viewModel.contact?.contact?.id,
            onClickAbout = {
                viewModel.push(AboutScreen)
            },
            onAddressChange = {
                scope.launch {
                    viewModel.updateAddress(it)
                }
            },
            onAddressAdd = {
                scope.launch {
                    viewModel.insertAddress(it)
                }
            },
        )
        is AboutScreen -> AboutScreen(
            scope = scope,
        )
    }
}
