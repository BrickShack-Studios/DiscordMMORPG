package main;

import entity.Player;
import lua.Parser;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import world.World;

public class MainListener extends ListenerAdapter
{
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
	
	private void parseMessage(MessageReceivedEvent event, MessageChannel channel, Message message, String content)
	{
		String id = event.getAuthor().getId();
		String username = event.getAuthor().getName();
		
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
				
				World.lookupPlayer(id).look(channel);
				break;
				
			case "go":
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
