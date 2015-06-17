/*
 * Copyright 2015 Justin W. Flory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.justinwflory.loginscript;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginScript extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("LoginScript disabled.");
    }

    @EventHandler
    public void scriptedLogin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String pName = p.getName();
        if (getConfig().getString("scripted-players").contains(pName)) {
            p.performCommand("v e lightning");
            p.performCommand("v e explode");
            p.performCommand("v e bats");
            p.performCommand("minecraft:gamemode 1");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("loginscript")) {
            if (args.length != 1) return false;

            else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("loginscript.reload")) {
                this.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
                return true;
            }
        }
        return false;
    }
}
