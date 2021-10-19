package model;

public class SeatModel {
    private int s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
            s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,
            s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,
            s31,s32,s33,s34,s35,s36,s37,s38,s39,s40;
//    private boolean isReserved;

    public SeatModel(int s1, int s2, int s3, int s4, int s5, int s6, int s7, int s8, int s9, int s10, int s11, int s12, int s13, int s14, int s15, int s16, int s17, int s18, int s19, int s20, int s21, int s22, int s23, int s24, int s25, int s26, int s27, int s28, int s29, int s30, int s31, int s32, int s33, int s34, int s35, int s36, int s37, int s38, int s39, int s40) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.s10 = s10;
        this.s11 = s11;
        this.s12 = s12;
        this.s13 = s13;
        this.s14 = s14;
        this.s15 = s15;
        this.s16 = s16;
        this.s17 = s17;
        this.s18 = s18;
        this.s19 = s19;
        this.s20 = s20;
        this.s21 = s21;
        this.s22 = s22;
        this.s23 = s23;
        this.s24 = s24;
        this.s25 = s25;
        this.s26 = s26;
        this.s27 = s27;
        this.s28 = s28;
        this.s29 = s29;
        this.s30 = s30;
        this.s31 = s31;
        this.s32 = s32;
        this.s33 = s33;
        this.s34 = s34;
        this.s35 = s35;
        this.s36 = s36;
        this.s37 = s37;
        this.s38 = s38;
        this.s39 = s39;
        this.s40 = s40;
    }

    public boolean[] isReserved(){
        int[] b = {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
                s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,
                s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,
                s31,s32,s33,s34,s35,s36,s37,s38,s39,s40};
        boolean[] res = {false,false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,false,};
        for (int i=0;i<b.length;i++) {
            if(b[i] == 0){
                res[i]=false;
            }
            else {
                res[i]=true;
            }
        }
        return res;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public void setS3(int s3) {
        this.s3 = s3;
    }

    public void setS4(int s4) {
        this.s4 = s4;
    }

    public void setS5(int s5) {
        this.s5 = s5;
    }

    public void setS6(int s6) {
        this.s6 = s6;
    }

    public void setS7(int s7) {
        this.s7 = s7;
    }

    public void setS8(int s8) {
        this.s8 = s8;
    }

    public void setS9(int s9) {
        this.s9 = s9;
    }

    public void setS10(int s10) {
        this.s10 = s10;
    }

    public void setS11(int s11) {
        this.s11 = s11;
    }

    public void setS12(int s12) {
        this.s12 = s12;
    }

    public void setS13(int s13) {
        this.s13 = s13;
    }

    public void setS14(int s14) {
        this.s14 = s14;
    }

    public void setS15(int s15) {
        this.s15 = s15;
    }

    public void setS16(int s16) {
        this.s16 = s16;
    }

    public void setS17(int s17) {
        this.s17 = s17;
    }

    public void setS18(int s18) {
        this.s18 = s18;
    }

    public void setS19(int s19) {
        this.s19 = s19;
    }

    public void setS20(int s20) {
        this.s20 = s20;
    }

    public void setS21(int s21) {
        this.s21 = s21;
    }

    public void setS22(int s22) {
        this.s22 = s22;
    }

    public void setS23(int s23) {
        this.s23 = s23;
    }

    public void setS24(int s24) {
        this.s24 = s24;
    }

    public void setS25(int s25) {
        this.s25 = s25;
    }

    public void setS26(int s26) {
        this.s26 = s26;
    }

    public void setS27(int s27) {
        this.s27 = s27;
    }

    public void setS28(int s28) {
        this.s28 = s28;
    }

    public void setS29(int s29) {
        this.s29 = s29;
    }

    public void setS30(int s30) {
        this.s30 = s30;
    }

    public void setS31(int s31) {
        this.s31 = s31;
    }

    public void setS32(int s32) {
        this.s32 = s32;
    }

    public void setS33(int s33) {
        this.s33 = s33;
    }

    public void setS34(int s34) {
        this.s34 = s34;
    }

    public void setS35(int s35) {
        this.s35 = s35;
    }

    public void setS36(int s36) {
        this.s36 = s36;
    }

    public void setS37(int s37) {
        this.s37 = s37;
    }

    public void setS38(int s38) {
        this.s38 = s38;
    }

    public void setS39(int s39) {
        this.s39 = s39;
    }

    public void setS40(int s40) {
        this.s40 = s40;
    }

    public int getS1() {
        return s1;
    }

    public int getS2() {
        return s2;
    }

    public int getS3() {
        return s3;
    }

    public int getS4() {
        return s4;
    }

    public int getS5() {
        return s5;
    }

    public int getS6() {
        return s6;
    }

    public int getS7() {
        return s7;
    }

    public int getS8() {
        return s8;
    }

    public int getS9() {
        return s9;
    }

    public int getS10() {
        return s10;
    }

    public int getS11() {
        return s11;
    }

    public int getS12() {
        return s12;
    }

    public int getS13() {
        return s13;
    }

    public int getS14() {
        return s14;
    }

    public int getS15() {
        return s15;
    }

    public int getS16() {
        return s16;
    }

    public int getS17() {
        return s17;
    }

    public int getS18() {
        return s18;
    }

    public int getS19() {
        return s19;
    }

    public int getS20() {
        return s20;
    }

    public int getS21() {
        return s21;
    }

    public int getS22() {
        return s22;
    }

    public int getS23() {
        return s23;
    }

    public int getS24() {
        return s24;
    }

    public int getS25() {
        return s25;
    }

    public int getS26() {
        return s26;
    }

    public int getS27() {
        return s27;
    }

    public int getS28() {
        return s28;
    }

    public int getS29() {
        return s29;
    }

    public int getS30() {
        return s30;
    }

    public int getS31() {
        return s31;
    }

    public int getS32() {
        return s32;
    }

    public int getS33() {
        return s33;
    }

    public int getS34() {
        return s34;
    }

    public int getS35() {
        return s35;
    }

    public int getS36() {
        return s36;
    }

    public int getS37() {
        return s37;
    }

    public int getS38() {
        return s38;
    }

    public int getS39() {
        return s39;
    }

    public int getS40() {
        return s40;
    }
    //    private int[] s = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
//        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//
//    public SeatModel(int[] s) {
//        this.s = s;
//    }
//
//    public void setS(int[] s) {
//        this.s = s;
//    }
//
//    public int[] getS() {
//        return s;
//    }
}
