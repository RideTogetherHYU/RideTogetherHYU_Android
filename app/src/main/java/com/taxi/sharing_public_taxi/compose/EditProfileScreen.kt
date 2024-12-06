package com.taxi.sharing_public_taxi.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import com.taxi.sharing_public_taxi.R

@Composable
fun EditProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9))
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 뒤로 가기 버튼
        IconButton(onClick = { navController.popBackStack() },
            modifier = Modifier
                .offset(y = 10.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "뒤로 가기",
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        // 프로필 이미지
        Box(
            modifier = Modifier
                .size(99.dp)
                .align(Alignment.CenterHorizontally)
            //.padding(top = 173.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile), // 이미지 리소스를 여기에 추가
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(99.dp)
                    .clip(RoundedCornerShape(35.dp))
                    .graphicsLayer(alpha = 0.5f)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(99.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "프로필 수정"
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // 이름/학적 필드
        InfoField(label = "이름/학적", value = "백도현 / 한양대학교 19학번")

        Spacer(modifier = Modifier.height(16.dp))

        // 닉네임 필드
        InfoField(label = "닉네임", value = "도현킹", editable = true)

        Spacer(modifier = Modifier.height(16.dp))

        // 한줄소개 필드
        InfoField(label = "한줄소개(20자 이내)", value = "안녕하세요!", editable = true)

        Spacer(modifier = Modifier.height(80.dp))

        // 회원탈퇴 버튼
        TextButton(
            onClick = { /* 회원탈퇴 처리 */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "회원탈퇴",
                fontSize = 12.sp,
                color = Color(0xFFFF5A5A),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 하단 네비게이션 바(예시)
        BottomNavigationBar()
    }
}

@Composable
fun InfoField(label: String, value: String, editable: Boolean = false) {
    var textValue by remember { mutableStateOf(value) }
    var isEditing by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF838383)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(isEditing && editable) {
                TextField(
                    value = textValue,
                    onValueChange = {textValue = it},
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                IconButton(onClick = {isEditing = false}) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "수정완료",
                        tint = Color(0xFF838383)
                    )
                }
            } else {
                Text(
                    text = textValue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                if (editable) {
                    IconButton(onClick = { isEditing = true }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "수정",
                            tint = Color(0xFF838383)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    //Button(
    //onClick = {},
    //modifier = Modifier
    //.fillMaxWidth()
    //.padding(horizontal = 16.dp)
    //.height(56.dp)
    //) {
    Image(
        painter = painterResource(id = R.drawable.bottom_mypage),
        contentDescription = "하단 버튼 이미지",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxSize()
            .height(56.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEditProfileScreen() {
    val navController = rememberNavController()
    EditProfileScreen(navController = navController)
}
