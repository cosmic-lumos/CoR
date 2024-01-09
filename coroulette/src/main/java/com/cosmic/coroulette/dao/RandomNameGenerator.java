package com.cosmic.coroulette.dao;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class RandomNameGenerator {
    public static String RandomName(){
        List<String> firstName = Arrays.asList(
            "강직한", "고요한", "고운", "기특한", "깜찍한", "근면한", "귀여운", "관대한", "깔끔한", 
            "꾸준한", "긍정적인", "깡이 있는", "겸손한", "검소한", "공손한", "기운찬","놀라운", "나눌 줄 아는", "느긋한", "넉살 좋은", "낭만적인"
            ,"다정한", "당당한", "든든한", "다재다능한", "단호한", "대담한",
            "리더십 있는", "로맨틱한","믿음직한", "매력적인", "맑은", "멋진",
            "반듯한", "발랄한", "부드러운", "밝은", "배짱 있는", "부지런한", "바른", 
            "싱그러운", "선한", "시원시원한", "사랑스러운", "성실한", "순수한", "상냥한", "생기있는", "솔직한", "신중한",
            "예쁜", "용감한", "우아한", "유쾌한", "아름다운", "여유로운", "에너지 넘치는", "유머러스한",
            "적극적인", "정의로운", "조용한", "재미있는", "자유로운", "지적인", "착한", "침착한", "차분한", "친절한",
            "쾌활한","털털한","편안한","화끈한"
        );
        
        List<String> secondName = Arrays.asList(
            "라이언", "어피치", "프로도", "네오", "무지", "콘", "튜브", "제이지"
        );
        
        Random random = new Random();
        int randomFirstIndex = random.nextInt(firstName.size());
        int randomSecondIndex = random.nextInt(secondName.size());

        return firstName.get(randomFirstIndex) + " " + secondName.get(randomSecondIndex);

    }
}
