package br.senai.sp.jandira.tripds2prof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripds2prof.components.BottomShape
import br.senai.sp.jandira.tripds2prof.components.TopShape
import br.senai.sp.jandira.tripds2prof.ui.theme.TripDS2TTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripDS2TTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TopShape()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(207, 6, 240)
            )
            Text(
                text = stringResource(id = R.string.please_signin),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(160, 156, 156)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email_24),
                        contentDescription = "",
                        tint = Color(207, 6, 240)
                    )
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock_24),
                        contentDescription = "",
                        tint = Color(207, 6, 240)
                    )
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(207, 6, 240)),
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row() {
                    Text(
                        text = stringResource(id = R.string.signin).uppercase(),
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(
                            id = R.drawable.arrow_forward_24
                        ),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.dont_account),
                fontSize = 12.sp,
                color = Color(160, 156, 156)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.signup),
                fontSize = 12.sp,
                color = Color(207, 6, 240)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            BottomShape()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TripDS2TTheme {
        LoginScreen()
    }
}