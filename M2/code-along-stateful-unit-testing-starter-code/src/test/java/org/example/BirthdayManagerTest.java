package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BirthdayManagerTest {
    BirthdayManager myTestManager;
    @BeforeEach
    void setUp() {
        myTestManager = new BirthdayManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFriendAlreadyExists() {
        //Arrange
        Friend testFriend1 = new Friend("testOne", "01/01/2001");
        Gift testGift1 = new Gift("NiceShirt", "L");
        myTestManager.addFriend(testFriend1);
        myTestManager.addGift(testFriend1, testGift1);
        //Act
        myTestManager.addFriend(testFriend1);
        //Assert
        assertTrue(myTestManager.getFriends().contains(testFriend1));
        assertEquals(1, myTestManager.getGiftsForFriend(testFriend1).size());
    }

    @Test
    void addNewFriend() {
        //Arrange
        Friend testFriend1 = new Friend("testOne", "01/01/2001");
        Friend testFriend2 = new Friend("testTwo", "02/02/2001");
        //Act
        myTestManager.addFriend(testFriend1);
        //Assert
        assertTrue(myTestManager.getFriends().contains(testFriend1));
        assertFalse(myTestManager.getFriends().contains(testFriend2));
        //Act
        myTestManager.addFriend(testFriend2);
        //Assert
        assertTrue(myTestManager.getFriends().contains(testFriend2));
    }
    @Test
    void addGiftNonExistentFriend() {
        //Arrange
        Friend testFriend1 = new Friend("testOne", "01/01/2001");
        Gift testGift1 = new Gift("niceShirt", "L");
        //Act
        myTestManager.addGift(testFriend1, testGift1);
        //Assert
        assertTrue(myTestManager.getFriends().contains(testFriend1));
        assertEquals(1, myTestManager.getGiftsForFriend(testFriend1).size());
    }
}