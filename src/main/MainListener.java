package main;

import entity.Player;
import lua.Parser;
import lua.api.Communication;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import world.World;
import lua.api.Communication;
/**
 * The `MainListener` captures all communication available to the bot.
 * Specifically, it is searching for commands targeted for its parsing.
 */
public class MainListener extends ListenerAdapter
{
	/**
	 * Preliminary filtering of messages. Discards messages from bots,
	 * and messages which don't start with a `>`.
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if (event.getAuthor().isBot())
		{
			// System.out.println("Blocked a message from a bot");
			return;
		}
			
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		String content = message.getContentRaw();
		
		if (content.length() > 0 && content.charAt(0) == '>')
		{
			System.out.println("Received a command: " + content);
			parseMessage(event, channel, message, content);
		}
		else if (event.getChannel().getType() == ChannelType.PRIVATE)
		{
			System.out.println("Received DM from " + 
					event.getAuthor().getName() + ":\n\t" +
					event.getMessage().getContentRaw());
		}
			
		
		return;
	}
	
	/**
	 * Given a message which we know for sure is directed at the bot,
	 * try and figure out which command the user was trying to run.
	 * 
	 * @param event		The main message event.
	 * @param channel	The channel the message came from.
	 * @param message	The message object itself.
	 * @param content	The message body.
	 */
	private void parseMessage(MessageReceivedEvent event, MessageChannel channel, Message message, String content)
	{
		String id = event.getAuthor().getId();
		String username = event.getAuthor().getName();
		
		event.getAuthor().openPrivateChannel().queue((dm) ->
				dm.sendMessage("Text here"));
		
		while (content.length() > 0 && 
				(content.charAt(0) == '>' || content.charAt(0) == ' '))
		{
			content = content.substring(1);
		}
			
		String[] words = content.split(" ");
		
		if (words.length == 0)
		{
			System.out.println("Warning: Empty command");
			return;
		}
			
		
		switch(words[0])
		{
			case "ping":
				channel.sendMessage("pong!").queue();
				break;
				
			case "register":
				World.addPlayer(new Player(username, id), channel);
				break;
				
			case "look":
				if (!World.registered(id))
				{
					channel.sendMessage("Please register first with `> register`!").queue();
					break;
				}
				
				channel.sendMessage(World.findPlayerByID(id).look()).queue();
				break;
				
			case "goto":
				break;
				
			case "whisper":
				if (!World.registered(id))
				{
					channel.sendMessage("Please register first with `> register`!").queue();
					break;
				}
				
				else
				{
					if (words.length <= 2)
					{
						channel.sendMessage("Warning: Not enough information!").queue();
						break;
					}
					
					String targetUser = ""; int numSpaces = 0;					
					for(int i = 1; i < words.length - 1; i++)
					{
						targetUser += words[i];
						if (words[i + 1].charAt(0) == '\"')
							break;
						targetUser += " ";
					}
					
					for(int i = 0; i < targetUser.length(); i++)
						if (targetUser.charAt(i) == ' ')
							numSpaces++;
					
					if (World.findPlayerByName(targetUser) != null)
					{
						Player target = World.findPlayerByName(targetUser);
						String msg = "";
						
						for(int i = 2 + numSpaces; i < words.length; i++)
							msg += words[i] + " ";
						
						new Communication().whisper(target.getID(), username + ": " + msg);
						
					}
					else
						channel.sendMessage("The user \'" + targetUser + "\' doesn't exist! Try entering a message surrounded by \"quotation marks\"").queue();
					
					break;
				}
				
			case "go":
				if (!World.registered(id))
				{
					channel.sendMessage("Please register first with `> register`!").queue();
					break;
				}
				
				if (!World.findPlayerByID(id).move(words[1]))
					channel.sendMessage("You aren't sure where that is.").queue();
				
				break;
				
			case "cast":
				if (!World.registered(id))
				{
					channel.sendMessage("Please register first with `> register`!").queue();
					break;
				}
				Parser.run(Parser.extractCode(content));
				break;
				
			default:
				System.out.println("Warning: Unknown comand header " + words[0]);
				return;
		}
	}
}
