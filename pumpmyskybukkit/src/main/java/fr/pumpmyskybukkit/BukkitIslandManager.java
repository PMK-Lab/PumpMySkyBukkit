package fr.pumpmyskybukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.function.mask.ExistingBlockMask;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.world.registry.WorldData;

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
		
		Location bukkitLocation = new Location(Bukkit.getWorld("Void"), (location.getX() * PlotManagerConstant.PLOT_SIZE) - (PlotManagerConstant.PLOT_SIZE / 2) , 60, (location.getZ() * PlotManagerConstant.PLOT_SIZE) - PlotManagerConstant.PLOT_SIZE / 2);
		
		System.out.println(bukkitLocation.getBlockX() + "    " + bukkitLocation.getBlockY() + "    " + bukkitLocation.getBlockZ());
		
		Vector to = new Vector(bukkitLocation.getBlockX(), bukkitLocation.getBlockY(), bukkitLocation.getBlockZ());

		BukkitWorld weWorld = new BukkitWorld(bukkitLocation.getWorld());
		WorldData worldData = weWorld.getWorldData();
		Clipboard clipboard = ClipboardFormat.SCHEMATIC.getReader(new FileInputStream(file)).read(worldData);
		Region region = clipboard.getRegion();

		EditSession extent = WorldEdit.getInstance().getEditSessionFactory().getEditSession(weWorld, -1);
		AffineTransform transform = new AffineTransform();

		//{ // Uncomment this if you want to rotate the schematic
//		    transform = transform.rotateY(90); // E.g. rotate 90
//		    extent = new BlockTransformExtent(clipboard, transform, worldData.getBlockRegistry());
		//}

		ForwardExtentCopy copy = new ForwardExtentCopy(clipboard, clipboard.getRegion(), clipboard.getOrigin(), extent, to);
		if (!transform.isIdentity()) copy.setTransform(transform);
		Operations.completeLegacy(copy);
		extent.flushQueue();
		
	}	
	
}
