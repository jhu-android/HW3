// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.data.AddressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactEditAddresses(
    scope: CoroutineScope,
    addresses: List<AddressEntity>?,
    getKey: (AddressEntity) -> String,
    onAddressDelete: suspend (String) -> Unit,
    onAddressEdit: suspend (AddressEntity) -> Unit,
) {
    Column {
        addresses?.forEach { address ->
            val key = getKey(address)
            Card(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            onAddressEdit(address)
                        }
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = address.street,
                        modifier = Modifier
                            .padding(8.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f)
                    )
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                onAddressDelete(key)
                            }
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Delete,
                            tint = Color(0xFFc8415f),
                            contentDescription = stringResource(id = R.string.icon_description_delete),
                        )
                    }
                }
            }
        }
    }
}