package fr.pumpmyskybukkit;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import fr.pumpmyplotcore.PlotLocation;
import fr.pumpmyplotcore.PlotManager;

@SuppressWarnings("deprecation")
public class BukkitIslandManager extends PlotManager<OfflinePlayer>{

	protected MainSky main;
	
	public BukkitIslandManager(MainSky main) throws IOException, InvalidConfigurationException {
		super(main.getDataFolder().toPath());
		
		this.main = main;
		
	}
	
	public MainSky getMain() {
		return this.main;
	}

	@Override
	public UUID getMinecraftUUID(OfflinePlayer player) {
		return player.getUniqueId();
	}
	
	public OfflinePlayer getOfflinePlayerByName(String name) {
		
		for (OfflinePlayer offlinePlayer : this.main.getServer().getOfflinePlayers()) {
			
			if(offlinePlayer.getName().equals(name)) {
				return offlinePlayer;
			}
			
		}
		
		return null;
		
	}

	public void pasteIslandSchematic(PlotLocation location) throws DataException, IOException, MaxChangedBlocksException {
		
		File file = new File(this.main.getDataFolder(),"ile.schematic");
		
		if(!file.exists()) {
			
			throw new IOException("Schematic does not exist !");
			
		}
		
		Location bukkitLocation = new Location(Bukkit.getWorld("Void"), (location.getX() * PlotManagerConstant.PLOT_SIZE) + PlotManagerConstant.PLOT_SIZE / 2 , 60, (location.getZ() * PlotManagerConstant.PLOT_SIZE) + PlotManagerConstant.PLOT_SIZE / 2);
		
		Vector v = new Vector(bukkitLocation.getBlockX(), bukkitLocation.getBlockY(), bukkitLocation.getBlockZ());
		
		EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(bukkitLocation.getWorld()), WorldEdit.getInstance().getConfiguration().maxChangeLimit);
		
		SchematicFormat format = SchematicFormat.getFormat(file);
		CuboidClipboard cc = format.load(file);
		
		cc.paste(es, v, false);		
		
	}	
	
}
