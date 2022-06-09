package com.laynox.betterchat.command;

import com.laynox.betterchat.Main;
import com.laynox.betterchat.manager;

public class reload {
    public reload() {
        manager.files.configFunctions.save();
        manager.files.playerFunctions.save();
        manager.files.configFunctions.set(Main.getPlugin());
        manager.files.playerFunctions.set(Main.getPlugin());
    }
}
