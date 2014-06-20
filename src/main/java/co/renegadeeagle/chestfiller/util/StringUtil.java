package co.renegadeeagle.chestfiller.util;

import org.bukkit.ChatColor;

/**
 * Created by Ryan on 6/19/2014.
 */
public class StringUtil {
    private static String prefix = ChatColor.GRAY+"["+ChatColor.GREEN+"ChestFiller"+ChatColor.GRAY+"]" ;
    private static String noPermission = "";

    public static String getPrefix() {
        return prefix;
    }
}
