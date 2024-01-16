package org.godotengine.plugin.android.template;

import android.text.Layout;
import android.text.style.AlignmentSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;

import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.RenderProps;
import io.noties.markwon.html.HtmlTag;
import io.noties.markwon.html.tag.SimpleTagHandler;

public class AlignTagHandler extends SimpleTagHandler {

    @Nullable
    @Override
    public Object getSpans(
            @NonNull MarkwonConfiguration configuration,
            @NonNull RenderProps renderProps,
            @NonNull HtmlTag tag) {

        final Layout.Alignment alignment;

        // html attribute without value, <align center></align>
        if (tag.attributes().containsKey("center")) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (tag.attributes().containsKey("end")) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            // empty value or any other will make regular alignment
            alignment = Layout.Alignment.ALIGN_NORMAL;
        }

        return new AlignmentSpan.Standard(alignment);
    }

    @NonNull
    @Override
    public Collection<String> supportedTags() {
        return Collections.singleton("align");
    }
}