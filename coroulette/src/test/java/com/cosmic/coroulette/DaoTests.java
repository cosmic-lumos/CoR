package com.cosmic.coroulette;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cosmic.coroulette.dao.Category;
import com.cosmic.coroulette.dao.Room;

@SpringBootTest
public class DaoTests {
    private Room myRoom;
    @BeforeEach
    void setUp(){
        myRoom = new Room("null", "null");
    }
    
    // @Test
    // void 카테고리_추가_테스트(){
    //     myRoom.addCategory("짜장면");
    //     assertEquals(myRoom.getCategories().get(new Category("짜장면")), 1);
    // }

    // @Test
    // void 카테고리_삭제_테스트(){
    //     myRoom.addCategory("짜장면");
    //     myRoom.removeCategory("짜장면");
    //     assertEquals(myRoom.getCategories().get(new Category("짜장면")), null);
    // }

    // @Test
    // void 카테고리_투표수_테스트(){
    //     myRoom.addCategory("짜장면");
    //     myRoom.addCategory("짜장면");
    //     myRoom.addCategory("짬뽕");
    //     assertEquals(myRoom.getCategories().get(new Category("짜장면")), 2);
    // }
}
