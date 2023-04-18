package br.senai.sp.jandira.tripds2prof

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripds2prof.components.BottomShape
import br.senai.sp.jandira.tripds2prof.components.TopShape
import br.senai.sp.jandira.tripds2prof.model.User
import br.senai.sp.jandira.tripds2prof.repository.UserRepository
import br.senai.sp.jandira.tripds2prof.ui.theme.TripDS2TTheme
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.io.File

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripDS2TTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SignupScreen("Android")
                }
            }
        }
    }
}

@Composable
fun SignupScreen(name: String) {

    var userNameState by rememberSaveable {
        mutableStateOf("")
    }
    var phoneState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var over18State by remember {
        mutableStateOf(false)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    
    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){ uri ->
        photoUri = uri
    }

    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(photoUri)
            .build()
    )

    var context = LocalContext.current

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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(weight = 0.1f)
        ) {
            //------------------------------->>>
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.signup),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(
                        207,
                        6,
                        240
                    )
                )
                Text(
                    text = stringResource(id = R.string.create_new_account),
                    //text = "Este é um texto bem longo que deverá ser truncado, pois o seu conteúdo é maior do que a caixa.",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (expanded) 50 else 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(
                        160,
                        156,
                        156
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .align(alignment = Alignment.TopEnd),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.Magenta
                                )
                            )
                        )
                    ) {
                        Image(
                            painter =  if(photoUri != null) painter else painterResource(id = R.drawable.person_24),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            contentScale = if(photoUri != null) ContentScale.Crop else ContentScale.Fit
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = null,
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .offset(
                                x = 0.dp,
                                y = 0.dp
                            )
                            .size(28.dp)
                            .clickable {
                                launcher.launch("image/*")
                                var m = "Nada ainda"
                                Log.i(
                                    "ds2",
                                    "-------------->> ${photoUri?.path ?: m} "
                                )
                            }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = userNameState,
                    onValueChange = { userName ->
                        userNameState = userName
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    label = {
                        Text(text = stringResource(id = R.string.username))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.person_24),
                            contentDescription = "",
                            tint = Color(
                                207,
                                6,
                                240
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phoneState,
                    onValueChange = { phone ->
                        phoneState = phone
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = stringResource(id = R.string.phone))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.phone_android_24),
                            contentDescription = "",
                            tint = Color(
                                207,
                                6,
                                240
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { email ->
                        emailState = email
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = {
                        Text(text = stringResource(id = R.string.email))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.email_24),
                            contentDescription = "",
                            tint = Color(
                                207,
                                6,
                                240
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = passwordState,
                    onValueChange = { pass ->
                        passwordState = pass
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    label = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.lock_24),
                            contentDescription = "",
                            tint = Color(
                                207,
                                6,
                                240
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = over18State,
                        onCheckedChange = { checked ->
                            over18State = checked
                        }
                    )
                    Text(text = stringResource(id = R.string.over_18))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {

                              expanded = !expanded

//                        userSave(
//                            context,
//                            emailState,
//                            userNameState,
//                            phoneState,
//                            passwordState,
//                            over18State
//                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(
                            207,
                            6,
                            240
                        )
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row() {
                        Text(
                            text = stringResource(id = R.string.create_new_account).uppercase(),
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
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
                    color = Color(
                        160,
                        156,
                        156
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.signup),
                    fontSize = 12.sp,
                    color = Color(
                        207,
                        6,
                        240
                    )
                )
            }
            //------------------------>>>>>>
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            BottomShape()
        }
    }
}

fun userSave(
    context: Context,
    email: String,
    userName: String,
    phone: String,
    password: String,
    isOver: Boolean
) {
    val userRepository = UserRepository(context)

    // Recuperando no banco um usuário que
    // tenha o email informado
    var user = userRepository.findUserByEmail(email)

    // Se user for null, gravamos
    // o novo usuário, senão, avisamos que o
    // usuário já existe.
    if (user == null){
        val newUser = User(
            userName = userName,
            phone = phone,
            email = email,
            password = password,
            isOver18 = isOver
        )
        val id = userRepository.save(newUser)
        Toast.makeText(
            context,
            "User created #$id",
            Toast.LENGTH_LONG
        ).show()
    } else {
        Toast.makeText(
            context,
            "User already exists!!",
            Toast.LENGTH_SHORT
        ).show()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    TripDS2TTheme {
        SignupScreen("Android")
    }
}