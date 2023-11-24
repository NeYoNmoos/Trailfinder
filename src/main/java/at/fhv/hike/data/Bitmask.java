package at.fhv.hike.data;

public class Bitmask {

    public static final int Month_1_Jan = 1;    //0000 0000 0001
    public static final int Month_2_Feb = 2;    //0000 0000 0010
    public static final int Month_3_Mar = 4;    //0000 0000 0100
    public static final int Month_4_Apr = 8;    //0000 0000 1000
    public static final int Month_5_May = 16;   //0000 0001 0000
    public static final int Month_6_Jun = 32;   //0000 0010 0000
    public static final int Month_7_Jul = 64;   //0000 0100 0000
    public static final int Month_8_Aug = 128;  //0000 1000 0000
    public static final int Month_9_Sep = 256;  //0001 0000 0000
    public static final int Month_10_Oct = 512; //0010 0000 0000
    public static final int Month_11_Nov = 1024;//0100 0000 0000
    public static final int Month_12_Dec = 2048;//1000 0000 0000

    private int _bitmask;

    public Bitmask(){
        _bitmask = 0;
    }

    public Bitmask(int bitmask){
        _bitmask = bitmask;
    }

    public int returnBitmask(){
        return _bitmask;
    }

    public int addJan(){
        _bitmask = _bitmask | Month_1_Jan;
        return _bitmask;
    }

    public int remJan(){
        _bitmask = _bitmask & ~ Month_1_Jan;
        return _bitmask;
    }

    public int addFeb(){
        _bitmask = _bitmask | Month_2_Feb;
        return _bitmask;
    }

    public int remFeb(){
        _bitmask = _bitmask & ~ Month_2_Feb;
        return _bitmask;
    }

    public int addMar(){
        _bitmask = _bitmask | Month_3_Mar;
        return _bitmask;
    }

    public int remMar(){
        _bitmask = _bitmask & ~ Month_3_Mar;
        return _bitmask;
    }

    public int addApr(){
        _bitmask = _bitmask | Month_4_Apr;
        return _bitmask;
    }

    public int remApr(){
        _bitmask = _bitmask & ~ Month_4_Apr;
        return _bitmask;
    }

    public int addMay(){
        _bitmask = _bitmask | Month_5_May;
        return _bitmask;
    }

    public int remMay(){
        _bitmask = _bitmask & ~ Month_5_May;
        return _bitmask;
    }

    public int addJun(){
        _bitmask = _bitmask | Month_6_Jun;
        return _bitmask;
    }

    public int remJun(){
        _bitmask = _bitmask & ~ Month_6_Jun;
        return _bitmask;
    }

    public int addJul(){
        _bitmask = _bitmask | Month_7_Jul;
        return _bitmask;
    }

    public int remJul(){
        _bitmask = _bitmask & ~ Month_7_Jul;
        return _bitmask;
    }

    public int addAug(){
        _bitmask = _bitmask | Month_8_Aug;
        return _bitmask;
    }

    public int remAug(){
        _bitmask = _bitmask & ~ Month_8_Aug;
        return _bitmask;
    }

    public int addSep(){
        _bitmask = _bitmask | Month_9_Sep;
        return _bitmask;
    }

    public int remSep(){
        _bitmask = _bitmask & ~ Month_9_Sep;
        return _bitmask;
    }

    public int addOct(){
        _bitmask = _bitmask | Month_10_Oct;
        return _bitmask;
    }

    public int remOct(){
        _bitmask = _bitmask & ~ Month_10_Oct;
        return _bitmask;
    }

    public int addNov(){
        _bitmask = _bitmask | Month_11_Nov;
        return _bitmask;
    }

    public int remNov(){
        _bitmask = _bitmask & ~ Month_11_Nov;
        return _bitmask;
    }

    public int addDec(){
        _bitmask = _bitmask | Month_12_Dec;
        return _bitmask;
    }

    public int remDec(){
        _bitmask = _bitmask & ~ Month_12_Dec;
        return _bitmask;
    }

}
