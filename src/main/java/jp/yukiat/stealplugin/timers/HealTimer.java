package jp.yukiat.stealplugin.timers;

import jp.yukiat.stealplugin.StealPlugin;
import jp.yukiat.stealplugin.config.Skin;
import jp.yukiat.stealplugin.config.SkinContainer;
import jp.yukiat.stealplugin.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class HealTimer extends BukkitRunnable
{

    public static void heal(UUID uuid)
    {
        if (Bukkit.getPlayer(uuid) == null)
            return;
        if (!StealPlugin.getPlugin().stealed.contains(uuid))
            return;

        Player player = Bukkit.getPlayer(uuid);
        Optional<MetadataValue> stealed = PlayerUtil.getMetaData(player, "order");

        if (!stealed.isPresent())
            return;

        int order = stealed.get().asInt();

        order--;

        if (order >= 0)
        {
            Skin data = SkinContainer.getSkinByOrder(order);
            if (data != null)
                PlayerUtil.setSkin(player, data);
            else
                PlayerUtil.setDefaultSkinAsync(player);
            player.getWorld().spawnParticle(
                    Particle.COMPOSTER,
                    player.getLocation().add(0, 1, 0),
                    10,
                    0.3,
                    0.3,
                    0.3,
                    0
            );
            PlayerUtil.setMetaData(player, "order", order);
            return;
        }
        StealPlugin.getPlugin().stealed.remove(uuid);

    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void run()
    {
        ArrayList<UUID> remove = new ArrayList<>();
        StealPlugin.getPlugin().stealed.forEach(uuid -> {
            if (Bukkit.getPlayer(uuid) == null)
            {
                remove.add(uuid);
                return;
            }

            Player player = Bukkit.getPlayer(uuid);
            Optional<MetadataValue> stealed = PlayerUtil.getMetaData(player, "order");

            if (!stealed.isPresent())
            {
                remove.add(uuid);
                return;
            }

            int order = stealed.get().asInt();

            order--;

            if (order >= 0)
            {
                Skin data = SkinContainer.getSkinByOrder(order);
                if (data != null)
                    PlayerUtil.setSkin(player, data);
                else
                    PlayerUtil.setDefaultSkinAsync(player);
                player.getWorld().spawnParticle(
                        Particle.COMPOSTER,
                        player.getLocation().add(0, 1, 0),
                        10,
                        0.3,
                        0.3,
                        0.3,
                        0
                );
                PlayerUtil.setMetaData(player, "order", order);
                return;
            }

            remove.add(uuid);
        });

        remove.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null)
                PlayerUtil.removeMetaData(player, "order");
            StealPlugin.getPlugin().stealed.remove(uuid);
        });
    }


}
