package com.abubusoft.kripton.quickstart.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

import java.net.URL;

@BindType
public class Photo {

    @BindColumn(foreignKey = Album.class )
    public long albumId;

    public long id;

    public String title;

    public URL url;

    public URL thumbnailUrl;
}
