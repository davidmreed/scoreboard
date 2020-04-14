package com.carolinarollergirls.scoreboard.core;
/**
 * Copyright (C) 2008-2012 Mr Temper <MrTemper@CarolinaRollergirls.com>
 *
 * This file is part of the Carolina Rollergirls (CRG) ScoreBoard.
 * The CRG ScoreBoard is licensed under either the GNU General Public
 * License version 3 (or later), or the Apache License 2.0, at your option.
 * See the file COPYING for details.
 */

import com.carolinarollergirls.scoreboard.event.AddRemoveProperty;
import com.carolinarollergirls.scoreboard.event.PermanentProperty;
import com.carolinarollergirls.scoreboard.event.ScoreBoardEventProvider;

public interface Media extends ScoreBoardEventProvider {
    public MediaFormat getFormat(String format);

    // Deletes a file off disk. True if successful.
    public boolean removeMediaFile(String format, String type, String id);

    public boolean validFileName(String fn);

    AddRemoveProperty<MediaFormat> FORMAT = new AddRemoveProperty<>(MediaFormat.class, "Format");

    public static interface MediaFormat extends ScoreBoardEventProvider {
        public String getFormat();
        public MediaType getType(String type);

        AddRemoveProperty<MediaType> TYPE = new AddRemoveProperty<>(MediaType.class, "Type");
    }

    public static interface MediaType extends ScoreBoardEventProvider {
        public String getFormat();
        public String getType();

        public MediaFile getFile(String id);
        public void addFile(MediaFile file);
        public void removeFile(MediaFile file);

        AddRemoveProperty<MediaFile> FILE = new AddRemoveProperty<>(MediaFile.class, "File");
    }

    public static interface MediaFile extends ScoreBoardEventProvider {
        public String getFormat();
        public String getType();
        @Override
        public String getId();
        public String getName();
        public void setName(String s);
        public String getSrc();

        PermanentProperty<String> SRC = new PermanentProperty<>(String.class, "Src", "");
        PermanentProperty<String> NAME = new PermanentProperty<>(String.class, "Name", "");
    }
}
