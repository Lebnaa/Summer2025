package seneca.summer25.apdfinal.Utils;

import seneca.summer25.apdfinal.Model.RoomType;

public class EnumConverter {

    public static String roomTypeToString(RoomType type) {
        return type != null ? type.name() : null;
    }

    public static RoomType stringToRoomType(String value) {
        if (value == null) return RoomType.SINGLE;
        try {
            return RoomType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return RoomType.SINGLE; // fallback
        }
    }
}
