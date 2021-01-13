package jp.yukiat.stealplugin.config;

import com.google.gson.*;
import com.google.gson.reflect.*;
import jp.yukiat.stealplugin.*;
import org.bukkit.*;

import java.util.*;

public class Rares
{
    private static ArrayList<RareItemEntry> rares = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static void loadRare()
    {
        rares = (ArrayList<RareItemEntry>) StealPlugin.config.getObject("rares", ArrayList.class);
        rares = new Gson().fromJson(new Gson().toJson(rares), new TypeToken<ArrayList<RareItemEntry>>()
        {
        }.getType());
    }

    public static SpecialCore selectAsSpecial()
    {
        SpecialCore.Item item = select();
        if (item == null)
            return null;
        SpecialCore core = new SpecialCore();
        core.items = new ArrayList<>();
        core.items.add(item);
        return core;
    }

    public static SpecialCore.Item select()
    {
        for (RareItemEntry ent: rares)
        {
            if (ent.chance <= 0)
                continue;
            if (new Random().nextInt(Math.toIntExact(ent.chance)) + 1 == 1)
                return ent.item;
        }
        return null;
    }

}