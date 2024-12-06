package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AccountRegistrationScreen(navController: NavHostController) {
    val accounts = remember { mutableStateListOf(
        "카카오뱅크 398490238492849",
        "신한은행 11048829992",
        "국민은행 34992030403"
    )}
    var selectedAccount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // Top Bar
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color(0xFFF5F6F9),
            modifier = Modifier.height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
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
                    text = "계좌등록 / 변경",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        /*
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .offset( y = 8.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "뒤로가기"
                    )
                }
            }
        )*/


        Spacer(modifier = Modifier.height(50.dp))

        // Selected Account
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF3E64FF), shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "★  ${accounts[selectedAccount]}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "내 계좌",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        accounts.forEachIndexed { index, accountName ->
            AccountItem(
                accountName = accountName,
                isSelected = selectedAccount == index
            ) { selectedAccount = index}
        }
        // Account List
        /*AccountItem("카카오뱅크 398490238492849", isSelected = selectedAccount == 0) { selectedAccount = 0 }
        AccountItem("신한은행 11048829992", isSelected = selectedAccount == 1) { selectedAccount = 1 }
        AccountItem("국민은행 34992030403", isSelected = selectedAccount == 2) { selectedAccount = 2 }
*/
        Spacer(modifier = Modifier.height(10.dp))

        // Add New Account Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
                .clickable {
                    /* 새 계좌 추가 처리 */
                    accounts.add("새 계좌 ${accounts.size + 1}")},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
        //BottomNavigationBar()
    }
}

@Composable
fun AccountItem(accountName: String, isSelected: Boolean, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(60.dp)
            .background(
                color = if (isSelected) Color.White else Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onSelect() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = if (isSelected) "⭐   $accountName" else "☆   $accountName",
                fontSize = 14.sp,
                color = if (isSelected) Color(0xFF3E64FF) else Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Edit",
                tint = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAccountRegistrationScreen() {
    val navController = rememberNavController()
    AccountRegistrationScreen(navController = navController)
}