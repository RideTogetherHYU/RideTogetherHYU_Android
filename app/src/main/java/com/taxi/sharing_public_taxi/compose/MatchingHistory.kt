package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import org.threeten.bp.format.TextStyle
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.Locale

@Composable
fun MatchingHistoryScreen(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var selectedDate by remember { mutableStateOf("전체") }
    var showCalendar by remember { mutableStateOf(false) }
    val historyList = listOf(
        "2025년 1월 15일\n제가 절반 부담할게요 ... - 도현킹",
        "2025년 1월 15일\n빠르게 ㅃㄹ - 한국인815",
        "2025년 1월 10일\n같이가자 어서어서 - 숭",
        "2024년 12월 25일\n빨리갈 사람 - 초이밍키2",
        "2024년 12월 13일\n...사람구해요... - 페페",
        "2024년 12월 12일\n말 안하고 갈사람 - 서멍"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color(0xFFF5F6F9),
            modifier = Modifier.height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "뒤로가기"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "완료한 매칭 이력",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { showCalendar = true },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xD9D9D9)),
            //elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selectedDate,
                fontSize = 16.sp,
                color = Color.White
            )
            //Icon(
            //imageVector = Icons.Default.KeyboardArrowDown,
            //contentDescription = "날짜 선택",
            //modifier = Modifier
            //.size(20.dp)
            //.padding(start = 8.dp)
            //)
        }

        Spacer(modifier = Modifier.height(10.dp))

        val filteredList = if (selectedDate == "전체") {
            historyList
        } else {
            historyList.filter { it.contains(selectedDate) }
        }

        if (showCalendar) {
            CustomCalendar(
                onDateSelected = { date ->
                    selectedDate = date.toString()
                    showCalendar = false
                }
            )
        } else {
            Column {
                filteredList.forEachIndexed { index, historyItem ->
                    HistoryCard(
                        text = historyItem,
                        screenHeight = screenHeight,
                        onClick = {
                            navController.navigate("matchingdetails/$index")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun HistoryCard(text: String, screenHeight: Dp, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight * (80f / 800f))
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val parts = text.split("\n")
            if (parts.size == 2) {
                Text(
                    text = parts[0],
                    fontSize = 12.sp,
                    color = Color(0xFF838383),
                    lineHeight = 21.sp,
                    letterSpacing = (-0.32).sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = parts[1],
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    lineHeight = 21.sp,
                    letterSpacing = (-0.32).sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Text(text = text, fontSize = 14.sp, color = Color(0xFF333333))
            }
        }
    }
}

@Composable
fun CustomCalendar(
    onDateSelected: (LocalDate) -> Unit
) {
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentMonth = currentDate.value.month
    val currentYear = currentDate.value.year

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            IconButton(onClick = { currentDate.value = currentDate.value.minusMonths(1) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
            }
            Text(
                text = "$currentYear ${currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())}",
                style = MaterialTheme.typography.h6
            )
            IconButton(onClick = { currentDate.value = currentDate.value.plusMonths(1) }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                Text(text = day, style = MaterialTheme.typography.body2, textAlign = TextAlign.Center)
            }
        }

        val daysInMonth = currentDate.value.lengthOfMonth()
        val firstDayOfWeek = currentDate.value.withDayOfMonth(1).dayOfWeek.value % 7

        Column {
            for (week in 0..5) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (day in 0..6) {
                        val dayNumber = week * 7 + day - firstDayOfWeek + 1
                        if (dayNumber in 1..daysInMonth) {
                            Text(
                                text = dayNumber.toString(),
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable { onDateSelected(currentDate.value.withDayOfMonth(dayNumber)) }
                            )
                        } else {
                            Spacer(modifier = Modifier.size(24.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchingHistoryScreen() {
    val navController = rememberNavController()
    MatchingHistoryScreen(navController = navController)
}
