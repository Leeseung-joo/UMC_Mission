package umc.study.util;

public class PageRequestConverter {
    public static int convert(int userPage) {
    return userPage - 1; // 1 → 0
}
}
