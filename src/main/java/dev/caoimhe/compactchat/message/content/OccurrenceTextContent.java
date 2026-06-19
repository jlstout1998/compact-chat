package dev.caoimhe.compactchat.message.content;

import dev.caoimhe.compactchat.config.Configuration;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.Component;

import java.util.Optional;

public class OccurrenceTextContent implements PlainTextContents {
    private final int occurrences;

    public OccurrenceTextContent(final int occurrences) {
        this.occurrences = occurrences;
    }

    public static MutableComponent create(final int occurrences) {
        return MutableComponent.create(new OccurrenceTextContent(occurrences));
    }

    @Override
    public String text() {
        if (this.occurrences >= Configuration.instance().maximumOccurrences) {
            return " (" + Configuration.instance().maximumOccurrences + "+)";
        }

        return " (" + this.occurrences + ")";
    }

    @Override
    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> visitor, Style style) {
        return visitor.accept(style, this.text());
    }

    @Override
    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> visitor) {
        return visitor.accept(this.text());
    }

    @Override
    public String toString() {
        return "compactChatTextOccurrences{occurrences = " + this.occurrences + "}";
    }
}
