package fr.pumpmyskybukkit.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.pumpmyskybukkit.BukkitIslandManager;
import fr.pumpmyskybukkit.commands.todo.HelpIslandCmd;

public class IslandCommandExecutor implements CommandExecutor, TabCompleter {

	public static final String NO_PERM = "Vous n'avez pas la permission de faire cela !";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if( args.length < 1 ) {
				
				return new GoToIslandCmd().onSubCommand(this,p, cmd);
				
			}else {
				
				String sub = args[0];
				
				for (SubCommandData s : this.subCommandList) {
					
					String subCmd = s.getSubCommand();
					String permission = s.getPermissionNode();
					
					if(sub.equals(subCmd)) {
						
						if(!permission.equals("none") & !p.hasPermission(permission)) {
							// pas la permission
							p.sendMessage(NO_PERM);
							return true;
							
						}else {
							// permission trouvé
							//System.out.println("Executor sub command : " + s.getSubCommandExecutor().getClass().getName());
							return s.execute(this,p, cmd, getArgs(args));
							
						}
						
					}
					
				}
				
				return new HelpIslandCmd().onSubCommand(this,p, cmd);
				
			}
			
		}else {
		
			sender.sendMessage("Vous devez etre joueur pour faire cela !");
			return true;
			
		}
		
	}
	
	public IslandCommandExecutor(BukkitIslandManager manager) {
		
		this.subCommandList = new ArrayList<>();
		this.islandManager = manager;
		
	}
	
	private List<SubCommandData> subCommandList;
	private BukkitIslandManager islandManager;
	
	public void addSubCommand(String sub , String perm , ISubCommand i) {
		
		this.subCommandList.add(new SubCommandData(sub, perm, i));
		
	}
	
	public void addSubCommand(String sub , ISubCommand i) {
		
		this.subCommandList.add(new SubCommandData(sub, i));
		
	}

	public List<SubCommandData> getSubCommandList() {
		return subCommandList;
	}
	
	private static List<String> getArgs(String[] a) {
		
		List<String> l = new ArrayList<>();
		
		for (int i = 1; i < a.length; i++) {
			
			l.add(a[i]);
			
		}
		
		return l;
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if(!(sender instanceof Player))
			return new ArrayList<>();
		
		List<String> subCmdNameList = new ArrayList<>();
		
		for (SubCommandData subcmd : this.subCommandList) {
			
			subCmdNameList.add(subcmd.getSubCommand());
			
		}
		
		if(args.length == 1) {
			
			String arg = args[0];
			
			if(arg.trim().isEmpty()) {
				
				return subCmdNameList;
				
			}else {
				
				for (Iterator<String> iterator = subCmdNameList.iterator(); iterator.hasNext();) {
					
					String string = iterator.next();
					
					if(!string.startsWith(arg)) {
						
						iterator.remove();
						
					}
					
				}
				
				return subCmdNameList;
				
			}
			
		}else if(args.length == 2 & subCmdNameList.contains(args[0])) {			
			
			String subcommand = args[0];
			
			for (SubCommandData subcmd : this.subCommandList) {
				
				if(subcmd.getSubCommand().equalsIgnoreCase(subcommand) & subcmd.getSubCommandExecutor() instanceof TabCompleter) {
					
					return ((TabCompleter) subcmd.getSubCommandExecutor()).onTabComplete(sender, command, alias, args);
					
				}
				
				continue;
				
			}
			
			return new ArrayList<>();
			
		}
		
		return new ArrayList<>();
		
	}

	public BukkitIslandManager getIslandManager() {
		return this.islandManager;
	}
	
}
