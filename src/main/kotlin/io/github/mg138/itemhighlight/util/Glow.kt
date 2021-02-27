package io.github.mg138.itemhighlight.util

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.scoreboard.Team
import java.awt.Color
import java.util.ArrayList

object Glow {
    private var teams = ArrayList<Team>()
    private var scoreboard = Bukkit.getScoreboardManager()!!.mainScoreboard

    fun setGlowColor(color: Color, entity: Entity) {
        val name = color.rgb.toString()
        var team = scoreboard.getTeam(name)

        if (team == null) {
            team = scoreboard.registerNewTeam(name)
            teams.add(team)
        }

        team.color = ColorUtil.fromRGB(color.red, color.green, color.blue)
        team.addEntry(entity.uniqueId.toString())
    }

    fun unload() {
        for (team in teams) {
            team.unregister()
        }
    }
}