package main;

import lua.Parser;
import main.Commands.AdministrativeCommands;
import main.Commands.CommunicationCommands;
import main.Commands.InteractionCommands;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import world.World;
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
		
		if (!unregisteredCommand(event, channel, words))
		{
			if (!World.registered(id))
			{
				channel.sendMessage("Please register first with `> register`!").queue();
				return;
			}
			else
			{
				if (!registeredCommand(event, channel, words))
					channel.sendMessage("You don't know what that means").queue();
			}
		}
		
		return;
	}
	
	/**
	 * Tries to see if the user ran a command which does not require
	 * registration.
	 * 
	 * @param event		The message event.
	 * @param channel	The channel the message was sent in.
	 * @param message	The message event.
	 * @param words		The preparsed word list.
	 * @return			True if a command was run, false otherwise.
	 */
	private static boolean unregisteredCommand(MessageReceivedEvent event,
												MessageChannel channel, 
												String[] words)
	{
		String id = event.getAuthor().getId();
		
		switch(words[0])
		{
			case "ping":
				channel.sendMessage("pong!").queue();
				return true;
				
			case "register":
				AdministrativeCommands.register(id, words, channel);
				return true;
				
			default:
				return false;
		}
	}
	
	/**
	 * Tries to see if the user ran a command which requires registration.
	 * 
	 * @param event		The message event.
	 * @param channel	The channel the message was sent in.
	 * @param message	The message event.
	 * @param words		The preparsed word list.
	 * @return			True if a command was run, false otherwise.
	 */
	private static boolean registeredCommand(MessageReceivedEvent event, 
											  MessageChannel channel,
											  String[] words)
	{
		String id = event.getAuthor().getId();
		
		switch(words[0])
		{
			case "look":
				InteractionCommands.look(id, channel);
				return true;

			case "whisper":
				CommunicationCommands.whisper(id, words, channel);
				return true;
				
			case "pickup":
				InteractionCommands.pickup(id, words, channel);
				return true;
				
			case "drop":
				InteractionCommands.drop(id, words, channel);
				return true;
			case "inventory":
				InteractionCommands.viewInventory(id, channel);
				return true;
			case "go":
			case "goto":
				InteractionCommands.go(id, words[1], channel);
				return true;
				
			case "cast":
				Parser.run(Parser.extractCode(event.getMessage().getContentRaw()));
				return true;
				
			default:
				System.out.println("Warning: Unknown comand header " + words[0]);
				return false;
		}
	}
}
