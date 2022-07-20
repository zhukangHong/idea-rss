# IntelliJTaoGuBa

<!-- ![Build](https://github.com/mikh-rich-is-team/IntelliJNews/workflows/Build/badge.svg)  -->
<!--  [![Version](https://img.shields.io/jetbrains/plugin/v/17293-intellijnews.svg)](https://plugins.jetbrains.com/plugin/17293-intellijnews)  -->
[![Downloads](https://img.shields.io/jetbrains/plugin/d/17293-intellijnews.svg)](https://github.com/zhukangHong/idea-rss/releases)


## 项目说明
程序员+炒股=此插件

韭菜们对陶县都不会陌生吧

本插件就是一个解析RSS的阅读器，RSS源为一个python脚本，定时抓取陶县的文章，在IDE内阅读

当然，在IDE比在网页上要安全一点

如果有什么更隐秘的想法，欢迎提出

想到一个但是能力有限改不动

具体是 panel内只显示一个feed，然后通过监听按键来切换到下一篇

原项目写得太死，改不动（我太菜了）


## 使用说明
1. channels填写rss源，点击Feed选项页
1. 点击Refresh刷新得到消息
1. 焦点在Feed选项页时，按空格即老板键一键隐藏内容

## 待完成
- [x] 空格老板键
- [x] 文字背景透明
- [ ] 单次只看一篇

<!-- Plugin description -->
IDE-News is a great plugin that allows you to subscribe to different RSS channels and read news not leaving the IDE.
<!-- Plugin description end -->

## 安装

- ~~Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "IntelliJNews"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/mikh-rich-is-team/IntelliJNews/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
