// Toyoaki, OHGOCHI  https://twitter.com/Ohgochi/

package com.example.camera2probe4;

public class Triple <K, V, S>{
    private K Key;
    private V Value;
    private S State;

    Triple (K t1, V t2, S t3) {
        Key = t1;
        Value = t2;
        State = t3;
    }

    K first() { return Key; }
    V second() { return Value; }
    S third() { return State; }
    void setFirst(K t1) {Key = t1;}
    void setSecond(V t2) {Value = t2;}
    void setThird(S t3) {State = t3;}

    @Override public String toString() {
        return "(" + Key + ", " + Value + ", " + State + ")";
    }
}
