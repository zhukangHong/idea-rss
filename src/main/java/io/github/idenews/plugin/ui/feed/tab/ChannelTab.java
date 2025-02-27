package io.github.idenews.plugin.ui.feed.tab;

import io.github.idenews.logic.RSSChannel;
import io.github.idenews.plugin.ui.Application;
import io.github.idenews.plugin.ui.util.ImagePanel;
import io.github.idenews.plugin.ui.Settings;
import io.github.idenews.plugin.ui.feed.channel_list.ChannelList;
import io.github.idenews.plugin.ui.feed.item.util.RSSTextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChannelTab extends JPanel {
    private final RSSChannel channel;
    private final Application application;

    public ChannelTab(Application application, RSSChannel channel) {
        this.application = application;
        this.channel = channel;
        buildGui();
    }

    private void buildGui() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        JPanel channelInfo = new JPanel();
        channelInfo.setLayout(new BorderLayout());
        channelInfo.add(new ImagePanel(channel.getImage()), BorderLayout.WEST);

        RSSTextPane title = new RSSTextPane(Settings.CHANNEL_NAME, channel.getTitle(),
                "normal", "bold");
        title.setFont(Settings.CHANNEL_NAME);
        channelInfo.add(title, BorderLayout.NORTH);
        RSSTextPane channelDescription = new RSSTextPane(Settings.CHANNEL_NAME,
                channel.getDescription(), "italic", "normal");
        channelInfo.add(channelDescription, BorderLayout.CENTER);

        JPanel button = new JPanel();
        button.setLayout(new BorderLayout());
        JLabel label = new JLabel("...", JLabel.CENTER);
        label.setFont(Settings.CHANNEL_NAME);
        label.setForeground(Settings.FONT_COLOR);
        label.setPreferredSize(Settings.CONTENT_INTERCELL_SPACING);
        button.add(label, BorderLayout.CENTER);
//        button.setBackground(Settings.ITEM_BACKGROUND);
        channelInfo.add(button, BorderLayout.EAST);

        ChannelList.ChannelListMenu channelListMenu = new ChannelList.ChannelListMenu(application, channel);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                channelListMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        info.add(channelInfo);
        mainPanel.add(info, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

}
