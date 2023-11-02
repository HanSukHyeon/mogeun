package io.ssafy.mogeun.ui.screens.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.ssafy.mogeun.R
import io.ssafy.mogeun.ui.screens.login.LoginViewModel

@Composable
fun SignupScreen(viewModel: SignupViewModel = viewModel(factory = SignupViewModel.Factory), navController: NavHostController) {
    val inputForm = viewModel.inputForm
    val firstText = viewModel.firstText


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = firstText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontSize = 24.sp
                )
                Text(
                    text = "입력해주세요",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
        when (inputForm) {
            1 -> Essential(viewModel, inputForm, firstText, navController)
            2 -> Inbody(inputForm, firstText, navController)
        }
    }
}

@Composable
fun Essential(viewModel: SignupViewModel = viewModel(factory = SignupViewModel.Factory), inputForm: Int, firstText: String, navController: NavHostController) {
    val id = viewModel.id
    val password = viewModel.password
    val checkingPassword = viewModel.checkingPassword
    val nickname = viewModel.nickname

    Column(modifier = Modifier.padding(28.dp)) {
        Text(text = "아이디")
        Row {
            TextField(
                value = id,
                onValueChange = viewModel::updateId,
                modifier = Modifier.width(220.dp),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    val ret = viewModel.dupEmail("mogun@ssafy.com")
                    Log.d("signIn", "$ret")
                },
                modifier = Modifier.width(100.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "중복확인")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "비밀 번호")
        TextField(
            value = password,
            onValueChange = viewModel::updatePassword,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "비밀 번호 확인")
        TextField(
            value = checkingPassword,
            onValueChange = viewModel::updateCheckingPassword,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "닉네임")
        TextField(
            value = nickname,
            onValueChange = viewModel::updateNickname,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(10.dp)) {
                Text(text = "남성")
            }
            Spacer(modifier = Modifier.width(48.dp))
            Button(
                onClick = {

                    },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "여성")
            }
        }
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                actions = {
                    IconButton(onClick = { navController.navigate("login") }) {
                        Image(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(50.dp)
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.updateInputForm(2)
                            viewModel.updateFirstText("인바디를")
                            },
                        containerColor = MaterialTheme.colorScheme.secondary,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Text(
                            text = "회원가입",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(
                                                    start = 20.dp,
                                                    end = 20.dp
                                                )
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = ""
        )
    }
}

@Composable
fun Inbody(
    inputForm: Int,
    firstText: String,
    navController: NavHostController,
    viewModel: SignupViewModel = viewModel(factory = SignupViewModel.Factory)
) {
    val height = viewModel.height
    val weight = viewModel.weight
    val muscleMass = viewModel.muscleMass
    val bodyFat = viewModel.bodyFat

    Column(modifier = Modifier.padding(28.dp)) {
        Text(text = "키")
        TextField(
            value = height.toString(),
            onValueChange = { viewModel::updateHeight },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "몸무게")
        TextField(
            value = weight.toString(),
            onValueChange = { viewModel::updateWeight },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "골격근량")
        TextField(
            value = muscleMass.toString(),
            onValueChange = { viewModel::updateMuscleMass },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "체지방")
        TextField(
            value = bodyFat.toString(),
            onValueChange = { viewModel::updateBodyFat },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                actions = {
                    IconButton(
                        onClick = {navController.navigate("login")},
                        modifier = Modifier
                            .width(104.dp)
                            .height(36.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(30.dp)),
                    ) {
                        Text(text = "건너뛰기", fontSize = 16.sp)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {navController.navigate("login")  },
                        containerColor = MaterialTheme.colorScheme.secondary,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Text(
                            text = "회원가입",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(
                                                    start = 20.dp,
                                                    end = 20.dp
                                                )
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = ""
        )
    }
}
