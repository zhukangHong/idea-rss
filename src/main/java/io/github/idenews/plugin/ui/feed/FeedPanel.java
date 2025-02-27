package io.github.idenews.plugin.ui.feed;

import io.github.idenews.logic.RSSChannel;
import io.github.idenews.logic.RSSContainer;
import io.github.idenews.logic.RSSItem;
import io.github.idenews.parser.Parser;
import io.github.idenews.plugin.ui.Application;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FeedPanel extends AbstractFeed {

    public FeedPanel(Application application, RSSContainer rssContainer) {
        super(application, getFeedItems(rssContainer));
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Parser parser = new Parser();
        FeedPanel feedPanel = new FeedPanel(null,
                RSSContainer.builder()
                        .channels(Arrays.asList(
                                parser.parse("https://www.iamkb.cn/?feed=rss2")

                        ))
                        .build()
        );

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            frame.add(feedPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static List<RSSItem> getFeedItems(RSSContainer rssContainer) {
        return rssContainer.getChannels()
                .stream()
                .map(RSSChannel::getItems)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(RSSItem::getPubDate).reversed())
                .collect(Collectors.toList());
    }
}
