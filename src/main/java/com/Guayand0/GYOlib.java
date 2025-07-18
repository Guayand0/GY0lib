package com.Guayand0;

import com.Guayand0.messages.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class GYOlib extends JavaPlugin {

    public final String prefix = "&4&l[&6&lGYO&a&llib&4&l]&f";
    public final String currentVersion = getDescription().getVersion();

    private final MessageUtils MU = new MessageUtils();

    @Override
    public void onEnable() {
        //Bukkit.getConsoleSender().sendMessage(MU.getColoredText(prefix + " &fEnabled, (&aVersion: &e" + currentVersion + "&f)"));
    }

    @Override
    public void onDisable() {
        //Bukkit.getConsoleSender().sendMessage(MU.getColoredText(prefix + " &fDisabled, (&aVersion: &b" + currentVersion + "&f)"));
    }
}

/* plugin.yml

main: com.Guayand0.GYOlib
name: GYOlib
version: 0.0.4
author: Guayand0
api-version: 1.13
description: Una libreria con varias clases.

softdepend:
  - PlaceholderAPI
  - Vault
  - Essentials

*/