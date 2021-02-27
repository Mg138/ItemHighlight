package io.github.mg138.itemhighlight

import io.github.mg138.itemhighlight.event.Highlighter
import io.github.mg138.itemhighlight.util.Glow
import me.pikamug.localelib.LocaleLib
import me.pikamug.localelib.LocaleManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ItemHighlight : JavaPlugin() {
    companion object {
        lateinit var inst: ItemHighlight
        lateinit var jar: File
        var localeLib: LocaleLib? = null
    }

    override fun onEnable() {
        inst = this
        jar = inst.file

        if (inst.server.pluginManager.isPluginEnabled("LocaleLib")) {
            localeLib = inst.server.pluginManager.getPlugin("LocaleLib") as LocaleLib
        }

        reg()
    }

    override fun onDisable() {
        unload()
    }

    private fun unload() {
        Glow.unload()
    }

    private fun reg() {
        Bukkit.getPluginManager().registerEvents(Highlighter(), inst)
    }
}