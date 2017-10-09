package DiscordBot.friend_bot;
import java.util.*;

import net.dv8tion.jda.core.entities.MessageChannel;
public class TeamFinder 
{
	private LinkedList<LFT> allEntries;
	
	
	public TeamFinder ()
	{
		allEntries = new LinkedList<LFT>();
	}
	
	public LFT[] getGame(String game)
	{
		LFT[] results = new LFT[100000];
		LFT temp;
		int size = 0;
		for(int i=0;i<allEntries.size();i++)
		{
			temp = allEntries.get(i);
			if(temp.getGame().equals(game))
			{
				results[size] = temp;
				size++;
			}
		}

		return results;
	}
	
	public void addEntry(LFT lft)
	{
		allEntries.addFirst(lft);
	}
	
	public void listAll(MessageChannel chan)
	{
		for(int i=0;i<allEntries.size();i++)
		{
			String g = allEntries.get(i).getGame();
			String r = allEntries.get(i).getRank();
			String n = allEntries.get(i).getNotes();
			//String u = allEntries.get(i).getUser().getName() + "#" + allEntries.get(i).getUser().getDiscriminator();
			String u = allEntries.get(i).getUser().getAsMention();
			chan.sendMessage(g + "-" + r + "-" + n + " " + u ).queue();
		}
	}
	private void clearList()
	{
		
	}
}
