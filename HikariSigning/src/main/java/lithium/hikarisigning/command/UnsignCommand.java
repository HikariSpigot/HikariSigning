package lithium.hikarisigning.command;

import lithium.hikarisigning.SignManager;
import lithium.hikarisigning.configuration.PluginConfiguration;
import lithium.hikarisigning.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnsignCommand extends AbstractCommand {


    private final PluginConfiguration configuration;
    private final SignManager signer;

    public UnsignCommand(String permission, String noPermissionMessage, PluginConfiguration configuration,
                         SignManager signer) {
        super(permission, noPermissionMessage);

        this.configuration = configuration;
        this.signer = signer;
    }

    @Override
    protected void run(Player player, String[] args) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (signer.isValid(item)) {
            player.sendMessage(ColorUtils.translateColorCodes(configuration.getMessage("no_item_in_hand")));
            return;
        }
        if (!configuration.isAllowed("unsign")) {
            player.sendMessage(ColorUtils.translateColorCodes(configuration.getMessage("unsign_not_allowed")));
            return;
        }


        if (!signer.isSigned(item)) {
            player.sendMessage(ColorUtils.translateColorCodes(configuration.getMessage("item_not_signed")));
            return;
        }

        signer.unsign(item);
        player.sendMessage(ColorUtils.translateColorCodes(configuration.getMessage("successfully_unsigned")));
    }

}
