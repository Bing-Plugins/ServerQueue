package cn.yistars.queue.api;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.group.ServerGroup;
import net.rfpixel.queue.group.ServerGroupsLoader;

public interface QueueHook {

	  public static String getServer(ProxiedPlayer player,String group) {
	        
          ServerGroup serverGroup = ServerGroupsLoader.get(group);

          Server server = serverGroup.queue(player);
          if (server != null) {
              return server.getProxyName();
          }
          return null;
	  }
	  
	  public static String getQueue(String server) {
		  return ServerGroupsLoader.serverNameGroups.get(server);
	  }
}
