package me.mxtery.minecraftbrawlstars.instance;

import me.mxtery.minecraftbrawlstars.GameState;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import me.mxtery.minecraftbrawlstars.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    private MinecraftBrawlStars minigame;

    private int id;
    private Location spawn;
    private Location spawn1;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public GameState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public List<UUID> getPlayers() {
        return players;
    }
    public Arena(MinecraftBrawlStars minigame, int id, Location spawn, Location spawn1){
        this.id  = id;
        this.spawn = spawn;
        this.spawn1 = spawn1;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(minigame, this);
        this.game = new Game(this);
        this.minigame = minigame;
    }

    public void start(){
game.start();
    }

    public Game getGame() {
        return game;
    }

    public void reset(boolean kickPlayers, boolean clearInv){
        if (kickPlayers){
            Location spawn = ConfigManager.getLobbySpawn();
for (UUID uuid : players){
    Bukkit.getPlayer(uuid).teleport(spawn);
    if (clearInv){
        Bukkit.getPlayer(uuid).getInventory().clear();
        MinecraftBrawlStars.getInstance().getConfig().set(Bukkit.getPlayer(uuid).getUniqueId().toString(), null);
        Shelly.SHELLY_PLAYERS.remove(uuid.toString());
        MinecraftBrawlStars.getInstance().getConfig().set("Shelly", Shelly.SHELLY_PLAYERS);

    }
}
players.clear();
        }

        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel(); //will not error even if the countdown is not running
        countdown = new Countdown(minigame, this);
        game = new Game(this);
    }


    public void sendMessage(String message){
        for (UUID uuid : players){
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subtitle){
        for (UUID uuid : players){
            //depricated, but we don't need to control the time of the title so this is fine.
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }


    public void addPlayer(Player player){
        player.setSaturation(0);

        players.add(player.getUniqueId());
        if (players.size() == 1){
            player.teleport(spawn);
        }else if (players.size() == 2){
            player.teleport(spawn1);
        }


        if (state == GameState.RECRUITING && players.size() >= ConfigManager.getRequiredPlayers()){
            countdown.start();
        }
    }
    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "");

        if (state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()){
            sendMessage(ChatColor.RED + "There is not enough players. Countdown stopped.");
            reset(false, true);
        }
        if (state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()){
            sendMessage(ChatColor.RED + "The game has ended as too many players have left.");
            reset(false, true);
        }
    }
    public void setState(GameState state){
        this.state = state;
    }


}
