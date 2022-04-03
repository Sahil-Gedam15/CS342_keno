import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

class MyTest {

	ArrayList<Integer> arr = new ArrayList<>();
	ArrayList<Integer> arrS1 = new ArrayList<>();
	ArrayList<Integer> arrS4 = new ArrayList<>();
	ArrayList<Integer> arrS8 = new ArrayList<>();
	ArrayList<Integer> arrS10 = new ArrayList<>();
	logic Logic = new logic();

	@BeforeEach
	void setup() {
		for(int i=1; i<=20;i++) {
			arr.add(i);
		}

		for(int i=1; i<=2;i++) {
			arrS1.add(i);
		}

		for(int i=1; i<=5;i++) {
			arrS4.add(i);
		}

		for(int i=1; i<=9;i++) {
			arrS8.add(i);
		}

		for(int i=1; i<=11;i++) {
			arrS10.add(i);
		}
	}

	@Test
	void getRandom20Round1() {
		assertNotEquals(arr,Logic.getRandom20(1),"getRandom20Round1() Error");
	}

	@Test
	void getRandom20Round2() {
		assertNotEquals(arr,Logic.getRandom20(2),"getRandom20Round2() Error");
	}

	@Test
	void getRandom20Round3() {
		assertNotEquals(arr,Logic.getRandom20(3),"getRandom20Round3() Error");
	}

	@Test
	void getRandom20Round4() {
		assertNotEquals(arr,Logic.getRandom20(4),"getRandom20Round4() Error");
	}

	@Test
	void getRandom20() {
		assertNotEquals(arr,Logic.getRandom20(1),"getRandom20(1)Error");
		assertNotEquals(arr,Logic.getRandom20(2),"getRandom20(2)Error");
		assertNotEquals(arr,Logic.getRandom20(3),"getRandom20(3)Error");
		assertNotEquals(arr,Logic.getRandom20(4),"getRandom20(4)Error");
	}

	@Test
	void getNumbersForUserS1_1 () {
		assertNotEquals(arrS1,Logic.getNumbersForUser(1),"getNumbersForUser Spot:1 Error");
	}

	@Test
	void getNumbersForUserS1_2 () {
		assertNotEquals(arrS4,Logic.getNumbersForUser(1),"getNumbersForUser Spot:1 Error");
	}

	@Test
	void getNumbersForUserS1_3() {
		assertNotEquals(arrS8,Logic.getNumbersForUser(1),"getNumbersForUser Spot:1 Error");
	}

	@Test
	void getNumbersForUserS1_4 () {
		assertNotEquals(arrS10,Logic.getNumbersForUser(1),"getNumbersForUser Spot:1 Error");
	}

	@Test
	void getNumbersForUserS4_1 () {
		assertNotEquals(arrS4,Logic.getNumbersForUser(4),"getNumbersForUser Spot:4 Error");
	}

	@Test
	void getNumbersForUserS4_2 () {
		assertNotEquals(arrS4,Logic.getNumbersForUser(4),"getNumbersForUser Spot:4 Error");
	}

	@Test
	void getNumbersForUserS4_3 () {
		assertNotEquals(arrS4,Logic.getNumbersForUser(4),"getNumbersForUser Spot:4 Error");
	}

	@Test
	void getNumbersForUserS8_1 () {
		assertNotEquals(arrS8,Logic.getNumbersForUser(8),"getNumbersForUser Spot:8 Error");
	}

	@Test
	void getNumbersForUserS8_2 () {
		assertNotEquals(arrS10,Logic.getNumbersForUser(8),"getNumbersForUser Spot:8 Error");
	}

	@Test
	void getNumbersForUserS10 () {
		assertNotEquals(arrS10,Logic.getNumbersForUser(10),"getNumbersForUser Spot:10 Error");
	}

	@Test
	void getMoneySpots10_0 () {
		assertEquals(4,Logic.getMoneySpots10(0),"getMoneySpots10_0 Error");
	}

	@Test
	void getMoneySpots10_5 () {
		assertEquals(2,Logic.getMoneySpots10(5),"getMoneySpots10_5 Error");
	}

	@Test
	void getMoneySpots10_6 () {
		assertEquals(15,Logic.getMoneySpots10(6),"getMoneySpots10_6 Error");
	}

	@Test
	void getMoneySpots10_7 () {
		assertEquals(50,Logic.getMoneySpots10(7),"getMoneySpots10_7 Error");
	}

	@Test
	void getMoneySpots10_8 () {
		assertEquals(500,Logic.getMoneySpots10(8),"getMoneySpots10_8 Error");
	}

	@Test
	void getMoneySpots10_9 () {
		assertEquals(5000,Logic.getMoneySpots10(9),"getMoneySpots10_9 Error");
	}

	@Test
	void getMoneySpots10_10 () {
		assertEquals(100000,Logic.getMoneySpots10(10),"getMoneySpots10_10 Error");
	}

	@Test
	void getMoneySpots10_4 () {
		assertEquals(0,Logic.getMoneySpots10(4),"getMoneySpots10_4 Error");
	}

	/////////////
	@Test
	void getMoneySpots8_4 () {
		assertEquals(2,Logic.getMoneySpots8(4),"getMoneySpots8_4 Error");
	}

	@Test
	void getMoneySpots8_5 () {
		assertEquals(10,Logic.getMoneySpots8(5),"getMoneySpots8_5 Error");
	}

	@Test
	void getMoneySpots8_6 () {
		assertEquals(75,Logic.getMoneySpots8(6),"getMoneySpots8_6 Error");
	}

	@Test
	void getMoneySpots8_7 () {
		assertEquals(500,Logic.getMoneySpots8(7),"getMoneySpots8_7 Error");
	}

	@Test
	void getMoneySpots8_8 () {
		assertEquals(10000,Logic.getMoneySpots8(8),"getMoneySpots8_8 Error");
	}

	@Test
	void getMoneySpots8_3 () {
		assertEquals(0,Logic.getMoneySpots8(3),"getMoneySpots8_3 Error");
	}
	//////////

	@Test
	void getMoneySpots4_4 () {
		assertEquals(100,Logic.getMoneySpots4(4),"getMoneySpots4_4 Error");
	}

	void getMoneySpots4_3 () {
		assertEquals(3,Logic.getMoneySpots4(3),"getMoneySpots4_3 Error");
	}

	void getMoneySpots4_2 () {
		assertEquals(1,Logic.getMoneySpots4(2),"getMoneySpots4_2 Error");
	}

	void getMoneySpots4_1 () {
		assertEquals(0,Logic.getMoneySpots4(1),"getMoneySpots4_1 Error");
	}

	void getMoneySpots1_1 () {
		assertEquals(2.50,Logic.getMoneySpots4(1),"getMoneySpots1_1 Error");
	}

	void getMoneySpots1_0 () {
		assertEquals(0,Logic.getMoneySpots4(0),"getMoneySpots1_0 Error");
	}

}
