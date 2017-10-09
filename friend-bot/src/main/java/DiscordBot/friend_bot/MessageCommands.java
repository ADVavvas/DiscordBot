package DiscordBot.friend_bot;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.login.LoginException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Hello world!
 * https://discordapp.com/oauth2/authorize?&client_id=366643359126781953&scope=bot&permissions=0
 *
 */
public class MessageCommands extends ListenerAdapter
{
	public String botPrefix = ";";
	public TeamFinder tf = new TeamFinder();
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
    		JDA jdaBot = new JDABuilder(AccountType.BOT).setToken("MzY2NjQzMzU5MTI2NzgxOTUz.DLwARg.8iABZ8kAu6osNlxcamPJm13x0VU").buildBlocking();
    		jdaBot.addEventListener(new MessageCommands());
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
    		Message messageObj = event.getMessage();
    		MessageChannel msgChnl = event.getChannel();
    		User msgUser = event.getAuthor();
    		String msg = messageObj.getContent().toLowerCase();
    		String msgAll = messageObj.getContent().toLowerCase();
    		
    		
//	      if (event.isFromType(ChannelType.TEXT))
//    	  {
//            System.out.printf("[%s][%s] %#s: %s%n", event.getGuild().getName(),
//            event.getChannel().getName(), event.getAuthor(), event.getMessage().getContent());
//        }
//        else
//        {
//            System.out.printf("[PM] %#s: %s%n", event.getAuthor(), event.getMessage().getContent());
//        }
    		
    		if(msg.startsWith(botPrefix))
    		{
    			
    			msgAll = msgAll.substring(1);
    			msg = msgAll;
    			if(msg.indexOf(' ') != -1)
    			{
    				msg = msg.substring(0, msg.indexOf(' '));
    			}
    			if(msg.equals("hello"))
        		{
        			msgChnl.sendMessage("Hello blyat " + msgUser.getAsMention() + ", nice to meet you!").queue();
        		}
    			else if(msg.equals("fuck"))
    			{
    				if(!messageObj.getMentionedUsers().isEmpty())
    				{
    					msgChnl.sendMessage("Fuck you " + messageObj.getMentionedUsers().get(0).getAsMention() + "!").queue();
    				}
    				else
    				{
    					msgChnl.sendMessage("Fuck you!").queue();
    				}
    			}
    			else if(msg.equals("help") || msg.equals("commands"))
    			{
    				msgChnl.sendMessage("This command has not been setup yet :( Nothing to do here *flies away*").queue();
    			}
    			else if(msg.equals("reddit"))
    			{
				msgChnl.sendMessage(formatURL("https://www.reddit.com/r/random/")).queue();
    			}
    			else if(msg.equals("imgur"))
    			{
    				msgChnl.sendMessage(formatURL("https://imgur.com/gallery/random")).queue();
    			}
    			else if(msg.equals("wiki") || msg.equals("wikipedia"))
    			{
    				msgChnl.sendMessage(formatURL("https://en.wikipedia.org/wiki/Special:Random")).queue();
    			}
    			else if(msg.equals("googlefull"))
    			{
    				String url = "http://www.google.com/search?q=";
    				msg = msgAll.substring(msgAll.indexOf(' ')+1);
    				String[] query = msg.split("\\s+");
    				for(int i=0;i<query.length;i++)
    				{
    					url +=query[i] + "+";
    				}
    				if(query.length>=1)
    				{
    					url = url.substring(0, url.length() - 1);
    				}
    				
    				msgChnl.sendMessage(url).queue();
    			}
    			else if(msg.equals("google"))
    			{
    				String url = "http://www.google.com/search?q=";
    				msg = msgAll.substring(msgAll.indexOf(' ')+1);
    				String[] query = msg.split("\\s+");
    				for(int i=0;i<query.length;i++)
    				{
    					url +=query[i] + "+";
    				}
    				if(query.length>=1)
    				{
    					url = url.substring(0, url.length() - 1);
    				}
    				url+="&btnI";
    				msgChnl.sendMessage(url).queue();
    			}
    			else if(msg.equals("ud") || msg.equals("urban"))
    			{
    				Random ran = new Random();
    				msgChnl.sendMessage("http://www.urbandictionary.com/random.php?page=" + ran.nextInt(100000)).queue();
    			}
    			else if(msg.equals("number"))
    			{
    				Random ran = new Random();
    				String url = "http://numbersapi.com/"+ran.nextInt(1000);
    				try {
						Document html = Jsoup.connect(url).get();
						msgChnl.sendMessage(html.body().ownText()).queue();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    			}
    			else if(msg.equals("lft"))
    			{
    				String game = null;
    				String rank = null;
    				String notes = "";
    				String[] query = msgAll.split("\\s+");
    				
 
    				if(query.length == 1)
    				{
    					msgChnl.sendMessage("You have to enter a valid format:" + botPrefix + "lft <game> <rank> <notes>").queue();
    					return;
    				}
    				else if(query.length == 2)
    				{
    					game = query[1];
    					rank = "no rank";
    					notes = "no notes";
    				}
    				else if(query.length == 3)
    				{
    					game = query[1];
    					rank = query[2];
    					notes = "no notes";
    				}
    				else if(query.length >=4)
    				{
    					game = query[1];
    					rank = query[2];
    					for(int i=3;i<query.length;i++)
    					{
    						notes += query[i] + " ";
    					}
    				}
    				
    				LFT 	entry = new LFT(game, rank, notes, msgUser);
    				tf.addEntry(entry);
    				
    				tf.listAll(msgChnl);
    			}
    			
    		}
    		
    }
    
 
    public String formatURL(String baseURL)
    {
    		try 
    		{
			Document html = Jsoup.connect(baseURL).get();
			return(html.baseUri());
		} 
    		catch (IOException e) 
    		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
    }

    
}
