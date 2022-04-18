package com.carolinarollergirls.scoreboard.core.admin;

import com.carolinarollergirls.scoreboard.core.interfaces.Clock;
import com.carolinarollergirls.scoreboard.core.interfaces.Game;
import com.carolinarollergirls.scoreboard.core.interfaces.ScoreBoard;
import com.carolinarollergirls.scoreboard.core.interfaces.Settings;
import com.carolinarollergirls.scoreboard.core.interfaces.Team;
import com.carolinarollergirls.scoreboard.event.ScoreBoardEventProvider;
import com.carolinarollergirls.scoreboard.event.ScoreBoardEventProviderImpl;
import com.carolinarollergirls.scoreboard.utils.ValWithId;

public class SettingsImpl extends ScoreBoardEventProviderImpl<Settings> implements Settings {
    public SettingsImpl(ScoreBoard s) {
        super(s, "", ScoreBoard.SETTINGS);
        addProperties(props);
        setDefaults();
    }
    public SettingsImpl(SettingsImpl cloned, ScoreBoardEventProvider root) { super(cloned, root); }

    @Override
    public ScoreBoardEventProvider clone(ScoreBoardEventProvider root) {
        return new SettingsImpl(this, root);
    }

    private void setDefaults() {
        set("Overlay.Interactive.Clock", "On");
        set("Overlay.Interactive.Score", "On");
        set("Overlay.Interactive.ShowJammers", "On");
        set("Overlay.Interactive.ShowLineups", "On");
        set("Overlay.Interactive.ShowAllNames", "Off");
        set("ScoreBoard.Operator_Default.StartStopButtons", "false");
        set("ScoreBoard.Operator_Default.TabBar", "true");
        set("ScoreBoard.Operator_Default.ReplaceButton", "false");
        set(ScoreBoard.SETTING_USE_LT, "false");
        set(ScoreBoard.SETTING_STATSBOOK_INPUT, "");
        set(ScoreBoard.SETTING_AUTO_START, "");
        set(ScoreBoard.SETTING_AUTO_START_BUFFER, "0:02");
        set(ScoreBoard.SETTING_AUTO_END_JAM, "true");
        set(ScoreBoard.SETTING_AUTO_END_TTO, "false");
        set(ScoreBoard.SETTING_CLOCK_AFTER_TIMEOUT, Clock.ID_LINEUP);
        set(Clock.SETTING_SYNC, "true");
        set(Team.SETTING_DISPLAY_NAME, Team.OPTION_LEAGUE_NAME);
        set(Game.SETTING_DEFAULT_NAME_FORMAT, "%G %d %1 vs. %2 (%s: %S)");
        set("ScoreBoard.Intermission.PreGame", "Time To Derby");
        set("ScoreBoard.Intermission.Intermission", "Intermission");
        set("ScoreBoard.Intermission.Unofficial", "Unofficial Score");
        set("ScoreBoard.Intermission.Official", "Final Score");
        set("ScoreBoard.Preview_BoxStyle", "box_flat_bright");
        set("ScoreBoard.Preview_CurrentView", "scoreboard");
        set("ScoreBoard.Preview_CustomHtml", "/customhtml/fullscreen/example.html");
        set("ScoreBoard.Preview_Image", "/images/fullscreen/test-image.png");
        set("ScoreBoard.Preview_HideLogos", "false");
        set("ScoreBoard.Preview_SidePadding", "");
        set("ScoreBoard.Preview_SwapTeams", "false");
        set("ScoreBoard.Preview_Video", "/videos/fullscreen/test-video.webm");
        set("ScoreBoard.View_BoxStyle", "box_flat_bright");
        set("ScoreBoard.View_CurrentView", "scoreboard");
        set("ScoreBoard.View_CustomHtml", "/customhtml/fullscreen/example.html");
        set("ScoreBoard.View_HideLogos", "false");
        set("ScoreBoard.View_Image", "/images/fullscreen/test-image.png");
        set("ScoreBoard.View_SidePadding", "");
        set("ScoreBoard.View_SwapTeams", "false");
        set("ScoreBoard.View_Video", "/videos/fullscreen/test-video.webm");
    }

    @Override
    public String get(String k) {
        synchronized (coreLock) {
            if (get(SETTING, k) == null) { return null; }
            return get(SETTING, k).getValue();
        }
    }
    @Override
    public void set(String k, String v) {
        synchronized (coreLock) {
            if (v == null) {
                remove(SETTING, k);
            } else {
                add(SETTING, new ValWithId(k, v));
            }
        }
    }
}
