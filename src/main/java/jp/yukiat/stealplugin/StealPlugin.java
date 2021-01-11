package jp.yukiat.stealplugin;

import jp.yukiat.stealplugin.commands.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

import java.util.*;

public final class StealPlugin extends JavaPlugin
{

    private static StealPlugin plugin;
    private Map<String, TextureData> dataMap = new HashMap<>();

    public static StealPlugin getPlugin()
    {
        return plugin;
    }

    @Override
    public void onEnable()
    {
        plugin = this;
        dataMap.put("toshi", new TextureData("ewogICJ0aW1lc3RhbXAiIDogMTYxMDMwNzc5NzM4NiwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjc1MzQ1MzM0NWRjNzY1MmVjZTBiNzc4Mjc2OGVkMmY2Zjg4NjNhOWU1MzUyMDFhODUzNTUyMjBmOWY5MjBjIgogICAgfQogIH0KfQ==", "kyPGlJ1i3wTBBvMnoNRze73wgf2ylxLWIzHEJhs7bugFrtWX+3bJdgfN/tC5s3dDMJ6yIcgjvVu7utkILC0M0Q9vYPjDrm1w3I/FmOWeQ64Q1hlmISk7AgAG2JaSS9EzLSVlvJLvdPogdgHwHe0dDkV31a1WCWUpBRDhYcYSj3oMgD6aw9Y03XVScsYzMZKZQG+GKD1EwY0RM0xvDLDmO3FVPDYCqpxzut3JEV/o6kL2XEvYa5H+5iEOuBcKoVnBDaUMIHExAoRtnfyNzZIfc2wK+ytHn1pY7vZPdHsEbKAXIxb8w93S/rFhPOzGzujOa0e+s6VqyfCsjRhHBp+Xj1COlQqAhObOS4shtzTs4vFsZKtDm+bQrHegCuEIflh2+ChqQ92BY+FbHz1La0OiyXmuGeqeSh9aSgr8Qxp5fS80IHVA9JLgRCG8mRY14c+Hwc08+ZE7l6RHLSRQtdU2cZUBqxw4RRCek+00q6kXyhIN2BMtN8zl6GD0t7JZG6F475FoisLnZpnsmEoW+mvkwzpKIg0SmZAs7nFpd9t9dSYhE/E36M+ccWoywXGoN6v3mAMcgO+iY2J7aq+wswNWx/jZi5vKxKP9tN3OXCEn/7D3uNDmLZy30YTtM+u2AVT8pqAeo1Mr3BWaWQfeRkAaxyjUZC9F0GCAcJHmLRqyXac="));
        dataMap.put("dhiguda", new TextureData("ewogICJ0aW1lc3RhbXAiIDogMTYxMDMwNzcwOTg1MywKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjdkZmFkNTZiMmQzNGM3NThlNmIxYzNiMTA2NzYwNjkwMWZiNDU0ODhjODgzOWE0YzU1ZWNlMGY0ZjYyYTJlMiIKICAgIH0KICB9Cn0=", "I2cD3Zhfg4E/LrOca+ETn9zEh2K0vfo5Ch0GXQbRqOh6kDXylhZU8nrJZ5I06S6fH9Tc8bN4Ext8Wr4+pWo6zhKw8OXdXPhOVu8J+EUwznQ7LrpHoy3I1qc/dIQGX92WumhKWr1MZrde13hxwjsJFfAUTSGgVHOzJGXFc/8qa658WKlhlPQ6FwdTAYDJ4ySNjJX8OQPytirUXHaTPhGg4jPHTdh0XQpoAv1+AZVbdAwe3cMIjmfq8nXVJXblHgqU6k2Xxb19VMoWqlfBT6xnxc3akb0KDVlvwxrw8Qwy3WAQBiJG6YAduSDd73D+9+XTSytpmSxK6xP3ul03leQDf9/yK0M9LIF9T6Z5+Qow6sMEqdpqXHmGjQNbV40ekGk3ztHsH2aGNIVpFhHeTLUbAJApwyARhdyiXhzb/+aTw2UGqxrdwbIFT/pDJ/sHfmM8cPcfVIJko4V43nhGvkOpM1m+3KGv12oZKUEOGxBcRFQK/H0eR0L56gsyaaR8HowVstN1Pqh4wIVaOF4yctnO+4p6Z3vRO6LZh825x5H+pyt82/o4w6J308wfWu8Eeg9dddieZEzkexC4b7gJD224JvEIiAQW7EqwDWYG+Pa916HVJtaFyB8TPmF/OjtL53B0xhRxx3EvY05GLwpW6L0Dd++w4Sk/FkrxAIqAirPA4qQ="));
        Bukkit.getPluginCommand("skull").setExecutor(new Skull());
        Bukkit.getPluginCommand("change").setExecutor(new ChangeSkin());


    }

    public Map<String, TextureData> getDataMap()
    {
        return dataMap;
    }

    @Override
    public void onDisable()
    {
    }

    public static class TextureData
    {
        private String value;
        private String signature;

        public TextureData(String value, String signature)
        {
            this.value = value;
            this.signature = signature;
        }

        public String getValue()
        {
            return value;
        }

        public String getSignature()
        {
            return signature;
        }
    }

}

