package tg.outfirstplugin.command.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tg.outfirstplugin.command.Command;

import java.util.List;

public class CalculatorCMD extends Command<Player> {
    public CalculatorCMD() {
        super("calculator", "calc, cal", "comandcalcul", "Калькулятор шитает всё");
    }

    @Override
    protected void execute(@NotNull Player player, String @NotNull [] args) {
            if (args.length == 0) {
                player.sendMessage("Вы не вели команду");
                return;
            }
            if (args[0].equals("help")) {
                player.sendMessage("/calculator <Цифра> пробел после цифры | + | - | * | / | % <Цифра>");
            }
            if (!parseInt(args[0]) || !parseInt(args[2])) {
                player.sendMessage("Вы не вели команду");
                return;
            }
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[2]);
            if  (args[1].equals("+")) {
                player.sendMessage("Ответ: "+String.valueOf(a + b));
            }
            else if (args[1].equals("-")) {
                player.sendMessage("Ответ: "+String.valueOf(a - b));
            }
            else if (args[1].equals("*")) {
                player.sendMessage("Ответ: "+String.valueOf(a * b));
            }
            else if (args[1].equals("/")) {
                player.sendMessage("Ответ: "+String.valueOf(a / b));
            }
            else if (args[1].equals("%")) {
                player.sendMessage("Ответ: "+String.valueOf(a % b));
            }

    }
    @Override
    protected @NotNull List<String> complete(@NotNull CommandSender sender, String @NotNull [] args) {
        return List.of();
    }

    private boolean parseInt(@NotNull String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
