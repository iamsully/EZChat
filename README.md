# EZChat

**EZChat** is a lightweight Hytale server plugin that enhances chat by adding customizable prefixes to player usernames,
functionality to post links, and direct messaging other players on the server. It is intended for **"vanilla"** servers that want
common chat features, without all the added overhead from other "essential" plugins.
---

## ✨ Features

- Custom chat prefixes for players
- Prefixes displayed before usernames in chat
- Clean and minimal formatting
- Lightweight & performance-friendly

**Using < Angle Brackets > will break the plugin.**

---

## 🧩 Example Chat Format

```
[Admin] Sullyq: Hello world!
[Builder] Alex: This looks awesome
[Member] Steve: hi

```

---

## ⚙️ Configuration

EZChat uses a very customizable configuration system to define prefixes.

**Example:**

```json
{
  "Tags": {
    "Tester": "<color:blue><b>[Tester]</b></color>",
    "Tester3": "<gradient:blue:red:green:blue><u>[Tester3]</u></gradient>",
    "Admin": "<color:red><b><mono>[Admin]</mono></b></color>",
    "Tester2": "<color:blue><u>[Tester2]</u></color>"
  }
}
```

## 🚀 Installation

1. Download the EZChat JAR
2. Place it in your server’s mods directory
3. Restart the server
4. Configure prefixes to your liking
5. Done 🎉

---

## 🛠️ Planned Features

- Per-player custom prefixes ✅
- Color & formatting support ✅
- Tag formatting customization ❌
- Prefix stacking (e.g. `[Admin][Dev]`) ❌
- Links ❌
- Private & Group messaging ❌

---

## 💬 Support

This plugin is actively developed.  
If you find bugs, have feature requests, or have any questions, please [Join Discord](https://discord.gg/8TbaDcT7jC) and
reach out
