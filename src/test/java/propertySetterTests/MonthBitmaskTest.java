package propertySetterTests;
import at.fhv.hike.data.Bitmask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthBitmaskTest {

    @Test
    void testBitmaskOperations() {
        Bitmask bitmask = new Bitmask();

        assertEquals(Bitmask.Month_1_Jan, bitmask.addJan());
        assertEquals(Bitmask.Month_1_Jan | Bitmask.Month_2_Feb, bitmask.addFeb());
        assertEquals(Bitmask.Month_1_Jan | Bitmask.Month_2_Feb | Bitmask.Month_3_Mar, bitmask.addMar());

        assertEquals(Bitmask.Month_1_Jan | Bitmask.Month_3_Mar, bitmask.remFeb());

        bitmask.addApr();
        bitmask.addMay();
        bitmask.addJun();
        bitmask.addJul();
        bitmask.addAug();
        bitmask.addSep();
        bitmask.addOct();
        bitmask.addNov();
        bitmask.addDec();

        assertEquals(Bitmask.Month_1_Jan | Bitmask.Month_3_Mar | Bitmask.Month_4_Apr | Bitmask.Month_5_May | Bitmask.Month_6_Jun | Bitmask.Month_7_Jul | Bitmask.Month_8_Aug | Bitmask.Month_9_Sep | Bitmask.Month_10_Oct | Bitmask.Month_11_Nov, bitmask.remDec());
        assertEquals(Bitmask.Month_1_Jan | Bitmask.Month_3_Mar | Bitmask.Month_4_Apr | Bitmask.Month_5_May | Bitmask.Month_6_Jun | Bitmask.Month_7_Jul | Bitmask.Month_8_Aug | Bitmask.Month_9_Sep | Bitmask.Month_11_Nov, bitmask.remOct());

        int expectedBitmask = Bitmask.Month_1_Jan | Bitmask.Month_3_Mar | Bitmask.Month_4_Apr | Bitmask.Month_5_May | Bitmask.Month_6_Jun | Bitmask.Month_7_Jul | Bitmask.Month_8_Aug | Bitmask.Month_9_Sep | Bitmask.Month_11_Nov;
        assertEquals(expectedBitmask, bitmask.returnBitmask());
    }
}
