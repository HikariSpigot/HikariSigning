package lithium.hikarisigning;

import lithium.hikarisigning.command.AbstractCommand;
import lithium.hikarisigning.command.SignCommand;
import lithium.hikarisigning.command.UnsignCommand;
import lithium.hikarisigning.configuration.PluginConfiguration;
import lithium.hikarisigning.utils.ColorUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class HikariSigning extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginConfiguration configuration = new PluginConfiguration(this);
        configuration.load();

        SignManager signer = new SignManager(configuration.getFormat());

        AbstractCommand signCommand = new SignCommand(
                configuration.getPermission("sign"),
                ColorUtils.translateColorCodes(configuration.getMessage("no_permission")),
                configuration, signer);
        AbstractCommand unsignCommand = new UnsignCommand(
                configuration.getPermission("unsign"),
                ColorUtils.translateColorCodes(configuration.getMessage("no_permission")),
                configuration, signer);

        getCommand("sign").setExecutor(signCommand);
        getCommand("unsign").setExecutor(unsignCommand);


    }
}
