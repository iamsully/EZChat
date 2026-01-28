# EZChat ( Currently Under Development )

**EZChat** is a lightweight Hytale server plugin that enhances chat by adding customizable prefixes to player usernames.

---

## ✨ Features

- Custom chat prefixes for players
- Prefixes displayed before usernames in chat
- Clean and minimal formatting
- Lightweight & performance-friendly

**This plugin is in active development and currently does not support specific permissions.
Server Admins must have the OP permission to use these commands. Tags will be customizable in the future, but right now
default
to using [Square Brackets]. Changing the [ ] in the config file, will break the plugin.**

**Changing the color of the tag needs to be done through the TagConfig.json in the /mods/ca_ezchat/ folder.
Doing this through a command and eventually a UI will come very soon.**

**This plugin currently rely's on https://github.com/Zoltus/TinyMessage/**
---

## 🧩 Example Chat Format

```
[Admin] Sullyq: Hello world!
[Builder] Alex: This looks awesome
[Member] Steve: hi

```

---

## ⚙️ Configuration

EZChat uses a simple configuration system to define prefixes.

Example:

```json
{
  "PlayerTags": {
    "[Admin]": "red",
    "[RandomTag]": "blue",
    "[HexTag]": "#ff00ff"
  }
}
```

## 🚀 Installation

1. Download the EZChat plugin JAR
2. Place it in your server’s mods directory
3. Restart the server
4. Configure prefixes to your liking
5. Done 🎉

---

## 🛠️ Planned Features

- Per-player custom prefixes ✅
- Color & formatting support ✅
- Permission-based prefix assignment ❌
- Chat formatting customization ❌
- Prefix stacking (e.g. `[Admin][Dev]`) ❌
- Links ❌
- Private & Group messaging ❌
- Custom join/leave messages ❌

---

## 🧪 Compatibility

- Hytale Server API (when available)
- Designed to work alongside other server plugins

---

## 💬 Support

This plugin is actively developed.  
If you find bugs, have feature requests, or have any questions, please [Join Discord](https://discord.gg/8TbaDcT7jC) and
reach out
---

## 📄 License

MIT