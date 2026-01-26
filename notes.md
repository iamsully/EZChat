# EZ Chat

- This chat plugin is an easy way to add cool colorful prefixes to players names.
- When the server starts, read the config and check all tags.
- Make sure all tags associated with a player are added on start.
- Default Symbol [TAG]
- Make the symbol customizable??

- Make Chat Groups
- Admin group
- Dev group

#### Config Example

```
{
    "Symbol": "[]"
    "Tags": [
        {
            "TagName": "CoolGuy",
            "Style": {
                "Gradient": "NOT IMPLEMENTING THIS YET",
                "Color": "NAME or HEX",
                "Bold": true,
                "Italic": true,
                "Underline": false,
                "Monospace": false,
            }
        }
    ],
    "Players": [
        {
            "Name": "Sullyq",
            "UUID": "1239123-123-12-123013",
            "Tags": ["Tag1", "Tag2"]
        }, 
        {
            "Name": "Ap3y",
            "UUID": "1239123-123-12-123013",
            "Tags": ["Tag3"]
        }
    ]
}
```

#### Examples

* [Cool Guy] Sullyq: My message here!
* [Random Tag] Sullyq: My message here!
* [Dev] Sullyq: My message here!
* [Friend] Sullyq: My message here!
* [Supporter] Sullyq: My message here!
* [Supporter] Sullyq: My message here!

### Commands

* /ec help -- displays help command
* /ec info -- gives info about the command and author
* /ec link < link > -- sends the link as a clickable link (Needs Perms)
* /ec tag add < tagName >
* /ec tag list -- Lists all tags
* /ec tag remove < tagName >
* /ec < player >  < permGroup >
* /ec addtag < TAG NAME > --- this adds a new tag in the ezchat config 