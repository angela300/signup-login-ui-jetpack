package com.learnandroid.loginapplication.composables

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.learnandroid.loginapplication.R
import com.learnandroid.loginapplication.ui.theme.primaryColor
import com.learnandroid.loginapplication.ui.theme.whiteBackground
import com.learnandroid.loginapplication.ui.theme.teal200

@Composable
fun LoginPage(navController: NavController) {

    val usernameValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
                .background(whiteBackground)
                .padding(10.dp)
        ) {

            ScrollableColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = TextUnit.Companion.Sp(2)
                    ),
                    fontSize = TextUnit.Companion.Sp(30)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Username",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = TextUnit.Sp(12)
                        )
                    )

                    OutlinedTextField(
                        value = usernameValue.value,
                        onValueChange = { usernameValue.value = it },
                        label = { Text(text = "Username") },
                        placeholder = { Text(text = "Your Username") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(10.dp)),
                        onImeActionPerformed = { _, _ ->
                            focusRequester.requestFocus()
                        }
                    )

                    Text(
                        text = "Password",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = TextUnit.Sp(12)
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    OutlinedTextField(
                        value = passwordValue.value,
                        onValueChange = { passwordValue.value = it },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    imageVector = vectorResource(id = R.drawable.password_eye),
                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                )
                            }
                        },
                        label = { Text("Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester).clip(RoundedCornerShape(10.dp)),
                        onImeActionPerformed = { _, controller ->
                            controller?.hideSoftwareKeyboard()
                        }

                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Checkbox(
                                checked = rememberMeChecked.value,
                                onCheckedChange = { isChecked ->
                                    rememberMeChecked.value = isChecked
                                },
                                modifier = Modifier.background(Color.Black) 
                            )

                            Text(
                                text = "Remember me",
                                style = TextStyle(
                                    fontSize = TextUnit.Sp(12)
                                )
                            )
                        }

                        Text(
                            text = "Forgot password?",
                            style = TextStyle(
                                fontSize = TextUnit.Sp(12),
                                color = primaryColor
                            ),
                            modifier = Modifier.clickable {
                                onClick={}
                            }
                        )
                    }

                    Spacer(modifier = Modifier.padding(20.dp))

                    Button(
                        onClick = {navController.navigate("register_page")},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp)) // Adjust the corner radius as needed
                            .background(teal200) // Set the background color
                    ) {
                        Text(text = "Sign In", fontSize = TextUnit.Companion.Sp(20))
                    }

                    Spacer(modifier = Modifier.padding(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Don't have an account? "),
                        style = TextStyle(fontSize = TextUnit.Sp(12))
                        Text(
                            text = "Sign in",
                            modifier = Modifier.clickable(
                                onClick = {navController.navigate("register_page")}
                            ),
                            style = TextStyle(color = teal200, fontSize = TextUnit.Sp(12))
                        )
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}
