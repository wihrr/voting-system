package by.intexsoft.vihrova.votingsystem.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RootConstants {
    public static final String ALL_DISHES_ROOT = "/dishes";
    public static final String DISH_BY_ID_ROOT = "/dishes/**";
    public static final String MENU_BY_ID_ROOT = "/menus/**";
    public static final String ALL_MENUS_ROOT = "/menus";
    public static final String RESTAURANT_BY_ID_ROOT = "/restaurants/**";
    public static final String ALL_RESTAURANTS_ROOT = "/restaurants";
}
