package me.mxtery.minecraftbrawlstars.instance;

import me.mxtery.minecraftbrawlstars.GameState;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {
private MinecraftBrawlStars minigame;
private Arena arena;
private int countdownSeconds;
    public Countdown(MinecraftBrawlStars minigame, Arena arena){
this.minigame = minigame;
this.arena = arena;
//saving to variable because we do not want to fetch a file every single time (slow)
this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start(){
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigame, 0, 20);
    }


    @Override
    public void run() {
        if (countdownSeconds == 0){
        cancel();
        arena.start();
        arena.sendTitle("", "");
            return;
            //will prevent from running code below ("game is starting in 0 seconds" will not show)
        }
        if (countdownSeconds <= 10 || countdownSeconds % 15 == 0){


            // ternary operator: (condition) ? true-clause : false-clause
            //You can think of it like: true? (then do this) :false (do this)

            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }
        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s"), ChatColor.GRAY + "until game starts");
        countdownSeconds--;

    }
}
