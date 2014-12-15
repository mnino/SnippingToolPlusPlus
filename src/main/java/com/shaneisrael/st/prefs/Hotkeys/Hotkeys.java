package com.shaneisrael.st.prefs.Hotkeys;

import java.awt.event.KeyEvent;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;
import com.melloware.jintellitype.JIntellitypeException;
import com.shaneisrael.st.prefs.Preferences;

public class Hotkeys
{
    
    public static final int NO_HOTKEY = 11111;
    
    
    public void registerHotkeys()
    {
        int[] hotkeyMods1 = Preferences.getInstance().getFirstHotkeyMods();
        int[] hotkeyMods2 = Preferences.getInstance().getSecondHotkeyMods();
        int[] hotkeyCodes = Preferences.getInstance().getHotkeyCodes();
        JIntellitype keyhook = null;

        try
        {
            keyhook = JIntellitype.getInstance();
        }
        catch (JIntellitypeException ex)
        {
            ex.printStackTrace();
        }
        if (keyhook != null)
        {
            for (int i = 1; i < hotkeyMods1.length; i++)
                keyhook.unregisterHotKey(i);
            for (int i = 1; i < hotkeyMods1.length; i++)
                if (hotkeyCodes[i - 1] != Hotkeys.NO_HOTKEY)
                    keyhook.registerHotKey(i, hotkeyMods1[i - 1] + hotkeyMods2[i - 1], hotkeyCodes[i - 1]);
        }

    }
    
    public static int getModBoxIndex(int mod)
    {
        switch (mod)
        {
        case 0:
            return 0;
        case JIntellitypeConstants.MOD_CONTROL:
            return 1;
        case JIntellitypeConstants.MOD_ALT:
            return 2;
        case JIntellitypeConstants.MOD_SHIFT:
            return 3;
        case JIntellitypeConstants.MOD_WIN:
            return 4;
        }
        return 0;
    }

    public static int getHotkeyMod(int index)
    {
        switch (index)
        {
        case 0:
            return 0;
        case 1:
            return JIntellitypeConstants.MOD_CONTROL;
        case 2:
            return JIntellitypeConstants.MOD_ALT;
        case 3:
            return JIntellitypeConstants.MOD_SHIFT;
        case 4:
            return JIntellitypeConstants.MOD_WIN;
        }
        return 0;
    }

    public static String getKeyCharacter(int keyCode)
    {
        if (keyCode == NO_HOTKEY || keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_ENTER
            || keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_ALT || keyCode == KeyEvent.VK_SHIFT
            || keyCode == KeyEvent.VK_WINDOWS)
            return "NONE";
        else
            return KeyEvent.getKeyText(keyCode);
    }
}
