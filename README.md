# CustomChat

A blazingly fast, ultra-lightweight (~18KB) chat formatting and color management plugin for PaperMC servers. 

Built using the modern Kyori Adventure API, CustomChat safely handles chat formatting without the bloat. It allows players to change their name color in chat using a predefined list of safe colors, preventing abuse from formatting codes like obfuscation or bolding.

## ✨ Features
* **Modern API:** Fully compliant with Paper's modern `AsyncChatEvent` and the Kyori Adventure API (No deprecation warnings!).
* **Zero Bloat:** Compiled with zero shaded libraries. It relies entirely on the Paper server API, keeping the file size incredibly small.
* **Predefined Safe Colors:** Players can only choose from a specific list of standard Minecraft colors.
* **Persistent Data:** Saves player color preferences safely using their UUIDs in a dedicated `data.yml` file.
* **Custom Formatting:** Easily adjust the layout of your server's chat in the configuration file.

---

## 📥 Installation
1. Download the latest `CustomChat-1.0.jar` from your build directory.
2. Place the `.jar` file into your server's `plugins/` folder.
3. Restart your server.
4. The plugin will automatically generate a `CustomChat` folder containing `config.yml` and `data.yml`.

---

## 💻 Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/color <colorname>` | Changes your chat name color. | `customchat.color` |

**Available Colors:**
`red`, `darkred`, `blue`, `lightblue`, `green`, `darkgreen`, `yellow`, `gold`, `pink`, `purple`, `white`, `gray`, `black`.

---

## ⚙️ Configuration (`config.yml`)

You can customize the layout of your chat messages by editing the `config.yml` file. 

* Use `{name}` for the player's username.
* Use `{color}` for their chosen custom color.
* Standard legacy `&` color codes can be used to color the brackets, colons, or default text.

**Example Configuration:**
```yaml
# CustomChat Configuration
# Use {name} for the player's name
# Use {color} for their custom color
# You can use standard Minecraft '&' color codes for the surrounding layout

chat-format: "{color}{name}&8: &f"