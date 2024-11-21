package com.taxi.sharing_public_taxi.compose
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.taxi.sharing_public_taxi.R


@Composable
fun MyPageScreen(onEditProfileClick: () -> Unit, onAccountChangeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6F9)),
        //.padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // 프로필 이미지와 이름
        ProfileSection(onEditProfileClick)

        Spacer(modifier = Modifier.height(36.dp))

        // 매칭 이력 섹션
        MatchingHistorySection()

        Spacer(modifier = Modifier.height(8.dp))

        // 설정 섹션
        SettingsSection(onAccountChangeClick)

        Spacer(modifier = Modifier.height(12.dp))

        // 로그아웃 버튼
        TextButton(onClick = { /* 로그아웃 처리 */ }) {
            Text("로그아웃", fontSize = 12.sp, color = Color(0xFF838383))
        }

        BottomButton()
    }
}

@Composable
fun ProfileSection(onEditProfileClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // 이미지(대체로 아이콘 사용)
        Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(99.dp)
                .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                .padding(0.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 사용자 이름 및 학교
        Text(
            text ="도현깅",
            //fontFamily = FontFamily(Font(R.font.noto_sans_kr)),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 21.sp,
            letterSpacing = (-.32).sp,
            textAlign = TextAlign.Left,
            color = Color(0xFF424242)
            //modifier = Modifier
            //.width(66.dp)
            //.height(21.dp)
            //.padding(start = 147.dp)
        )
        Text(
            text = "한양대학교 19학번",
            //fontFamily = FontFamily(Font(R.font.noto_sans_kr)),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 21.sp,
            letterSpacing = (-.32).sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF333333)
            //modifier = Modifier
            //.width(91.dp)
            //.height(21.dp)
            //.padding(start = 135.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 프로필 수정 버튼
        Button(
            onClick = { onEditProfileClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(103.dp)
                .height(35.dp),
            //elevation = 0.dp
            //.padding(start = 129.dp)
        ) {
            Text(
                text = "프로필 수정",
                //fontFamily = FontFamily(Font(R.font.noto_sans_kr)),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 21.sp,
                letterSpacing = (-0.32).sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF838383)
                //modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MatchingHistorySection() {
    Card(
        modifier = Modifier
            .width(330.dp)
            .height(142.dp)
            //.padding(vertical = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(40.dp)),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
                .background(Color.White, shape = RoundedCornerShape(15.dp))
        ) {
            Text(
                text = "내 매칭 이력",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFF838383)
                //modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "진행중",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
                //modifier = Modifier
                //.width(260.dp)
                //.height(24.dp)
                //.padding(start = 50.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                color = Color(0xFFF5F6F9),
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "완료",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
                //modifier = Modifier
                //.width(26.dp)
                //.height(21.dp)
                //.padding(start = 50.dp)
            )
        }
    }
}

@Composable
fun SettingsSection(onAccountChangeClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(330.dp)
            .height(198.dp)
            //.padding(vertical = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(40.dp)),
        elevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "설정",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFF838383)
                //modifier = Modifier
                //.width(22.dp)
                //.height(21.dp)
                //.padding(start = 50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 카카오 계정
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAccountChangeClick() },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "카카오 계정",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                    //modifier = Modifier
                    //.width(260.dp)
                    //.height(21.dp)
                    //.padding(start = 50.5.dp)
                )
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "다음")
            }
            Spacer(modifier = Modifier.height(12.dp))

            // 선 추가
            Divider(
                color = Color(0xFFF5F6F9),
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
                //.padding(horizontal = 0.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 계좌 등록/변경
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* 계좌 등록/변경 처리 */ },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "계좌 등록/변경",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                    //modifier = Modifier
                    //.width(260.dp)
                    //.height(21.dp)
                    //.padding(start = 50.dp)
                )
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "다음")
            }
            Spacer(modifier = Modifier.height(12.dp))

            //선추가
            Divider(
                color = Color(0xFFF5F6F9),
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
                //.padding(horizontal = 0.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 설정
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* 설정 화면 이동 */ },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "설정",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                    //modifier = Modifier
                    //.width(260.dp)
                    //.height(21.dp)
                    //.padding(start = 505.dp)
                )
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "다음")
            }
        }
    }
}

@Composable
fun BottomButton() {
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
fun PreviewMyPageScreen() {
    MyPageScreen(
        onEditProfileClick = {},
        onAccountChangeClick = {}
    )
}