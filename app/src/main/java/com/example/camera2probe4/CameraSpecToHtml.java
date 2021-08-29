package com.example.camera2probe4;

import android.content.Context;
import android.content.res.Resources;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

public class CameraSpecToHtml {
    static String unencodedHtml = "";

    final String STYLE_OPEN_POSITIVE = "<font style=\"color:#00aa00;\">";
    final String STYLE_OPEN_NEGATIVE = "<font style=\"color:#990000;\">";
    final String STYLE_CLOSE_NEGAPOSI = "</font><br style=\"clear:both;\">";
    final String FONT_CHECK = "<div style=\"float:left;width:20px;color:#00aa00;\">&#x2713; </div>";
    final String FONT_CROSS = "<div style=\"float:left;width:20px;color:#990000;\">&#x2717; </div>";

    final String HTML_OPEN_HEAD = "<!DOCTYPE html><html><head><title>";
    final String HTML_OPEN_BODY = "</title></head><body>";
    final String HTML_CLOSE_BODY = "</body></html>";

    final String HTML_OPEN_TITLE = "<br><b>";
    final String HTML_CLOSE_TITLE = "</b><br>";
    final String HTML_BREAK_LINE = "<br>";

    final String STR_COLON = ": ";

    private Context thisContext;

    public CameraSpecToHtml (Context context) {
        thisContext = context;
    }

    public String getUnencodedHtml() {
        if (unencodedHtml == null) unencodedHtml = "";
        return unencodedHtml;
    }

    //     public void setUnencodedHtml(List<Triple<String, String, Integer>> specs) {
    public void setUnencodedHtml(List<CameraSpecResult> specs) {
        unencodedHtml = HTML_OPEN_HEAD + thisContext.getString(R.string.app_name) + HTML_OPEN_BODY;
        specs.forEach( p -> {
            if (p.first().equals(CameraSpec.KEY_TITLE)) {
                unencodedHtml += HTML_OPEN_TITLE + p.second() + HTML_CLOSE_TITLE;
                return;
            }
            if (p.first().equals(CameraSpec.KEY_LOGICAL) || p.first().equals(CameraSpec.KEY_PHYSICAL)) {
                unencodedHtml += HTML_OPEN_TITLE + p.first() + STR_COLON + p.second() + HTML_CLOSE_TITLE;
                return;
            }
            if (p.third().equals(CameraSpec.NONE)) {
                unencodedHtml += p.first() + ": " + p.second() + HTML_BREAK_LINE;
                return;
            }
            if (p.third().equals(CameraSpec.CROSS)) {
                unencodedHtml += FONT_CROSS + STYLE_OPEN_NEGATIVE + p.first() + STYLE_CLOSE_NEGAPOSI;
                return;
            }
            if (p.third().equals(CameraSpec.CHECK)) {
                unencodedHtml += FONT_CHECK + STYLE_OPEN_POSITIVE + p.first() + STYLE_CLOSE_NEGAPOSI;
                return;
            }
        });
        unencodedHtml += HTML_CLOSE_BODY;
    }


}
