// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.data.ContactWithAddresses

@Composable
fun ContactDisplayBody(
    contact: ContactWithAddresses?,
) {
    requireNotNull(contact)
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Card(
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Column {
                if (contact.contact.mobilePhone.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.label_mobilePhone),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = contact.contact.mobilePhone,
                        modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp)
                    )
                }
                if (contact.contact.homePhone.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.label_homePhone),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = contact.contact.homePhone,
                        modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp)
                    )
                }
                if (contact.contact.workPhone.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.label_workPhone),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = contact.contact.workPhone,
                        modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp)
                    )
                }
                if (contact.contact.emailAddress.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.label_emailAddress),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = contact.contact.emailAddress,
                        modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp)
                    )
                }

            }
        }
        if (contact.addresses.isNotEmpty()) {
            contact.addresses.forEach {
                Card(
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.label_homeAddress),
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(text = it.street, modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp))
                        Text(text = "${it.city}, ${it.state} ${it.zip}", modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp))
                    }

                }
            }
        }
    }
}