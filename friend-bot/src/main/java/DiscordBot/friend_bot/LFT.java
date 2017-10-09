package DiscordBot.friend_bot;

import net.dv8tion.jda.core.entities.User;

public class LFT 
{
	private String game;
	private String rank;
	private String notes;
	private User user;
	
	public LFT(String game, String rank, String notes, User user)
	{
		this.game = game;
		this.rank = rank;
		this.notes = notes;
		this.user = user;
	}

	public String LFTtoString()
	{
		return "";
	}
	
	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
