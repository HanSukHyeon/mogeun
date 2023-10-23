package io.ssafy.mogeun.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ssafy.mogeun.R
import io.ssafy.mogeun.ui.screens.record.RecordScreen
import io.ssafy.mogeun.ui.screens.routine.RoutineScreen
import io.ssafy.mogeun.ui.screens.setting.SettingScreen
import io.ssafy.mogeun.ui.screens.summary.SummaryScreen

data class Destination(
    val route: String,
    val title: String,
    val vectorId: Int,
    val component: @Composable () -> Unit
)

val Destinations: Array<Destination> = arrayOf(
    Destination("routine", "루틴", R.drawable.icon_routine) { RoutineScreen() },
    Destination("record2", "기록", R.drawable.icon_record) { RecordScreen()},
    Destination("summary", "요약", R.drawable.icon_summary) { SummaryScreen()},
    Destination("setting", "설정", R.drawable.icon_setting) { SettingScreen()}
)

val RootDestinations: Array<Destination> = arrayOf(
    Destinations[0],
    Destinations[1],
    Destinations[2],
    Destinations[3],
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    var selectedItem by remember { mutableStateOf(0)}

    val navController: NavHostController = rememberNavController()

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(RootDestinations[selectedItem].title) },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                RootDestinations.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = { Image(imageVector = ImageVector.vectorResource(id = item.vectorId), contentDescription = item.route) },
                        label= { Text(item.title) }
                    )
                }
            }
        }
    ) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "record2") {
        Destinations.map { dest ->
            composable(dest.route) { dest.component }
        }
        composable("record") { RecordScreen()}
    }
}
