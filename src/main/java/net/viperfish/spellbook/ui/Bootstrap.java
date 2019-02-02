package net.viperfish.spellbook.ui;

import com.j256.ormlite.db.H2DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.ItemService;
import net.viperfish.spellbook.core.Spell;
import net.viperfish.spellbook.core.SpellService;
import net.viperfish.spellbook.db.ORMLiteRepository;
import net.viperfish.spellbook.db.SpellOrmLiteRepository;
import net.viperfish.spellbook.task.TaskItemService;
import net.viperfish.spellbook.task.TaskSpellService;

public class Bootstrap {

	private static JdbcConnectionSource initConnection(String path) throws IOException {
		try {
			JdbcConnectionSource conn = new JdbcConnectionSource("jdbc:h2:" + path, "", "",
				new H2DatabaseType());
			return conn;
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	private static Item getItem(Scanner scan) {
		System.out.print("Name:");
		String name = scan.nextLine();
		System.out.print("Amount:");
		Double amount = Double.parseDouble(scan.nextLine());
		System.out.println("Description:");
		String descBuf = scan.nextLine();
		StringBuilder desc = new StringBuilder();
		while (!descBuf.equals(".")) {
			desc.append(descBuf).append("\n");
			descBuf = scan.nextLine();
		}
		return new Item(name, amount, desc.toString());
	}

	private static Spell getSpell(Scanner scan) {
		System.out.print("Name:");
		String name = scan.nextLine();
		System.out.print("Duration:");
		String duration = scan.nextLine();
		System.out.print("Casting Time:");
		String castingTime = scan.nextLine();
		System.out.print("Required Items:");
		Spell result = new Spell(name, duration, castingTime, "");
		String items = scan.nextLine();
		for (String i : items.split(",")) {
			String[] parts = i.split(":");
			ItemRequirement req = new ItemRequirement(Double.parseDouble(parts[1]));
			req.setSpell(result);
			Item associated = new Item();
			associated.setId(Long.parseLong(parts[0]));
			req.setItem(associated);
			result.getRequirements().add(req);
		}
		System.out.println("Description:");
		String descBuf = scan.nextLine();
		StringBuilder desc = new StringBuilder();
		while (!descBuf.equals(".")) {
			desc.append(descBuf).append("\n");
			descBuf = scan.nextLine();
		}
		result.setDescription(desc.toString());
		return result;
	}


	public static void main(String[] args) {
		System.setProperty("com.j256.ormlite.logger.level", "ERROR");
		JdbcConnectionSource connectionSource = null;
		ORMLiteRepository<Long, Spell> spellRepo = null;
		ORMLiteRepository<Long, Item> itemRepo = null;
		SpellService spellService = null;
		ItemService itemService = null;
		try {
			connectionSource = initConnection("./db");
			spellRepo = new SpellOrmLiteRepository(connectionSource, Spell.class);
			itemRepo = new ORMLiteRepository<>(connectionSource, Item.class);
			spellRepo.init();
			itemRepo.init();
			spellService = new TaskSpellService(spellRepo, itemRepo);
			itemService = new TaskItemService(itemRepo);
			try (Scanner input = new Scanner(System.in)) {
				while (true) {
					System.out.print("spellbook>>");
					if (!input.hasNextLine()) {
						continue;
					}
					String commandName = input.nextLine();
					switch (commandName) {
						case "ls": {
							spellService.execGetAll(new ListSpellCallback());
							break;
						}
						case "li": {
							itemService.execGetAll(new ListItemCallback());
							break;
						}
						case "ai": {
							Item item = getItem(input);
							itemService.execPersist(item, new ItemAddedCallback());
							break;
						}
						case "as": {
							Spell sp = getSpell(input);
							spellService.execPersist(sp, new SpellAddedCallback());
							break;
						}
						case "ri": {
							System.out.print("Item ID:");
							Long id = Long.parseLong(input.nextLine());
							itemService.execDelete(id, new RemoveStuffCallback<>());
							break;
						}
						case "rs": {
							System.out.print("Spell ID:");
							Long id = Long.parseLong(input.nextLine());
							spellService.execDelete(id, new RemoveStuffCallback<>());
							break;
						}
						case "cs": {
							spellService.execGetCastable(new ListSpellCallback());
							break;
						}
						case "ca": {
							System.out.print("Item ID:");
							Long id = Long.parseLong(input.nextLine());
							System.out.print("Delta:");
							Double delta = Double.parseDouble(input.nextLine());
							itemService.execChangeAmount(id, delta, new ChangeItemCallback());
							break;
						}
						case "fire": {
							System.out.print("Spell ID:");
							Long id = Long.parseLong(input.nextLine());
							spellService.execSpell(id, new CastSpellCallback());
							break;
						}
						case "quit": {
							return;
						}
						default: {
							if (!commandName.isEmpty()) {
								System.out.println(commandName + " is not a valid command");
							}
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Failed to initiate database connection");
			e.printStackTrace();
		} finally {
			if (spellService != null) {
				spellService.destroy();
			}
			if (itemService != null) {
				itemService.destroy();
			}
			if (connectionSource != null) {
				connectionSource.closeQuietly();
			}
		}
	}

}
