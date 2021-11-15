package de.lcraft.plugins.challenges;

import de.lcraft.api.utils.minecraft.spigot.module.Module;

public class ChallengesPlugin extends Module {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        getListenerManager().registerAllListeners();
    }

    @Override
    public void onDisable() {

    }

}
