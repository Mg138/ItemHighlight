package io.github.mg138.itemhighlight.util

import net.md_5.bungee.api.ChatColor
import java.awt.Color
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs

object ColorUtil {
    private fun constructColorMap(): Map<org.bukkit.ChatColor, Color> {
        val map = EnumMap<org.bukkit.ChatColor, Color>(org.bukkit.ChatColor::class.java)
        map[org.bukkit.ChatColor.BLACK] = Color(0, 0, 0)
        map[org.bukkit.ChatColor.DARK_BLUE] = Color(0, 0, 170)
        map[org.bukkit.ChatColor.DARK_GREEN] = Color(0, 170, 0)
        map[org.bukkit.ChatColor.DARK_AQUA] = Color(0, 170, 170)
        map[org.bukkit.ChatColor.DARK_RED] = Color(170, 0, 0)
        map[org.bukkit.ChatColor.DARK_PURPLE] = Color(170, 0, 170)
        map[org.bukkit.ChatColor.GOLD] = Color(255, 170, 0)
        map[org.bukkit.ChatColor.GRAY] = Color(170, 170, 170)
        map[org.bukkit.ChatColor.DARK_GRAY] = Color(85, 85, 85)
        map[org.bukkit.ChatColor.BLUE] = Color(85, 85, 255)
        map[org.bukkit.ChatColor.GREEN] = Color(85, 255, 85)
        map[org.bukkit.ChatColor.AQUA] = Color(85, 255, 255)
        map[org.bukkit.ChatColor.RED] = Color(255, 85, 85)
        map[org.bukkit.ChatColor.LIGHT_PURPLE] = Color(255, 85, 255)
        map[org.bukkit.ChatColor.YELLOW] = Color(255, 255, 85)
        map[org.bukkit.ChatColor.WHITE] = Color(255, 255, 255)
        return map
    }

    private val colorMap = constructColorMap()

    fun fromRGB(r: Int, g: Int, b: Int): org.bukkit.ChatColor {
        val closest = TreeMap<Int, org.bukkit.ChatColor>()
        colorMap.forEach { (color: org.bukkit.ChatColor, set: Color) ->
            val red = abs(r - set.red)
            val green = abs(g - set.green)
            val blue = abs(b - set.blue)
            closest[red + green + blue] = color
        }
        return closest.firstEntry().value
    }

    fun getMostSignificantColor(string: String?): Color {
        string ?: return ChatColor.WHITE.color
        val colors: MutableMap<Color, Int> = HashMap()
        var color: Color? = null
        var c = 0
        var i = 0
        while (i < string.length) {
            when (string[i]) {
                ChatColor.COLOR_CHAR -> {
                    if (color != null) {
                        if (colors.containsKey(color)) {
                            colors[color] = colors[color]!! + c
                        } else {
                            colors[color] = c
                        }
                        c = 0
                    }
                    color = ChatColor.getByChar(string[i + 1]).color
                    i += 2
                }
                '#' -> {
                    if (color != null) {
                        if (colors.containsKey(color)) {
                            colors[color] = colors[color]!! + c
                        } else {
                            colors[color] = c
                        }
                        c = 0
                    }
                    color = Color(string.substring(i + 1, i + 7).toInt(16))
                    i += 7
                }
                else -> {
                    if (color == null) {
                        color = ChatColor.WHITE.color
                    }
                    c++
                    i++
                }
            }
        }
        if (color != null) {
            if (colors.containsKey(color)) {
                colors[color] = colors[color]!! + c
            } else {
                colors[color] = c
            }
        }
        var result: Color = ChatColor.WHITE.color
        val max = 0
        colors.forEach { (color, i) ->
            if (i > max) {
                result = color
            }
        }
        return result
    }
}