package jobs4u.app.customer.console.messages;

public enum MessageCode {
    COMMTEST(0),
    DISCONN(1),
    ACK(2),
    ERR(3),
    AUTH(4),
    GETJOBOP(5),
    JOBOPLST(6),
    GETNOTIFS(7),
    NOTIFLST(8),
    READNOTIF(9),
    GETJOBAP(10),
    JOBAPLST(11);
    private final byte value;
    MessageCode(int value){this.value = (byte)value;}
    public byte getValue() {
        return value;
    }
    public static MessageCode getName(int value) {
        for (MessageCode code : MessageCode.values()) {
            if (code.getValue() == (byte) value) {
                return code;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
