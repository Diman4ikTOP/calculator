package tg.outfirstplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import tg.outfirstplugin.command.Command;

import java.lang.reflect.InvocationTargetException;

public final class Calculator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Успеный запуск");
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Не работает плагин");
    }
    private void registerCommands() {
        int amount = 0;
        final String packet = getClass().getPackageName();
        for (Class<? extends Command> clazz : new Reflections(
                packet + ".command").getSubTypesOf(Command.class)
        ) {
            try {
                final Command command = clazz.getDeclaredConstructor().newInstance();
                this.getServer().getCommandMap().register(
                        command.getLabel(), this.getName(), command);
                amount += 1;
            } catch (NoSuchMethodException | InvocationTargetException
                     | InstantiationException | IllegalAccessException exception
            ) {
                this.getSLF4JLogger().error("[{}] Failed to load commands.",
                        exception.getClass().getName(), exception
                );
                this.getServer().getPluginManager().disablePlugin(this);
            }
        } this.getSLF4JLogger().info("Successfully loaded {} commands.", amount);
    }
}
